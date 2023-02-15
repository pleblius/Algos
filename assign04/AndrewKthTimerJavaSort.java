package assign04;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

import assign03.SortedSimplePriorityQueue;

/**
 * This program determines the runtimes of the findMax and insert methods
 * of the SimplePriorityQueue class for assignment 03
 * 
 * @using TimingExperiment08 by Erin Parker, Ver. Jan 21 2022
 * @using collect_running_times from Assignment 02
 * 
 * @author Andrew Tolton
 * @version January 21, 2022
 */
public class AndrewKthTimerJavaSort {
	public static void main(String[] args) {
		
		// Setting up queues
		
		Random rng = new Random();
		Integer rand;
		
		int timesToLoop = 1000;
		
		// For each problem of size n
		for (int n = 1000; n <= 50000; n += 2000) {
			
			// Generate a new list Integer arrays
			
			List<Integer[]> list = new ArrayList<Integer[]>(n);
			Random intGen = new Random();
			
			for (int ii = 0; ii < n; ii++) {
				
				int intArraySize = intGen.nextInt(1, 10);
				Integer[] arr = new Integer[intArraySize];
				
				for (int jj = 0; jj < intArraySize; jj++) {
					arr[jj] = Math.abs(intGen.nextInt());
				}
				
				list.add(arr);
			}

			
			long startTime, queueTime, midTime, insertTime, stopTime;

			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.

			startTime = System.nanoTime();
			while(System.nanoTime() - startTime < 1000000000) { // empty block
			}
			
			
			// Now, run the test.
			startTime = System.nanoTime();

			for (int i = 0; i < timesToLoop; i++) {
				int k = intGen.nextInt(0, n - 1);
				LargestNumberSolverJavaSort.findKthLargest(list, k);
			}
			
			midTime = System.nanoTime();

			// Subtract overhead code
			
			for (int i = 0; i < timesToLoop; i++) {
				int k = intGen.nextInt(0, n - 1);
			}
			
			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and computing square roots.
			// Average it over the number of runs.

			double averageFindKthNumber = ((midTime - startTime) - (stopTime - midTime)) / timesToLoop;

			System.out.print("For problem size " + n);
			System.out.println(", findKth: " + averageFindKthNumber + " ns");
		}
		
		
	}
}