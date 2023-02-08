package assign04;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * This class contains methods to perform operations on arrays of integers,
 * calculating the biggest possible number that can be assembled from the integers in the provided arrays.
 * 
 * @author Andrew Tolton and Tyler Wilcox
 * @version February 07, 2023
 *
 */

public class LargestNumberSolver {
	
	/**
	 * Performs an insertion sort on the given generic array, using the comparator cmp to determine
	 * sorting method. The array parameter is modified during this sort.
	 * 
	 * @param <T> Generic type T
	 * @param arr Array of generic type T to be sorted using insertion sort
	 * @param cmp A comparator object or lambda function to determine how to sort the given array
	 */
	public static <T> void insertionSort(T[] arr, Comparator<? super T> cmp) {
		T temp;
		
		for (int i = 0; i < arr.length; i++) {
			temp = arr[i];
			
			for (int j = i; j > 0 && cmp.compare(arr[j-1], arr[j]) > 0; j--) {
				arr[j] = arr[j-1];
				arr[j-1] = temp;
			}
		}
	}
	
	/**
	 * Finds the largest possible number that can be assembled from the integers contained in the given array.
	 * Result is a BigInteger object to avoid possible overflow concerns.
	 * 
	 * @param arr The array to be evaluated to find the biggest number.
	 * @return BigInteger
	 */
	public static BigInteger findLargestNumber(Integer[] arr) {
		if (arr.length == 0)
			return new BigInteger("0");
		
		Comparator<Integer> intCmp = (LHS, RHS) -> {
			
			StringBuilder stringLHS = new StringBuilder(LHS.toString());
			StringBuilder stringRHS = new StringBuilder(RHS.toString());
			
			StringBuilder XY = new StringBuilder(stringLHS);
			StringBuilder YX = new StringBuilder(stringRHS);
			
			XY.append(stringRHS);
			YX.append(stringLHS);
			
			return -XY.compareTo(YX);
		};
		
		Integer[] sortedArr = Arrays.copyOf(arr, arr.length);
		insertionSort(sortedArr, intCmp);
		
		StringBuilder big = new StringBuilder();
		
		for (Integer num : sortedArr) {
			big.append(num);
		}
		
		
		return new BigInteger(big.toString());
	}
	
	/**
	 * Finds the largest possible number that can be assembled from the integers contained in the given array.
	 * Throws an OutOfRangeException if the result would be too large to be represented as an int.
	 * @param arr Array to be evaluated
	 * @return int
	 * @throws OutOfRangeException if the supplied array creates an overflow
	 */
	public static int findLargestInt(Integer[] arr) throws OutOfRangeException {
		BigInteger big = findLargestNumber(arr);
		
		if (big.compareTo(new BigInteger(Integer.toString(Integer.MAX_VALUE))) > 0) {
			throw new OutOfRangeException("Result is too large for type int");
		}
		
		return big.intValue();
	}
	
	/**
	 * Finds the largest possible number that can be assembled from the integers contained in the given array.
	 * Throws an OutOfRangeException if the result would be too large to be represented as a long.
	 * @param arr Array to be evaluated
	 * @return long
	 * @throws OutOfRangeException if the supplied array creates an overflow
	 */
	public static long findLargestLong(Integer[] arr) throws OutOfRangeException {
		BigInteger big = findLargestNumber(arr);
		
		if (big.compareTo(new BigInteger(Long.toString(Long.MAX_VALUE))) > 0) {
			throw new OutOfRangeException("Result is too large for type long");
		}
		
		return big.longValue();
	}
	
	/**
	 * Adds the sum of the largest numbers that can be assembled from each integer array in the 
	 * provided list.
	 * 
	 * @param list A list of Integer[] arrays whose biggest numbers are to be summed
	 * @return BigInteger
	 */
	public static BigInteger sum(List<Integer[]> list) {
		BigInteger total = BigInteger.ZERO;
		
		for (Integer[] i : list) {
			total = total.add(findLargestNumber(i));
		}
		
		return total;
	}

	/**
	 * Finds the kth largest biggest number of the Integer[] arrays contained in the provided list.
	 * Throws an IllegalArgumentException if k is out of bounds of the size of the provided list.
	 * 
	 * @param list The list of Integer[] arrays to be evaluated
	 * @param k The order of the largest number to be returned
	 * @return Integer[]
	 * @throws IllegalArgumentException
	 */
	public static Integer[] findKthLargest(List<Integer[]> list, int k) throws IllegalArgumentException {
		if (k >= list.size()) {
			throw new IllegalArgumentException("That index is out of bounds.");
		}
		
		BigInteger[] bigList = new BigInteger[list.size()];
		
		for (int i = 0; i < list.size(); i++) {
			bigList[i] = (findLargestNumber(list.get(i)));
		}
		
		BigInteger[] bigListCopy = Arrays.copyOf(bigList, bigList.length);
		insertionSort(bigList,(lhs, rhs) -> {return -lhs.compareTo(rhs);});
		
		for (int i = 0; i < bigList.length; i++) {
			if (bigListCopy[i].equals(bigList[k])) {
				return list.get(i);
			}
		}
		
		return null;
	}
	
	/**
	 * Parses a .txt file to create a list of Integer[] arrays to be used in these methods.
	 * Each line is parsed into a separate Integer[] array, with spaces delimiting new entries in each array.
	 * 
	 * @param filename A string containing the filename to be parsed
	 * @return List<Integer[]>
	 */
	public static List<Integer[]> readFile(String filename) {
		
		List<Integer[]> list = new ArrayList<Integer[]>();
		Integer[] intArray;
		String line = new String();
		
		File f = new File(filename);
		
		try (Scanner fileReader = new Scanner(f);) {			
			
			// for each line in the file
			while(fileReader.hasNextLine()) {
				line = fileReader.nextLine();
				String[] intStringArr = line.split(" ");
				intArray = new Integer[intStringArr.length];
				
				// Add each int to the intArray
				for (int i = 0; i < intStringArr.length; i++) {
					intArray[i] = Integer.parseInt(intStringArr[i]);
				}
				
				// Add the int array to the list
				list.add(intArray);
			}
			
			return list;
			
		} catch(FileNotFoundException e) {
			
			return list;
		}
	}
}
