package assign09;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import analysis.Utils;

public class HashTableAnalysis {

	public static void main(String[] args) {
		
		int timesToLoop = 500;
		int numSizes = 20;
		int baseSize = 250;
		
		
		int[] sizes = new int[numSizes];
		int currentSize;
		long[] badTimes = new long[numSizes];
		long[] mediumTimes = new long[numSizes];
		long[] goodTimes = new long[numSizes];
		
		for (int i = 0; i < numSizes; i++) {
			sizes[i] = baseSize + 100*(i);
		}
		
		long startTime;
		long midTime;
		long endTime;
		
		List<StudentBadHash> students = new ArrayList<StudentBadHash>();
		
		for (int i = 0; i < numSizes; i++) {
			sizes[i] = (i + 1)*baseSize;
		}
		
		for (int i = 0; i < numSizes; i++) {
			currentSize = sizes[i];
			System.out.println(currentSize);
			
			List<HashTable<StudentBadHash, Integer>> maps = new ArrayList<HashTable<StudentBadHash, Integer>>();
//			HashTable<StudentBadHash, Integer> map = new HashTable<StudentBadHash, Integer>();
			
			Random rand = new Random();
			
//			for (int j = 0; j < currentSize; j++) {
//				map.put(new StudentBadHash(j, "a", "a"), 0);
//			}
			
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) {}
			
			startTime = System.nanoTime();
			for (int j = 0; j < timesToLoop; j++) {
				HashTable<StudentBadHash, Integer> map = new HashTable<StudentBadHash, Integer>();
				
				for (int k = 0; k < currentSize; k++) {
					map.put(new StudentBadHash(k, "a", "a"), 0);
				}
			}
			
			midTime = System.nanoTime();
			
			for (int j = 0; j < timesToLoop; j++) {
				HashTable<StudentBadHash, Integer> map = new HashTable<StudentBadHash, Integer>();
				
				for (int k = 0; k < currentSize; k++) {
					new StudentBadHash(k, "a", "a");
				}
			}
			
			endTime = System.nanoTime();
			
			badTimes[i] = Utils.getTime(startTime, midTime, endTime, timesToLoop);
		}
		
		Utils.writeToFile(sizes, badTimes, "badTimes.dat");
		
		for (int i = 0; i < numSizes; i++) {
			currentSize = sizes[i];
			System.out.println(currentSize);
			
			List<HashTable<StudentMediumHash, Integer>> maps = new ArrayList<HashTable<StudentMediumHash, Integer>>();
//			HashTable<StudentMediumHash, Integer> map = new HashTable<StudentMediumHash, Integer>();
			
			Random rand = new Random();
			
//			for (int j = 0; j < currentSize; j++) {
//				map.put(new StudentMediumHash(j, "a", "a"), 0);
//			}
			
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) {}
			
			startTime = System.nanoTime();
			for (int j = 0; j < timesToLoop; j++) {
				HashTable<StudentMediumHash, Integer> map = new HashTable<StudentMediumHash, Integer>();

				for (int k = 0; k < currentSize; k++) {
					map.put(new StudentMediumHash(k, "a", "a"), 0);
				}
			}
			
			midTime = System.nanoTime();
			
			for (int j = 0; j < timesToLoop; j++) {
				HashTable<StudentMediumHash, Integer> map = new HashTable<StudentMediumHash, Integer>();
				
				for (int k = 0; k < currentSize; k++) {
					new StudentMediumHash(k, "a", "a");
				}
			}
			
			endTime = System.nanoTime();
			
			mediumTimes[i] = Utils.getTime(startTime, midTime, endTime, timesToLoop);
		}
		
		Utils.writeToFile(sizes, mediumTimes, "mediumTimes.dat");
		
		for (int i = 0; i < numSizes; i++) {
			currentSize = sizes[i];
			System.out.println(currentSize);
			
			List<HashTable<StudentGoodHash, Integer>> maps = new ArrayList<HashTable<StudentGoodHash, Integer>>();
//			HashTable<StudentGoodHash, Integer> map = new HashTable<StudentGoodHash, Integer>();
			
			Random rand = new Random();
			
//			for (int j = 0; j < currentSize; j++) {
//				map.put(new StudentGoodHash(j, "a", "a"), 0);
//			}
			
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) {}
			
			startTime = System.nanoTime();
			for (int j = 0; j < timesToLoop; j++) {
				HashTable<StudentGoodHash, Integer> map = new HashTable<StudentGoodHash, Integer>();
				
				for (int k = 0; k < currentSize; k++) {
					map.put(new StudentGoodHash(k, "a", "a"), 0);
				}
			}
			
			midTime = System.nanoTime();
			
			for (int j = 0; j < timesToLoop; j++) {
				HashTable<StudentGoodHash, Integer> map = new HashTable<StudentGoodHash, Integer>();
				
				for (int k = 0; k < currentSize; k++) {
					new StudentGoodHash(k, "a", "a");
				}
			}
			
			endTime = System.nanoTime();
			
			goodTimes[i] = Utils.getTime(startTime, midTime, endTime, timesToLoop);
		}
		
		Utils.writeToFile(sizes, goodTimes, "goodTimes.dat");
	}

}
