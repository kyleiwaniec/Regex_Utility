package edu.mccc.cos210.tests;
import edu.mccc.cos210.ds.ArrayListGraph;
import edu.mccc.cos210.ds.Edge;
import java.util.Iterator;

public class ArrayListGraphTest{
	public static void main(String[] sa){

		// char[] a = {'a'};
		// char[] b = {'b'};
		// char[] c = {'c'};

		Edge e1 = new Edge(0,1, 'a');
		Edge e2 = new Edge(0,1, 'b');
		Edge e3 = new Edge(1,2, 'c');

		ArrayListGraph lg = new ArrayListGraph();
		lg.insert(e1);
		lg.insert(e2);
		lg.insert(e3);

		System.out.println(lg.toString());

		System.out.println(lg.numEdges());
		System.out.println(lg.getNumV());
	}
}