package edu.mccc.cos210.tests;
import edu.mccc.cos210.ds.Queue;
public class QueueTest{
	public static void main(String[] sa){
		//@SuppressWarnings("unchecked")
		Queue<String> q = new Queue<String>();
		q.add("this");
		q.add("queue");
		q.add("is");
		q.add("my");
		q.add("implementation");

		System.out.println(q.toString());

		q.offer("another");
		q.offer("awsome");
		q.offer("thriller!");

		System.out.println(q.toString());

		System.out.println("remove first item: "+q.remove());
		System.out.println(q.toString());

		System.out.println("poll (remove) first item: "+q.poll());
		System.out.println(q.toString());

		System.out.println("element: "+q.element());
		System.out.println(q.toString());

		System.out.println("peek: "+q.peek());
		System.out.println(q.toString());
	}
}