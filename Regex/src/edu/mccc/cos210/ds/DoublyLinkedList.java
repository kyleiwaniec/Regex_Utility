package edu.mccc.cos210.ds;
import java.util.Iterator;

public class DoublyLinkedList<E> implements DoublyLinkedListInt<E>, Iterable<E>{
	private DNode<E> head;
	private DNode<E> tail;
	private DNode<E> current;
	private int size = 0;

	public DoublyLinkedList() {
		this.head = null;
		this.tail = null;
		this.current = null;
	}
	/* this was being used for a version of Iterator class, 
	* which I have since deleted, but leaving this in for now
	*/
	public DNode<E> getFirstNode() throws java.util.NoSuchElementException {
		if (this.size == 0) {
			throw new java.util.NoSuchElementException();
		}
		this.current = this.head;
		return this.head;
	}
	@Override
	public E getFirst() throws java.util.NoSuchElementException {
		if (this.size == 0) {
			throw new java.util.NoSuchElementException();
		}
		this.current = this.head;
		return this.head.element;
	}
	@Override
	public E getLast() throws java.util.NoSuchElementException {
		if (this.size == 0) {
			throw new java.util.NoSuchElementException();
		}
		this.current = this.tail;
		return this.tail.element;
	}
	@Override
	public E getNext() throws java.util.NoSuchElementException {
		if (this.size == 0 || this.current == null) {
			throw new java.util.NoSuchElementException();
		}
		this.current = this.current.next;
		if (this.current == null) {
			throw new java.util.NoSuchElementException();
		}
		E element = this.current.element;
		return element;
	}
	public DNode<E> getNextNode() throws java.util.NoSuchElementException {
		if (this.size == 0 || this.current == null) {
			throw new java.util.NoSuchElementException();
		}
		this.current = this.current.next;
		if (this.current == null) {
			throw new java.util.NoSuchElementException();
		}
		return this.current;
	}
	public boolean hasNext(){
		if (this.size == 0 || this.current == null) {
			return false;
		}
		if(this.current.next == null){
			return false;
		}
		return true;
	}
	@Override
	public E getPrevious() {
		if (this.size == 0 || this.current == null) {
			throw new java.util.NoSuchElementException();
		}
		this.current = this.current.previous;

		if (this.current == null) {
			throw new java.util.NoSuchElementException();
		}
		E element = this.current.element;
		return element;
	}
	@Override
	public E getCurrent() throws java.util.NoSuchElementException {
		if (this.size == 0 || this.current == null) {
			throw new java.util.NoSuchElementException();
		}
		E element = this.current.element;
		return element;
	}
	@Override 
	public void addFirst(E element) {
		DNode<E> node = new DNode<E>(element);
		if(this.head != null){
			node.next = this.head;
			node.previous = null;
			this.head.previous = node;
			this.head = node;
		}else{
			node.next = null;
			node.previous = null;
			this.tail = node;
			this.head = node;
		}
		this.current = node;
		this.size++;
	}
	@Override 
	public void addLast(E element) {
		if (this.size == 0) { // or, check: this.tail == null
			addFirst(element);
		} else {
			DNode<E> node = new DNode<E>(element);
			node.previous = this.tail;
			node.next = null;
			this.tail.next = node;
			this.tail = node;
			this.current = node;
			this.size++;
		}
	}
	@Override 
	public void addNext(E element) {
		if (this.size == 0) {
			addFirst(element);
		}else if(this.current == this.tail){
			addLast(element);
		}else{
			DNode<E> node = new DNode<E>(element);
			node.previous = this.current;
			node.next = this.current.next;
			this.current.next.previous = node;
			this.current.next = node;
			this.current = node;
			this.size++;
		}
	}
	@Override
	public void addPrevious(E element) {
		if(this.current == this.head){
			throw new java.util.NoSuchElementException();
		}else{
			DNode<E> node = new DNode<E>(element);
			node.next = this.current;
			node.previous = this.current.previous;
			this.current.previous.next = node;
			this.current.previous = node;
			this.current = node;
			this.size++;
		}
	}
	@Override 
	public void removeFirst() throws java.util.NoSuchElementException {
		if (this.size == 0) {
			throw new java.util.NoSuchElementException();
		}
		if(this.size == 1){
			this.current = null;
			this.head = null;
		}else{
			this.current = this.head.next;
			this.head.next.previous = null;
		}
		DNode<E> node = this.head;
		this.head = node.next;
		node.reset();
		this.size--;
		if(this.size == 1){
			this.tail = this.current;
		}
	} 
	@Override
	public void removeLast() throws java.util.NoSuchElementException {
		if (this.size == 0) {
			throw new java.util.NoSuchElementException();
		}
		if (size == 1) {
			this.current = null;
			this.head = null;
		} else {
			this.current = this.tail.previous;
			this.tail.previous.next = null;
		}
		DNode<E> node = this.tail;
		this.tail = node.previous;
		node.reset();
		this.size--;
	}
	@Override
	public void removeNext() throws java.util.NoSuchElementException {
		if (this.size == 0 || this.current == null || this.current.next == null) {
			this.current = null;
			throw new java.util.NoSuchElementException();
		}
		DNode<E> node = this.current.next;
		this.current.next = node.next;
		if (this.current.next != null) {
			this.current.next.previous = this.current;
		} else {
			this.tail = this.current;
			//this.current = null;
		}
		node.reset();
		this.size--;
	}
	@Override
	public void removePrevious() throws java.util.NoSuchElementException {
		if (this.size == 0 || this.current == null || this.current.previous == null) {
			this.current = null;
			throw new java.util.NoSuchElementException();
		}
		DNode<E> node = this.current.previous;
		this.current.previous = node.previous;
		if (this.current.previous != null) {
			this.current.previous.next = this.current;
		} else {
			this.head = this.current;
			//this.current = null;
		}
		node.reset();
		this.size--;
	}

