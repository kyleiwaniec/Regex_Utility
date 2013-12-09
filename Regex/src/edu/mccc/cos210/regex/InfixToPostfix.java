package edu.mccc.cos210.regex;
import java.io.*;
import edu.mccc.cos210.ds.Stack;
import edu.mccc.cos210.ds.ArrayList;
import edu.mccc.cos210.ex.GrumpyCatError;

public class InfixToPostfix{
	// Data Fields
	private ArrayList<Character> language = new ArrayList<Character>();
    /** The operator stack */
    private Stack<Character> operatorStack;
    /** The operators */
    private static final String OPERATORS = "[](){}*+?•^$|";
    /** The precedence of the operators, matches order in OPERATORS. 

    The order of precedence for of operators is as follows:
		Collation-related bracket symbols [==] [::] [..]
		Escaped characters \
		Character set (bracket expression) []
		Grouping ()
		Single-character-ERE duplication * + ? {m,n}
		Concatenation -- will need to insert some arbitrary character to use for concat • (bullet symbol - not within the 128 ascii we're suporting.)
		Anchoring ^$
		Alternation |
	*/
    private static final int[] PRECEDENCE = {8,8,7,7,6,6, 5, 5,5,4,3,3,2};
	private StringBuilder postfix;

	public InfixToPostfix(String infix){
	
	}
	/** First, insert concat operators into string 
	*/
	public String insertConcatOps(String str) throws IOException{
		BufferedReader br = stringToBR(str);
		StringBuilder sb = new StringBuilder(); 
		int c;
		boolean isToken = false; // this is to keep track of tokens and only insert concat operators between tokens
		boolean closingBracket = false; // this is to keep track of closing brackets and insert concat if the next token is NOT an operator.
		boolean bracketExp = false;
		boolean range = false;
		boolean dashFlag = false;
		boolean duplication = false;
		int sbIdx = 0; // keeps track of number of charcters in converted regex sring

		while ((c = br.read()) != -1){
			char curr = (char)c;
			if(bracketExp && !dashFlag && (char)c == '-'){ // the dash is not the first character after the bracket
				// process range
				// we need to know the PREVIOUS charcter and NEXT character to build range
				
				char prev = sb.charAt(sbIdx-1);
				int n = br.read(); //*********** CAREFUL - WE ARE ADVANCING THE READER ***********
				char next = (char)n;

				//INSERT range of characters including prev and next with | operators
				while(prev < next){
					sb.append('|');
					sbIdx++;
					sb.append(++prev);
					sbIdx++;
				}
				continue;		
			}
			if(bracketExp){
				dashFlag = false;
				// continue to process the dash as a regular character
			}
			if((char)c == '['){
				bracketExp = true;
				dashFlag = true;
			}
			if((char)c == ']'){
				bracketExp = false;
			}
			if(bracketExp){ // tokens inside bracket expressions use alternation, NOT concatenation.
				if(!isOperator((char)c) && isToken){
					sb.append('|');
					sbIdx++;
				}else if((char)c == '[' && (isToken || closingBracket)){
					sb.append('•');
					sbIdx++;
				}
				if(isOperator((char)c)){ 
					isToken = false;
				}else{
					isToken = true;
				}
			}else{
				if(!isOperator((char)c) && isToken){
					sb.append('•');
					sbIdx++;
				}else if((char)c == '(' && (isToken || closingBracket)){
					sb.append('•');
					sbIdx++;
				}
				if(isOperator((char)c)){
					isToken = false;
				}else{
					isToken = true;
				}
				if(closingBracket && isToken){
					sb.append('•');
					sbIdx++;
				}
				if(isToken && duplication){
					sb.append('•');
					sbIdx++;
				}
				
			}
			if(curr == '*' || curr == '+' || curr == '?'){
				duplication = true;
			}else{
				duplication = false;
			}
			if((char)c == ']' || (char)c == ')'){
				closingBracket = true;
			}else{
				closingBracket = false;
			}
			if((char)c == '['){
				sb.append('(');
			}else if((char)c == ']'){
				sb.append(')');
			}else{
				sb.append((char)c);
			}
			

			sbIdx++;
		}
		//System.out.println("with concat ops: "+sb.toString());
		return sb.toString();
	}

	public String convert(String infix) throws IOException, GrumpyCatError{
		operatorStack = new Stack<Character>();
        postfix = new StringBuilder();
        BufferedReader br = stringToBR(insertConcatOps(infix));
        int c;
        while ((c = br.read()) != -1){
        	char firstChar = (char)c;
        	if (isOperator(firstChar)) {
                    processOperator(firstChar);
                }else{
                	postfix.append(firstChar);
                	buildLanguage(firstChar);
                }
        }
        // Pop any remaining operators and
        // append them to postfix.
        while (!operatorStack.empty()) {
    		char op = operatorStack.pop();
       		postfix.append(op);
        }
        return postfix.toString();
	}

	// private helper methods:
	private static BufferedReader stringToBR(String str){
		InputStream is = new ByteArrayInputStream(str.getBytes());
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		return br;
	}

	/**
     * Method to process operators.
     * @param op The operator
     * @throws EmptyStackException
     */
    private void processOperator(char op) throws GrumpyCatError{
    	if (operatorStack.empty()) {
            operatorStack.push(op);
        } else {
        	//System.out.println("operatorStack: "+operatorStack);

            // Peek the operator stack and
            // let topOp be top operator.
            char topOp = operatorStack.peek();
            if(op == '('){
            	operatorStack.push(op);
            }else if ((topOp == '(' || precedence(op) > precedence(topOp)) && op != ')') {
                operatorStack.push(op);
            }else if(op == ')'){
            	while(topOp != '('){
            		operatorStack.pop();
            		postfix.append(topOp);
            		if (!operatorStack.empty()) {
                        // Reset topOp.
                        topOp = operatorStack.peek();
                    }
            	}
            	operatorStack.pop();
        	} else {
                // Pop all stacked operators with equal
                // or higher precedence than op.
                while (!operatorStack.empty() && precedence(op) <= precedence(topOp) && topOp != '(') {
                    operatorStack.pop();
                    postfix.append(topOp);
                    if (!operatorStack.empty()) {
                        // Reset topOp.
                        topOp = operatorStack.peek();
                    }
                }
                // assert: Operator stack is empty or
                //         current operator precedence >
                //         top of stack operator precedence.
                operatorStack.push(op);
            }
        }
    }
    public char[] getLanguage(){
		int length = this.language.size();
		char[] lang = new char[length];
		for(int i = 0; i < length; i++){
			lang[i] = this.language.get(i);
		}

		return lang;
	}
	public String languageToString(){
		StringBuilder sb = new StringBuilder();
	        for(int i = 0; i < this.language.size(); i++) {
	            sb.append(language.get(i));
	        }
	    return sb.toString();    
	}
	private void setLanguage(){
		this.language = language;
	}
	private void buildLanguage(char c){
		if(!this.language.contains(c)){
			this.language.add(c);
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

    /**
     * Determine the precedence of an operator.
     * @param op The operator
     * @return the precedence
     */
    private int precedence(char op) {
        return PRECEDENCE[OPERATORS.indexOf(op)];
    }
}