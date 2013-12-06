package edu.mccc.cos210.regex;
import java.io.*;
import edu.mccc.cos210.ds.DoublyLinkedList;
import edu.mccc.cos210.ds.ArrayList;
import edu.mccc.cos210.ds.DFAStatesList;
import edu.mccc.cos210.ds.Stack;
import edu.mccc.cos210.ds.DFAStack;
import edu.mccc.cos210.ds.Edge;
import edu.mccc.cos210.ds.ArrayListGraph;
import edu.mccc.cos210.ds.ListGraph;

import edu.mccc.cos210.regex.NFA;

import java.util.Iterator;

import edu.mccc.cos210.ex.GrumpyCatError;



public class DFA{

	private DFAStatesList<DFAStack<Integer>> dfaStatesList = new DFAStatesList<DFAStack<Integer>>(); // extended ArrayList with "marked" data field
	private DFAStack<Integer> eClos = new DFAStack<Integer>();
	private int finalState;
	private ArrayList<int[]> dfaTransitionList = new ArrayList<int[]>(); // the int[] will hold 3 values fromState/input/toState
	private int[] transition;
	private int dfaStatelabel = 0;

	public DFA(NFA nfa, char[] language) throws GrumpyCatError{
		
		ListGraph l = nfa.getGraph();
		int s = l.getStartState();

		DFAStack<Integer> startStateStack = new DFAStack<Integer>();
		startStateStack.mark(false);
		startStateStack.setLabel(dfaStatelabel);

		// increment count of unmarked states in dfaStatesList:
		dfaStatesList.updateUnMarkedStates(1);


		startStateStack.push(s); // startStateStack now contains 1 state: the NFA startState.
	
		eClos = getEClosure(startStateStack, l); // adds new closure to DFA states (dfaStatesList), and returns it.
												 // it's the closure that needs to be marked / unmarked.
		
		// while there are unmarked states, do:
		while(dfaStatesList.hasUnMarkedStates()){
			// DFAStack<Integer> lastDFAlist = (DFAStack<Integer>) dfaStatesList.getLast(); // get the index of this item to use to construct the transition table
			// lastDFAlist.mark(true);

			eClos.mark(true);
			System.out.println("label: "+eClos.getLabel());
			dfaStatesList.updateUnMarkedStates(-1);

			transition = new int[3];
			transition[0] = eClos.getLabel();
			dfaTransitionList.add(transition);

			

			if(eClos.contains(nfa.getAcceptState())){
				// TODO: mark this StateSet as final in DFA.
			}; // A
			// foreach in the regex alphabet:

			DFAStack<Integer> nextClos = new DFAStack<Integer>();
			
			for(int i = 0; i < language.length; i++){
				nextClos = getEClosure(move(eClos, i, language, l), l);



				if(dfaStatesList.size() != 0){
					for(Object stackObj : dfaStatesList){
						DFAStack<Integer> stackStack = (DFAStack<Integer>) stackObj;
						System.out.println("stackObj: " +stackStack);
						if(stackStack.equals(nextClos)){
							nextClos = stackStack;
							break;
						}else{
							nextClos.mark(false);
							nextClos.setLabel(++dfaStatelabel);
							dfaStatesList.add(nextClos); 
						}
					}
				}else{
					nextClos.mark(false);
					nextClos.setLabel(++dfaStatelabel);
					dfaStatesList.add(nextClos);
				}

				transition[1] = i;
				transition[2] = nextClos.getLabel();
			}
			for(Object arr : dfaTransitionList){
				int[] arrs = (int[]) arr;
				for(int a = 0; a < 3; a++){
					System.out.println(", "+arrs[a]);
				}
				System.out.println("---");
			}
		}
		
		
	}
	public DFAStack<Integer> move(DFAStack<Integer> S, int letter, char[] language, ListGraph l){
		DFAStack<Integer> stack = new DFAStack<Integer>();
		System.out.println("input stack: "+S+", letter: "+language[letter]);
		while(!S.empty()){
			int s = S.pop();

			DoublyLinkedList dll = l.get(s);
			for(Object edge : dll){
				Edge e = (Edge)edge;
				if(e.getWeight() == language[letter]){
					stack.push(e.getDest());
				}
			}
		}
		System.out.println("move return: "+stack+", letter: "+language[letter]);
		return stack;
	}
	public DFAStack<Integer> getEClosure(DFAStack<Integer> S, ListGraph l){ // StateSet S
		Stack<Integer> stack = new Stack<Integer>();
		DFAStack<Integer> eClosure = new DFAStack<Integer>();

		// for each state in Set S, push state s onto stack.
		while(!S.empty()){
			int s = S.pop();
			stack.push(s); // push state onto stack
			eClosure.push(s); // push state onto eClosure stack
		}
		

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
		System.out.println("epsilon clos states: "+eClosure.toString());
		// TODO: check if eClosure is not already in the dfaStatesList, (add, unmarked, and provide a label). else return the closure that's already in the table
		System.out.println("dfaStatesList size: "+dfaStatesList.size());

		
		
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