package edu.mccc.cos210.ds;
public interface QueueInt<E> {
	boolean add(E e);
	boolean offer(E e);
	E remove();
	E poll();
	E element();
	E peek();
}
