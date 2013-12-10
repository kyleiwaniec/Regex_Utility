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
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(this.label+" : [");
		for(int i = 0; i < this.size(); i++) {
			sb.append(", "+this.get(i));
		}
		sb.append("]");
		if(sb.length()>=3){sb.delete(5,7);};
		return sb.toString();
	}
}