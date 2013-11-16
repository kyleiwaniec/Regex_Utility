package edu.mccc.cos210.tests;
import edu.mccc.cos210.ds.BinarySearchTree;
public class BinaryTreeTest{
	public static void main(String[] args){
		String[] data = {"Kyle", "Robert", "Sammy", "Duke", "Paul", "Fish", "Amoeba"};

		
		BinarySearchTree bst = new BinarySearchTree();

		for(int i = 0; i < data.length; i++){
			bst.add(data[i]);
		};
		

		System.out.println(bst.toString());

		System.out.println(bst.contains("Sammy"));
		System.out.println(bst.contains("Bob"));

		System.out.println("Min: "+bst.getMin());
		System.out.println("Max: "+bst.getMax());

		// Insert solution to programming exercise 3, section 4, chapter 6 here
    // in-order toString method
	}
}