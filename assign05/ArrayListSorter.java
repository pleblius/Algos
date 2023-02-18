package assign05;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This is a static class containing methods to sort ArrayLists using either megesort
 * or quicksort. It also contains three helper methods to generate new arrays in ascending,
 * descending, and random order for testing.
 * Both sort methods also utilize insertion sorting after reaching a certain size threshold
 * to limit recursive calls once arrays are small enough.
 * 
 * @author Tyler Wilcox and Andrew Tolton
 * @version 17 February 2023
 */
public class ArrayListSorter {
	
	private static final int THRESHOLD = 5;
	private static int PIVOTNUMBER = 3;
	
	
	/*
	 * Merge Sort Methods
	 */
	
	/**
	 * Sorts the given array using the merge sort algorithm. This method
	 * generates a temporary ArrayList to store a copy of the ArrayList while it is
	 * being merged.
	 * 
	 * @param <T> Generic type contained in the ArrayList
	 * @param arr The ArrayList to be sorted using merge sort
	 */
	public static <T extends Comparable<? super T>> void mergesort(ArrayList<T> arr) {
		ArrayList<T> tempArray = new ArrayList<T>(arr.size());
		for (int ii = 0; ii < arr.size(); ii++) {
			tempArray.add(arr.get(ii));
		}
		
		mergesort(arr, tempArray, 0, arr.size() - 1);
	}

	/**
	 * Method that performs the merge sort called in the above driver method.
	 * Takes a portion of the list indicated by the left and right boundaries and
	 * recursively divides it into two halves. When the sections of the array are fully sorted,
	 * they are then merged in order into a temporary ArrayList and copied back into the original.
	 * 
	 * When the portions of the list being sorted reach the threshold size THRESHOLD,
	 * they are sorted using insertion sort to reduce stack usage and function call-time.
	 * 
	 * @param <T> Generic type contained in the ArrayList
	 * @param arr The ArrayList to be sorted
	 * @param tempArray The temporary copy ArrayList used to store values during the merge process
	 * @param leftBound The left-most index of the portion of arr being sorted (inclusive)
	 * @param rightBound The right-most index of the portion of arr being sorted (inclusive)
	 */
	private static <T extends Comparable<? super T>> void mergesort(ArrayList<T> arr, ArrayList<T> tempArray, int leftBound, int rightBound) {
		if ((rightBound - leftBound) <= THRESHOLD) {
			insertionSort(arr, leftBound, rightBound);
			return;
		}
		
		int mid = (leftBound + rightBound)/2;
		
		mergesort(arr, tempArray, leftBound, mid);
		mergesort(arr, tempArray, mid + 1, rightBound);
		merge(arr, tempArray, leftBound, mid + 1, rightBound);
	}
	
	/**
	 * Mergesort helper method to perform the merge portion of the merge sort.
	 * Takes in the sorted portion of arr and three indeces, indicating the left-most, right-most, and dividing
	 * indeces of the portion of the array to be merged, then merges them into the temporary array.
	 * The values are then copied over into the orginal array.
	 * 
	 * @param <T> Generic type contained in the ArrayList
	 * @param arr The ArrayList being sorted
	 * @param tempArray The temporary ArrayList to store values while they are being merged
	 * @param startL The left-most index of the section of arr being merged (inclusive)
	 * @param startR The array indicating the start of the right half of the array (inclusive)
	 * @param endR The right-most index of the section of arr being merged (inclusive)
	 */
	private static <T extends Comparable<? super T>> void merge(ArrayList<T> arr, ArrayList<T> tempArray, int startL, int startR, int endR) {
		int curL = startL;
		int curR = startR;
		
		// Sort the values from the split arrays into the temporary array
		for (int ii = startL; ii <= endR; ii++) {
			
			if (curR > endR || arr.get(curL).compareTo(arr.get(curR)) < 0 && !(curL > startR - 1)) {
				tempArray.set(ii, arr.get(curL));
				curL++;
			}
			
			else {
				tempArray.set(ii, arr.get(curR));
				curR++;
			}
		}
		
		// Replace the values in the original array
		for (int ii = startL; ii <= endR; ii++) {
			arr.set(ii, tempArray.get(ii));
		}
	}
	
