package assign08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.TreeSet;

public class BSTTiming {

	public static void main(String[] args) {
		
//		System.out.println("Stick Times:");
//		ammortizedStickTiming(750, 100);
//		
//		System.out.println("Random Times:");
//		ammortizedRandomTiming(750, 1000);
		
		System.out.println("Random Times2:");
		ammortizedRandomTiming(100000, 100);
		
		System.out.println("Balanced Times:");
		ammortizedBalancedTiming(100000, 100);
		
	}
	
	public static double[] ammortizedStickTiming(int nStep, int timesToLoop){
		
		Random rng = new Random();
		
		int numN = 20;
		
		double[] stickTimes = new double[numN];

		for (int ii = 0; ii < numN; ii++) {
			
			int n = nStep * (ii + 1);		
			ArrayList<Integer> sortedInts = new ArrayList<Integer>();
			
			for (int index = 0; index < n; index++) {
				sortedInts.add(index);
			}
			
			long startTime, midTime, endTime;

			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.

			startTime = System.nanoTime();
			while(System.nanoTime() - startTime < 1000000000) { // empty block
			}
			
			// Now, run the test.
			startTime = System.nanoTime();

			for (int i = 0; i < timesToLoop; i++) {
				BinarySearchTree<Integer> BST = new BinarySearchTree<Integer>();
				
				for (int index = 0; index < n; index++) {
					BST.add(sortedInts.get(index));
				}
				
			}
			
			midTime = System.nanoTime();
			
			// Subtract overhead code
			
			for (int i = 0; i < timesToLoop; i++) {
				BinarySearchTree<Integer> BST = new BinarySearchTree<Integer>();
				for (int index = 0; index < n; index++) {
					sortedInts.get(index);
				}
			}

			endTime = System.nanoTime();
			
			stickTimes[ii] = ((midTime - startTime) - (endTime - midTime)) / timesToLoop / n;
			System.out.println(stickTimes[ii]);
		}
		
		return stickTimes; // stub
	}
	
	public static double[] ammortizedRandomTiming(int nStep, int timesToLoop) {
		Random rng = new Random();
		
		int numN = 20;
		
		double[] randomTimes = new double[numN];

		for (int ii = 0; ii < numN; ii++) {
			
			int n = nStep * (ii + 1);
			ArrayList<Integer> randomInts = new ArrayList<Integer>(n);
			
			for (int index = 0; index < n; index++) {
				randomInts.add(index);
			}
			
			Collections.shuffle(randomInts);
			
			
			long startTime, midTime, endTime;

			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.

			startTime = System.nanoTime();
			while(System.nanoTime() - startTime < 1000000000) { // empty block
			}
			
			// Now, run the test.
			startTime = System.nanoTime();

			for (int i = 0; i < timesToLoop; i++) {
				BinarySearchTree<Integer> BST = new BinarySearchTree<Integer>();
				
				for (int index = 0; index < n; index++) {
					BST.add(randomInts.get(index));
				}
				
			}
			
			midTime = System.nanoTime();
			
			// Subtract overhead code
			
			for (int i = 0; i < timesToLoop; i++) {
				BinarySearchTree<Integer> BST = new BinarySearchTree<Integer>();
				for (int index = 0; index < n; index++) {
					randomInts.get(index);
				}
			}

			endTime = System.nanoTime();
			
			randomTimes[ii] = ((midTime - startTime) - (endTime - midTime)) / timesToLoop / n;
			System.out.println(randomTimes[ii]);
		}
		
		return randomTimes; // stub
	}
	
	public static double[] ammortizedBalancedTiming(int nStep, int timesToLoop) {
		Random rng = new Random();
		
		int numN = 20;
		
		double[] balancedTimes = new double[numN];

		for (int ii = 0; ii < numN; ii++) {
			
			int n = nStep * (ii + 1);
			ArrayList<Integer> randomInts = new ArrayList<Integer>(n);
			
			for (int index = 0; index < n; index++) {
				randomInts.add(index);
			}
			
			Collections.shuffle(randomInts);
			
			
			long startTime, midTime, endTime;

			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.

			startTime = System.nanoTime();
			while(System.nanoTime() - startTime < 1000000000) { // empty block
			}
			
			// Now, run the test.
			startTime = System.nanoTime();

			for (int i = 0; i < timesToLoop; i++) {
				TreeSet<Integer> BST = new TreeSet<Integer>();
				
				for (int index = 0; index < n; index++) {
					BST.add(randomInts.get(index));
				}
				
			}
			
			midTime = System.nanoTime();
			
			// Subtract overhead code
			
			for (int i = 0; i < timesToLoop; i++) {
				TreeSet<Integer> BST = new TreeSet<Integer>();
				for (int index = 0; index < n; index++) {
					randomInts.get(index);
				}
			}

			endTime = System.nanoTime();
			
			balancedTimes[ii] = ((midTime - startTime) - (endTime - midTime)) / timesToLoop / n;
			System.out.println(balancedTimes[ii]);
		}
		
		return balancedTimes; // stub
	}

}
