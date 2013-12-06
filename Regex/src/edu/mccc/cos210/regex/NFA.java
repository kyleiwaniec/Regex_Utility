package edu.mccc.cos210.regex;
import java.io.*;
import edu.mccc.cos210.ds.DoublyLinkedList;
import edu.mccc.cos210.ds.ArrayList;
import edu.mccc.cos210.ds.Stack;
import edu.mccc.cos210.ds.Edge;
import edu.mccc.cos210.ds.ArrayListGraph;
import edu.mccc.cos210.ds.ListGraph;

import java.util.Iterator;

import edu.mccc.cos210.ex.GrumpyCatError;

/** Takes a posix expression, and builds NFA.
*	Example posix expression: ca•t•ab|c|d|*•
*	Equivalent infix: cat[a-d]
*
*/


public class NFA extends ListGraph{
	private static final String OPERATORS = "[](){}*+?•^$|.";
	private Stack<ListGraph> nfaStack;
	private String posixRegexp;
    private int rgl; 
	public NFA(String posixRegexp) throws GrumpyCatError{
		this.posixRegexp = posixRegexp;
        rgl = posixRegexp.length();
        nfaStack = new Stack<ListGraph>();
        int state = -1; // vertices
        int startState;
        int acceptSate;
        int numV = 0;

        for(int i = 0; i < rgl; i++){
        	char c = posixRegexp.charAt(i);
        	if(!isOperator(c)){
        		// make edge to nfa and push on stack
        		ListGraph nfa = new ListGraph(numV+=2,true);

        		nfa.setStartState(++state);
        		nfa.setAcceptState(++state);

        		nfa.insert(new Edge(nfa.getStartState(), nfa.getAcceptState(), c));
        		nfaStack.push(nfa);
        	}else if(c == '•'){
        		// pop 2 nfas off stack,
        		// modyfy
        		// push on stack;
        		ListGraph top = nfaStack.pop();
        		ListGraph bot = nfaStack.pop();

        		ListGraph nfa = new ListGraph(numV, true);

        		for(int j = 0; j < top.getNumV(); j++){
        			for(Object e1 : top.get(j)){
        				nfa.insert((Edge) e1);
        			}
        		}
        		for(int k = 0; k < bot.getNumV(); k++){
        			for(Object e2 : bot.get(k)){
        				nfa.insert((Edge) e2);
        			}
        		}
        		// insert epilon edge
        		nfa.insert(new Edge(bot.getAcceptState(), top.getStartState()));

        		nfa.setStartState(bot.getStartState());
        		nfa.setAcceptState(top.getAcceptState());

        		nfaStack.push(nfa);
        		System.out.println("• NFA: "+nfa.toString());
        		System.out.println("start: "+nfa.getStartState()+" accept: "+nfa.getAcceptState());

        	}else if(c == '*'){
        		// pop 1 nfa off stack,
        		// modyfy
        		// push on stack;
        		ListGraph top = nfaStack.pop();

        		ListGraph nfa = new ListGraph(numV+=2, true);
        		for(int j = 0; j < top.getNumV(); j++){
        			for(Object e1 : top.get(j)){
        				nfa.insert((Edge) e1);
        			}
        		}

        		nfa.insert(new Edge(++state, top.getStartState()));
        		nfa.setStartState(state);
        		nfa.insert(new Edge(top.getAcceptState(), ++state));
        		nfa.setAcceptState(state);
        		nfa.insert(new Edge(nfa.getStartState(), nfa.getAcceptState()));

        		nfa.insert(new Edge(nfa.getAcceptState(), top.getStartState()));
        		nfaStack.push(nfa);
        		System.out.println("* NFA: "+nfa.toString());
        		System.out.println("start: "+nfa.getStartState()+" accept: "+nfa.getAcceptState());

        	}else if(c == '+'){
        		// pop 1 nfa off stack,
        		// modyfy
        		// push on stack;
        		ListGraph top = nfaStack.pop();

        		ListGraph nfa = new ListGraph(numV+=2, true);
        		for(int j = 0; j < top.getNumV(); j++){
        			for(Object e1 : top.get(j)){
        				nfa.insert((Edge) e1);
        			}
        		}
        		nfa.insert(new Edge(top.getAcceptState(), top.getStartState()));

        		nfa.insert(new Edge(++state, top.getStartState()));
        		nfa.setStartState(state);

        		nfa.insert(new Edge(top.getAcceptState(), ++state));
        		nfa.setAcceptState(state);

        		nfaStack.push(nfa);

        		System.out.println("+ NFA: "+nfa.toString());
        		System.out.println("start: "+nfa.getStartState()+" accept: "+nfa.getAcceptState());
        	}else if(c == '?'){
        		// pop 1 nfa off stack,
        		// modyfy
        		// push on stack;
        		ListGraph top = nfaStack.pop();

        		ListGraph nfa = new ListGraph(numV, true);
        		for(int j = 0; j < top.getNumV(); j++){
        			for(Object e1 : top.get(j)){
        				nfa.insert((Edge) e1);
        			}
        		}
        		nfa.insert(new Edge(top.getStartState(), top.getAcceptState()));
        		// start and accept states don't change
        		nfa.setStartState(top.getStartState());
        		nfa.setAcceptState(top.getAcceptState());
        		nfaStack.push(nfa);

        		System.out.println("? NFA: "+nfa.toString());
        		System.out.println("start: "+nfa.getStartState()+" accept: "+nfa.getAcceptState());

        	}else if(c == '^'){
        		// set flag to say match must start at begginning of input string

        	}else if(c == '$'){
        		// set flag to say match must end at end of input string

        	}else if(c == '|'){
        		// pop 2 nfas off stack,
        		// modyfy
        		// push on stack;
        		ListGraph top = nfaStack.pop();
        		ListGraph bot = nfaStack.pop();

        		ListGraph nfa = new ListGraph(numV+=2, true);

        		for(int j = 0; j < top.getNumV(); j++){
        			for(Object e1 : top.get(j)){
        				nfa.insert((Edge) e1);
        			}
        		}
        		for(int k = 0; k < bot.getNumV(); k++){
        			for(Object e2 : bot.get(k)){
        				nfa.insert((Edge) e2);
        			}
        		}

        		// new vertex and two epsilon edges to the left
        		nfa.insert(new Edge(++state, top.getStartState()));
        		nfa.insert(new Edge(state, bot.getStartState()));
        		nfa.setStartState(state);

        		// new vertex and two epsilon edges to the right
        		nfa.insert(new Edge(top.getAcceptState(), ++state));
        		nfa.insert(new Edge(bot.getAcceptState(), state));
        		nfa.setAcceptState(state);


        		nfaStack.push(nfa);
        		System.out.println("| NFA: "+nfa.toString());
        		System.out.println("start: "+nfa.getStartState()+" accept: "+nfa.getAcceptState());

        	}else{
        		throw new GrumpyCatError("STOP TRYING TO BREAK ME.. CAN'T YOU SEE I'M FRAGILE?!");
        	}
        	
        }

	}
    public ListGraph getGraph(){
        return nfaStack.pop();
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