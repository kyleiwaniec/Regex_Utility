package edu.mccc.cos210.tests;
import edu.mccc.cos210.ds.PriorityQueue;
import edu.mccc.cos210.ex.NoSuchElementException;

public class PriorityQueueTest{
	public static void main(String[] sa) throws NoSuchElementException{
		String[] data = {"Francis", "Alice", "Betty", "Charlie", "Duke", "Gregory", "Thomas"};
		PriorityQueue<String> pq = new PriorityQueue<String>();
		for(int i = 0; i<data.length; i++){
			pq.offer(data[i]);
			//System.out.println("pqsize: "+pq.size());
		}
		System.out.println(pq.toString());
		System.out.println("remove: " +pq.remove());
		System.out.println(pq.toString());
		System.out.println("peek:  "+pq.peek());
		System.out.println(pq.toString());
		System.out.println("poll:  "+pq.poll());
		System.out.println(pq.toString());
	}
}