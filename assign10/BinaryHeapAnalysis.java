package assign10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import analysis.Utils;
import assign09.StudentBadHash;

public class BinaryHeapAnalysis {
	public static void main(String[] args) {
		int timesToLoop = 100;
		int numSizes = 20;
		int baseSize = 10000;
		int k;
		
		int[] sizes = new int[numSizes];
		int currentSize;
		
		long[] smallKSort = new long[numSizes];
		long[] largeKSort = new long[numSizes];
		long[] smallKHeap = new long[numSizes];
		long[] largeKHeap = new long[numSizes];
		
		for (int i = 0; i < numSizes; i++) {
			sizes[i] = baseSize + 5000*i;
		}
		
		long startTime;
		long midTime;
		long endTime;
		
		for (int i = 0; i < numSizes; i++) {
			currentSize = sizes[i];
			System.out.println(currentSize);
			
			List<Integer> averageSet = new ArrayList<>(currentSize);
			
			for (int j = 0; j < currentSize; j++) {
				averageSet.add(j);
			}
			
			Collections.shuffle(averageSet);
			
			// Small k
			k = currentSize/10;
			
			// Heap
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) {}
			
			startTime = System.nanoTime();
			for (int j = 0; j < timesToLoop; j++) {
				FindKLargest.findKLargestHeap(averageSet, k);
			}
			midTime = System.nanoTime();
			for (int j = 0; j < timesToLoop; j++) {	}
			endTime = System.nanoTime();
			
			smallKHeap[i] = Utils.getTime(startTime, midTime, endTime, timesToLoop);
			
			// Sort
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) {}
			
			startTime = System.nanoTime();
			for (int j = 0; j < timesToLoop; j++) {
				FindKLargest.findKLargestSort(averageSet, k);
			}
			midTime = System.nanoTime();
			for (int j = 0; j < timesToLoop; j++) {	}
			endTime = System.nanoTime();
			
			smallKSort[i] = Utils.getTime(startTime, midTime, endTime, timesToLoop);
			
			
			// Big k
			k = (currentSize*9)/10;
			
			// Heap
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) {}
			
			startTime = System.nanoTime();
			for (int j = 0; j < timesToLoop; j++) {
				FindKLargest.findKLargestHeap(averageSet, k);
			}
			midTime = System.nanoTime();
			for (int j = 0; j < timesToLoop; j++) {	}
			endTime = System.nanoTime();
			
			largeKHeap[i] = Utils.getTime(startTime, midTime, endTime, timesToLoop);
			
			// Sort
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) {}
			
			startTime = System.nanoTime();
			for (int j = 0; j < timesToLoop; j++) {
				FindKLargest.findKLargestSort(averageSet, k);
			}
			midTime = System.nanoTime();
			for (int j = 0; j < timesToLoop; j++) {	}
			endTime = System.nanoTime();
			
			largeKSort[i] = Utils.getTime(startTime, midTime, endTime, timesToLoop);
		}
		
		Utils.writeToFile(sizes, largeKHeap, "largeKHeap");
		Utils.writeToFile(sizes, smallKHeap, "smallKHeap");
		Utils.writeToFile(sizes, largeKSort, "largeKSort");
		Utils.writeToFile(sizes, smallKSort, "smallKSort");
		
//		for (int i = 0; i < numSizes; i++) {
//			currentSize = sizes[i];
//			System.out.println(currentSize);
//			
//			List<Integer> averageSet = new ArrayList<>(currentSize);
//			
//			for (int j = 0; j < currentSize; j++) {
//				averageSet.add(j);
//			}
//			
//			Collections.shuffle(averageSet);
//			BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>();
//			
//			for (int j = 0; j < currentSize/2; j++) {
//				heap.add(averageSet.get(j));
//			}
//			
//			startTime = System.nanoTime();
//			while (System.nanoTime() - startTime < 1000000000) {}
//			
//			startTime = System.nanoTime();
//			for (int j = currentSize/2; j < currentSize; j++) {
//				heap.add(averageSet.get(j));
//			}
//			midTime = System.nanoTime();
//			for (int j = currentSize/2; j < currentSize; j++) {
//				averageSet.get(j);
//			}
//			endTime = System.nanoTime();
//			
//			addTimesAverage[i] = Utils.getTime(startTime, midTime, endTime, currentSize/2);
//			
//			heap.clear();
//			Collections.sort(averageSet);
//			
//			for (int j = 0; j < currentSize/2; j++) {
//				heap.add(averageSet.get(j));
//			}
//			
//			startTime = System.nanoTime();
//			while (System.nanoTime() - startTime < 1000000000) {}
//			
//			startTime = System.nanoTime();
//			for (int j = currentSize/2; j < currentSize; j++) {
//				heap.add(averageSet.get(j));
//			}
//			midTime = System.nanoTime();
//			for (int j = currentSize/2; j < currentSize; j++) {
//				averageSet.get(j);
//			}
//			endTime = System.nanoTime();
//			
//			addTimesWorst[i] = Utils.getTime(startTime, midTime, endTime, currentSize/2);
//			
//			startTime = System.nanoTime();
//			while (System.nanoTime() - startTime < 1000000000) {}
//			
//			startTime = System.nanoTime();
//			for (int j = 0; j < 1000; j++) {
//				heap.peek();
//			}
//			midTime = System.nanoTime();
//			for (int j = 0; j < 1000; j++) {}
//			endTime = System.nanoTime();
//			
//			peekTimes[i] = Utils.getTime(startTime, midTime, endTime, 1000000000);
//			
//			Collections.shuffle(averageSet);
//			heap = new BinaryMaxHeap<>(averageSet);
//
//			startTime = System.nanoTime();
//			while (System.nanoTime() - startTime < 1000000000) {}
//			
//			startTime = System.nanoTime();
//			for (int j = 0; j < timesToLoop; j++) {
//				heap.extractMax();
//			}
//			midTime = System.nanoTime();
//			for (int j = 0; j < timesToLoop; j++) {}
//			endTime = System.nanoTime();
//			
//			extractTimes[i] = Utils.getTime(startTime, midTime, endTime, timesToLoop);
//		}
//		
//		Utils.writeToFile(sizes, extractTimes, "extract");
//		Utils.writeToFile(sizes, peekTimes, "peek");
//		Utils.writeToFile(sizes, addTimesWorst, "addWorst");
//		Utils.writeToFile(sizes, addTimesAverage, "addAverage");
	}
}
