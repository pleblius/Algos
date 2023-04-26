package exammakeup;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import analysis.Utils;

public class MedianAnalysis {
	
	public static void main(String[] args) {
		int timesToLoop = 5000;
		int numSizes = 10;
		int baseSize = 5000;
		
		int[] currentArray;
		
		int[] sizes = new int[numSizes];
		int currentSize;
		long[] selectTimes = new long[numSizes];
		long[] sortTimes = new long[numSizes];
		
		long startTime;
		long midTime;
		long endTime;
		
		for (int i = 0; i < numSizes; i++) {
			sizes[i] = baseSize*(i + 1);
		}
		
		List<int[]> testArrays;
		List<int[]> sortArrays;
		
		for (int i = 0; i < numSizes; i++) {
			System.out.println(sizes[i]);
			
			testArrays = new ArrayList<int[]>(timesToLoop);
			sortArrays = new ArrayList<int[]>(timesToLoop);
			
			for (int j = 0; j < timesToLoop; j++) {
				currentArray = new int[sizes[i]];
				
				for (int k = 0; k < sizes[i]; k++) {
					currentArray[k] = k + 1;
				}
				
				Median.shuffle(currentArray);
				
				testArrays.add(Arrays.copyOf(currentArray, sizes[i]));
				sortArrays.add(Arrays.copyOf(currentArray, sizes[i]));
			}
			
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) {}
			
			startTime = System.nanoTime();
			for (int j = 0; j < timesToLoop; j++) {
				Median.median(testArrays.get(j));
			}
			
			midTime = System.nanoTime();
			
			for (int j = 0; j < timesToLoop; j++) {
				testArrays.get(j);
			}
			
			endTime = System.nanoTime();
			
			selectTimes[i] = Utils.getTime(startTime, midTime, endTime, timesToLoop);
			
			startTime = System.nanoTime();


			startTime = System.nanoTime();
			for (int j = 0; j < timesToLoop; j++) {
				Median.quickSort(sortArrays.get(j));
			}
			
			midTime = System.nanoTime();
			
			for (int j = 0; j < timesToLoop; j++) {
				sortArrays.get(j);
			}
			
			endTime = System.nanoTime();
			
			sortTimes[i] = Utils.getTime(startTime, midTime, endTime, timesToLoop);
			
		}
		
		Utils.writeToFile(sizes, selectTimes, "quickselect.dat");
		Utils.writeToFile(sizes, sortTimes, "quicksort.dat");
	}
}
