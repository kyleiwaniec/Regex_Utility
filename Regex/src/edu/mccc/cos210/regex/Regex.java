package edu.mccc.cos210.regex;

import java.io.*;
import edu.mccc.cos210.ds.ArrayList;
import edu.mccc.cos210.regex.NFA;

import java.util.Iterator;

import edu.mccc.cos210.ex.GrumpyCatError;

public class Regex{
	
	String regExpr;
	String target;
	private int start;
	private int end;
	private int marker = 0;

	public Regex(){
	}
	public Regex(String regularExpression){
	}
	public Regex(String regExpr, String target){
		this.regExpr = regExpr;
		this.target = target;
	}

	// public methods:
	public boolean match(String regExpr, String target) throws IOException, GrumpyCatError {

		InfixToPostfix itp = new InfixToPostfix(regExpr);
		String postfix = itp.convert(regExpr);
		char[] language = itp.getLanguage();

     	String langStr = new String(language);

		System.out.println("infix: "+regExpr);
		System.out.println("postfix: "+postfix);
		System.out.println("alphabet: "+langStr);

		NFA nfa = new NFA(postfix);
		DFA dfa = new DFA(nfa, language);

		System.out.println("dfa final states: "+dfa.getfinalStates()); // Arraylist
		//ArrayList<Integer> finalStates = dfa.getfinalStates();

		//return eatDFA(int[][] table, String lang, ArrayList<Integer> fs, String target);
		return exactMatch(dfa.getTransitionTable(), langStr, dfa.getfinalStates(), target);
	}
	public boolean find(String regExpr, String target) throws IOException, GrumpyCatError{
		InfixToPostfix itp = new InfixToPostfix(regExpr);
		String postfix = itp.convert(regExpr);
		char[] language = itp.getLanguage();

     	String langStr = new String(language);

		System.out.println("infix: "+regExpr);
		System.out.println("postfix: "+postfix);
		System.out.println("alphabet: "+langStr);

		NFA nfa = new NFA(postfix);
		DFA dfa = new DFA(nfa, language);

		System.out.println("dfa final states: "+dfa.getfinalStates()); // Arraylist
		//ArrayList<Integer> finalStates = dfa.getfinalStates();

		//return eatDFA(int[][] table, String lang, ArrayList<Integer> fs, String target);
		return findMatch(dfa.getTransitionTable(), langStr, dfa.getfinalStates(), target);
	};
	public boolean find(String regExpr, String target, int start) throws IOException, NullPointerException{

		//reset();
		return true;
	};
	public int getStart(){
		return this.start;
	};
	public int getEnd(){
		return this.end;
	};
	public void setStart(int start){
		this.start = start;
	};
	public void setEnd(int end){
		this.end = end;
	};
	public String getCapture(int n){
		return "";
	};
	public int captureCount(){
		return 0;
	};
	public void reset(){
		this.start = 0;
	};
	
	// private methods:
	// exact match:
	private boolean exactMatch(int[][] table, String lang, ArrayList<Integer> fs, String target) throws IOException{

		BufferedReader br = stringToBR(target);
		int c; 
		int state = 0;
		while (((c = br.read()) != -1)) {
			int input = lang.indexOf((char)c);
			if(input != -1){ // if it's not in the language, then total fail
				if (table[state][input] == -1){ // unreachable state
					return false; 
				}else if (fs.contains(table[state][input]) && !br.ready()){ // fs => final states in DFA
					return true; // Table indicates that for this state, we accept the input given
				}
			// Advance to next state.
		    state = table[state][input];	
		    }else{
		    	return false;
		    }  
		}
		return true;
	}
	// find a match: (returns shortest match)
	// this prob needs some clean-up - NO TIME.
	private boolean findMatch(int[][] table, String lang, ArrayList<Integer> fs, String target) throws IOException{

		BufferedReader br = stringToBR(target);
		int c; 
		int state = 0;
		int currState = 0;
		StringBuilder sb = new StringBuilder();
		while (((c = br.read()) != -1)) {
			
			sb.append((char)c);
			int input = lang.indexOf((char)c);
			if (fs.contains(state)){ // if the state we're in  is a final state in DFA (returns shortest match)
										// this was put here as a fix for when state 0 is a final state.
				System.out.println("meow: "+sb.toString()); // this is as far as I got
				return true;
			}else if(input != -1){ // the input letter is in the language of the regex
				if (fs.contains(table[state][input])){ 
					System.out.println("meow: "+sb.toString()); // this is as far as I got
					return true; // Table indicates that the next state is an accepting state.
				}else if (table[state][input] == -1){ // unreachable state
					state = currState;
					continue;
				}
				currState = state;	 // track current state.		
				state = table[state][input]; // Advance to next state.
			}else{ 
		    	state = currState;
				continue;	
		    }
		}
		return false;
	}
	
	// private helper methods:
	private static BufferedReader stringToBR(String str){
		InputStream is = new ByteArrayInputStream(str.getBytes());
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		return br;
	}
    
}

	