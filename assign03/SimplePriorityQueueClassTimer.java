package assign03;

import java.util.Random;

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
public class SimplePriorityQueueClassTimer {
	public static void main(String[] args) {
		
		// Setting up queues
		
		Random rng = new Random();
		Integer rand;
		
		int timesToLoop = 10000;
		
		// For each problem of size n
		for (int n = 100_000; n <= 2_000_000; n += 100_000) {
			
			// Generate a new queue of Integers
			Integer [] ints = new Integer[n];
			for (int i = 0; i < n; i++) {
				ints[i] = i;
			}
			
			long startTime, queueTime, midTime, insertTime, stopTime;

			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.

			startTime = System.nanoTime();
			while(System.nanoTime() - startTime < 1000000000) { // empty block
			}
			
			// Create queue
			var queue = new SortedSimplePriorityQueue<Integer>(ints);
			
			// Now, run the test.
			startTime = System.nanoTime();

			for (int i = 0; i < timesToLoop; i++) {
				queue.findMax();
			}
			
			queueTime = System.nanoTime();

			// Subtract overhead code
			
			for (int i = 0; i < timesToLoop; i++) {
			}
			
			midTime = System.nanoTime();
			
			// Start second test
			
			for (int i = 0; i < timesToLoop; i++) {
				// Generate a random number to insert
				rand = rng.nextInt();
				
				// Create queue
				queue = new SortedSimplePriorityQueue<Integer>(ints);
				
				queue.insert(rand);
			}

			insertTime = System.nanoTime();
			
			// Subtract overhead code
			
			for (int i = 0; i < timesToLoop; i++) {
				// Generate a random number to insert
				rand = rng.nextInt();
				
				// Create queue
				queue = new SortedSimplePriorityQueue<Integer>(ints);
			}
			

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and computing square roots.
			// Average it over the number of runs.

			double averageFindMaxTime = ((queueTime - startTime) - (midTime - queueTime)) / timesToLoop;
			double averageInsertTime = ((insertTime - queueTime) - (stopTime - insertTime)) / timesToLoop;

			System.out.println("For problem size " + n);
			System.out.println("findMax: " + averageFindMaxTime + " ns");
			System.out.println("insert: " + averageInsertTime + " ns");
		}
		
		
	}
}
