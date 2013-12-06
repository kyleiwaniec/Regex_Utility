package edu.mccc.cos210.tests;
import edu.mccc.cos210.ds.PriorityQueue;
import edu.mccc.cos210.ex.NoSuchElementException;

public class PriorityQueueTest{
	public static void main(String[] sa) throws NoSuchElementException{
		String[] data = {"Francis", "Gregory", "Alice", "Betty", "Duke", "Zevan", "Sam","Charlie", "Thomas", "Connor"};
		PriorityQueue<String> pq = new PriorityQueue<String>();
		for(int i = 0; i<data.length; i++){
			pq.offer(data[i]);
		}
		System.out.println(pq.toStringDesc());

		PriorityQueue<String> pqclone = (PriorityQueue<String>) pq.clone();
		System.out.println("clone: " +pqclone.toStringDesc());


		System.out.println("remove: " +pq.remove());
		System.out.println(pq.toStringDesc());

		System.out.println("peek:  "+pq.peek());
		System.out.println(pq.toStringDesc());

		System.out.println("poll:  "+pq.poll());
		System.out.println(pq.toStringDesc());

		System.out.println("poll:  "+pq.poll());
		System.out.println(pq.toStringDesc());

		System.out.println("remove: " +pq.remove());
		System.out.println(pq.toStringDesc());

		System.out.println("Is the quque empty? "+pq.isEmpty());
		System.out.println("Size: "+pq.getSize());

		System.out.println(" clone toStringMin: " +pqclone.toStringDesc());
		System.out.println("CloneSize: "+pqclone.getSize());
		System.out.println(" clone toString: " +pqclone.toString());
	}
}

