package edu.mccc.cos210.tests;
import edu.mccc.cos210.ds.HashTable;
public class HashTableTest{
	public static void main(String[] sa){
		HashTable<Integer, String> ht = new HashTable();

		// ht.put(1,"Anna");
		// ht.put(2,"Brittany");
		// ht.put(20,"Samnuel");
		ht.put(3, "A");
		System.out.println(ht.toString());
		ht.put(3, "B");
		System.out.println(ht.toString());
		ht.put(3, "C");
		System.out.println(ht.toString());
		ht.put(3, "D");
		System.out.println(ht.toString());
		ht.put(3, "E");
		System.out.println(ht.toString());
		ht.put(3, "F");
		System.out.println(ht.toString());
		ht.put(3, "G");
		ht.put(782,"Sammy");
		ht.put(178,"bob");
		System.out.println(ht.toString());
		System.out.println("get sammy"+ht.get(4));

		ht.remove(782);
		System.out.println(ht.toString());

	}
}