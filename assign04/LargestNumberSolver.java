package assign04;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class LargestNumberSolver {
	
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
	
	public static int findLargestInt(Integer[] arr) throws OutOfRangeException {
		
		
		return  5;
	}
	
	public static long findLargestLong(Integer[] arr) throws OutOfRangeException {
		
		return 5;
	}
	
	public static BigInteger sum(List<Integer[]> list) {
		BigInteger total = BigInteger.ZERO;
		
		for (Integer[] i : list) {
			total = total.add(findLargestNumber(i));
		}
		
		return total;
	}

	public static Integer[] findKthLargest(List<Integer[]> list, int k) throws IllegalArgumentException {
		return null; // stub
	}
	
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