	/*
	 * Quick Sort Methods
	 */
	
	/**
	 * Sorts the given ArrayList using the quick sort algorithm
	 * 
	 * @param <T> Generic type contained in the ArrayList
	 * @param arr The ArrayList to be sorted
	 */
	public static <T extends Comparable<? super T>> void quicksort(ArrayList<T> arr) {

		quicksort(arr, 0, arr.size() - 1);
	}
	
	/**
	 * Method to perform the quick sort called in the quick sort driver method.
	 * Takes in a portion of the ArrayList arr, partitions it using a pivot generated
	 * by the selected method, and then recursively quick sorts the two sides of the partition.
	 * 
	 * When the threshold value THRESHOLD is reached,
	 * the method calls an insertion sort on the section of the array and kills the recursion
	 * for that section of the list.
	 * 
	 * @param <T> Generic type contained in the ArrayList
	 * @param arr ArrayList being quick sorted
	 * @param leftBound The left-most index of the portion of arr to be sorted (inclusive)
	 * @param rightBound The right-most index of the portion of arr to be sorted (inclusive)
	 */
	private static <T extends Comparable<? super T>> void quicksort(ArrayList<T> arr, int leftBound, int rightBound) {
		// Find Pivot
		if (rightBound - leftBound <= THRESHOLD) {
			insertionSort(arr, leftBound, rightBound);
			return;
		}
		
		int pivotIndex = findPivot(arr, leftBound, rightBound);
		
			
		pivotIndex = partition(arr, leftBound, rightBound, pivotIndex);
		
		quicksort(arr, leftBound, pivotIndex-1);
		quicksort(arr, pivotIndex+1, rightBound);
	}
	
	/**
	 * Helper method to perform the partition section of the quick sort algorithm.
	 * Takes in a portion of the array being sorted with a given pivot index, then separates
	 * the section into values greater than or less than the pivot, then returns the new index
	 * location of the pivot.
	 * 
	 * @param <T> Generic type contained in the ArrayList
	 * @param arr The ArrayList being sorted
	 * @param leftBound The left-most index of the portion of arr being partitioned (inclusive)
	 * @param rightBound The right-most index of the portion of arr being partitioned (inclusive)
	 * @param pivotIndex The index of the value being used as the pivot
	 * @return The new index of the value that was used as the pivot
	 */
	private static <T extends Comparable<? super T>> int partition(ArrayList<T> arr, int leftBound, int rightBound, int pivotIndex) {
		swap(arr, pivotIndex, rightBound);
		
		int leftPointer = leftBound;
		int rightPointer = rightBound;
		
		while (leftPointer < rightPointer) {
			while (arr.get(leftPointer).compareTo(arr.get(rightBound)) <= 0 && leftPointer < rightPointer) {
				leftPointer++;
			}
			while (arr.get(rightPointer).compareTo(arr.get(rightBound)) >= 0 && rightPointer > leftPointer) {
				rightPointer--;
			}
			swap(arr, leftPointer, rightPointer);
		}
		
		swap(arr, leftPointer, rightBound);
		return leftPointer;
	}
	
	/**
	 * Quick sort helper method containing several methods to obtain a pivot, to be
	 * used in the partition section of the quick sort algorithm
	 * 
	 * Method 1: Random Pivot
	 * 		Generates a random index in the array
	 * Method 2: Sampled Median
	 * 		Takes the left-most, right-most, and middle values of the array and returns the
	 * 		index of the median value of this sample
	 * Method 3: Naive End Index
	 * 		Returns the right-most index of the array
	 * 
	 * @param <T> Generic type contained in the ArrayList
	 * @param arr The ArrayList being sorted
	 * @param leftBound The left-most index of the portion of arr being partitioned (inclusive)
	 * @param rightBound The right-most index of the portion of arr being partitioned (inclusive)
	 * @return
	 */
	private static <T extends Comparable<? super T>> int findPivot(ArrayList<T> arr, int leftBound, int rightBound) {

		switch(PIVOTNUMBER) {
		
		// Random pivot
		case 1:
			return (int)Math.random()*(rightBound + 1 - leftBound) + leftBound;
			
		// Sampled median
		case 2:
			ArrayList<T> sample = new ArrayList<T>(3);
			sample.add(arr.get(leftBound));
			sample.add(arr.get((rightBound + leftBound) / 2));
			sample.add(arr.get(rightBound));
			
			insertionSort(sample, 0, 2);
			
			if (sample.get(1) == arr.get(leftBound))
				return leftBound;
			else if(sample.get(1) == arr.get(rightBound))
				return rightBound;
			else
				return (rightBound + leftBound)/2;
			
		// Naive end index
		case 3:
			return rightBound;
			
		// Middle index
		default:
			return (rightBound + leftBound)/2;
		}
	}
	
