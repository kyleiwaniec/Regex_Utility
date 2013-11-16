package edu.mccc.cos210.ds;
import edu.mccc.cos210.ex.NoSuchElementException;
public interface QueueInt<E> {
	boolean add(E e);
	boolean offer(E e);
	E remove() throws NoSuchElementException;
	E poll();
	E element();
	E peek();
}
