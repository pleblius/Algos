package assign07;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Random;

public class RandomGraph {
	  
	  public static LinkedList<LinkedList<String>> generateRandomSimple(int vertexCount) {
		    Random rng = new Random();
		    LinkedList<String> srcList = new LinkedList<String>();
		    LinkedList<String> dstList = new LinkedList<String>();

		    // generate a list of vertices
		    String[] vertex = new String[vertexCount];
		    for(int i = 0; i < vertexCount; i++)
		      vertex[i] = "v" + i;

		    // randomly connect the vertices using |V| - |V|^2 edges
		    int numEdges = rng.nextInt(vertexCount, vertexCount * vertexCount);
		    
		    for(int i = 0; i < numEdges; i++) {		
		      srcList.add(vertex[rng.nextInt(vertexCount)]);	
		      dstList.add(vertex[rng.nextInt(vertexCount)]);
		      
		    }
		    
		    LinkedList<LinkedList<String>> directions = new LinkedList<LinkedList<String>>();
		    directions.add(srcList);
		    directions.add(dstList);
		    
		    return directions;

	  }
	  
	  public static LinkedList<LinkedList<String>> generateRandomAcyclic(int vertexCount) {
		    Random rng = new Random();
		    LinkedList<String> srcList = new LinkedList<String>();
		    LinkedList<String> dstList = new LinkedList<String>();

		    // generate a list of vertices
		    String[] vertex = new String[vertexCount];
		    for(int i = 0; i < vertexCount; i++)
		      vertex[i] = "v" + i;
		    
		    for(int i = 0; i < vertexCount - 1; i++) {		
		      srcList.add(vertex[i]);	
		      dstList.add(vertex[i + 1 + rng.nextInt(vertexCount - (i + 1))]);
		      
		    }
		
		    LinkedList<LinkedList<String>> directions = new LinkedList<LinkedList<String>>();
		    directions.add(srcList);
		    directions.add(dstList);
		    
		    return directions;
	  }
	  

	
}
