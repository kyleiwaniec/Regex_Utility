package edu.mccc.cos210.tests;
import edu.mccc.cos210.ds.ListGraph;
import edu.mccc.cos210.ds.Edge;
import java.util.Iterator;

public class ListGraphTest{
	public static void main(String[] sa){

		char[] a = {'a'};
		char[] b = {'b'};
		char[] c = {'c'};

		Edge e1 = new Edge(0,1, a);
		Edge e2 = new Edge(0,1, b);
		Edge e3 = new Edge(1,2, c);

		ListGraph lg = new ListGraph(3, true);
		lg.insert(e1);
		lg.insert(e2);
		lg.insert(e3);

		System.out.println(lg.toString());
	}
}