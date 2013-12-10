package edu.mccc.cos210.ds;

public class DFAList<E> extends ArrayList<E>{
	boolean marked = false;
	int label;
	public DFAList() {
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
	public void markAsFinal(){
		
	}

}