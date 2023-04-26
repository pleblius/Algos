package comprehensive;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import analysis.Utils;

public class Analysis {
	public static void main(String [] args) {
		int timesToLoop = 100;
		int numSizes = 20;
		int baseSize = 100_000;
		
		
		
//		makeSet(timesToLoop, numSizes, baseSize);
//		getRep(timesToLoop, numSizes, baseSize);
//		goodUnion(timesToLoop, numSizes, baseSize);
		badUnion(timesToLoop, numSizes, baseSize);
	}
	


	public static void makeSet(int timesToLoop, int numSizes, int baseSize) {
		long[] mapTimes = new long[numSizes];
		long[] forestTimes = new long[numSizes];
		
		int[] sizes = new int[numSizes];
		int currentSize;
		
		for (int i = 0; i < numSizes; i++) {
			sizes[i] = baseSize*(i + 1);
		}
		
		long startTime;
		long midTime;
		long endTime;
		
		for (int i = 0; i < numSizes; i++) {
			currentSize = sizes[i];
			System.out.println(currentSize);
			DiscreteMap<Integer> map = new DiscreteMap<>();
			DisjointForest<Integer> forest = new DisjointForest<>();
			
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) {}
			
			startTime = System.nanoTime();
			for (int j = 0; j < currentSize; j++) {
				map.makeSet(j);
			}
			midTime = System.nanoTime();
			for (int j = 0; j < currentSize; j++) { }
			endTime = System.nanoTime();
			
			mapTimes[i] = Utils.getTime(startTime, midTime, endTime, currentSize);
			
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) {}
			startTime = System.nanoTime();
			for (int j = 0; j < currentSize; j++) {
				forest.makeSet(j);
			}
			midTime = System.nanoTime();
			for (int j = 0; j < currentSize; j++) {
				
			}
			endTime = System.nanoTime();
			
