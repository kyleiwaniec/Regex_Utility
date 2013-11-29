package edu.mccc.cos210.ds;
import edu.mccc.cos210.ex.NoSuchElementException;
/**
 * The PriorityQueue implements the Queue interface
 *  by building a heap in an ArrayList. The heap is structured
 *  so that the "smallest" item is at the top.
 */
public class PriorityQueue<E> extends QueueSingle<E> implements Cloneable{

    // Data Fields
    /** The ArrayList to hold the data. */
    private ArrayList<E> theData;
    /** An optional reference to a Comparator object. */
    Comparator<E> comparator = null;

    /******** for exxample ************
    //Comparator anonymous class implementation
    public static Comparator<Customer> idComparator = new Comparator<Customer>(){
        @Override
        public int compare(Customer c1, Customer c2) {
            return (int) (c1.getId() - c2.getId());
        }
    };
    ******** end exxample ************/



    // Methods
    // Constructor
    public PriorityQueue() {
       theData = new ArrayList<E>();
    }



    /**
     * Creates a heap-based priority queue with the specified initial
     * capacity that orders its elements according to the specified
     * comparator.
     * @param cap The initial capacity for this priority queue
     * @param comp The comparator used to order this priority queue
     * @throws IllegalArgumentException if cap is less than 1
     */

    // TODO: implement comparator
    public PriorityQueue(int cap, Comparator<E> comp) {
        if (cap < 1) {
            throw new IllegalArgumentException();
        }
        theData = new ArrayList<E>(cap + 1);
        comparator = comp;
    }

    /**
     * Insert an item into the priority queue.
     * @pre The ArrayList theData is in heap order.
     * @post The item is in the priority queue and
     *       theData is in heap order.
     * @param item The item to be inserted
     * @throws NullPointerException if the item to be inserted is null.
     */
    @Override
    public boolean offer(E item) {
        // Add the item to the heap.
        theData.add(item);
        // child is newly inserted item.
        int child = theData.size() - 1;
        int parent = (child - 1) / 2; // Find child's parent.
        // Reheap

        while (parent >= 0 && compare(theData.get(parent), theData.get(child)) > 0) {
            swap(parent, child);
            child = parent;
            parent = (child - 1) / 2;
        }
        return true;
    }
    public void swap(int a, int b){
        E temp = theData.get(a); 
        theData.set(a, theData.get(b)); 
        theData.set(b, temp);
    }
    public boolean isEmpty(){
        if(theData.size() == 0){
            return true;
        }else{
            return false;
        }
    }
    /**
     * Removes the entry at the front of the queue and returns it.
     * @pre The ArrayList theData is in heap order.
     * @post Removed smallest item, theData is in heap order.
     * @return The item with the smallest priority value or null if empty.
     */
    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        // Save the top of the heap. smallest item
        E result = theData.get(0);
        // If only one item then remove it.
        if (theData.size() == 1) {
            theData.remove(0);
            return result;
        }
        // Remove the last item from the ArrayList and place it into
        // the first position.
        theData.set(0, theData.pop());
        // The parent starts at the top.
        
        int parent = 0;
        
        while (true) {
            
            int leftChild = 2 * parent + 1;
            if (leftChild >= theData.size()) {
                break; // Out of heap.
            }
            int rightChild = leftChild + 1;
            int minChild = leftChild;
            // See whether rightChild is smaller.
            if (rightChild < theData.size() && compare(theData.get(leftChild), theData.get(rightChild)) > 0) {
                minChild = rightChild;
            }
            // assert: minChild is the index of the smaller child.
            // Move smaller child up heap if necessary.
            if (compare(theData.get(parent), theData.get(minChild)) > 0) {
                swap(parent, minChild);
                parent = minChild;
            } else { // Heap property is restored.
                break;
            }
        }
        return result;
    }
    public int getSize(){
        return theData.size();
    }

     /**
     * Removes the entry at the front of the queue and returns it.
     * @return The item removed if successful, throws exception if not
     */
    @Override
    public E remove() throws NoSuchElementException{
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
           return poll();
        }
    }
    /**
     * Returns the item at the front of the queue without removing it.
     * @return The item at the front if successful; null if not
     */
    @Override
    public E peek() {
        if (theData.size() == 0) {
            return null;
        } else {
            return theData.get(0);
        }
    }
    // TODO: level-order traverse:
    // /**
    //  * Perform a level-order traversal.
    //  * @param sb The string buffer to save the output
    //  */
    // 1. Dequeue a nodee
    // 2. Visit it.
    // 3. Enqueue it's children left to right until Queue is empty.
    // }

    public Object clone(){
        try{
            PriorityQueue<E> cloned = (PriorityQueue<E>) super.clone();
            cloned.theData = (ArrayList<E>) theData.clone();
            return cloned;
        }catch(CloneNotSupportedException ex){
            throw new InternalError();
        }
    }

    public String toStringDesc(){
        PriorityQueue<E> theCopy = (PriorityQueue<E>) this.clone();
        int size = theCopy.theData.size();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i++) {
            sb.append(", "+theCopy.poll());
        }
        if(sb.length()>=2){sb.delete(0,2);};
        return sb.toString();
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < theData.size(); i++) {
            sb.append(", "+theData.get(i));
        }
        if(sb.length()>=2){sb.delete(0,2);};
        return sb.toString();
    }
    /**
     * Compare two items using either a Comparator object�s compare method
     * or their natural ordering using method compareTo.
     * @pre If comparator is null, left and right implement Comparable<E>.
     * @param left One item
     * @param right The other item
     * @return Negative int if left less than right,
     *         0 if left equals right,
     *         positive int if left > right
     * @throws ClassCastException if items are not Comparable
     */
    @SuppressWarnings("unchecked")
    private int compare(E left, E right) {
        if (comparator != null) { // A Comparator is defined.
            return comparator.compare(left, right);
        } else { // Use left�s compareTo method.
            return ((Comparable<E>) left).compareTo(right);
        }
    }
}
