package edu.mccc.cos210.tests;
import edu.mccc.cos210.ds.ArrayList;
import java.util.Iterator;

public class ArrayListTest{
	public static void main(String[] sa){
		//@SuppressWarnings("unchecked")
		ArrayList<String> list = new ArrayList<String>();
		list.add("this");
		list.add("list");
		list.add("cat");
		list.add("is");
		list.add("my");
		list.add("implementation");
		System.out.println(list.toString());


		Iterator<String> iter = list.iterator();

		while(iter.hasNext()){
			String nextItem = iter.next();
			System.out.println(nextItem);
		}

		for(Object name : list){
			System.out.println("name: "+name);
		} 

		System.out.println("afer:  "+list.toString());
		System.out.println("find cat:"+ list.find("cat")); 
		System.out.println("removed:"+ list.remove(list.find("cat"))); 
		System.out.println(list.toString());

		System.out.println("contains 'is': "+list.contains("is"));
		

		System.out.println("removed item: "+list.remove(2));
		System.out.println(list.toString());
		System.out.println("contains 'is': "+list.contains("is"));

		System.out.println("shift: "+list.shift());
		System.out.println(list.toString());

		ArrayList<String> clone = (ArrayList<String> ) list.clone();
		System.out.println("clone:"+clone.toString());

		System.out.println(list.toString());
		System.out.println(list.size());
		list.add(" - woohoo!");
		System.out.println(list.toString());
		list.add(1, "awesome");
		System.out.println(list.toString());
		list.set(3, "was");
		System.out.println(list.toString());
		//list.remove(0);
		System.out.println("removed item: "+list.remove(0));
		System.out.println(list.toString());
		
		System.out.println("the word awesome is at index: "+list.find("awesome"));
		System.out.println("popped item: "+list.pop());
		System.out.println(list.toString());

		System.out.println("shifted item: "+list.shift());
		System.out.println(list.toString());

		System.out.println("cloneafter:"+clone.toString());
	}
}