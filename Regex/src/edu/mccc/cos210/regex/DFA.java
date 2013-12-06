package edu.mccc.cos210.regex;
import java.io.*;
import edu.mccc.cos210.ds.DoublyLinkedList;
import edu.mccc.cos210.ds.ArrayList;
import edu.mccc.cos210.ds.DFAStatesList;
import edu.mccc.cos210.ds.Stack;
import edu.mccc.cos210.ds.DFAList;
import edu.mccc.cos210.ds.Edge;
import edu.mccc.cos210.ds.ArrayListGraph;
import edu.mccc.cos210.ds.ListGraph;

import edu.mccc.cos210.regex.NFA;

import java.util.Iterator;

import edu.mccc.cos210.ex.GrumpyCatError;



public class DFA{

	private DFAStatesList<DFAList<Integer>> dfaStatesList = new DFAStatesList<DFAList<Integer>>(); // extended ArrayList with "marked" data field
	private DFAList<Integer> eClos = new DFAList<Integer>();
	private DFAList<Integer> nextClos = new DFAList<Integer>();
	private int finalState;
	private ArrayList<int[]> dfaTransitionList = new ArrayList<int[]>(); // the int[] will hold 3 values fromState/input/toState
	private int[] transition = new int[3];
	private int dfaStatelabel = 0;

	public DFA(NFA nfa, char[] language) throws GrumpyCatError{
		
		ListGraph l = nfa.getGraph();
		int s = l.getStartState();

		DFAList<Integer> startStateList = new DFAList<Integer>();
		startStateList.mark(false);
		startStateList.setLabel(dfaStatelabel);

		// increment count of unmarked states in dfaStatesList:
		dfaStatesList.updateUnMarkedStates(1);


		startStateList.add(s); // startStateList now contains 1 state: the NFA startState.
		
		eClos = getEClosure(startStateList, l); // adds new closure to DFA states (dfaStatesList), and returns it.
		dfaStatesList.add(eClos);	
		
		// while there are unmarked states, do:
		while(dfaStatesList.hasUnMarkedStates()){

			// loope over all the lists and return the first unmarked one.
			for(Object m : dfaStatesList){
				DFAList<Integer> mm = (DFAList<Integer>) m;
				if(!mm.isMarked()){
					eClos = mm;
					break;
				}
				
			}

			eClos.mark(true);
			dfaStatesList.updateUnMarkedStates(-1);
			System.out.println("label: "+eClos.getLabel());
			
			transition[0] = eClos.getLabel();
			

			if(eClos.contains(nfa.getAcceptState())){
				// TODO: mark this StateSet as final in DFA.
			}; 

			DFAList<Integer> nextClos = new DFAList<Integer>();

			for(int i = 0; i < language.length; i++){
				nextClos = getEClosure(move(eClos, i, language, l), l);
				transition[1] = i;
				int equalsCount = 0;

				for(Object listObj : dfaStatesList){
					DFAList<Integer> existingList = (DFAList<Integer>) listObj;
					if(existingList.equals(nextClos)){
						equalsCount++;
						transition[2] = existingList.getLabel();
						dfaTransitionList.add(transition);
						break;
					}
				}
				if(equalsCount == 0 && nextClos.size() > 0){
					nextClos.mark(false);
					dfaStatesList.updateUnMarkedStates(1);
					nextClos.setLabel(++dfaStatelabel);
					dfaStatesList.add(nextClos); 
					transition[2] = nextClos.getLabel();
					dfaTransitionList.add(transition);
				}
				// convenience DEBUG:
				System.out.println(i+". dfaStatesList: " +dfaStatesList);
				for(int d = 0; d < dfaTransitionList.size(); d++){
					int[] arrs = dfaTransitionList.get(d);
					for(int a = 0; a < 3; a++){
						System.out.println(", "+arrs[a]);
					}
					System.out.println("---");
				}
			}
			eClos = nextClos;
		}
	}
	public DFAList<Integer> move(DFAList<Integer> S, int letter, char[] language, ListGraph l){
		DFAList<Integer> list = new DFAList<Integer>();
		for(Object ss : S){
			Integer s = (Integer) ss;
			DoublyLinkedList dll = l.get(s);
			for(Object edge : dll){
				Edge e = (Edge)edge;
				if(e.getWeight() == language[letter]){
					list.add(e.getDest());
				}
			}
		}
		return list;
	}
	public DFAList<Integer> getEClosure(DFAList<Integer> S, ListGraph l){ // StateSet S
		Stack<Integer> stack = new Stack<Integer>();
		DFAList<Integer> eClosure = new DFAList<Integer>();

		for(Object ss : S){
			Integer s = (Integer) ss;
			stack.push(s); // push state onto stack
			eClosure.add(s); // add state to eClosure list
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
						eClosure.add(e.getDest());
					}
				}
			}
		}		
		return eClosure;
	}

}