package assign07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Contains several methods for solving problems on generic, directed, unweighted, sparse graphs.
 * 
 * @author Erin Parker & Tyler Wilcox
 * @version March 14, 2022
 */
public class GraphUtility {

	/**
	 * Checks if there is a valid path from the data value srcData to the data value
	 * dstData contained within the data-set indicated by the source and
	 * destination lists.
	 * </br>
	 * Source and destination lists must be of the same type and must contain the
	 * respective data parameters srcData and dstData.
	 * 
	 * @param <Type> Generic data type of source and destination lists.
	 * @param sources The generic list containing the source data of each source-destination pair.
	 * @param destinations The generic list containing the destination data of each source-destination pair.
	 * @param srcData The data value representing the "start-point" of the path in question.
	 * @param dstData The data value representing the "end-point" of the path in question.
	 * @return True if a valid path exists from srcData to dstData; false otherwise.
	 * @throws IllegalArgumentException - If the source and destination list are different sizes, or if the data points
	 * srcData or dstData are not contained in their respective lists.
	 */
	public static <Type> boolean areConnected(List<Type> sources, List<Type> destinations, Type srcData, Type dstData)
			throws IllegalArgumentException {
		// Check for illegal arguments
		if (sources.size() != destinations.size())
			throw new IllegalArgumentException("Input lists are incompatible.");
		if (!sources.contains(srcData))
			throw new IllegalArgumentException("Source list does not contain indicated source data.");
		if (!destinations.contains(dstData))
			throw new IllegalArgumentException("Destination list does not contain indicated destination data.");
		
		// Generate graph
		Graph<Type> graph = generateGraph(sources,destinations);
		
		// Return result
		return false;
	}
	
	/**
	 * Finds the shortest valid path in the data-set indicated by the source and destination
	 * lists from the source point, srcData, and the destination point, dstData. The shortest
	 * path may not be a unique data set, so there may be other paths of equal length that
	 * are not returned by this method.
	 * </br>
	 * The source and destination lists must be of the same size, srcData and dstData
	 * must be contained in their respective lists, and their must be a valid path that exists
	 * in the data set between srcData and dstData. (The GraphUtility.areConnected() can verify
	 * if a valid path exists before calling this method.)
	 * 
	 * @param <Type> Generic data type of source and destination lists.
	 * @param sources The generic list containing the source data of each source-destination pair.
	 * @param destinations The generic list containing the destination data of each source-destination pair.
	 * @param srcData The data value representing the "start-point" of the path in question.
	 * @param dstData The data value representing the "end-point" of the path in question.
	 * @return A list containing the ordered sequence of data values that corresponds to the shortest path from srcData to
	 * dstData.
	 * @throws IllegalArgumentException - If the source and destination list are different sizes, if the data points
	 * srcData or dstData are not contained in their respective lists, or if there is no valid path
	 * from srcData to dstData.
	 */
	public static <Type> List<Type> shortestPath(List<Type> sources, List<Type> destinations, Type srcData, Type dstData)
			throws IllegalArgumentException {
		// Check for illegal arguments
		if (sources.size() != destinations.size())
			throw new IllegalArgumentException("Input lists are incompatible.");
		if (!sources.contains(srcData))
			throw new IllegalArgumentException("Source list does not contain indicated source data.");
		if (!destinations.contains(dstData))
			throw new IllegalArgumentException("Destination list does not contain indicated destination data.");
		
		// Generate Graph
		Graph<Type> graph = generateGraph(sources, destinations);
		
		List<Type> path = graph.breadthFirstSearch(srcData, dstData);
		
		if (path.size() == 0)
			throw new IllegalArgumentException("No valid path exists for these data points.");
		else
			return path;
	}
	
	/**
	 * Generates a sorted dependency list based on the data-set indicated by the source
	 * and destination lists. The first item will have zero dependencies, and all dependent
	 * data elements will only be included after all of their dependencies have been included.
	 * </br>This method returns one such sorted list, although there may be other possible lists
	 * that could be generated from the data-set.
	 * </br>The data set must not contain any cyclic paths, and the source and destination
	 * lists must be of the same size.
	 * 
	 * @param <Type> Generic data type of the source and destination lists.
	 * @param sources The generic list containing the source data of each source-destination pair.
	 * @param destinations The generic list containing the destination data of each source-destination pair.
	 * @return A sorted dependency list based on the provided data-set.
	 * @throws IllegalArgumentException - If the source and destination list are of different
	 * sizes, or if the data-set contains a cycle.
	 */
	public static <Type> List<Type> sort(List<Type> sources, List<Type> destinations) throws IllegalArgumentException {
		// Check for illegal arguments
		if (sources.size() != destinations.size())
			throw new IllegalArgumentException("Input lists are incompatible.");
		
		// Run topological sort
		
		// Generate list
		return null;
	}

	/**
	 * Builds "sources" and "destinations" lists according to the edges
	 * specified in the given DOT file (e.g., "a -> b"). Assumes that the vertex
	 * data type is String.
	 * 
	 * Accepts many valid "digraph" DOT files (see examples posted on Canvas).
	 * --accepts \\-style comments 
	 * --accepts one edge per line or edges terminated with ; 
	 * --does not accept attributes in [] (e.g., [label = "a label"])
	 * 
	 * @param filename - name of the DOT file
	 * @param sources - empty ArrayList, when method returns it is a valid
	 *        "sources" list that can be passed to the public methods in this
	 *        class
	 * @param destinations - empty ArrayList, when method returns it is a valid
	 *        "destinations" list that can be passed to the public methods in
	 *        this class
	 */
	public static void buildListsFromDot(String filename, ArrayList<String> sources, ArrayList<String> destinations) {

		Scanner scan = null;
		try {
			scan = new Scanner(new File(filename));
		}
		catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}

		scan.useDelimiter(";|\n");

		// Determine if graph is directed (i.e., look for "digraph id {").
		String line = "", edgeOp = "";
		while(scan.hasNext()) {
			line = scan.next();

			// Skip //-style comments.
			line = line.replaceFirst("//.*", "");

			if(line.indexOf("digraph") >= 0) {
				edgeOp = "->";
				line = line.replaceFirst(".*\\{", "");
				break;
			}
		}
		if(edgeOp.equals("")) {
			System.out.println("DOT graph must be directed (i.e., digraph).");
			scan.close();
			System.exit(0);

		}

		// Look for edge operator -> and determine the source and destination
		// vertices for each edge.
		while(scan.hasNext()) {
			String[] substring = line.split(edgeOp);

			for(int i = 0; i < substring.length - 1; i += 2) {
				// remove " and trim whitespace from node string on the left
				String vertex1 = substring[0].replace("\"", "").trim();
				// if string is empty, try again
				if(vertex1.equals(""))
					continue;

				// do the same for the node string on the right
				String vertex2 = substring[1].replace("\"", "").trim();
				if(vertex2.equals(""))
					continue;

				// indicate edge between vertex1 and vertex2
				sources.add(vertex1);
				destinations.add(vertex2);
			}

			// do until the "}" has been read
			if(substring[substring.length - 1].indexOf("}") >= 0)
				break;

			line = scan.next();

			// Skip //-style comments.
			line = line.replaceFirst("//.*", "");
		}

		scan.close();
	}
	
	/*
	 * Helper Methods
	 */
	
	private static <Type> Graph<Type> generateGraph(List<Type> sources, List<Type> destinations) {
		Graph<Type> graph = new Graph<Type>();
		
		for (int i = 0; i < sources.size(); i++) {
			graph.addEdge(sources.get(i), destinations.get(i));
		}
		
		return graph;
	}
}
