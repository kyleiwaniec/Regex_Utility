package edu.mccc.cos210.ds;
public class Comparator<E> implements ComparatorInt<E>{

	/*  STUBS ONLY!!!  */

	public int compare(E left, E right){
		// E p1 =(E) obj1;
		// E p2 =(E) obj2;
		// Comparable<E> left = p1.getName();
		// Comparable<E> right = p2.getName();
		return ((Comparable<E>) left).compareTo(right);
	}
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
}