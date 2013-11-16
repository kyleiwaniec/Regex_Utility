package edu.mccc.cos210.tests;
import edu.mccc.cos210.ds.Stack;
public class StackTest{
	public static void main(String[] sa){
		//@SuppressWarnings("unchecked")
		Stack<String> stack = new Stack<String>();
		stack.push("this");
		stack.push("stack");
		stack.push("is");
		stack.push("my");
		stack.push("implementation");

		System.out.println(stack.toString());

		stack.push(" - woohoo!");
		System.out.println(stack.toString());
		stack.push("awesome");
		System.out.println(stack.toString());

		
		System.out.println("popped item: "+stack.pop());
		System.out.println(stack.toString());

		System.out.println("peeked item: "+stack.peek());
		System.out.println(stack.toString());

		System.out.println("is the stack empty? "+stack.empty());
	}
}