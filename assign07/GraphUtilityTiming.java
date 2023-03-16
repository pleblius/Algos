package assign07;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class GraphUtilityTiming {

	public static void main (String[] args) {
		
		// areConnectedTiming
		var areConnectedTimes = areConnectedTiming();
		
		System.out.println("areConnectedTimes:");
		for (int ii = 0; ii < areConnectedTimes.length; ii++) {
			System.out.printf("%10f\n", areConnectedTimes[ii]);
		}
		
		// shortestPathTiming
		var shortestPathTimes = shortestPathTiming();
		
		System.out.println("\nshortestPathTimes:");
		for (int ii = 0; ii < shortestPathTimes.length; ii++) {
			System.out.printf("%10f\n", shortestPathTimes[ii]);
		}
		
		// topoSortTiming
		var topoSortTimes = topoSortTiming();
		
		System.out.println("\ntopoSortTimes:");
		for (int ii = 0; ii < topoSortTimes.length; ii++) {
			System.out.printf("%10f\n", topoSortTimes[ii]);
		}
		
	}
	
	public static double[] areConnectedTiming(){
				
		Random rng = new Random();
		
		int timesToLoop = 10;		
		int nStep = 100;
		int numN = 20;
		
		LinkedList<String> srcList;
		LinkedList<String> dstList;
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
				var directions = RandomGraphLists.generateRandomLists(n, 2*n);
				srcList = directions.get(0);
				dstList = directions.get(1);
				
				src = srcList.get(rng.nextInt(srcList.size()));
				dst = dstList.get(rng.nextInt(dstList.size()));
				
				GraphUtility.areConnected(srcList, dstList, src, dst);
			}
			
			midTime = System.nanoTime();
			
			// Subtract overhead code
			
			for (int i = 0; i < timesToLoop; i++) {
				var directions = RandomGraphLists.generateRandomLists(n, 2*n);
				srcList = directions.get(0);
				dstList = directions.get(1);
				
				src = srcList.get(rng.nextInt(srcList.size()));
				dst = dstList.get(rng.nextInt(dstList.size()));
			}

			endTime = System.nanoTime();
			
			areConnectedTimes[ii] = ((midTime - startTime) - (endTime - midTime)) / timesToLoop;
		}
		
		return areConnectedTimes; // stub
	}
	
	public static double[] shortestPathTiming(){
		
		Random rng = new Random();
		
		int timesToLoop = 10;		
		int nStep = 100;
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
				var directions = RandomGraphLists.generateRandomAcyclicLists(n);
				srcList = directions.get(0);
				dstList = directions.get(1);
				
				src = srcList.get(rng.nextInt(srcList.size()));
				dst = dstList.get(rng.nextInt(dstList.size()));
				
				GraphUtility.shortestPath(srcList, dstList, src, dst);
			}
			
			midTime = System.nanoTime();
			
			// Subtract overhead code
			
			for (int i = 0; i < timesToLoop; i++) {
				var directions = RandomGraphLists.generateRandomAcyclicLists(n);
				srcList = directions.get(0);
				dstList = directions.get(1);
				
				src = srcList.get(rng.nextInt(srcList.size()));
				dst = dstList.get(rng.nextInt(dstList.size()));
			}

			endTime = System.nanoTime();
			
			shortestPathTimes[ii] = ((midTime - startTime) - (endTime - midTime)) / timesToLoop;
		}
		
		return shortestPathTimes; // stub
	}
	
	public static double[] topoSortTiming(){
		
		Random rng = new Random();
		
		int timesToLoop = 10;		
		int nStep = 100;
		int numN = 20;
		
		LinkedList<String> srcList;
		LinkedList<String> dstList;
		String src;
		String dst;
		
		double[] topoSortTiming = new double[numN];

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
				var directions = RandomGraphLists.generateRandomAcyclicLists(n);
				srcList = directions.get(0);
				dstList = directions.get(1);
				
				src = srcList.get(rng.nextInt(srcList.size()));
				dst = dstList.get(rng.nextInt(dstList.size()));
				
				GraphUtility.shortestPath(srcList, dstList, src, dst);
			}
			
			midTime = System.nanoTime();
			
			// Subtract overhead code
			
			for (int i = 0; i < timesToLoop; i++) {
				var directions = RandomGraphLists.generateRandomAcyclicLists(n);
				srcList = directions.get(0);
				dstList = directions.get(1);
				
				src = srcList.get(rng.nextInt(srcList.size()));
				dst = dstList.get(rng.nextInt(dstList.size()));
			}

			endTime = System.nanoTime();
			
			topoSortTiming[ii] = ((midTime - startTime) - (endTime - midTime)) / timesToLoop;
		}
		
		return topoSortTiming; // stub
	}
	
	
	
	
}