			forestTimes[i] = Utils.getTime(startTime, midTime, endTime, currentSize);
		}
		
		Utils.writeToFile(sizes, forestTimes, "forestMake.txt");
		Utils.writeToFile(sizes, mapTimes, "mapMake.txt");
	}
	
	public static void getRep(int timesToLoop, int numSizes, int baseSize) {
		long[] mapTimes = new long[numSizes];
		long[] forestTimes = new long[numSizes];
		
		int[] sizes = new int[numSizes];
		int currentSize;
		
		for (int i = 0; i < numSizes; i++) {
			sizes[i] = baseSize*(i + 1);
		}
		
		long startTime;
		long midTime;
		long endTime;
		
		for (int i = 0; i < numSizes; i++) {
			currentSize = sizes[i];
			System.out.println(currentSize);
			DiscreteMap<Integer> map = new DiscreteMap<>();
			DisjointForest<Integer> forest = new DisjointForest<>();
			
			for (int j = 0; j < currentSize; j++) {
				map.makeSet(j);
			}
			
			Random rand = new Random();
			
			for (int j = 0; j < currentSize; j++) {
				map.union(rand.nextInt(currentSize), rand.nextInt(currentSize));
			}
			
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) {}
			startTime = System.nanoTime();
			
			for (int j = 0; j < currentSize; j++) {
				map.getRepresentative(j);
			}
			midTime = System.nanoTime();
			for (int j = 0; j < currentSize; j++) { }
			endTime = System.nanoTime();
			
			mapTimes[i] = Utils.getTime(startTime, midTime, endTime, currentSize);
			
			
			for (int j = 0; j < currentSize; j++) {
				forest.makeSet(j);
			}
			
			rand = new Random();
			
			for (int j = 0; j < currentSize; j++) {
				forest.union(rand.nextInt(currentSize), rand.nextInt(currentSize));
			}
			
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) {}
			startTime = System.nanoTime();
			
			for (int j = 0; j < currentSize; j++) {
				forest.getRepresentative(j);
			}
			midTime = System.nanoTime();
			for (int j = 0; j < currentSize; j++) { }
			endTime = System.nanoTime();
			
			forestTimes[i] = Utils.getTime(startTime, midTime, endTime, currentSize);
		}
		
		Utils.writeToFile(sizes, forestTimes, "forestRep.txt");
		Utils.writeToFile(sizes, mapTimes, "mapRep.txt");
	}
	
	public static void goodUnion(int timesToLoop, int numSizes, int baseSize) {
		long[] mapTimes = new long[numSizes];
		long[] forestTimes = new long[numSizes];
		
		int[] sizes = new int[numSizes];
		int currentSize;
		
		for (int i = 0; i < numSizes; i++) {
			sizes[i] = baseSize*(i + 1);
		}
		
		long startTime;
		long midTime;
		long endTime;
		
		for (int i = 0; i < numSizes; i++) {
			currentSize = sizes[i];
			System.out.println(currentSize);
			
			List<Integer> list = new ArrayList<Integer>(currentSize);
			for (int j = 0; j < currentSize; j++) {
				list.add(j);
			}
			
			DisjointSet<Integer> forest = new DisjointForest<>(list);
			DisjointSet<Integer> map = new DiscreteMap<>(list);
			
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) {}
			
			startTime = System.nanoTime();
			for (int j = 0; j < currentSize-1; j++) {
				forest.union(j, j + 1);
			}
			midTime = System.nanoTime();
			for (int j = 0; j < currentSize-1; j++) {}
			
			endTime = System.nanoTime();
			
			forestTimes[i] = Utils.getTime(startTime, midTime, endTime, currentSize);
			
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) {}
			
			startTime = System.nanoTime();
			for (int j = 0; j < currentSize-1; j++) {
				map.union(j, j + 1);
			}
			midTime = System.nanoTime();
			for (int j = 0; j < currentSize-1; j++) {}
			
			endTime = System.nanoTime();
			
			mapTimes[i] = Utils.getTime(startTime, midTime, endTime, currentSize);
		}
		
		Utils.writeToFile(sizes, forestTimes, "forestGood.txt");
		Utils.writeToFile(sizes, mapTimes, "mapGood.txt");
	}

	private static void badUnion(int timesToLoop, int numSizes, int baseSize) {
		baseSize = 10_000;
		numSizes = 20;
		timesToLoop = 100;
		
		long[] mapTimes = new long[numSizes];
		long[] forestTimes = new long[numSizes];
		
		int[] sizes = new int[numSizes];
		int currentSize;
		
		for (int i = 0; i < numSizes; i++) {
			sizes[i] = baseSize*(i + 1);
		}
		
		long startTime;
		long midTime;
		long endTime;
		
		for (int i = 0; i < numSizes; i++) {
			currentSize = sizes[i];
			System.out.println(currentSize);
			
			List<DiscreteMap<Integer>> list = new ArrayList<>(timesToLoop);
			List<DisjointForest<Integer>> forestList = new ArrayList<>(timesToLoop);
			
			for (int j = 0; j < timesToLoop; j++) {
				DiscreteMap<Integer> map = new DiscreteMap<>();
				list.add(map);
				
				for (int k = 0; k < currentSize; k++) {
					map.makeSet(k);
				}
				
				for (int k = 0; k < currentSize/2 - 1; k++) {
					map.union(k, k+1);
				}
				for (int k = currentSize/2; k < currentSize - 1; k++) {
					map.union(k, k+1);
				}
			}
			
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) {}
			startTime = System.nanoTime();
			
			for (int j = 0; j < timesToLoop; j++) {
				list.get(j).union(0, currentSize-1);
			}
			midTime = System.nanoTime();
			for (int j = 0; j < timesToLoop; j++) {
				list.get(j);
			}
			endTime = System.nanoTime();
			
			mapTimes[i] = Utils.getTime(startTime, midTime, endTime, timesToLoop);
			
			
			for (int j = 0; j < timesToLoop; j++) {
				DisjointForest<Integer> forest = new DisjointForest<>();
				forestList.add(forest);
				
				for (int k = 0; k < currentSize; k++) {
					forest.makeSet(k);
				}
				
				for (int k = 0; k < currentSize/2 - 1; k++) {
					forest.union(k, k+1);
				}
				for (int k = currentSize/2; k < currentSize - 1; k++) {
					forest.union(k, k+1);
				}
			}
			
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) {}
			startTime = System.nanoTime();
			
			for (int j = 0; j < timesToLoop; j++) {
				forestList.get(j).union(0, currentSize-1);
			}
			midTime = System.nanoTime();
			for (int j = 0; j < timesToLoop; j++) {
				forestList.get(j);
			}
			endTime = System.nanoTime();
			
			forestTimes[i] = Utils.getTime(startTime, midTime, endTime, timesToLoop);
		}
		
		
		Utils.writeToFile(sizes, forestTimes, "forestBad.txt");
		Utils.writeToFile(sizes, mapTimes, "mapBad.txt");
	}
}
