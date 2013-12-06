package edu.mccc.cos210.ds;
 
 /** An Edge represents a relationship between two 
 * vertices. 
 */ 
 public class Edge{ 
	 // Data Fields 
	 
	 /** The source vertex */ 
	 private int source; 
	 /** The destination vertex */ 
	 private int dest; 

	 private boolean epsilon;

	 /** The weight */ 
	 //private double weight; 

	 private char weight; 
	 
	 // Constructor 
	 /** epsilon edge */ 
	 public Edge(int source, int dest) { 
		 this.source = source; 
		 this.dest = dest; 
		 this.epsilon = true;
		 //weight = new Character();
		// weight = new char[128];  // the ASCII set we're working with.
	 } 
	 
	 /** Construct a weighted edge with a source 
	 * of from and a destination of to. Set the 
	 * weight to w. 
	 * @param from - The source vertex 
	 * @param to - The destination vertex 
	 * @param w - The weight 
	 */ 
	 public Edge(int source, int dest, char w) { 
		 this.source = source; 
		 this.dest = dest; 
		 this.weight = w; 
	 } 
	 
	 /** epsilon edge */
	 public Edge(int source, int dest, boolean e) { 
		 this.source = source; 
		 this.dest = dest; 
		 this.epsilon = e; 
	 } 

	 // Methods 
	 /** Get the source 
	 * @return The value of source 
	 */ 
	 public int getSource() { 
		return source; 
	 } 
	 
	 /** Get the destination 
	 * @return The value of dest 
	 */ 
	 public int getDest() { 
		return dest; 
	 } 
	 
	 /** Get the weight 
	 * @return the value of weight 
	 */ 
	 public char getWeight() { 
		return weight; 
	 } 
	 
	 /** Get the weight 
	 * @return the value of weight 
	 */ 
	 public boolean isEpsilon() { 
		return this.epsilon; 
	 } 


	 /** Return a String representation of the edge 
	 * @return A String representation of the edge 
	 */ 
	 @Override 
	 public String toString() { 
		 StringBuilder sb = new StringBuilder(", [("); 
		 sb.append(Integer.toString(source)); 
		 sb.append(", "); 
		 sb.append(Integer.toString(dest)); 
		 sb.append("): "); 
		 sb.append(String.valueOf(weight)); 
		 sb.append("]"); 

		

		 return sb.toString(); 
	 } 
	 
	
	 /** Return true if two edges are equal. Edges 
	 * are equal if the source and destination Programming Exercise Solutions Page 51 of 64
	 * are equal. Weight is not conidered.
	 * @param obj The object to compare to 
	 * @return true if the edges have the same source 
	 * and destination 
	 */ 
	 @Override 
	 public boolean equals(Object obj) { 
		 if (obj instanceof Edge) { 
			 Edge edge = (Edge) obj; 
			 return (source == edge.source && dest == edge.dest); 
		} else { 
			return false; 
		} 
	 } 
	 
	 public boolean contains(char c){
	 	return (c == weight);
	 }

	 /** Return a hash code for an edge. The hash 
	 * code is the source shifted left 16 bits 
	 * exclusive or with the dest 
	 * @return a hash code for an edge 
	 */ 
	 @Override 
	 public int hashCode() { 
		return (source << 16) ^ dest; 
	 } 
} 
