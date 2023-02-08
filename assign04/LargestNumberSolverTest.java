package assign04;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.math.BigInteger;

/**
 * A class containing a variety of J Unit tests to be performed to confirm proper functioning
 * of the LargestNumberSolver class.
 * 
 * @author Andrew Tolton and Tyler Wilcox
 * @version 07 February, 2023
 */
public class LargestNumberSolverTest {
	Integer[] arr1;
	Integer[] arr2;
	Integer[] arr3;
	Integer[] arr4;
	Integer[] randArr;
	Integer[] compArr;
	Comparator<Integer> basicCmp = (Integer lhs, Integer rhs) -> {return lhs.compareTo(rhs);};
	
	List<Integer[]> testList;
	List<Integer[]> testList2;
	
	
	@BeforeEach
	void setup() {
		arr1 = new Integer[] {1, 0, 2};
		arr2 = new Integer[] {25, 51, 37};
		arr3 = new Integer[] {9, 9, 9, 9, 9, 9, 9, 9, 999999999, 9, 9, 9, 1};
		arr4 = new Integer[] {9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 111, 1};
		randArr = new Integer[100];
		
		for (int i = 0; i < 100; i++) {
			randArr[i] = (int) (Math.random() * 100); 
		}
		
		testList = new ArrayList<Integer[]>();
		testList.add(arr1); testList.add(arr2);
		
		testList2 = new ArrayList<Integer[]>();
		testList2.add(arr1); testList2.add(arr2); testList2.add(arr3); testList2.add(arr4);
		
		compArr = new Integer[] {7, 78, 72};
	}
	
	// Basic Tests
	
	@Test
	void basicInsertionSort() {
		Integer[] testArr1 = {0, 1, 2};
		Integer[] testArr2 = {25, 37, 51};
		
		LargestNumberSolver.insertionSort(arr1, basicCmp);
		LargestNumberSolver.insertionSort(arr2, basicCmp);
		
		assertTrue(Arrays.equals(testArr1, arr1));
		assertTrue(Arrays.equals(testArr2, arr2));
		
		Integer[] testArr3 = Arrays.copyOf(randArr, randArr.length);
		Arrays.sort(testArr3);
		LargestNumberSolver.insertionSort(randArr, basicCmp);
		
		assertTrue(Arrays.equals(testArr3, randArr));
	}
	
	@Test
	void basicFindLargestNumber() {
		BigInteger big1 = new BigInteger("210");
		BigInteger big2 = new BigInteger("513725");
		BigInteger big3 = new BigInteger("999999999999999999991");
		
		assertEquals(big1, LargestNumberSolver.findLargestNumber(arr1));
		assertEquals(big2, LargestNumberSolver.findLargestNumber(arr2));
		assertEquals(big3, LargestNumberSolver.findLargestNumber(arr3));
	}
	
	@Test
	void basicFindLargestInt() {
		int big1 = 210;
		int big2 = 513725;
		
		assertEquals(big1, LargestNumberSolver.findLargestInt(arr1));
		assertEquals(big2, LargestNumberSolver.findLargestInt(arr2));
	}
	
	@Test
	void basicFindLargestLong() {
		long big1 = 210;
		long big2 = 513725;
		long big3 = 999999999991111l;
		
		assertEquals(big1, LargestNumberSolver.findLargestLong(arr1));
		assertEquals(big2, LargestNumberSolver.findLargestLong(arr2));
		assertEquals(big3, LargestNumberSolver.findLargestLong(arr4));
	}
	
	@Test 
	void basicSum() {
		BigInteger big1 = new BigInteger("210");
		BigInteger big2 = new BigInteger("513725");
		BigInteger big3 = new BigInteger("999999999999999999991");
		BigInteger big4 = new BigInteger("999999999991111");
		
		List<Integer[]> singleTestList = new ArrayList<Integer[]>();
		singleTestList.add(arr1);
		
//		assertEquals(big1, LargestNumberSolver.sum(singleTestList));
		assertEquals(big1.add(big2),LargestNumberSolver.sum(testList));
		assertEquals(big1.add(big2.add(big3.add(big4))), LargestNumberSolver.sum(testList2));
	}
	
	@Test 
	void basicKthLargest() {
		assertTrue(Arrays.equals(arr2, LargestNumberSolver.findKthLargest(testList, 0)));
		assertTrue(Arrays.equals(arr1, LargestNumberSolver.findKthLargest(testList, 1)));
	}
	
	@Test
	void basicReadFile() {
		List<Integer[]> testArray1 = new ArrayList<Integer[]>();
		
		testArray1.add(new Integer[] {0, 1, 2});
		testArray1.add(new Integer[] {3, 2, 1});
		testArray1.add(new Integer[] {1});
		
		List<Integer[]> readArray1 = new ArrayList<Integer[]>();
		readArray1 = LargestNumberSolver.readFile("testfile1.txt");
		
		for (int i = 0; i < readArray1.size(); i++) {
			assertTrue(Arrays.equals(testArray1.get(i), readArray1.get(i)));
		}
	}
	
	// Exception Tests
	
	@Test
	void findLargestIntException() {
		assertThrows(OutOfRangeException.class, () -> {LargestNumberSolver.findLargestInt(arr3);});
		assertThrows(OutOfRangeException.class, () -> {LargestNumberSolver.findLargestInt(arr4);});
	}
	
	@Test
	void findLargestLongException() {
		assertThrows(OutOfRangeException.class, () -> {LargestNumberSolver.findLargestLong(arr3);});
	}
	
	
	@Test
	void kthLargestIllegalArgument() {
		for (int i = 2; i < 10; i++) {
			assertThrows(IllegalArgumentException.class, () -> {LargestNumberSolver.findKthLargest(testList, 4);});
	
		}
	}
	
	// Edge Tests
	
	@Test
	void emptyListSum() {
		List<Integer[]> emptyList = new ArrayList<Integer[]>();
		assertEquals(new BigInteger("0"), LargestNumberSolver.sum(emptyList));
	}
	
	@Test
	void emptyLargestNumber() {
		Integer[] emptyList = new Integer[0];
		assertEquals(new BigInteger("0"), LargestNumberSolver.findLargestNumber(emptyList));
	}
	
	@Test
	void emptyReadFile() {
		var emptyList = new ArrayList<Integer[]>();
		
		assertTrue(emptyList.equals(LargestNumberSolver.readFile("emptytestfile.txt")));
	}
	
	@Test
	void readSuppliedFile() {
		BigInteger test = new BigInteger("8851");
		List<Integer[]> readList = LargestNumberSolver.readFile("integers.txt");
		
		assertEquals(test, LargestNumberSolver.findLargestNumber(readList.get(7)));
	}
}
