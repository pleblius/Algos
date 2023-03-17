package assign07;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Random;

public class RandomGraphLists {
	  
	  public static LinkedList<LinkedList<String>> generateRandomLists(int vertexCount, int edgeCount) {
		    Random rng = new Random();
		    LinkedList<String> srcList = new LinkedList<String>();
		    LinkedList<String> dstList = new LinkedList<String>();

		    // generate a list of vertices
		    String[] vertex = new String[vertexCount];
		    for(int i = 0; i < vertexCount; i++)
		      vertex[i] = "v" + i;
		    
		    for(int i = 0; i < edgeCount; i++) {		
		      srcList.add(vertex[rng.nextInt(vertexCount)]);	
		      dstList.add(vertex[rng.nextInt(vertexCount)]);
		      
		    }
		    
		    LinkedList<LinkedList<String>> directions = new LinkedList<LinkedList<String>>();
		    directions.add(srcList);
		    directions.add(dstList);
		    
		    return directions;

	  }
	  
	  
	  public static LinkedList<LinkedList<String>> generateRandomAcyclicLists(int vertexCount, int edgeCount) {
		    Random rng = new Random();
		    LinkedList<String> srcList = new LinkedList<String>();
		    LinkedList<String> dstList = new LinkedList<String>();
		    int srcVertex;

		    // generate a list of vertices
		    String[] vertex = new String[vertexCount];
		    for(int i = 0; i < vertexCount; i++)
		      vertex[i] = "v" + i;
		    
		    for(int i = 0; i < edgeCount; i++) {
		    	
		      srcVertex = rng.nextInt(0, vertexCount - 1);
		    	
		      srcList.add(vertex[srcVertex]);	
		      dstList.add(vertex[srcVertex + rng.nextInt(1, vertexCount - srcVertex)]);
		      
		    }
		
		    LinkedList<LinkedList<String>> directions = new LinkedList<LinkedList<String>>();
		    directions.add(srcList);
		    directions.add(dstList);
		    
		    return directions;
	  }
	  
	  public static LinkedList<LinkedList<String>> generateRandomCyclicLists(int vertexCount) {
		  
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
		    
		    for (String v : dstList) {
		    	if (!srcList.contains(v)) { // if is a leaf node
		    		srcList.add(v);
		    		dstList.add("v0");
		    	}
		    }
		
		    LinkedList<LinkedList<String>> directions = new LinkedList<LinkedList<String>>();
		    directions.add(srcList);
		    directions.add(dstList);
		    
		    return directions;
	 }
	  

	
}
