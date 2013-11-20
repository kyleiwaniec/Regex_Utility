package edu.mccc.cos210.ds;

/** Itereface to specify the Graph ADT. 
Each implementation should provide a constructor 
that specifies the number of veritcies and whether 
or not the grapgh is directed
*/

public interface Graph{
	// Accessor methods
	/** Return the number of veritces.
		@return The number of vertices
	*/
	int getNumV();

	/** Determine whether the graph is directed.
		@return true if the graph is directed graph
	*/
	boolean isDirected();

	/** Insert a new edge into the graph
		@param edge The new Edge
	*/
	void insert(Edge edge);
	
	/** Determone whether edge exists.
		@param source The source vertex
		@param dest The desitination vertex
		@return true if there is an edge from source to dest.
	*/
	boolean isEdge(int source, int dest);

	/** Get the edge between two veritces.
		@param source The source vertex
		@param dest The desitination vertex
		@return The Edge between these two vertices or 
		an edge with a weight of Double.POSITIVE_INFINITY 
		if there is no edge
	*/
	Edge getEdge(int source, int dest);

	/** Return an iterator to the edges connected to a given vertex
		@param source The source vertex
		@return An Iterator<Edge> to the vertices connected to the source

		But we're not so lucky so Iterator goes away...
	*/
	//Iterator<Edge> edgeIterator(int source);
}	 
