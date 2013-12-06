package edu.mccc.cos210.ds;

public class DFAStack<E> extends Stack<E>{
	boolean marked = false;
	int label;
	public DFAStack() {
		super();
	}
	public void mark(boolean marked){
		this.marked = marked;
	}
	public boolean isMarked(){
		return this.marked;
	}
	public void setLabel(int label){
		this.label = label;
	}
	public int getLabel(){
		return this.label;
	}
}