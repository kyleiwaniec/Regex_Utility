package edu.mccc.cos210.ds;
import java.util.Iterator;
public class ArrayList<E>  implements Cloneable, Iterable<E>{

	// Data fields
	/** The default initial capacity */
	private static final int DEFAULT_CAPACITY = 10;
	private int initialCapacity;


	/** The undelying data array */
	private E[] theData;

	/** The current size */
	private int size = 0;

	/** The current capacity */
	private int capacity = 0;


	//@SuppressWarnings("unchecked") // <= why does this have no effect on the console???
	public ArrayList(){
		this(DEFAULT_CAPACITY);
	}
	//@SuppressWarnings("unchecked")
	public ArrayList(int initialCapacity){
		capacity = initialCapacity;
		theData = (E[]) new Object[capacity];
	}

	public boolean add(E anEntry){
		if(size == capacity){
			reallocate();
		}
		theData[size] = anEntry;
		size++;
		return true;
	}

	public void add(int index, E anEntry){
		if(index < 0 || index > capacity){
			throw new ArrayIndexOutOfBoundsException(index);
		}
		if(size == capacity){
			reallocate();
		}
		// Shift data in elements from index to size-1
		for(int i = size; i > index; i--){
			theData[i] = theData[i - 1];
		}
		//Insert the new item
		theData[index] = anEntry;
		size++;
	}
	public E get(int index){
		//System.out.println("size: "+size);
		if(index < 0 || (size != 0 && index >= capacity)){
			throw new ArrayIndexOutOfBoundsException(index);
		}
		return theData[index];
	}
	public E find(int index){
		if(index < 0 || (size != 0 && index >= capacity)){
			return null;
		}
		return theData[index];
	}
	public E set(int index, E newValue){
		if(index < 0 || (size != 0 && index >= capacity)){
			throw new ArrayIndexOutOfBoundsException(index);
		}
		E oldValue = theData[index];
		theData[index] = newValue;
		return oldValue;
	}
	public E remove(int index){
		if(index < 0 || (size != 0 && index >= capacity)){
			throw new ArrayIndexOutOfBoundsException(index);
		}
		E returnValue = theData[index];

		if(index == 0){
			this.shift();
		}else if(index == size-1){
			this.pop();
		}else{
			for(int i = index; i < size-1; i++){
				theData[i] = theData[i+1];
			}
			size--;
		}
		return returnValue;
	}

	public E shift(){ // removes the first item in the list
		E returnValue = theData[0];
		for(int i = 0; i < size-1; i++){
				theData[i] = theData[i+1];
			}
		size--;
		return returnValue;	
	}
	public E getLast(){ // returns the last item in the list
		E returnValue = theData[size-1];
		return returnValue;	
	}
	public E pop(){ // removes the last item in the list and returns it.
		E returnValue = theData[size-1];
		size--;
		return returnValue;	
	}

	public int find(E target){
		int index = 0;
		for(int i = 0; i < size; i++){
			if(theData[i] == target){
				index = i;
				return index;
			}
			if(i == size-1 && theData[i] != target){
				index = -1;
				return index;
			}
		}
		return index;
	}
	public boolean contains(E target){
		for(int i = 0; i < size; i++){
			if(theData[i] == target){
				return true;
			}
		}
		return false;	
	}
	public int size(){
		return this.size;
	}
	private void reallocate(){
		capacity = 2*capacity;
		E[] newData = (E[]) new Object[capacity];
		System.arraycopy(theData, 0, newData, 0, size);
		theData = newData;
	}

	 public Object clone(){
        try{
            ArrayList<E> cloned = (ArrayList<E>) super.clone();
            cloned.theData = (E[]) theData.clone();
            return cloned;
        }catch(CloneNotSupportedException ex){
            throw new InternalError();
        }
    }
    @Override
    public boolean equals(Object obj){
        if (obj instanceof ArrayList) { 
             ArrayList list = (ArrayList) obj; 
             if(this.size == list.size()){ // first of all, are they the same size?
                for(int i = 0; i < this.size; i++){ // iterate over all elements in this
                    if(list.get(i) != theData[i]){ // if the list doesn't contain the element, return false. 
                    								// THIS ASSUMES THAT ITEMS ARE UNIQUE and in the same order - for this project, we don't care.
                        return false;
                    }
                 }
             }else{
                return false;
             }
        }
        return true; 
    }
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(int i = 0; i < size; i++) {
			sb.append(", "+theData[i]);
		}
		sb.append("]");
		if(sb.length()>=3){sb.delete(1,3);};
		return sb.toString();
	}
	@Override
	public Iterator<E> iterator(){
		return new Iter();
	};
	private class Iter implements Iterator<E> {
        // Data Fields
        // Index of next element */

        private int index;
        // Count of elements accessed so far */
        private int count = 0;

        // Methods
        // Constructor
        /**
         * Initializes the Iter object to reference the
         * first queue element.
         */
        public Iter() {
            count = 0;
        }

        /**
         * Returns true if there are more elements in the queue to access.
         * @return true if there are more elements in the queue to access.
         */
        @Override
        public boolean hasNext() {
            return count < size;
        }

        /**
         * Returns the next element in the queue.
         * @pre index references the next element to access.
         * @post index and count are incremented.
         * @return The element with subscript index
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            E returnValue = theData[index];
            index = (index + 1) % capacity;
            count++;
            
            return returnValue;
        }

        /**
         * Remove the item accessed by the Iter object -- not implemented.
         * @throws UnsupportedOperationException when called
         */
        @Override
        public void remove() {
        	throw new UnsupportedOperationException();
        }
    }
}