	/** Removes the current item
	*	sets the next item to current
	*/
	@Override
	public void removeCurrent() throws java.util.NoSuchElementException {
		if (this.size == 0 || this.current == null) {
			this.current = null;
			throw new java.util.NoSuchElementException();
		}
		if(this.current.next == null){
			removeLast();
		}else if (this.current.previous == null) {
			removeFirst();
		} else {
			DNode<E> node = this.current;
			this.current.next.previous = this.current.previous;
			this.current.previous.next = this.current.next;
			this.current = this.current.next;
			node.reset();
			this.size--;
		}
	}
	@Override
	public int getSize() {
		return this.size;
	}
	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("<");
		DNode<E> node = head;
		while (node != null) {
			if (node.element != null) {
				sb.append(", "+node.element.toString());
			} else {
				sb.append("null ");
			}
			node = node.next;
		}
		if(sb.length()>=2){sb.deleteCharAt(1);};
		sb.append(" >");
		return sb.toString();
	}
	public boolean contains(E el){
		DNode<E> node = head;
		while (node != null) {
			if (node.element != null) {
				 if(el.equals(node.element)){return true;};
			}
			node = node.next;
		}
		return false;
	}
	public class DNode<T> {
		public T element;
		public DNode<T> next = null;
		public DNode<T> previous = null;
		public DNode(T element) {
			this.element = element;
		}
		public void reset() {
			this.element = null;
			this.next = null;
			this.previous = null;
		}
	}
	
	
	
/*******************************************************
 *
 *  The Iterator class
 *
********************************************************/
   @Override
   public Iterator<E> iterator(){
      return new DoublyLinkedListIterator();
   }

   private class DoublyLinkedListIterator  implements Iterator<E> {
      private DNode<E> nextNode;

      public DoublyLinkedListIterator(){
         nextNode = head;
      }
      @Override
      public boolean hasNext(){
         return nextNode != null;
      }
      @Override
      public E next(){
         if (!hasNext()) { return null; }
         E element = nextNode.element;
         nextNode = nextNode.next;
         return element;
      }
      @Override
        public void remove() {
        	throw new UnsupportedOperationException();
        }
   }

/* another version of iterator ******************************* /	
    @Override
	public Iterator<E> iterator() {
		return new Iter<E>(this);
	}
  	private class Iter<T> implements Iterator<E> {
        private int count = 0;
        private DoublyLinkedList<E> dll;
        public Iter(DoublyLinkedList<E> dll) {
           count = 0;
           this.dll = dll;
           dll.current = dll.head;
        }
        @Override
        public boolean hasNext() {
            return count < this.dll.getSize();
        }
        @Override
        public E next() {
			count++;
			E element = this.dll.current.element;
			this.dll.current = this.dll.current.next;
			return element;
        }
        @Override
        public void remove() {
        	throw new UnsupportedOperationException();
        }
    }
/**********************************************************/
}
