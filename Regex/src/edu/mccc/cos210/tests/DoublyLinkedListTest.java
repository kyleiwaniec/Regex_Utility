package edu.mccc.cos210.tests;
import edu.mccc.cos210.ds.DoublyLinkedList;
import java.util.Iterator;

public class DoublyLinkedListTest{
	public static void main(String[] sa) throws Exception {
		new DoublyLinkedListTest().doIt();
	}
	
	private void doIt() throws Exception {
		//@SuppressWarnings("unchecked")
		int i = 0;
		DoublyLinkedList<String> dlls = new DoublyLinkedList<String>();

		System.out.println(++i+". "+dlls);
		dlls.addNext("A");
		System.out.println(++i+". "+dlls);
		dlls.addLast("B");
		System.out.println(++i+". "+dlls);
		dlls.addNext("C");
		System.out.println(++i+". "+dlls);
		dlls.addLast("D");
		System.out.println(++i+". "+dlls);
		dlls.addPrevious("x");
		System.out.println(++i+". "+dlls);

		System.out.println("current: "+dlls.getCurrent());

		for(Object item : dlls){
			System.out.println("item: "+item);
		}

		System.out.println("toString: ");
		System.out.println(dlls.toString());
		//System.out.println("current 2: "+dlls.getCurrent());


		System.out.println("first: "+dlls.getFirst());
		System.out.println("last: "+dlls.getLast());
		System.out.println(++i+". "+dlls);
		dlls.removeFirst();
		System.out.println("remove first: ");
		System.out.println(++i+". "+dlls);

		System.out.println("next: "+dlls.hasNext());


		dlls.removeLast();
		System.out.println("remove last: ");
		System.out.println(++i+". "+dlls);
		dlls.getPrevious();
		System.out.println("current Element: "+dlls.getCurrent());
		dlls.removeCurrent();
		System.out.println("removed current^^: ");
		System.out.println(++i+". "+dlls);
		
		System.out.println("current Element: "+dlls.getCurrent());
		System.out.println(++i+". "+dlls);

		dlls.removePrevious();
		System.out.println("current Element: "+dlls.getCurrent());
		System.out.println(++i+". "+dlls);

		System.out.println("size: "+dlls.getSize());
		System.out.println(++i+". "+dlls);

		System.out.println("is the list empty?: "+dlls.isEmpty());
		System.out.println(++i+". "+dlls);

		System.out.println("last: "+dlls.getLast());
		System.out.println("first: "+dlls.getFirst());
		System.out.println(++i+". "+dlls);

	}
}