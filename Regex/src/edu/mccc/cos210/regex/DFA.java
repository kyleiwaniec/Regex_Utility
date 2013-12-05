package edu.mccc.cos210.regex;
import java.io.*;
import edu.mccc.cos210.ds.DoublyLinkedList;
import edu.mccc.cos210.ds.ArrayList;
import edu.mccc.cos210.ds.Stack;
import edu.mccc.cos210.ds.Edge;
import edu.mccc.cos210.ds.ArrayListGraph;
import edu.mccc.cos210.ds.ListGraph;

import edu.mccc.cos210.regex.NFA;

import java.util.Iterator;

import edu.mccc.cos210.ex.GrumpyCatError;



public class DFA{


	public DFA(NFA nfa) throws GrumpyCatError{
		
		ListGraph l = nfa.getGraph();

		int s = l.getStartState();

		
		

	}

	public Stack<Integer> getEpsilon(int start){
		Stack<Integer> stack = new Stack<Integer>();
		Stack<Integer> eClosure = new Stack<Integer>();
		stack.push(s); // push start state onto stack
		eClosure.push(s); // push start state onto stack

		// return all states reachable by epsilon transitions
		while(!stack.empty()){
			int t = stack.pop();

			DoublyLinkedList dll = l.get(t);
			for(Object edge : dll){
				Edge e = (Edge)edge;
				if(e.isEpsilon()){
					if(!eClosure.contains(e.getDest())){  /* destination state is not in the Closure */
						stack.push(e.getDest());
						eClosure.push(e.getDest());
					}
				}
			}
		}	
		System.out.println("reachabe states: "+eClosure.toString());
		return eClosure;
	}

	public class State{
		private int ss; // single state
		private boolean marked;
		public State(int ss, boolean marked){
			this.ss = ss;
			this.marked = marked;
		}
	}
	public class StateSet{
		private Stack<State> source;
		private Stack<State> dest;
		private boolean isMarked;
		public StateSet(Stack<State> source, boolean isMarked){
			this.source = source;
			this.dest = dest;
			this.isMarked = isMarked;
		}
		public Stack<State> getSource(){
			return this.source;
		}
		public boolean isMarked(){
			return this.isMarked;
		}
		public void setSource(Stack<State> source){
			this.source = source;
		}
		
		public void mark(){
			this.isMarked = true;
		}
	}
}