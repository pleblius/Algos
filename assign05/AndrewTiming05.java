package assign05;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

import assign03.SortedSimplePriorityQueue;

/**
 * This compares the runtimes of quicksort using different threshold values
 * 
 * @using TimingExperiment08 by Erin Parker, Ver. Jan 21 2022
 * @using collect_running_times from Assignment 02
 * 
 * @author Andrew Tolton
 * @version January 21, 2022
 */

public class AndrewTiming05 {

	
	public static void main(String[] args) {
//		mergesortThresholdTest();
//		quicksortPivotTest();
		mergeVsQuickTest();
			
	}
	
	public static void mergesortThresholdTest() {
		// Setting up queues
		
		Random rng = new Random();
		Integer rand;
		
		int timesToLoop = 5000;
		
		
		// Set up table
		System.out.printf("%14s%14s%14s%14s%14s", "t1", "t2", "t3", "t4", "t5");
		System.out.println();
		
		// For each problem of size n
		for (int n = 1000; n <= 10000; n += 1000) {
			
			// Generate timesToLoop new ArrayList of Integer ArrayLists
			
			ArrayList<ArrayList<Integer>> masterList = new ArrayList<ArrayList<Integer>>(timesToLoop);
			Random intGen = new Random();
			
			for (int ii = 1; ii <= timesToLoop; ii++) {
				masterList.add(ArrayListSorter.generatePermuted(n));
			}

			// Create thresholds
			int[] thresholds = {0, 10, 25, 50, 100};
			
			// For each threshold to test
			
			for (int tInd = 0; tInd < 5; tInd++) {
				long startTime, midTime, stopTime;
				ArrayList<ArrayList<Integer>> sortableList = Utils.arrayArrayCopy(masterList);
				
				ArrayListSorter.setThreshold(thresholds[tInd]);

				// First, spin computing stuff until one second has gone by.
				// This allows this thread to stabilize.

				startTime = System.nanoTime();
				while(System.nanoTime() - startTime < 1000000000) { // empty block
				}
				
				
				// Now, run the test.
				startTime = System.nanoTime();

				for (int i = 0; i < timesToLoop; i++) {
					ArrayListSorter.mergesort(sortableList.get(i));
				}
				
				midTime = System.nanoTime();

				// Subtract overhead code
				
				for (int i = 0; i < timesToLoop; i++) {
				}
				
				stopTime = System.nanoTime();

				// Compute the time, subtract the cost of running the loop
				// from the cost of running the loop and computing square roots.
				// Average it over the number of runs.

				double averageTime = ((midTime - startTime) - (stopTime - midTime)) / timesToLoop;

				System.out.printf("%14.2f", averageTime);
			}
			System.out.println();
		
		}
		
		
	}

	public static void quicksortPivotTest() {
		// Setting up queues
		
		Random rng = new Random();
		Integer rand;
		
		int timesToLoop = 5000;
		
		
		// Set up table
		System.out.printf("%14s%14s%14s", "p1", "p2", "p3");
		System.out.println();
		
		// For each problem of size n
		for (int n = 1000; n <= 10000; n += 1000) {
			
			// Generate timesToLoop new ArrayList of Integer ArrayLists
			
			ArrayList<ArrayList<Integer>> masterList = new ArrayList<ArrayList<Integer>>(timesToLoop);
			Random intGen = new Random();
			
			for (int ii = 1; ii <= timesToLoop; ii++) {
				masterList.add(ArrayListSorter.generatePermuted(n));
			}

			// Pivots to test
			int[] pivotNumbers = {1, 2, 5};
			
			// For each pivot to test
			
			for (int pInd = 0; pInd < 3; pInd++) {
				long startTime, midTime, stopTime;
				ArrayList<ArrayList<Integer>> sortableList = Utils.arrayArrayCopy(masterList);
				
				ArrayListSorter.setPivotNumber(pivotNumbers[pInd]);

				// First, spin computing stuff until one second has gone by.
				// This allows this thread to stabilize.

				startTime = System.nanoTime();
				while(System.nanoTime() - startTime < 1000000000) { // empty block
				}
				
				
				// Now, run the test.
				startTime = System.nanoTime();

				for (int i = 0; i < timesToLoop; i++) {
					ArrayListSorter.quicksort(sortableList.get(i));
				}
				
				midTime = System.nanoTime();

				// Subtract overhead code
				
				for (int i = 0; i < timesToLoop; i++) {
				}
				
				stopTime = System.nanoTime();

				// Compute the time, subtract the cost of running the loop
				// from the cost of running the loop and computing square roots.
				// Average it over the number of runs.

				double averageTime = ((midTime - startTime) - (stopTime - midTime)) / timesToLoop;

				System.out.printf("%14.2f", averageTime);
			}
			System.out.println();
		
		}
		
		
	}
	
