package assign04;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import assign03.SPQ2;

/**
 * This class contains methods to perform operations on arrays of integers,
 * calculating the biggest possible number that can be assembled from the integers in the provided arrays.
 * 
 * @author Andrew Tolton and Tyler Wilcox
 * @version February 07, 2023
 *
 */

public class LNSAnalysis {

	public static void main(String[] args) {
		int length, loopTimes;
		Integer[] randomInts;
		long[] insertionTimes = new long[10];
		long[] sortTimes = new long[10];
		
		long startTime, midTime, endTime;
		
		loopTimes = 500;
		
		// Generate random integer list
		for (int i = 0; i < 10; i++) {
			// Loop index checker to make sure it's running
			length = (1000*(i+1));
			System.out.println("Length = " + length);
			
			List<Integer[]> testList = new ArrayList<Integer[]>(length);
			
			for (int j = 0; j < length; j++) {
				randomInts = new Integer[3];
				
				for (int k = 0; k < 3; k++) {
					randomInts[k] = (int)(Math.random()*length);
				}
				testList.add(randomInts);
			}
			// Pause operations for one second
			startTime = System.nanoTime();
			while(System.nanoTime() - startTime < 1000000000) {}
			
			// Insertion Sort timing
			startTime = System.nanoTime();
			
			for (int j = 0; j < loopTimes; j++) {
				//LargestNumberSolver.findKthLargest(testList, 0);
			}
			
			// Account for loop timing
			midTime = System.nanoTime();
			for (int j = 0; j < loopTimes; j++) {}
			endTime = System.nanoTime();
			
			// Save times
			insertionTimes[i] = ((midTime - startTime) - (endTime - midTime))/loopTimes;
			
			// Pause operations for one second
			startTime = System.nanoTime();
			while(System.nanoTime() - startTime < 1000000000) {}
			
			// Quicksort timing
			startTime = System.nanoTime();
			for (int j = 0; j < loopTimes; j++) {
				LargestNumberSolver.findKthLargestSort(testList, 0);
			}
			// Account for empty loop timing
			midTime = System.nanoTime();
			for (int j = 0; j < loopTimes; j++) {}
			endTime = System.nanoTime();
			
			// Save times
			sortTimes[i] = ((midTime - startTime) - (endTime - midTime))/loopTimes;
		}
		
		try (FileWriter writer = new FileWriter(("dat.txt")))
		{
			PrintWriter pw = new PrintWriter(writer);
			pw.flush();
			pw.println("Number of Loops = " + loopTimes);
			pw.println();
			pw.println("Insert");
			pw.println();
			pw.println("length  IS (ns)  quicksort (ns)");
			for (int i = 0; i < 10; i++) {
				pw.print((i+1)*500 + ", " + insertionTimes[i]);
				pw.println(" , " + sortTimes[i]);
			}
			
		}
		catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
}
