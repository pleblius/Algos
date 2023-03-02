package assign06;

import java.util.ArrayList;
import java.util.Random;

import assign05.ArrayListSorter;
import assign05.Utils;

/**
 * This compares the runtimes of ArrayStack and LinkedListStack methods
 * 
 * @using TimingExperiment08 by Erin Parker, Ver. Jan 21 2022
 * 
 * @author Andrew Tolton
 * @version Mar 2nd, 2023
 */

public class StackComparisonTiming {

	public static void main(String[] args) {
		var popTimes = popComparison();
		var asPopTimes = popTimes.get(0);
		var llsPopTimes = popTimes.get(1);
		
		System.out.println("popTimes:");
		System.out.printf("%16s%16s\n", "ArrayStack", "LinkedListStack");
		
		for (int ii = 0; ii < asPopTimes.length; ii++) {
			System.out.printf("%16f%16f\n", asPopTimes[ii], llsPopTimes[ii]);
		}
		
		var peekTimes = peekComparison();
		var asPeekTimes = peekTimes.get(0);
		var llsPeekTimes = peekTimes.get(1);
		
		System.out.println("peekTimes:");
		System.out.printf("%16s%16s\n", "ArrayStack", "LinkedListStack");
		
		for (int ii = 0; ii < asPeekTimes.length; ii++) {
			System.out.printf("%16f%16f\n", asPeekTimes[ii], llsPeekTimes[ii]);
		}
		
		var pushTimes = pushComparison();
		var asPushTimes = pushTimes.get(0);
		var llsPushTimes = pushTimes.get(1);
		
		System.out.println("pushTimes:");
		System.out.printf("%16s%16s\n", "ArrayStack", "LinkedListStack");
		
		for (int ii = 0; ii < asPushTimes.length; ii++) {
			System.out.printf("%16f%16f\n", asPushTimes[ii], llsPushTimes[ii]);
		}
		
		

	}

	public static ArrayList<double[]> popComparison() {
		Random rng = new Random();
		Integer rand;
		
		int timesToLoop = 2000;
		int Nstep = 1_000;
		
		ArrayList<double[]> popTimes = new ArrayList<double[]>();
		double[] asPopTimes = new double[20];
		double[] llsPopTimes = new double[20];
		
		for (int N = Nstep; N <= 20*Nstep; N += Nstep) {
			
			ArrayList<ArrayStack<Integer>> ASList = new ArrayList<ArrayStack<Integer>>(timesToLoop);
			ArrayList<LinkedListStack<Integer>> LLSList = new ArrayList<LinkedListStack<Integer>>(timesToLoop);
			
			for (int ii = 0; ii < timesToLoop; ii++) {
				
				ASList.add(new ArrayStack<Integer>());
				LLSList.add(new LinkedListStack<Integer>());
				
				for (int n = 0; n < N; n++) {
					rand = rng.nextInt();
					ASList.get(ii).push(rand);
					LLSList.get(ii).push(rand);
				}
			}
			
			
			long startTime, asPopTime, llsPopTime, asSubtract, llsSubtract;

			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.

			startTime = System.nanoTime();
			while(System.nanoTime() - startTime < 1000000000) { // empty block
			}
			
			
			// Now, run the test.
			startTime = System.nanoTime();

			for (int i = 0; i < timesToLoop; i++) {
				ASList.get(i).pop();
			}
			
			asPopTime = System.nanoTime();
			
			for (int i = 0; i < timesToLoop; i++) {
				LLSList.get(i).pop();
			}

			llsPopTime = System.nanoTime();
			
			// Subtract overhead code
			
			for (int i = 0; i < timesToLoop; i++) {
				ASList.get(i);
			}
			
			asSubtract = System.nanoTime();
			
			for (int i = 0; i < timesToLoop; i++) {
				LLSList.get(i);
			}
			
			llsSubtract = System.nanoTime();
	

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and computing square roots.
			// Average it over the number of runs.

			asPopTimes[N / Nstep - 1] = ((asPopTime - startTime) - (asSubtract - llsPopTime)) / timesToLoop;
			llsPopTimes[N / Nstep - 1] = ((llsPopTime - asPopTime) - (llsSubtract - asSubtract)) / timesToLoop;
			
		}
		
		popTimes.add(asPopTimes);
		popTimes.add(llsPopTimes);
		
		return popTimes;
	}
	