	/*
	 * Sorting helper methods
	 */
	
	/**
	 * A sorting helper method that performs an insertion sort on a selected portion of an array.
	 * Used to minimize function calls near the end of the merge sort and quick sort algorithms,
	 * when the simplicity of insertion sort can slightly increase efficiency and decrease stack
	 * memory consumption.
	 * 
	 * @param <T> Generic type of the ArrayList
	 * @param arr ArrayList containing the section to be sorted
	 * @param leftBound The leftmost index of the array containing the section to be sorted (inclusive)
	 * @param rightBound The rightmost index of the array containing the section to be sorted (inclusive)
	 */
	private static <T extends Comparable<? super T>> void insertionSort(ArrayList<T> arr, int leftBound, int rightBound) {
		T temp;
		
		for (int i = leftBound; i <= rightBound; i++) {
			temp = arr.get(i);
			
			for (int j = i; j > leftBound && arr.get(j - 1).compareTo(arr.get(j)) > 0; j--) {
				arr.set(j, arr.get(j-1));
				arr.set(j-1, temp);
			}
		}
	}
	
	/**
	 * ArrayList helper method that swaps the values of the array at the two given indeces.
	 * 
	 * @param <T> Generic type contained in the ArrayList
	 * @param arr The array containing the values to be swapped
	 * @param leftIndex The index value of the left item to be swapped
	 * @param rightIndex The index value of the right item to be swapped
	 */
	private static <T> void swap(ArrayList<T> arr, int leftIndex, int rightIndex) {
		T temp = arr.get(leftIndex);
		
		arr.set(leftIndex, arr.get(rightIndex));
		arr.set(rightIndex, temp);
	}	
	
	/*
	 *  Array generation methods
	 */
	
	/**
	 * Generates an Integer ArrayList of the given size in ascending order,
	 * with a minimum value of 1 and a maximum value of size.
	 * E.G. {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}
	 * 
	 * @param size The size and maximum value of the generated array
	 * @return ArrayList<Integer>
	 */
	public static ArrayList<Integer> generateAscending(int size) {
		var arr = new ArrayList<Integer>(size);
		
		for (int i = 0; i < size; i++) {
			arr.add(i, i + 1);
		}

		return arr;
	}
	
	/**
	 * Generates an Integer ArrayList of the given size in random order,
	 * with a minimum value of 1 and a maximum value of size.
	 * E.G. {3, 1, 6, 5, 2, 4}
	 * 
	 * @param size The size and maximum value of the generated array
	 * @return ArrayList<Integer>
	 */
	public static ArrayList<Integer> generatePermuted(int size) {
		var arr = generateAscending(size);
		
		Collections.shuffle(arr);
		
		return arr;
	}
	
	/**
	 * Generates an Integer ArrayList of the given size in descending order,
	 * with a minimum value of 1 and a maximum value of size.
	 * E.G. {10, 9, 8, 7, 6, 5, 4, 3, 2, 1}
	 * 
	 * @param size The size and maximum value of the generated array
	 * @return ArrayList<Integer>
	 */
	public static ArrayList<Integer> generateDescending(int size) {
		var arr = new ArrayList<Integer>(size);
		
		for (int i = 0; i < size; i++) {
			arr.add(i, size - i);
		}
		
		return arr;
	}
}