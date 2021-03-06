package edu.mccc.cos210.ds;
import java.util.Iterator;

/** A ListGraph is a class that uses an array of lists to represent the edges
*/
public class ListGraph implements Graph{
	// Data field
	/** An array of Lists to contain the edges that originate with each vertex
		
	*/
	private ArrayList<Edge>[] edges;

	// Data Fields
    /** The number of vertices */
    private int numV;
    /** Flag to indicate whether this is a directed graph */
    private boolean directed;
    private int size;

    private int startState;
    private int acceptState;
	/** Construct a graph with the specified number of vertices
		and directionality
		@param numV The number of vertices
		@param directed The directionality flag
	*/	
	public ListGraph(int numV, boolean isDirected){
		this.numV = numV;
        this.directed = isDirected;
		edges = new ArrayList[numV];
		for(int i = 0; i < numV; i++){
			edges[i] = new ArrayList<Edge>();
		}
	}
	public ListGraph(){

	}
	/** Inset a new edge into the graph.
		a new Edge is created in AbstractGraph when read in from file
		@param edge The new edge

	*/	
	public void insert(Edge edge){ // usage: insert(new Edge(int source, int destination, char weight))
		// add the edge to the end of the list for the given source vertex
		edges[edge.getSource()].add(edge);

		if(!isDirected()){

			edges[edge.getDest()].add(new Edge(edge.getDest(),
												edge.getSource(),
												edge.getWeight()));
		}
		size++;
		//System.out.println(toString());
	}
	 // Accessor Methods

	public int numEdges() {
        return ListGraph.this.size;
    }
    public ArrayList<Edge> get(int i){
        return edges[i];
    }
    /**
     * Return the number of vertices.
     * @return The number of vertices
     */
    @Override
    public int getNumV() {
        return ListGraph.this.numV;
    }

    /**
     * Return whether this is a directed graph.
     * @return true if this is a directed graph
     */
    @Override
    public boolean isDirected() {
        return directed;
    }

	/** Determine whether an edge exists.
		@param source The source vertex
		@param dest The desitonation vertex
		@return true if there is an edge from source to dest
	*/
	public boolean isEdge(int source, int dest){
		Edge target = new Edge(source, dest);
        return edges[source].contains(new Edge(source, dest));
	}

	/** Get the edge between two vertices. If an edge does not exist, 
		an Edge with a weight of Double.POSITIVE_INFINITY is returned.
		@param source The source
		@param dest The destination
		@return the edge between these two vertices
	*/
	public Edge getEdge(int source, int dest){
		Edge target = new Edge(source, dest);

        for(Object edge : edges[source]){
            if(edge.equals(target)){
                return (Edge)edge;
            }
        }
		//Assert: All edges for source checked. desired edge not found;
		return target;
	}

    public int numEdgesInSource(int source){
        return edges[source].size();
    }

    public void setStartState(int startState){
        this.startState = startState;
    }
    public void setAcceptState(int acceptState){
        this.acceptState = acceptState;
    }

    public int getStartState(){
        return this.startState;
    }
    public int getAcceptState(){
        return this.acceptState;
    }

	public String toString(){
		StringBuilder sb = new StringBuilder(); 
        for(int i = 0; i < edges.length; i++){
                for(Object e : edges[i]){
                        sb.append(e); 
                }
        }
        if(sb.length()>=2){sb.delete(0,2);};
        return sb.toString();
	}


    
    @Override
    public Iterator<Edge> edgeIterator(int source){
            return edges[source].iterator();
    };

}