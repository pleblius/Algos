package assign07;

/**
 * This class represents an edge between a source vertex and a destination
 * vertex in a directed graph.
 * 
 * The source of this edge is the Vertex whose object has an adjacency list
 * containing this edge.
 * 
 * @author Erin Parker && Tyler Wilcox & Andrew Tolton
 * @version March 14, 2023
 */
public class Edge {

	// destination of this directed edge
	private Vertex<?> dst;

	/**
	 * Creates an Edge object, given the Vertex that is the destination.
	 * (The Vertex that stores this Edge object is the source.)
	 * 
	 * @param dst - the destination Vertex
	 */
	public Edge(Vertex<?> dst) {
		this.dst = dst;
	}

	/**
	 * @return the destination Vertex of this Edge
	 */
	public Vertex<?> getOtherVertex() {
		return this.dst;
	}

	/**
	 * Returns the string representation of the data in the destination Vertex 
	 * as a textual representation of this Edge.
	 */
	public String toString() {
		return this.dst.getData().toString();
	}
}