	public static ArrayList<double[]> peekComparison() {
		Random rng = new Random();
		Integer rand;
		
		int timesToLoop = 2000;
		int Nstep = 1_000;
		
		ArrayList<double[]> peekTimes = new ArrayList<double[]>();
		double[] asPeekTimes = new double[20];
		double[] llsPeekTimes = new double[20];
		
		for (int N = Nstep; N <= 20*Nstep; N += Nstep) {
			
			ArrayList<ArrayStack<Integer>> ASList = new ArrayList<ArrayStack<Integer>>(timesToLoop);
			ArrayList<LinkedListStack<Integer>> LLSList = new ArrayList<LinkedListStack<Integer>>(timesToLoop);
			
			for (int ii = 0; ii < timesToLoop; ii++) {
				
				ASList.add(new ArrayStack<Integer>());
				LLSList.add(new LinkedListStack<Integer>());
				
				for (int n = 0; n < N; n++) {
					rand = rng.nextInt();
					ASList.get(ii).push(rand);
					LLSList.get(ii).push(rand);
				}
			}
			
			
			long startTime, asPeekTime, llsPeekTime, asSubtract, llsSubtract;

			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.

			startTime = System.nanoTime();
			while(System.nanoTime() - startTime < 1000000000) { // empty block
			}
			
			
			// Now, run the test.
			startTime = System.nanoTime();

			for (int i = 0; i < timesToLoop; i++) {
				ASList.get(i).peek();
			}
			
			asPeekTime = System.nanoTime();
			
			for (int i = 0; i < timesToLoop; i++) {
				LLSList.get(i).peek();
			}

			llsPeekTime = System.nanoTime();
			
			// Subtract overhead code
			
			for (int i = 0; i < timesToLoop; i++) {
				ASList.get(i);
			}
			
			asSubtract = System.nanoTime();
			
			for (int i = 0; i < timesToLoop; i++) {
				LLSList.get(i);
			}
			
			llsSubtract = System.nanoTime();
	

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and computing square roots.
			// Average it over the number of runs.

			asPeekTimes[N / Nstep - 1] = ((asPeekTime - startTime) - (asSubtract - llsPeekTime)) / timesToLoop;
			llsPeekTimes[N / Nstep - 1] = ((llsPeekTime - asPeekTime) - (llsSubtract - asSubtract)) / timesToLoop;
			
		}
		
		peekTimes.add(asPeekTimes);
		peekTimes.add(llsPeekTimes);
		
		return peekTimes;
	}
	
	public static ArrayList<double[]> pushComparison() {
		Random rng = new Random();
		Integer rand;
		
		int timesToLoop = 2000;
		int Nstep = 1_000;
		
		ArrayList<double[]> pushTimes = new ArrayList<double[]>();
		double[] asPushTimes = new double[20];
		double[] llsPushTimes = new double[20];
		
		for (int N = Nstep; N <= 20*Nstep; N += Nstep) {
			
			ArrayList<ArrayStack<Integer>> ASList = new ArrayList<ArrayStack<Integer>>(timesToLoop);
			ArrayList<LinkedListStack<Integer>> LLSList = new ArrayList<LinkedListStack<Integer>>(timesToLoop);
			
			for (int ii = 0; ii < timesToLoop; ii++) {
				
				ASList.add(new ArrayStack<Integer>());
				LLSList.add(new LinkedListStack<Integer>());
				
				for (int n = 0; n < N; n++) {
					rand = rng.nextInt();
					ASList.get(ii).push(rand);
					LLSList.get(ii).push(rand);
				}
			}
			
			
			long startTime, asPushTime, llsPushTime, asSubtract, llsSubtract;

			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.

			startTime = System.nanoTime();
			while(System.nanoTime() - startTime < 1000000000) { // empty block
			}
			
			
			// Now, run the test.
			startTime = System.nanoTime();

			for (int i = 0; i < timesToLoop; i++) {
				rand = rng.nextInt();
				ASList.get(i).push(rand);
			}
			
			asPushTime = System.nanoTime();
			
			for (int i = 0; i < timesToLoop; i++) {
				rand = rng.nextInt();
				LLSList.get(i).push(rand);
			}

			llsPushTime = System.nanoTime();
			
			// Subtract overhead code
			
			for (int i = 0; i < timesToLoop; i++) {
				rand = rng.nextInt();
				ASList.get(i);
			}
			
			asSubtract = System.nanoTime();
			
			for (int i = 0; i < timesToLoop; i++) {
				rand = rng.nextInt();
				LLSList.get(i);
			}
			
			llsSubtract = System.nanoTime();
	

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and computing square roots.
			// Average it over the number of runs.

			asPushTimes[N / Nstep - 1] = ((asPushTime - startTime) - (asSubtract - llsPushTime)) / timesToLoop;
			llsPushTimes[N / Nstep - 1] = ((llsPushTime - asPushTime) - (llsSubtract - asSubtract)) / timesToLoop;
			
		}
		
		pushTimes.add(asPushTimes);
		pushTimes.add(llsPushTimes);
		
		return pushTimes;
	}
	
}
