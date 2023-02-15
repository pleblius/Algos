package assign05;

import java.util.ArrayList;
import java.util.Collections;

public class ArrayListSorter {
	
	private static final int THRESHOLD = 0;
	
	public static <T extends Comparable<? super T>> void mergesort(ArrayList<T> arr) {
		ArrayList<T> tempArray = new ArrayList<T>(arr.size());
		for (int ii = 0; ii < arr.size(); ii++) {
			tempArray.add(arr.get(ii));
		}
		
		mergesort(arr, tempArray, 0, arr.size() - 1);
	}

	private static <T extends Comparable<? super T>> void mergesort(ArrayList<T> arr, ArrayList<T> temp, int left, int right) {
		if ((right - left) <= THRESHOLD) {
			return;
		}
		
		int mid = (left + right)/2;
		
		mergesort(arr, temp, left, mid);
		mergesort(arr, temp, mid + 1, right);
		merge(arr, temp, left, mid, right);
	}
	
	private static <T extends Comparable<? super T>> void merge(ArrayList<T> arr, ArrayList<T> temp, int startL, int startR, int endR) {
		int curL = startL;
		int curR = startR;
		
		// Sort the values from the split arrays into the temporary array
		for (int ii = startL; ii <= endR; ii++) {
			
			var leftVal = arr.get(curL);
			var rightVal = arr.get(curR);
			
			if (leftVal.compareTo(rightVal) < 0 || curR > endR && !(curL > startR - 1)) {
				temp.set(ii, leftVal);
				curL++;
			}
			
			else {
				temp.set(ii, rightVal);
				curR++;
			}
		}
		
		
		// Replace the values in the original array
		for (int ii = startL; ii <= endR; ii++) {
			arr.set(ii, temp.get(ii));
		}
		
	}
	
	public static <T extends Comparable<? super T>> void quicksort(ArrayList<T> arr) {
		
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