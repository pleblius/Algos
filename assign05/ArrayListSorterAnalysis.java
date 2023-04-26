package assign05;

import java.util.ArrayList;
import java.util.List;

import analysis.Utils;

public class ArrayListSorterAnalysis {
	
	public static void main(String[] args) {
		int[] lengths;
		long[] times;
		
		// Threshold experiment
		lengths = new int[10];
		
		for (int i = 0; i < 10; i++) {
			lengths[i] = (i+1)*50000;
		}

		
		long startTime;
		long midTime;
		long endTime;
		int loopTimes = 100;
		
		List<ArrayList<Integer>> testArrays;
		List<ArrayList<Integer>> benchArrays = new ArrayList<ArrayList<Integer>>(10);
		
//		for (int i = 0; i < 10; i++) {
//			benchArrays.add(ArrayListSorter.generatePermuted(lengths[i]));
//		}
		
//		for (int i = 0; i < 10; i++) {
//			times = new long[10];
//			ArrayListSorter.setThreshold(10*i);
//			
//			for (int j = 0; j < 10; j++) {
//				System.out.println(i + " " + j);
//				
//				testArrays = new ArrayList<ArrayList<Integer>>(loopTimes);
//				for (int k = 0; k < loopTimes; k++) {
//					testArrays.add(Utils.generateCopy(benchArrays.get(j)));
//				}
//				
//				startTime = System.nanoTime();
//				while(System.nanoTime() - startTime < 1000000000) {}
//				
//				// Mergesort threshold timing
//				startTime = System.nanoTime();
//				
//				for (int k = 0; k < loopTimes; k++) {
//					ArrayListSorter.mergesort(testArrays.get(k));
//				}
//				
//				// Account for loop timing
//				midTime = System.nanoTime();
//				for (int k = 0; k < loopTimes; k++) {}
//				endTime = System.nanoTime();
//				
//				// Save times
//				times[j] = ((midTime - startTime) - (endTime - midTime))/loopTimes;
//			}
//			// Write times
//			Utils.writeToFile(lengths, times, "thr" + 10*i +".txt");
//		}
		
//		benchArrays = new ArrayList<ArrayList<Integer>>(10);
//		
//		for (int i = 0; i < 10; i++) {
//			benchArrays.add(ArrayListSorter.generatePermuted(lengths[i]));
//		}
//		
//		for (int i = 1; i <= 3; i++) {
//			times = new long[10];
//			ArrayListSorter.setPivotNumber(i);
//			
//			for (int j = 0; j < 10; j++) {
//				System.out.println(i + " " + j);
//				
//				testArrays = new ArrayList<ArrayList<Integer>>(loopTimes);
//				for (int k = 0; k < loopTimes; k++) {
//					testArrays.add(Utils.generateCopy(benchArrays.get(j)));
//				}
//				
//				startTime = System.nanoTime();
//				while(System.nanoTime() - startTime > 1000000000) {}
//				
//				startTime = System.nanoTime();
//				
//				for (int k = 0; k < loopTimes; k++) {
//					ArrayListSorter.quicksort(testArrays.get(k));
//				}
//				
//				midTime = System.nanoTime();
//				for (int k = 0; k < loopTimes; k++) {}
//				
//				endTime = System.nanoTime();
//				
//				times[j] = ((midTime - startTime) - (endTime - midTime))/loopTimes;
//			}
//			
//			Utils.writeToFile(lengths, times, "pn" + i +".txt");
//		}
		
		ArrayListSorter.setPivotNumber(1);
		ArrayListSorter.setThreshold(20);
		
		for (int i = 0; i < 6; i++) {
			benchArrays = new ArrayList<ArrayList<Integer>>(lengths.length);
			
			if (i%3 == 0) {
				for (int j = 0; j < lengths.length; j++) {
					benchArrays.add(ArrayListSorter.generateAscending(lengths[j]));
				}
			}
			else if (i%3 == 1) {
				for (int j = 0; j < lengths.length; j++) {
					benchArrays.add(ArrayListSorter.generateDescending(lengths[j]));
				}
			}
			else if (i%3 == 2) {
				for (int j = 0; j < lengths.length; j++) {
					benchArrays.add(ArrayListSorter.generatePermuted(lengths[j]));
				}
			}
			
			times = new long[lengths.length];
			
			for (int j = 0; j < lengths.length; j++) {
				System.out.println(i + " " + j);
				
				if (i < 3) {
					testArrays = new ArrayList<ArrayList<Integer>>(loopTimes);
					for (int k = 0; k < loopTimes; k++) {
						testArrays.add(Utils.generateCopy(benchArrays.get(j)));
					}
				
					startTime = System.nanoTime();
					while (System.nanoTime() - startTime < 1000000000) {}
				
					startTime = System.nanoTime();
					for (int k = 0; k < loopTimes; k++) {
						ArrayListSorter.quicksort(testArrays.get(k));
					}
					
					midTime = System.nanoTime();
					for (int k = 0; k < loopTimes; k++) {
						testArrays.get(k);
					}
					
					endTime = System.nanoTime();
					
					times[j] = ((midTime - startTime) - (endTime - midTime))/loopTimes;
				}
				
				else if (2 < i && i < 6) {
					testArrays = new ArrayList<ArrayList<Integer>>(loopTimes);
					for (int k = 0; k < loopTimes; k++) {
						testArrays.add(Utils.generateCopy(benchArrays.get(j)));
					}
					
					startTime = System.nanoTime();
					while (System.nanoTime() - startTime < 1000000000) {}
					
					startTime = System.nanoTime();
					for (int k = 0; k < loopTimes; k++) {
						ArrayListSorter.mergesort(testArrays.get(k));
					}
					
					midTime = System.nanoTime();
					for (int k = 0; k < loopTimes; k++) {
						testArrays.get(k);
					}
					
					endTime = System.nanoTime();
					
					times[j] = ((midTime - startTime) - (endTime - midTime))/loopTimes;
				}
			}
			
			Utils.writeToFile(lengths, times, "comp" + i +".txt");
		}
	}
}
