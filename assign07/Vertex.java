package assign07;

import java.util.LinkedList;
import java.util.Iterator;

/**
 * This class represents a vertex (AKA node) in a directed graph, with generic data type.
 * 
 * @author Erin Parker && Tyler Wilcox
 * @version March 14, 2023
 * @param Type - generic data type
 */
public class Vertex<Type> {

	// used to id the Vertex
	private Type data;

	// adjacency list
	private LinkedList<Edge> adj;

	/**
	 * Creates a new Vertex object containing the given data.
	 * 
	 * @param data - generic data stored in this vertex.
	 */
	public Vertex(Type data) {
		this.data = data;
		this.adj = new LinkedList<Edge>();
	}

	/**
	 * @return the data value stored in this vertex.
	 */
	public Type getData() {
		return data;
	}

	/**
	 * Adds a directed edge from this Vertex to another.
	 * 
	 * @param otherVertex - the Vertex object that is the destination of the edge
	 */
	public void addEdge(Vertex<Type> otherVertex) {
		adj.add(new Edge(otherVertex));
	}

	/**
	 * @return a iterator for accessing the edges for which this Vertex is the source
	 */
	public Iterator<Edge> edges() {
		return adj.iterator();
	}

	/**
	 * Generates and returns a textual representation of this Vertex.
	 */
	public String toString() {
		String s = "Vertex " + data.toString() + " adjacent to vertices ";
		Iterator<Edge> itr = adj.iterator();
		while(itr.hasNext())
			s += itr.next().toString() + "  ";
		return s;
	}
}