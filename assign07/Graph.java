package assign07;

import java.util.ArrayDeque;
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
		vertex2.incrementDegree();
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
			Iterator<Edge<Type>> edges = v.edges();
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
		for (Vertex<Type> v : vertices.values()) {
			v.setVisited(false);
			v.setCameFrom(null);
		}
		
		return DFS(srcData, dstData, new LinkedList<Type>());
	}
	
	private List<Type> DFS(Type srcData, Type dstData, List<Type> path) {
		Iterator<Edge<Type>> iter;
		Vertex<Type> neighbor;
		
		if (srcData.equals(dstData)) {
			path.add(srcData);
			
			return path;
		}
		else {
			iter = vertices.get(srcData).edges();
			
			while (iter.hasNext()) {
				neighbor = iter.next().getOtherVertex();
				
				if (!neighbor.visited()) {
					neighbor.setVisited(true);
					
					path = DFS(neighbor.getData(), dstData, path);
					
					if (!path.isEmpty()) {
						path.add(0,srcData);
						
						return path;
					}
				}
			}
		}
		
		return path;
	}
	
	public List<Type> breadthFirstSearch(Type srcData, Type dstData) {
		ArrayDeque<Vertex<Type>> frontier = new ArrayDeque<Vertex<Type>>();
		Iterator<Edge<Type>> iter;
		
		for (Vertex<Type> v : vertices.values()) {
			v.setVisited(false);
			v.setCameFrom(null);
		}
		
		frontier.add(vertices.get(srcData));
		
		while (!frontier.isEmpty()) {
			Vertex<Type> n = frontier.remove();
			
			n.setVisited(true);
			
			if (n.getData().equals(dstData)) {
				return recreatePath(n);
			}
			
			iter = n.edges();
			while(iter.hasNext()) {
				Vertex<Type> neighbor = iter.next().getOtherVertex();
				
				if (!neighbor.visited()) {
					neighbor.setCameFrom(n);
					neighbor.setVisited(true);
					frontier.add(neighbor);
				}
			}
	
		}

		return recreatePath(null);	
	}
	
	public List<Type> topologicalSort(List<Type> srcList, List<Type> dstList) {
		LinkedList<Vertex<Type>> reachableVertices = new LinkedList<Vertex<Type>>();
		LinkedList<Type> sortedList = new LinkedList<Type>();
		
		Iterator<Edge<Type>> iter;
		Vertex<Type> neighbor;
		Vertex<Type> n;
		
		for (Vertex<Type> v : vertices.values()) {
			if (v.getDegree() == 0) {
				reachableVertices.add(v);
			}
		}
		
		while(!reachableVertices.isEmpty()) {
			n = reachableVertices.pop();
			sortedList.add(n.getData());
			
			iter = n.edges();
			while(iter.hasNext()) {
				neighbor = iter.next().getOtherVertex();
				
				neighbor.decrementDegree();
				if (neighbor.getDegree() == 0 ) {
					reachableVertices.add(neighbor);
				}
				
			}
		}
		
		if (sortedList.size() != vertices.values().size())
			throw new IllegalArgumentException("This is not an Acyclic path.");
		return sortedList; // Stub
	}
	
	/*
	 * Helper methods
	 */
	
	private List<Type> recreatePath(Vertex<Type> v) {
		List<Type> path = new LinkedList<Type>();
		
		while (v != null) {
			path.add(0, v.getData());
			
			v = v.getCameFrom();
		}
		return path;
	}
}