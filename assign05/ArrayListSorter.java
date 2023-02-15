package assign05;

import java.util.ArrayList;
import java.util.Collections;

public class ArrayListSorter {
	
	private static final int THRESHOLD = 0;
	private static int PIVOTNUMBER = 1;
	
	public static <T extends Comparable<? super T>> void mergesort(ArrayList<T> arr) {
		ArrayList<T> tempArray = new ArrayList<T>(arr.size());
		for (int ii = 0; ii < arr.size(); ii++) {
			tempArray.add(arr.get(ii));
		}
		
		mergesort(arr, tempArray, 0, arr.size() - 1);
	}

	private static <T extends Comparable<? super T>> void mergesort(ArrayList<T> arr, ArrayList<T> temp, int left, int right) {
		if ((right - left) <= THRESHOLD) {
			// Call insertion sort
			return;
		}
		
		int mid = (left + right)/2;
		
		mergesort(arr, temp, left, mid);
		mergesort(arr, temp, mid + 1, right);
		merge(arr, temp, left, mid + 1, right);
	}
	
	private static <T extends Comparable<? super T>> void merge(ArrayList<T> arr, ArrayList<T> temp, int startL, int startR, int endR) {
		int curL = startL;
		int curR = startR;
		
		// Sort the values from the split arrays into the temporary array
		for (int ii = startL; ii <= endR; ii++) {
			
			if (curR > endR || arr.get(curL).compareTo(arr.get(curR)) < 0 && !(curL > startR - 1)) {
				temp.set(ii, arr.get(curL));
				curL++;
			}
			
			else {
				temp.set(ii, arr.get(curR));
				curR++;
			}
		}
		
		// Replace the values in the original array
		for (int ii = startL; ii <= endR; ii++) {
			arr.set(ii, temp.get(ii));
		}
		
	}
	
	public static <T extends Comparable<? super T>> void quicksort(ArrayList<T> arr) {

		quicksort(arr, 0, arr.size() - 1);
	}
	
	private static <T extends Comparable<? super T>> void quicksort(ArrayList<T> arr, int left, int right) {
		// Find Pivot
		int pivot;
		
			
		
		
		// Partition
		// quicksort(left)
		// quicksort (right)
	}
	
	private static <T extends Comparable<? super T>> void findPivot(ArrayList<T> arr) {
		switch(PIVOTNUMBER) {
		
		// Random pivot
		case 1:
			pivot = (int)Math.random()*(right + 1 - left) + left;
			
		// Sampled pivot
		case 2:
			ArrayList<T> sample = new ArrayList<T>(3);
			sample.add(arr.get(left));
			sample.add(arr.get((right + left) / 2));
			sample.add(arr.get(right));
			
			for (int ii = 0; ii < 3; ii++) {
				if (!(sample.get(ii) == Collections.min(sample)) && !(sample.get(ii) == Collections.max(sample))) 
					return ii
			}
			
		// Middle index
		case 3:
	}
	}
	
	private static <T> void swap(T[] arr, int left, int right) {
		T temp = arr[left];
		
		arr[left] = arr[right];
		arr[right] = temp;
	}
	
	private static <T extends Comparable<? super T>> void partition(ArrayList<T> arr, int left, int right, int pivot) {

	}
	
	/*
	 * Sorting helper methods
	 */
	
	
	
	
	/*
	 *  Array generation methods
	 */
	
	public static ArrayList<Integer> generateAscending(int size) {
		var arr = new ArrayList<Integer>(size);
		
		for (int i = 0; i < size; i++) {
			arr.add(i, i + 1);
		}
		
		return arr;
	}
	
	public static ArrayList<Integer> generatePermuted(int size) {
		var arr = generateAscending(size);
		
		Collections.shuffle(arr);
		
		return arr;
	}
	
	public static ArrayList<Integer> generateDescending(int size) {
		var arr = new ArrayList<Integer>(size);
		
		for (int i = 0; i < size; i++) {
			arr.add(i, size - i);
		}
		
		return arr;
	}
}