package edu.mccc.cos210.ds;
import edu.mccc.cos210.ex.NoSuchElementException;
/** 
 * Implements the Queue interface using an ArrayList component.
 **/
public class QueueSingle<E> implements QueueInt<E> {

    // Data Field
    private ArrayList<E> theQueue = new ArrayList<E>(); // ArrayList that is the queue.

    /**
     * Inserts an item at the rear of the queue.
     * @param item The element to add
     * @return true (always successful)
     */
    @Override
    public boolean offer(E item) {
        theQueue.add(item);
        return true;
    }
    @Override
    public boolean add(E item) throws IllegalStateException{
        return offer(item);
    } 
    /**
     * Removes the entry at the front of the queue and returns it.
     * @return The item removed if successful, or null if not
     */
    @Override
    public E poll() {
        if (theQueue.size() == 0) {
            return null;
        } else {
            E first = theQueue.get(0);
            theQueue.remove(0);
            return first;
        }
    }
    /**
     * Removes the entry at the front of the queue and returns it.
     * @return The item removed if successful, throws exception if not
     */
    @Override
    public E remove() throws NoSuchElementException{
        if (theQueue.size() == 0) {
            throw new NoSuchElementException();
        } else {
            E first = theQueue.get(0);
            theQueue.remove(0);
            return first;
        }
    }
    /**
     * Returns the item at the front of the queue without removing it.
     * @return The item at the front if successful; null if not
     */
    @Override
    public E peek() {
        if (theQueue.size() == 0) {
            return null;
        } else {
            return theQueue.get(0);
        }
    }
    /**
     * Returns the item at the front of the queue without removing it.
     * @return The item at the front if successful; throws exception if not
     */
    @Override
    public E element() throws java.util.NoSuchElementException{
        if (theQueue.size() == 0) {
           throw new java.util.NoSuchElementException();
        } else {
            return theQueue.get(0);
        }
    }
    @Override
    public String toString(){
        String res = "";
        for(int i = 0; i < theQueue.size(); i++) {
            res += ", "+theQueue.get(i);
        }
        res = res.substring(2, res.length());

        return res;

    }
}
