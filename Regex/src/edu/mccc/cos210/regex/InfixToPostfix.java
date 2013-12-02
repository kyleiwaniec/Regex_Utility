package edu.mccc.cos210.regex;
import java.io.*;
import edu.mccc.cos210.ds.Stack;

public class InfixToPostfix{
	// Data Fields
    /** The operator stack */
    private Stack<Character> operatorStack;
    /** The operators */
    private static final String OPERATORS = "\\[](){}*+?~^$|.";
    private static final String CONCATTOKENS = "[](){}."; // STUFF GETS CONCATENATED TO THESE, but only on the outsides. ???
    /** The precedence of the operators, matches order in OPERATORS. 

    The order of precedence for of operators is as follows:
		Collation-related bracket symbols [==] [::] [..]
		Escaped characters \
		Character set (bracket expression) []
		Grouping ()
		Single-character-ERE duplication * + ? {m,n}
		Concatenation -- will need to insert some arbitrary character to use for concat ~
		Anchoring ^$
		Alternation |
	*/
    private static final int[] PRECEDENCE = {7,6,6,5,5,4,4,4,4,4,3,2,2,1,1};
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
		boolean bracketExp = false;
		boolean range = false;
		boolean dashFlag = false;
		int sbIdx = 0;

		while ((c = br.read()) != -1){
			
			if(bracketExp && !dashFlag && (char)c == '-'){ // the dash is not the first character after the bracket
				// process range
				// we need to know the PREVIOUS charcter and NEXT character to build range
				char curr = (char)c;
				char prev = sb.charAt(sbIdx-1);
				int n = br.read(); //*********** CAREFUL - WE ARE ADVANCING THE READER ***********
				char next = (char)n;
				System.out.println("length: "+sb.length()+", prev: "+prev+", next: "+next);

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
				}
				if(isOperator((char)c)){ 
					isToken = false;
				}else{
					isToken = true;
				}
			}else{
				if(!isOperator((char)c) && isToken){
					sb.append('~');
					sbIdx++;
				}
				if(isOperator((char)c)){
					isToken = false;
				}else{
					isToken = true;
				}
			}
				
			sb.append((char)c);
			sbIdx++;
		}
		return sb.toString();
	}

	public String convert(String infix) throws IOException{
		operatorStack = new Stack<Character>();
        postfix = new StringBuilder();
        BufferedReader br = stringToBR(insertConcatOps(infix));
        int c;
        while ((c = br.read()) != -1){
        	char firstChar = (char)c;

        	if (isOperator(firstChar)) {
                    processOperator(firstChar);
                }else{
                	postfix.append((char)c);
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
    private void processOperator(char op) {
    	System.out.println("0 stack: "+operatorStack.toString());
    	System.out.println("op: "+op);
        if (operatorStack.empty()) {
            operatorStack.push(op);
        } else {
            // Peek the operator stack and
            // let topOp be top operator.
            char topOp = operatorStack.peek();
            System.out.println("topop: "+topOp);
            if(op == '('){
            	operatorStack.push(op);
            }else if ((topOp == '(' || precedence(op) > precedence(topOp)) && op != ')') {

                operatorStack.push(op);
                System.out.println("1 stack: "+operatorStack.toString());
            }else if(op == ')'){

            	while(topOp != '('){
            		System.out.println("2 stack: "+operatorStack.toString());
            		operatorStack.pop();
            		postfix.append(topOp);
            		if (!operatorStack.empty()) {
                        // Reset topOp.
                        topOp = operatorStack.peek();
                    }
            	}
            	operatorStack.pop();
            	

            } else {
            	System.out.println("3 stack: "+operatorStack.toString());
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