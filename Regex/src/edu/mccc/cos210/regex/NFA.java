package edu.mccc.cos210.regex;
import java.io.*;
import edu.mccc.cos210.ds.DoublyLinkedList;
import edu.mccc.cos210.ds.ArrayList;
import edu.mccc.cos210.ds.Stack;
import edu.mccc.cos210.ds.Edge;
import edu.mccc.cos210.ds.ArrayListGraph;

import java.util.Iterator;

import edu.mccc.cos210.ex.GrumpyCatError;

/**
* Example posix expression: ca•t•ab|c|d|*•
*
*/


public class NFA{
	private static final String OPERATORS = "[](){}*+?•^$|.";
	private Stack<ArrayListGraph> nfaStack;
	private String posixRegexp;
    private int rgl; 
	public NFA(String posixRegexp) throws GrumpyCatError{
		this.posixRegexp = posixRegexp;
        rgl = posixRegexp.length();
        nfaStack = new Stack<ArrayListGraph>();
        int state = 0; // vertices
        int startState;
        int acceptSate;

        for(int i = 0; i < rgl; i++){
        	char c = posixRegexp.charAt(i);
        	if(!isOperator(c)){
        		// make nfa and push on stack
        		System.out.println("state: "+state);
        		nfaStack.push(new ArrayListGraph(new Edge(state, ++state, c)));
        	}else if(c == '•'){
        		// pop 2 nfas off stack,
        		// modyfy
        		// push on stack;

        		//nfaStack.pop();
        		// process
        		//nfaStack.push();
        	}else if(c == '*'){

        	}else if(c == '+'){

        	}else if(c == '?'){

        	}else if(c == '^'){

        	}else if(c == '$'){

        	}else if(c == '|'){

        	}else{
        		throw new GrumpyCatError("STOP TRYING TO BREAK ME.. CAN'T YOU SEE I'M FRAGILE?");	
        	}
        	
        }
	}
	/**
     * Determine whether a character is an operator.
     * @param ch The character to be tested
     * @return true if ch is an operator
     */
    private boolean isOperator(char ch) {
        return OPERATORS.indexOf(ch) != -1;
    }
}