package assign07;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a sparse, unweighted, directed graph (a set of vertices and a set of edges). 
 * Data is of a generic type and is stored using a hashmap to allow for quick read and
 * write access.
 * 
 * @author Erin Parker && Tyler Wilcox & Andrew Tolton
 * @version March 14, 2023
 * @param Type - Generic data type
 */
public class Graph<Type> {

	// the graph -- a set of vertices (data value mapped to vertex instance)
	private HashMap<Type, Vertex<Type>> vertices;

	/**
	 * Constructs an empty graph.
	 */
	public Graph() {
		vertices = new HashMap<Type, Vertex<Type>>();
	}

	/**
	 * Adds to the graph a directed edge from the vertex with data "srcData" 
	 * to the vertex with data "dstData".  (If either vertex does not already 
	 * exist in the graph, it is added.)
	 * 
	 * @param srcData - Generic data for source vertex
	 * @param dstData - Generic data for destination vertex
	 */
	public void addEdge(Type srcData, Type dstData) {
		Vertex<Type> vertex1;
		// if vertex already exists in graph, get its object
		if(vertices.containsKey(srcData))
			vertex1 = vertices.get(srcData);
		// else, create a new object and add to graph
		else {
			vertex1 = new Vertex<Type>(srcData);
			vertices.put(srcData, vertex1);
		}

		Vertex<Type> vertex2;
		if(vertices.containsKey(dstData))
			vertex2 = vertices.get(dstData);
		else {
			vertex2 = new Vertex<Type>(dstData);
			vertices.put(dstData, vertex2);
		}

		// add new directed edge from vertex1 to vertex2
		vertex1.addEdge(vertex2);
	}
	
	/**
	 * Generates the DOT encoding of this graph as string, which can be 
	 * pasted into http://www.webgraphviz.com to produce a visualization.
	 */
	public String generateDot() {
		StringBuilder dot = new StringBuilder("digraph d {\n");
		
		// for every vertex 
		for(Vertex<Type> v : vertices.values()) {
			// for every edge
			Iterator<Edge> edges = v.edges();
			while(edges.hasNext()) 
				dot.append("\t\"" + v.getData().toString() + "\" -> \"" + edges.next() + "\"\n");
			
		}
		
		return dot.toString() + "}";
	}
	
	/**
	 * Generates a simple textual representation of this graph.
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		
		for(Vertex<Type> v : vertices.values()) 
			result.append(v.toString() + "\n");
		
		return result.toString();
	}
	
	/*
	 * Traversal Methods
	 */
	
	public List<Type> depthFirstSearch(Type srcData, Type dstData) {
		// TODO DFS
		return null; // Stub
	}
	
	public List<Type> breadthFirstSearch(Type srcData, Type dstData) {
		List<Type> path = new LinkedList<Type>();
		
		return null; // Stub
	}
	
	public List<Type> topologicalSort() {
		// TODO Topo Sort
		return null; // Stub
	}
	
	/*
	 * Helper methods
	 */
	
	private Collection<Type> recreatePath() {
		// TODO generate path
		return null; // Stub
	}
}