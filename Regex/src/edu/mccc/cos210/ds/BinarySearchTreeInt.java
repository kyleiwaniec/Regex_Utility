package edu.mccc.cos210.ds;
public interface BinarySearchTreeInt<E>{
	boolean add(E target);
	E find(E target);
	boolean contains(E target);
	E delete(E target);
	boolean remove(E target);
	E getMax();
	E getMin();
}