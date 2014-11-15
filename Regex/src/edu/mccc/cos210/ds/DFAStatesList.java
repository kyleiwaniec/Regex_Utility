package edu.mccc.cos210.ds;
import java.util.Iterator;
public class DFAStatesList<E> extends ArrayList<E>{

	private int numUnMarkedStates = 0;
	public DFAStatesList(){
		super();
	}
	public int getNumUnMarkedStates(){
		return this.numUnMarkedStates;
	}
	public boolean hasUnMarkedStates(){
		return this.numUnMarkedStates > 0;
	}
	public void updateUnMarkedStates(int i){
		this.numUnMarkedStates+=i;
	}
	
}


