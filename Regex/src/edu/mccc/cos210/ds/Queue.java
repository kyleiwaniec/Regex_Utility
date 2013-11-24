package edu.mccc.cos210.ds;
/** 
 * Implements the Queue interface using a LinkedList component.
 * @author Koffman & Wolfgang & Kyle Hamilton
 **/
public class Queue<E> implements QueueInt<E> {

    // Data Field
    private DoublyLinkedList<E> theQueue = new DoublyLinkedList<E>(); // LinkedList that is the queue.
    private int size(){
        return theQueue.getSize();
    }
    /**
     * Inserts an item at the rear of the queue.
     * @param item The element to add
     * @return true (always successful)
     */
    @Override
    public boolean offer(E item) {
        theQueue.addLast(item);
        return true;
    }
     /**
     * Inserts an item at the rear of the queue. Don't need this since we are using a linked list.
     * @param item The element to add
     * @return true if successful, IllegalStateException if no space available
     */
    @Override
    public boolean add(E item) throws IllegalStateException{
            theQueue.addLast(item);
            return true;
    }
    /**
     * Removes the entry at the front of the queue and returns it.
     * @return The item removed if successful, or null if not
     */
    @Override
    public E poll() {
        if (size() == 0) {
            return null;
        } else {
            E first = theQueue.getFirst();
            theQueue.removeFirst();
            return first;
        }
    }
    /**
     * Removes the entry at the front of the queue and returns it.
     * @return The item removed if successful, throws exception if not
     */
    @Override
    public E remove() throws java.util.NoSuchElementException{
        if (size() == 0) {
            throw new java.util.NoSuchElementException();
        } else {
            E first = theQueue.getFirst();
            theQueue.removeFirst();
            return first;
        }
    }
    /**
     * Returns the item at the front of the queue without removing it.
     * @return The item at the front if successful; null if not
     */
    @Override
    public E peek() {
        if (size() == 0) {
            return null;
        } else {
            return theQueue.getFirst();
        }
    }
    /**
     * Returns the item at the front of the queue without removing it.
     * @return The item at the front if successful; throws exception if not
     */
    @Override
    public E element() throws java.util.NoSuchElementException{
        if (size() == 0) {
           throw new java.util.NoSuchElementException();
        } else {
            return theQueue.getFirst();
        }
    }
    public String toString(){
        return theQueue.toString();
    }
}
