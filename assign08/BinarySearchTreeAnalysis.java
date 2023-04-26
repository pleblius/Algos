package assign08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import analysis.Utils;

public class BinarySearchTreeAnalysis {

	public static void main(String[] args) {
		
		int timesToLoop = 5000;
		int numSizes = 10;
		int baseSize = 1000;
		
		
		int[] sizes = new int[numSizes];
		int currentSize;
		long[] sortedTimes = new long[numSizes];
		long[] randomTimes = new long[numSizes];
		
		for (int i = 0; i < numSizes; i++) {
			sizes[i] = baseSize*(i + 1);
		}
		
		long startTime;
		long midTime;
		long endTime;
		

		
//		for (int i = 0; i < numSizes; i++) {
//			currentSize = sizes[i];
//			System.out.println(currentSize);
//			
//			List<Integer> sortedList = new ArrayList<Integer>();
//			List<Integer> randomList = new ArrayList<Integer>();
//			BinarySearchTree<Integer> sortedTree = new BinarySearchTree<Integer>();
//			BinarySearchTree<Integer> randomTree = new BinarySearchTree<Integer>();
//			
//			for (int j = 0; j < currentSize; j++) {
//				sortedList.add(j);
//				randomList.add(j);
//			}
//			
//			Collections.shuffle(randomList);
//			
//			sortedTree.addAll(sortedList);
//			randomTree.addAll(randomList);
//			
//			Collections.shuffle(randomList);
//			
//			startTime = System.nanoTime();
//			while (System.nanoTime() - startTime < 1000000000) {}
//			
//			startTime = System.nanoTime();
//			for (int j = 0; j < timesToLoop; j++) {
//				for (int k = 0; k < baseSize; k++) {
//					sortedTree.contains(randomList.get(k));
//				}
//			}
//			
//			midTime = System.nanoTime();
//			for (int j = 0; j < timesToLoop; j++) {
//				for (int k = 0; k < baseSize; k++) {
//					randomList.get(k);
//				}
//			}
//			endTime = System.nanoTime();
//			
//			sortedTimes[i] = Utils.getTime(startTime, midTime, endTime, timesToLoop);
//			
//			startTime = System.nanoTime();
//			while (System.nanoTime() - startTime < 1000000000) {}
//			startTime = System.nanoTime();
//			for (int j = 0; j < timesToLoop; j++) {
//				for (int k = 0; k < baseSize; k++) {
//					randomTree.contains(randomList.get(k));
//				}
//			}
//			midTime = System.nanoTime();
//			for (int j = 0; j < timesToLoop; j++) {
//				for (int k = 0; k < baseSize; k++) {
//					randomList.get(k);
//				}
//			}
//			endTime = System.nanoTime();
//			
//			randomTimes[i] = Utils.getTime(startTime, midTime, endTime, timesToLoop);
//		}
//		
//		Utils.writeToFile(sizes, sortedTimes, "sorted");
//		Utils.writeToFile(sizes, randomTimes, "random");
		
		timesToLoop = 1000;
		baseSize = 25000;
		
		for (int i = 0; i < numSizes; i++) {
			sizes[i] = baseSize*(i+1);
		}
		
		long[] treeSetContains = new long[numSizes];
		long[] treeSetAdd = new long[numSizes];
		long[] BSTContains = new long[numSizes];
		long[] BSTAdd = new long[numSizes];
		
		for (int i = 0; i < numSizes; i++) {
			currentSize = sizes[i];
			System.out.println(currentSize);
			
			BinarySearchTree<Integer> BST = new BinarySearchTree<Integer>();
			Set<Integer> treeSet = new TreeSet<Integer>();
			
			List<Integer> intList = new ArrayList<Integer>();
			
			for (int j = 0; j < currentSize; j++) {
				intList.add(j);
			}
			
			Collections.shuffle(intList);
			
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) {}
			startTime = System.nanoTime();
			treeSet.addAll(intList);
			midTime = System.nanoTime();
			endTime = midTime;
			
			treeSetAdd[i] = Utils.getTime(startTime, midTime, endTime, 1);
			
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) {}
			startTime = System.nanoTime();
			BST.addAll(intList);
			midTime = System.nanoTime();
			endTime = midTime;
			
			BSTAdd[i] = Utils.getTime(startTime, midTime, endTime, 1);
			
			Collections.shuffle(intList);
			
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) {}
			startTime = System.nanoTime();
			for (int j = 0; j < timesToLoop; j++) {
				for (int k = 0; k < baseSize; k++) {
					treeSet.contains(intList.get(j));
				}
			}
			midTime = System.nanoTime();
			for (int j = 0; j < timesToLoop; j++) {
				for (int k = 0; k < baseSize; k++) {
					intList.get(j);
				}
			}
			endTime = System.nanoTime();
			
			treeSetContains[i] = Utils.getTime(startTime, midTime, endTime, timesToLoop);
			
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) {}
			startTime = System.nanoTime();
			for (int j = 0; j < timesToLoop; j++) {
				for (int k = 0; k < baseSize; k++) {
					BST.contains(intList.get(k));
				}
			}
			midTime = System.nanoTime();
			for (int j = 0; j< timesToLoop; j++) {
				for (int k = 0; k < baseSize; k++) {
					intList.get(k);
				}
			}
			endTime = System.nanoTime();
			
			BSTContains[i] = Utils.getTime(startTime, midTime, endTime, timesToLoop);
		}
		
		Utils.writeToFile(sizes, BSTAdd, "BSTAdd");
		Utils.writeToFile(sizes, BSTContains, "BSTContains");
		Utils.writeToFile(sizes, treeSetAdd, "treeAdd");
		Utils.writeToFile(sizes, treeSetContains, "treeContains");
	}

}
