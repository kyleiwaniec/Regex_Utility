package edu.mccc.cos210.ds;
public class ArrayList<E>{

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
		if(index < 0 || index > size){
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
		if(index < 0 || index >= size){
			throw new ArrayIndexOutOfBoundsException(index);
		}
		return theData[index];
	}
	public E set(int index, E newValue){
		if(index < 0 || index >= size){
			throw new ArrayIndexOutOfBoundsException(index);
		}
		E oldValue = theData[index];
		theData[index] = newValue;
		return oldValue;
	}
	public E remove(int index){
		if(index < 0 || index >= size){
			throw new ArrayIndexOutOfBoundsException(index);
		}
		E returnValue = theData[index];

		if(index == 0){
			this.shift();
		}else if(index == size-1){
			this.pop();
		}else{
			for(int i = index; i < size; i++){
				theData[i-1] = theData[i];
			}
			size--;
		}
		return returnValue;
	}

	public E shift(){ // removes the first item in the list
		E returnValue = theData[0];
		for(int i = 0; i < size; i++){
				theData[i] = theData[i+1];
			}
		size--;
		return returnValue;	
	}

	public E pop(){ // removes the last item in the list
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
		return size;
	}
	private void reallocate(){
		capacity = 2*capacity;
		E[] newData = (E[]) new Object[capacity];
		System.arraycopy(theData, 0, newData, 0, size);
		theData = newData;
	}

	public String toString(){
		String res = "";
		for(int i = 0; i < size; i++) {
			res += ", "+theData[i];
		}
		res = res.substring(2, res.length());

		return res;

	}
}


