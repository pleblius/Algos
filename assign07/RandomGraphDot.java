package assign07;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class RandomGraphDot {

	  public static void generateRandomDotFile(String filename, int vertexCount) {
		    PrintWriter out = null;
		    try {
		      out = new PrintWriter(filename);
		    } 
		    catch (IOException e) {
		      System.out.println(e);
		    }

		    Random rng = new Random();

		    // randomly construct either a graph or a digraph
		    String edgeOp = "--";
		    if (rng.nextBoolean()) {
		      out.print("di");
		      edgeOp = "->";
		    }
		    out.println("graph G {");

		    // generate a list of vertices
		    String[] vertex = new String[vertexCount];
		    for(int i = 0; i < vertexCount; i++)
		      vertex[i] = "v" + i;

		    // randomly connect the vertices using |V| - |V|^2 edges
		    int numEdges = rng.nextInt(vertexCount, vertexCount * vertexCount);
		    
		    for(int i = 0; i < numEdges; i++)
		      out.println("\t" + vertex[rng.nextInt(vertexCount)] + edgeOp + vertex[rng.nextInt(vertexCount)]);

		    out.println("}");
		    out.close();
		  }
	  
	  public static void generateRandomSimpleDotFile(String filename, int vertexCount) {
		    PrintWriter out = null;
		    try {
		      out = new PrintWriter(filename);
		    } 
		    catch (IOException e) {
		      System.out.println(e);
		    }

		    Random rng = new Random();

		    // randomly construct either a graph or a digraph
		    String edgeOp = "--";
		    if (rng.nextBoolean()) {
		      out.print("di");
		      edgeOp = "->";
		    }
		    out.println("graph G {");

		    // generate a list of vertices
		    String[] vertex = new String[vertexCount];
		    for(int i = 0; i < vertexCount; i++)
		      vertex[i] = "v" + i;

		    // randomly connect the vertices using |V| - |V|^2 edges
		    int numEdges = rng.nextInt(vertexCount, vertexCount * vertexCount);
		    
		    for(int i = 0; i < numEdges; i++) {		
		      int vertexInd1;	
		    	
		      out.println("\t" + vertex[rng.nextInt(vertexCount)] + edgeOp + vertex[rng.nextInt(vertexCount)]);
		    }

		    out.println("}");
		    out.close();
		  }
	  
	  public static void generateRandomAcyclicDotFile(String filename, int vertexCount) {
		    PrintWriter out = null;
		    try {
		      out = new PrintWriter(filename);
		    } 
		    catch (IOException e) {
		      System.out.println(e);
		    }

		    Random rng = new Random();

		    // randomly construct either a graph or a digraph
		    String edgeOp = "--";
		    if (rng.nextBoolean()) {
		      out.print("di");
		      edgeOp = "->";
		    }
		    out.println("graph G {");

		    // generate a list of vertices
		    String[] vertex = new String[vertexCount];
		    for(int i = 0; i < vertexCount; i++)
		      vertex[i] = "v" + i;

		    // randomly connect the vertices using |V| - |V|^2 edges
//		    int numEdges = rng.nextInt(vertexCount, vertexCount * vertexCount);
		    
		    for(int i = 0; i < vertexCount - 1; i++)
		    	  out.println("\t" + vertex[i] + "->" + vertex[i + 1 + rng.nextInt(vertexCount - (i + 1))]);

		    out.println("}");
		    out.close();
		  }
	  
	  public static void generateRandomDirectedAcyclicDotFile(String filename, int vertexCount) {
		    PrintWriter out = null;
		    try {
		      out = new PrintWriter(filename);
		    } 
		    catch (IOException e) {
		      System.out.println(e);
		    }

		    Random rng = new Random();

		    // randomly construct either a graph or a digraph

		    out.print("digraph G {");
		    String edgeOp = "->";

		    // generate a list of vertices
		    String[] vertex = new String[vertexCount];
		    for(int i = 0; i < vertexCount; i++)
		      vertex[i] = "v" + i;

		    // randomly connect the vertices using |V| - |V|^2 edges
//		    int numEdges = rng.nextInt(vertexCount, vertexCount * vertexCount);
		    
		    for(int i = 0; i < vertexCount - 1; i++)
		    	  out.println("\t" + vertex[i] + "->" + vertex[i + 1 + rng.nextInt(vertexCount - (i + 1))]);

		    out.println("}");
		    out.close();
		  }
	
}
