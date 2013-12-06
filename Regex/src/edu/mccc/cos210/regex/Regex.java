package edu.mccc.cos210.regex;

import java.io.*;
import edu.mccc.cos210.ds.DoublyLinkedList;
import edu.mccc.cos210.ds.ArrayList;
import edu.mccc.cos210.ds.Stack;
import edu.mccc.cos210.ds.DFAStack;
import edu.mccc.cos210.ds.Edge;
import edu.mccc.cos210.ds.ArrayListGraph;
import edu.mccc.cos210.ds.ListGraph;
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
		String posix = itp.convert(regExpr);
		char[] language = itp.getLanguage();
		System.out.println("posix: "+posix);
		System.out.println("language: "+language.length);
		NFA nfa = new NFA(posix);
		DFA dfa = new DFA(nfa, language);

		return true;
	}
	public boolean find(String regExpr, String target) throws IOException, NullPointerException{
		return true;
	};
	public boolean find(String regExpr, String target, int start) throws IOException, NullPointerException{

		// JUST SOME STUBS:
		ArrayListGraph dfa = parseRegex(regExpr);
		//reset();
		return eatString(dfa, target, start);
		//return true;
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

	// parse the regex string: make edges. edge has vertices and weight. list indexes are vertices. stack is weight.
	private ArrayListGraph parseRegex(String regExpr) throws IOException {  

		BufferedReader br = stringToBR(regExpr);
		int c;

		ArrayListGraph listGraph = new ArrayListGraph();

		//ArrayList<Stack<String>> list = new ArrayList<Stack<String>>();

		Stack<String> stack = new Stack<String>(); 

		int counter = 0;
		int startGroup = -1;
		int endGroup = -1;

		int state = 0;


		while ((c = br.read()) != -1) {
			// The special characters are: {}[]+*()|-
			switch(c){
				case '|' : //System.out.println("|");
							state--;
				break;
				case '[' : System.out.println("["); // check what comes next: '-' or ':' or anything else, then process accorignly. (push chars onto same stack)
				break;
				case ']' : System.out.println("]");
				break;
				case '(' : //System.out.println("("); // start a new stack for each character that follows. keep track of position of '(' in case there is an '|'.
							startGroup = counter;	
				break;
				case ')' : //System.out.println(")");
							endGroup = counter;
				break;
				case '*' : System.out.println("*"); // always true. return to begining of group, and continue searching
				break;
				case '+' : System.out.println("+"); 
				break;
				case '-' : //System.out.println("-"); 
				break;
				case '{' : System.out.println("{"); 
				break;
				case '}' : System.out.println("}"); 
				break;
				case ':' : System.out.println("}"); 
				break;
				case '.' : System.out.println("."); 
				break;
				case '$' : System.out.println("$"); 
				break;
				case '^' : System.out.println("^"); 
				break;
				default : 

					// make a new edge
					//char[] ca = {(char)c};
					listGraph.insert(new Edge(state, ++state, (char)c));
			}
			counter++;
		}

		br.close();
		return listGraph;
	}
	private boolean matchString(ArrayListGraph dfa, String target) throws IOException{
		BufferedReader br = stringToBR(target);
		int c; 
		int srcV = 0;
		int destV;
		while (((c = br.read()) != -1)) {
			char nextChar = (char)c;
			System.out.println("nextchar: "+nextChar);
			Iterator<Edge> it = dfa.edgeIterator(srcV);

			while(it.hasNext()){
				Edge next = it.next();
				System.out.println("next: "+next);
				if(next.getWeight() == nextChar){
					System.out.println("a match!: "+next);
					srcV = next.getDest();
				}else{
					System.out.println("not a match!: "+next);
					//continue;
					//return false;
				}
			}
		}

		return true;
	}


	private boolean eatString(ArrayListGraph dfa, String target, int start) throws IOException{
		System.out.println("eatme: "+start);
		marker = start;
		if(marker >= target.length()){
			System.out.println("it's too big - oh no!!!!!");
			return false;
		};
		BufferedReader br = stringToBR(target.substring(start));
		int c; 
		int srcV = 0;
		int destV;

		while (((c = br.read()) != -1) && marker < target.length()) {
			marker++;
			char targetChar = (char)c;
			System.out.println("marker: "+marker+", srcV: "+srcV+", char-c: "+targetChar);

			Iterator<Edge> it = dfa.edgeIterator(srcV); // todo: reset iterator after successful find.

			while(it.hasNext()){
				Edge next = it.next();
				System.out.println("hasnext: "+next);
				if(next.contains(targetChar)){
					System.out.println("woohoo! a match!: "+next);
					srcV = next.getDest();
					setStart(marker);
					break;

				}else{
					System.out.println("oh, how sad: "+next+" marker: "+marker);
					if(!eatString(dfa, target, marker)){
						//reset(); // marker to 0
						return false; // this means: it's too big - oh no!!!!!
					} 
				}
			}

			// if(!eatString(dfa, target, marker)){
			// 	return false; // this means: it's too big - oh no!!!!!
			// }
		}
		//reset(); // marker to 0
		return true; // modify to true here, which means that target.length is still within marker
	}
	
	private boolean findInEdge(){
		return true;
	}

	// private helper methods:
	private static BufferedReader stringToBR(String str){
		InputStream is = new ByteArrayInputStream(str.getBytes());
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		return br;
	}


	private static String fileToString(String pathToFile) {
        String result = null;
        DataInputStream in = null;
        try {
            File f = new File(pathToFile);
            byte[] buffer = new byte[(int) f.length()];
            in = new DataInputStream(new FileInputStream(f));
            in.readFully(buffer);
            result = new String(buffer, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException("IO problem in fileToString", e);
        } finally {
            try {
                in.close();
            } catch (IOException e) { 
                    e.printStackTrace();
            }
        }
        return result;
    }
    
}

	