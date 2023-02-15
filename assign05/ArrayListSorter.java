package assign05;

import java.util.ArrayList;
import java.util.Collections;

public class ArrayListSorter {
	
	private static final int THRESHOLD = 1;
	
	public static <T extends Comparable<? super T>> void mergesort(ArrayList<T> arr) {
		var tempArray = new ArrayList<T>(arr.size());
		
		mergesort(arr, tempArray, 0, arr.size() - 1);
	}

	private static <T extends Comparable<? super T>> void mergesort(ArrayList<T> arr, ArrayList<T> temp, int left, int right) {
		if ((right - left) <= THRESHOLD) {
			return;
		}
		
		int mid = (left + right)/2;
		
		mergesort(arr, temp, left, mid - 1);
		mergesort(arr, temp, mid, right);
		merge(arr, temp, left, mid, right);
	}
	
	private static <T extends Comparable<? super T>> void merge(ArrayList<T> arr, ArrayList<T> temp, int startL, int startR, int endR) {
		int curL = startL;
		int curR = startR;
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
			arr.set(i, i + 1);
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
			arr.set(i, size - i);
		}
		
		return arr;
	}
}