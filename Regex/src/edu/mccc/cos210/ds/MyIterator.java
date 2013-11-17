package edu.mccc.cos210.ds;
public interface MyIterator<E>{
	boolean hasNext();
    E next();
    void remove();
}