	public static void mergeVsQuickTest() {
		// Setting up queues
		
		Random rng = new Random();
		Integer rand;
		
		int timesToLoop = 5000;
		int nstep = 1000;
		
		
		// Create arrays to hold average times
		double[][] quickTimes = new double[10][3];
		double[][] mergeTimes = new double[10][3];
		
		// For each problem of size n
		for (int n = nstep; n <= 10*nstep; n +=  nstep) {
			
			// Generate timesToLoop new ArrayList of Integer ArrayLists
			
			ArrayList<ArrayList<Integer>> ascendingLists = new ArrayList<ArrayList<Integer>>(timesToLoop);
			ArrayList<ArrayList<Integer>> descendingLists = new ArrayList<ArrayList<Integer>>(timesToLoop);
			ArrayList<ArrayList<Integer>> permutedLists = new ArrayList<ArrayList<Integer>>(timesToLoop);
			Random intGen = new Random();
			
			for (int ii = 1; ii <= timesToLoop; ii++) {
				ascendingLists.add(ArrayListSorter.generateAscending(n));
				descendingLists.add(ArrayListSorter.generateDescending(n));
				permutedLists.add(ArrayListSorter.generatePermuted(n));
			}

			// Setup pivot and threshold
			ArrayListSorter.setThreshold(20);
			ArrayListSorter.setPivotNumber(5);
			
			/////////// Mergesort timing //////////////
			long startTime, ascendingTime, descendingTime, permutedTime, stopTime;
			ArrayList<ArrayList<Integer>> ascendingSortableList = Utils.arrayArrayCopy(ascendingLists);
			ArrayList<ArrayList<Integer>> descendingSortableList = Utils.arrayArrayCopy(descendingLists);
			ArrayList<ArrayList<Integer>> permutedSortableList = Utils.arrayArrayCopy(permutedLists);
			

			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.

			startTime = System.nanoTime();
			while(System.nanoTime() - startTime < 1000000000) { // empty block
			}
			
			
			// Now, run the test.
			startTime = System.nanoTime();

			// Test ascending sort
			for (int i = 0; i < timesToLoop; i++) {
				ArrayListSorter.mergesort(ascendingSortableList.get(i));
			}
			
			ascendingTime = System.nanoTime();
			
			// Test descending sort
			for (int i = 0; i < timesToLoop; i++) {
				ArrayListSorter.mergesort(descendingSortableList.get(i));
			}
			
			descendingTime = System.nanoTime();
			
			// Test permuted sort
			for (int i = 0; i < timesToLoop; i++) {
				ArrayListSorter.mergesort(permutedSortableList.get(i));
			}
			
			permutedTime = System.nanoTime();

			// Subtract overhead code
			
			for (int i = 0; i < timesToLoop; i++) {
			}
			
			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and computing square roots.
			// Average it over the number of runs.

			double averageAscendingTime = ((ascendingTime - startTime) - (stopTime - permutedTime)) / timesToLoop;
			double averageDescendingTime = ((descendingTime - ascendingTime) - (stopTime - permutedTime)) / timesToLoop;
			double averagePermutedTime = ((permutedTime - descendingTime) - (stopTime - permutedTime)) / timesToLoop;

			mergeTimes[(n / nstep) - 1][0] = averageAscendingTime;
			mergeTimes[(n / nstep) - 1][1] = averageDescendingTime;
			mergeTimes[(n / nstep) - 1][2] = averagePermutedTime;
			
		
			/////////// Quicksort timing //////////////
			ascendingSortableList = Utils.arrayArrayCopy(ascendingLists);
			descendingSortableList = Utils.arrayArrayCopy(descendingLists);
			permutedSortableList = Utils.arrayArrayCopy(permutedLists);
			

			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.

			startTime = System.nanoTime();
			while(System.nanoTime() - startTime < 1000000000) { // empty block
			}
			
			
			// Now, run the test.
			startTime = System.nanoTime();

			// Test ascending sort
			for (int i = 0; i < timesToLoop; i++) {
				ArrayListSorter.quicksort(ascendingSortableList.get(i));
			}
			
			ascendingTime = System.nanoTime();
			
			// Test descending sort
			for (int i = 0; i < timesToLoop; i++) {
				ArrayListSorter.quicksort(descendingSortableList.get(i));
			}
			
			descendingTime = System.nanoTime();
			
			// Test permuted sort
			for (int i = 0; i < timesToLoop; i++) {
				ArrayListSorter.quicksort(permutedSortableList.get(i));
			}
			
			permutedTime = System.nanoTime();

			// Subtract overhead code
			
			for (int i = 0; i < timesToLoop; i++) {
			}
			
			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and computing square roots.
			// Average it over the number of runs.

			averageAscendingTime = ((ascendingTime - startTime) - (stopTime - permutedTime)) / timesToLoop;
			averageDescendingTime = ((descendingTime - ascendingTime) - (stopTime - permutedTime)) / timesToLoop;
			averagePermutedTime = ((permutedTime - descendingTime) - (stopTime - permutedTime)) / timesToLoop;

			quickTimes[(n / nstep) - 1][0] = averageAscendingTime;
			quickTimes[(n / nstep) - 1][1] = averageDescendingTime;
			quickTimes[(n / nstep) - 1][2] = averagePermutedTime;
			
		}
		
		// Printing Output
		System.out.println("Mergesort Times");
		System.out.printf("%14s%14s%14s", "Ascending", "Descending", "Permuted");
		System.out.println();
		for (int ii = 0; ii < 10; ii++) {
			for (int jj = 0; jj < 3; jj++) {
				System.out.printf("%14.2f", mergeTimes[ii][jj]);
			}
			System.out.println();
		}

		System.out.println("Quicksort Times");
		System.out.printf("%14s%14s%14s", "Ascending", "Descending", "Permuted");
		System.out.println();
		for (int ii = 0; ii < 10; ii++) {
			for (int jj = 0; jj < 3; jj++) {
				System.out.printf("%14.2f", quickTimes[ii][jj]);
			}
			System.out.println();
		}
		
		
	}
	
}


