package assign07;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class GraphUtilityTiming {

	public static void main (String[] args) {
		
		// areConnectedTiming
		System.out.println("areConnectedTimes:");
		var areConnectedTimes = areConnectedTiming(500, 100);
		
		// shortestPathTiming
		System.out.println("\nshortestPathTimes:");
		var shortestPathTimes = shortestPathTiming(500, 100);
		
		// topoSortTiming
		System.out.println("\ntopoSortTimes:");
		var topoSortTimes = topoSortTiming(500, 100);
		
	}
	
	public static double[] areConnectedTiming(int nStep, int timesToLoop){
				
		Random rng = new Random();
		
		int numN = 20;
		
		ArrayList<String> srcList = new ArrayList<String>();
		ArrayList<String> dstList = new ArrayList<String>();
		String src;
		String dst;
		
		double[] areConnectedTimes = new double[numN];

		for (int ii = 0; ii < numN; ii++) {
			
			int n = nStep * (ii + 1);
			
			
			
			long startTime, midTime, endTime;

			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.

			startTime = System.nanoTime();
			while(System.nanoTime() - startTime < 1000000000) { // empty block
			}
			
			// Now, run the test.
			startTime = System.nanoTime();

			for (int i = 0; i < timesToLoop; i++) {
				RandomGraphDot.generateRandomDotFile("areConnectedTiming.dot", n, 2*n);
				GraphUtility.buildListsFromDot("areConnectedTiming.dot", srcList, dstList);
				
				src = srcList.get(rng.nextInt(srcList.size()));
				dst = dstList.get(rng.nextInt(dstList.size()));
				
				GraphUtility.areConnected(srcList, dstList, src, dst);
			}
			
			midTime = System.nanoTime();
			
			// Subtract overhead code
			
			for (int i = 0; i < timesToLoop; i++) {
				RandomGraphDot.generateRandomDotFile("areConnectedTiming2.dot", n, 2*n);
				GraphUtility.buildListsFromDot("areConnectedTiming2.dot", srcList, dstList);
				
				src = srcList.get(rng.nextInt(srcList.size()));
				dst = dstList.get(rng.nextInt(dstList.size()));
			}

			endTime = System.nanoTime();
			
			areConnectedTimes[ii] = ((midTime - startTime) - (endTime - midTime)) / timesToLoop;
			System.out.println(areConnectedTimes[ii]);
		}
		
		return areConnectedTimes; // stub
	}
	
	public static double[] shortestPathTiming(int nStep, int timesToLoop){
		
		Random rng = new Random();
		
		int numN = 20;
		
		LinkedList<String> srcList;
		LinkedList<String> dstList;
		String src;
		String dst;
		
		double[] shortestPathTimes = new double[numN];

		for (int ii = 0; ii < numN; ii++) {
			
			int n = nStep * (ii + 1);
			
			long startTime, midTime, endTime;

			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.

			startTime = System.nanoTime();
			while(System.nanoTime() - startTime < 1000000000) { // empty block
			}
			
			// Now, run the test.
			startTime = System.nanoTime();

			for (int i = 0; i < timesToLoop; i++) {
				var directions = RandomGraphLists.generateRandomLists(n, 2*n);
				srcList = directions.get(0);
				dstList = directions.get(1);
				
				src = srcList.get(rng.nextInt(srcList.size()));
				dst = dstList.get(rng.nextInt(dstList.size()));
				
				
				try {
				GraphUtility.shortestPath(srcList, dstList, src, dst);
				}
				catch (IllegalArgumentException ignored) {
				}
			}
			
			midTime = System.nanoTime();
			
			// Subtract overhead code
			
			for (int i = 0; i < timesToLoop; i++) {
				var directions = RandomGraphLists.generateRandomLists(n, 2*n);
				srcList = directions.get(0);
				dstList = directions.get(1);
				
				src = srcList.get(rng.nextInt(srcList.size()));
				dst = dstList.get(rng.nextInt(dstList.size()));
				
				try {
					
				}
				catch (IllegalArgumentException ignored) {
				}
			}

			endTime = System.nanoTime();
			
			shortestPathTimes[ii] = ((midTime - startTime) - (endTime - midTime)) / timesToLoop;
			System.out.println(shortestPathTimes[ii]);
		}
		
		return shortestPathTimes; // stub
	}
	
	public static double[] topoSortTiming(int nStep, int timesToLoop){
		
		Random rng = new Random();
		
		int numN = 20;
		
		LinkedList<String> srcList;
		LinkedList<String> dstList;
		String src;
		String dst;
		
		double[] topoSortTimes = new double[numN];

		for (int ii = 0; ii < numN; ii++) {
			
			int n = nStep * (ii + 1);
			
			long startTime, midTime, endTime;

			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.

			startTime = System.nanoTime();
			while(System.nanoTime() - startTime < 1000000000) { // empty block
			}
			
			// Now, run the test.
			startTime = System.nanoTime();

			for (int i = 0; i < timesToLoop; i++) {
				var directions = RandomGraphLists.generateRandomAcyclicLists(n, 2*n);
				srcList = directions.get(0);
				dstList = directions.get(1);
				
				src = srcList.get(rng.nextInt(srcList.size()));
				dst = dstList.get(rng.nextInt(dstList.size()));
				
				GraphUtility.sort(srcList, dstList);
			}
			
			midTime = System.nanoTime();
			
			// Subtract overhead code
			
			for (int i = 0; i < timesToLoop; i++) {
				var directions = RandomGraphLists.generateRandomAcyclicLists(n, 2*n);
				srcList = directions.get(0);
				dstList = directions.get(1);
				
				src = srcList.get(rng.nextInt(srcList.size()));
				dst = dstList.get(rng.nextInt(dstList.size()));
			}

			endTime = System.nanoTime();
			
			topoSortTimes[ii] = ((midTime - startTime) - (endTime - midTime)) / timesToLoop;
			System.out.println(topoSortTimes[ii]);
		}
		
		return topoSortTimes; // stub
	}
	
	
	
	
}
