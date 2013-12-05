package edu.mccc.cos210.ds;
import java.util.Iterator;

/** A ArrayListGraph is a class that uses a list of lists to represent the edges
*/
public class ArrayListGraph implements Iterable, Graph{
	// Data field
	/** An list of Lists to contain the edges that originate with each vertex
		
	*/

	private ArrayList<DoublyLinkedList<Edge>> edges;

	// Data Fields
    /** The number of vertices */
    private int numV;
    /** Flag to indicate whether this is a directed graph */
    private boolean directed;
    private int size;

    private int startState;
    private int acceptState;


	public ArrayListGraph(){
		this.directed = true;
		edges = new ArrayList<DoublyLinkedList<Edge>>();
	}
	public ArrayListGraph(Edge edge){
		this.directed = true;
		edges = new ArrayList<DoublyLinkedList<Edge>>();
		this.insert(edge);
	}
	public void insert(Edge edge){ // usage: insert(new Edge(int source, int destination, char weight))
		// add the edge to the end of the list for the given source vertex

		if(edges.find(edge.getSource()) != null){
			edges.get(edge.getSource()).addLast(edge);
		}else{
			edges.add(edge.getSource(), new DoublyLinkedList<Edge>());
			edges.get(edge.getSource()).addLast(edge);
		};
		size++;
	}
	 // Accessor Methods
	public int numEdges() {

		int ne = 0;
		for(Object list : (ArrayList) edges){
			DoublyLinkedList<Edge> l = (DoublyLinkedList<Edge>) list;
			if(l != null){
				ne += l.getSize();
			}
			
		}

        return ne;
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
    /**
     * Return the number of vertices.
     * @return The number of vertices
     */
    @Override
    public int getNumV() {
        return edges.size() +1; // adding 1 for final destination vertex.
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
		// loop over edges.get(source) (DLL) => return edge if exists
		// this should be re-written back to the original using the iterator, but I don't have that kind of time.


		Edge nextItem = (Edge) edges.get(source).getFirst();
        for(int i = 0; i < edges.get(source).getSize(); i++){
            if (nextItem.equals(target)) {
                 return true;
            }
            while(edges.get(source).hasNext()){
                nextItem = (Edge) edges.get(source).getNext();
            }
        }

		//Assert: All edges for source checked. desired edge not found;
		return false;
	}

	/** Get the edge between two vertices. If an edge does not exist, 
		an Edge with a weight of Double.POSITIVE_INFINITY is returned.
		@param source The source
		@param dest The destination
		@return the edge between these two vertices
	*/
	public Edge getEdge(int source, int dest){
		//Edge target = new Edge(source, dest, new char[128]);
		Edge target = new Edge(source, dest);
		// loop over edges.get(source) (DLL) => return edge if exists


		Edge nextItem = (Edge) edges.get(source).getFirst();
        for(int i = 0; i < edges.get(source).getSize(); i++){
            if (nextItem.equals(target)) {
                 return nextItem;
            }
            while(edges.get(source).hasNext()){
                nextItem = (Edge) edges.get(source).getNext();
            }
        }

		//Assert: All edges for source checked. desired edge not found;
		return target;
	}
	public String toString(){
		StringBuilder sb = new StringBuilder(); 


		for(Object dll : edges){
			System.out.println("dll "+edges);
			Iterator<DoublyLinkedList<Edge>> iter = edges.iterator();

			while(iter.hasNext()){
				DoublyLinkedList<Edge> next = iter.next();
				System.out.println("next.toString() "+next);
			}
		}
			

		// for(Object list : edges){
		// 	for(Object e : list){
		// 		sb.append(e); 
		// 	}
		// }
		if(sb.length()>=2){sb.delete(0,2);};
		return sb.toString();
	}

	@Override
	public Iterator<Edge> iterator(){
		return new Iter();
	};
	@Override
	public Iterator<Edge> edgeIterator(int source){
		return edges.get(source).iterator();
	};

	private class Iter implements Iterator<Edge> {
        // Data Fields

        // Count of elements accessed so far */
        private int count = 0;
        private int source;

        // Methods
        // Constructor
        /**
         * Initializes the Iter object to reference the
         * first  element.
         */
        public Iter(){
        }
        public Iter(int source) {
        	this.source = source;
            count = 0;
            edges.get(source).getFirst();
        }

        /**
         * Returns true if there are more elements in the queue to access.
         * @return true if there are more elements in the queue to access.
         */
        @Override
        public boolean hasNext() {
        	System.out.println("source: "+source);
        	System.out.println("edges: "+edges.toString());
        	System.out.println("edges.get(source): "+edges.get(source));
        	System.out.println("edges.get(source).hasNext(): "+edges.get(source).hasNext());
            return edges.get(source).hasNext();

        }

        /**
         * Returns the next element in the list.
         * @pre index references the next element to access.
         * @post index and count are incremented.
         * @return The element with subscript index
         */
        @Override
        public Edge next() {
            count++;
			return edges.get(source).getNext();
            
        }

        /**
         * Remove the item accessed by the Iter object -- not implemented.
         * @throws UnsupportedOperationException when called
         */
        @Override
        public void remove() {
        	throw new UnsupportedOperationException();
        }
    }


}