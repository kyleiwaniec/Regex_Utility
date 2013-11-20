package edu.mccc.soc210.ds;

/** A ListGraph is an extention of the AbstractGraph 
	abstract class that uses an array of lists to represent the edges
*/
public class ListGraph extends AbstractGraph{
	// Data field
	/** An array of Lists to contain the edges that originate with each vertex
		(each vertex, as in each index value will be a state, each edge will be transition/conditional 
		it's what tells the machine which state to move to. EX: if I'm in state 1, and pattern matches edge a, go to state 2
		1 ------a------- 2 )
	*/
	private ArrayList<Edge>[] edges;

	/** Construct a graph with the specified number of vertices
		and directionality
		@param numV The number of vertices
		@param directed The directionality flag
	*/	
	public ListGraph(int numV, boolean isDirected){
		super(numV, directed);
		edges = new ArrayList[numV];
		for(int i = 0; i < numV; i++){
			edges[i] = new DoublyLinkedList<Edge>();
		}
	}

	/** Inset a new edge into the graph.
		@param edge The new edge
	*/	
	public void insert(Edge edge){
		edges[edge.getSource()].add(edge);
		if(!isDirected()){
			edges[edge.getDest()].add(new Edge(edge.getDest(),
												edge.getSource(),
												edge.getWeight()));
		}
	}

	/** Determine whether an edge exists.
		@param source The source vertex
		@param dest The desitonation vertex
		@return true if there is an edge from source to dest
	*/
	public boolean isEdge(int source, int dest){
		return edges[source].contains(new Edge(source, dest));
	}

	/** Get the edge between two vertices. If an edge does not exist, 
		an Edge with a weight of Double.POSITIVE_INFINITY is returned.
		@param source The source
		@param dest The destination
		@return the edge between these two vertices
	*/
	public Edge getEdge(int source, int dest){
		Edge target = new Edge(source, dest, Double.POSITIVE_INFINITY);
		// loop over edges[source] (DLL) => return edge if exists


		Edge nextItem = (Edge) edges[source].getFirst();
        for(int i = 0; i < edges[source].getSize(); i++){
            if (nextItem.equals(traget)) {
                 return nextItem;
            }
            while(edges[source].hasNext()){
                nextItem = (Edge) edges[source].getNext();
            }
        }

		//Assert: All edges for source checked. desired edge not found;
		return target;
	}	
}