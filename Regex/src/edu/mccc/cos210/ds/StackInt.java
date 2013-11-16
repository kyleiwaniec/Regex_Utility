package edu.mccc.cos210.ds;
public interface StackInt<E> {
	boolean	empty();
	E peek();
	E pop();
	E push(E item);
}