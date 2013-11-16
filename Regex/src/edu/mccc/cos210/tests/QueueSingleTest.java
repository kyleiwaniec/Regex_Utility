package edu.mccc.cos210.tests;
import edu.mccc.cos210.ds.QueueSingle;
import edu.mccc.cos210.ex.NoSuchElementException;
public class QueueSingleTest{
	public static void main(String[] sa) throws NoSuchElementException{
		//@SuppressWarnings("unchecked")
		QueueSingle<String> q = new QueueSingle<String>();

		//q.remove(); // throws nosuchelementexception

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