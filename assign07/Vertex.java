package assign07;

import java.util.LinkedList;
import java.util.Iterator;

/**
 * This class represents a vertex (AKA node) in a directed graph, with generic data type.
 * 
 * @author Erin Parker && Tyler Wilcox & Andrew Tolton
 * @version March 14, 2023
 * @param Type - generic data type
 */
public class Vertex<Type> {

	// used to id the Vertex
	private Type data;
	
	private boolean isVisited;
	private int degree;
	private Vertex<Type> cameFrom;

	// adjacency list
	private LinkedList<Edge<Type>> adj;

	/**
	 * Creates a new Vertex object containing the given data.
	 * 
	 * @param data - generic data stored in this vertex.
	 */
	public Vertex(Type data) {
		this.data = data;
		this.adj = new LinkedList<Edge<Type>>();
		
		this.isVisited = false;
		this.degree = 0;
		this.cameFrom = null;
	}

	/**
	 * @return the data value stored in this vertex.
	 */
	public Type getData() {
		return data;
	}
	
	/**
	 * @return whether the vertex has been visited
	 */
	public boolean visited() {
		return isVisited;
	}
	/**
	 * @return the vertex's in degree
	 */
	public int getDegree() {
		return degree;
	}
	/**
	 * @return the vertex that this vertex was traversed from
	 */
	public Vertex<Type> getCameFrom() {
		return cameFrom;
	}
	
	/**
	 * Sets the boolean isVisited flag
	 * @param flag to set isVisited to
	 */
	public void setVisited(boolean flag) {
		isVisited = flag;
	}
	/**
	 * Increments vertex degree by 1
	 */
	public void incrementDegree() {
		degree++;
	}
	
	/**
	 * Decrements vertex degree by 1
	 */
	public void decrementDegree() {
		degree--;
	}
	
	/**
	 * Sets the vertex this was traversed from to the parameter vertex
	 * 
	 * @param vertex this vertex came from
	 */
	public void setCameFrom(Vertex<Type> vertex) {
		cameFrom = vertex;
	}

	/**
	 * Adds a directed edge from this Vertex to another.
	 * 
	 * @param otherVertex - the Vertex object that is the destination of the edge
	 */
	public void addEdge(Vertex<Type> otherVertex) {
		adj.add(new Edge<Type>(otherVertex));
	}

	/**
	 * @return a iterator for accessing the edges for which this Vertex is the source
	 */
	public Iterator<Edge<Type>> edges() {
		return adj.iterator();
	}

	/**
	 * Generates and returns a textual representation of this Vertex.
	 */
	public String toString() {
		String s = "Vertex " + data.toString() + " adjacent to vertices ";
		Iterator<Edge<Type>> itr = adj.iterator();
		while(itr.hasNext())
			s += itr.next().toString() + "  ";
		return s;
	}
}