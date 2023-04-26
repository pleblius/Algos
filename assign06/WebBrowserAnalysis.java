package assign06;

import java.util.ArrayList;

import analysis.Utils;

public class WebBrowserAnalysis {

	public static void main(String[] args) {
		
		Integer testInt = 0;
		
		int timesToLoop = 1000;
		int numSizes = 10;
		int baseSize = 5000;
		
		
		int[] sizes = new int[numSizes];
		int currentSize;
		long[] linkedTimes = new long[numSizes];
		long[] arrayTimes = new long[numSizes];
		
		long startTime;
		long midTime;
		long endTime;
		

		
		for (int i = 0; i < numSizes; i++) {
			sizes[i] = (i+1)*baseSize;
		}
		
		for (int i = 0; i < numSizes; i++) {
			
			currentSize = sizes[i];
			
			var linkedLists = new ArrayList<LinkedListStack<Integer>>(timesToLoop);
			var arrayLists = new ArrayList<ArrayStack<Integer>>(timesToLoop);
			
			for (int j = 0; j < timesToLoop; j++) {
				
				linkedLists.add(new LinkedListStack<Integer>());
				arrayLists.add(new ArrayStack<Integer>());
				
				for (int k = 0; k < currentSize; k++) {
					linkedLists.get(j).push(testInt);
					arrayLists.get(j).push(testInt);
				}
			}
			
			System.out.println(currentSize);
			
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) {}
			
			startTime = System.nanoTime();
			for (int j = 0; j < timesToLoop; j++) {
				linkedLists.get(j).peek();
			}
			
			midTime = System.nanoTime();
			for (int j = 0; j < timesToLoop; j++) {
				linkedLists.get(j);
			}
			
			endTime = System.nanoTime();
			
			linkedTimes[i] = Utils.getTime(startTime, midTime, endTime, timesToLoop);
			
			
			startTime = System.nanoTime();
			
			while (System.nanoTime() - startTime < 1000000000) {}
			
			startTime = System.nanoTime();
			for (int j = 0; j < timesToLoop; j++) {
				arrayLists.get(j).peek();
			}
			
			midTime = System.nanoTime();
			for (int j = 0; j < timesToLoop; j++) {
				arrayLists.get(j);
			}
			
			endTime = System.nanoTime();
			
			arrayTimes[i] = Utils.getTime(startTime, midTime, endTime, timesToLoop);
		}
	
		Utils.writeToFile(sizes, linkedTimes, "linkedTimes.dat");
		Utils.writeToFile(sizes, arrayTimes, "arrayTimes.dat");
	}
}
