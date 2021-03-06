package edu.mccc.cos210.regex;
import java.io.*;
import edu.mccc.cos210.ds.ArrayList;
import edu.mccc.cos210.ds.DFAStatesList;
import edu.mccc.cos210.ds.Stack;
import edu.mccc.cos210.ds.DFAList;
import edu.mccc.cos210.ds.Edge;
import edu.mccc.cos210.ds.ListGraph;

import edu.mccc.cos210.regex.NFA;

import java.util.Iterator;

import edu.mccc.cos210.ex.GrumpyCatError;



public class DFA{

	private DFAStatesList<DFAList<Integer>> dfaStatesList = new DFAStatesList<DFAList<Integer>>(); // extended ArrayList with "marked" data field
	private DFAList<Integer> eClos = new DFAList<Integer>();
	private DFAList<Integer> nextClos;
	private DFAList<Integer> startStateList = new DFAList<Integer>();
	private ArrayList<Integer> finalDFAStateList;
	private Stack<int[]> dfaTransitionStack; // the int[] will hold 3 values fromState/input/toState
	private int[] transition = new int[3];
	private int dfaStatelabel = 0;
	private int count = 0;
	private int[][] dfaTransTable;

	public DFA(NFA nfa, char[] language) throws GrumpyCatError{
		
		ListGraph l = nfa.getGraph();
		int s = l.getStartState();
		int langLength = language.length;
		dfaTransitionStack = new Stack<int[]>();
		finalDFAStateList = new ArrayList<Integer>();
		startStateList = new DFAList<Integer>();
		startStateList.mark(false);
		startStateList.setLabel(dfaStatelabel);

		// increment count of unmarked states in dfaStatesList:
		dfaStatesList.updateUnMarkedStates(1);
		startStateList.add(s); // startStateList now contains 1 state: the NFA startState.
		
		eClos = getEClosure(startStateList, l); // adds new closure to DFA states (dfaStatesList), and returns it.
		eClos.mark(false);
		eClos.setLabel(dfaStatelabel);
		dfaStatesList.add(eClos);	
		
		// while there are unmarked states, do:
		while(dfaStatesList.hasUnMarkedStates()){
			// loop over all the lists and return the first unmarked one.
			for(Object m : dfaStatesList){
				DFAList<Integer> mm = (DFAList<Integer>) m;
				if(!mm.isMarked()){
					eClos = mm;
					break;
				}
				
			}
			transition[0] = eClos.getLabel();
			eClos.mark(true);
			dfaStatesList.updateUnMarkedStates(-1);

			//mark this StateSet as final in DFA if it contains a final state from the NFA
			if(eClos.contains(nfa.nfaAcceptState())){
				if(!finalDFAStateList.contains(eClos.getLabel())){
					finalDFAStateList.add(eClos.getLabel());
				}
			}
			

			nextClos = new DFAList<Integer>();
			
			for(int i = 0; i < langLength; i++){
				transition[1] = i; // the letter
				nextClos = getEClosure(move(eClos, i, language, l), l);
				int equalsCount = 0;
				for(Object listObj : dfaStatesList){
						DFAList<Integer> existingList = (DFAList<Integer>) listObj;
						if(existingList.equals(nextClos)){
							equalsCount++;
					}
				}
				if(equalsCount == 0){
					nextClos.setLabel(++dfaStatelabel);
				}

				// check if the list of DFA states alreaady contains the new closure
				// by iterating over all the lists and comparing
				if(equalsCount == 0 && nextClos.size() > 0){
					nextClos.mark(false);
					dfaStatesList.updateUnMarkedStates(1);
					//nextClos.setLabel(++dfaStatelabel);
					dfaStatesList.add(nextClos);
				}

				transition[2] = nextClos.getLabel();
				dfaTransitionStack.push(new int[] {transition[0], transition[1], transition[2]});
			}
			
		}
		int lbe = dfaStatelabel+1;
		dfaTransTable = new int[lbe][langLength];
		for(int i = 0; i < dfaTransTable.length; i++){
			for(int j = 0; j<langLength; j++){
				dfaTransTable[i][j] = -1;
			}
		}
		System.out.println("NFA to DFA State map: " +dfaStatesList);
		
		while(!dfaTransitionStack.empty()){
			StringBuilder sb = new StringBuilder();
			sb.append("[ ");
			int[] trans = dfaTransitionStack.pop();
				dfaTransTable[trans[0]][trans[1]] = trans[2];
				for(int j = 0; j<3; j++){
					sb.append(trans[j]+" ");
				}
			sb.append("]");	
			//System.out.println("dfaTransTable: "+sb);
		}

		System.out.println("DFA Transition Table (2D array of: DFA States x Alphabet)");
		System.out.println("(-1 denotes unreachable states)");
		printTable(dfaTransTable, language);

	}
	public DFAList<Integer> move(DFAList<Integer> S, int letter, char[] language, ListGraph l){
		DFAList<Integer> list = new DFAList<Integer>();
		for(Object ss : S){
			Integer s = (Integer) ss;
			ArrayList dll = l.get(s);
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

			ArrayList dll = l.get(t);
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
	public ArrayList<Integer> getfinalStates(){
		return finalDFAStateList;
	}
	public int[][] getTransitionTable(){
		return dfaTransTable;
	}
	public static void printTable(int[][] array, char[] language) {
		System.out.print("  ");
		for (int col = 0; col < language.length; col++) {
			System.out.printf("%-1s %-2c", " ", language[col]);
		}
		String separator = "----";
		for(int ls = 0; ls<language.length; ls++){
			separator+="----";
		}
		System.out.println("\n   "+separator);
		for (int j = 0; j < array.length; j++) {
			System.out.printf("%-2d", 0 +j);
			for (int i = 0; i < language.length; i++) {
				System.out.printf("%-1s %-2d", "|", array[j][i]);
			}
			System.out.println("|\n   "+separator);
		}
	}
}