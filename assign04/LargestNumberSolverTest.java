package assign04;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.math.BigInteger;


public class LargestNumberSolverTest {
	Integer[] arr1;
	Integer[] arr2;
	Integer[] arr3;
	Integer[] arr4;
	Integer[] randArr;
	Comparator<Integer> basicCmp = (Integer lhs, Integer rhs) -> {return lhs.compareTo(rhs);};
	
	@BeforeEach
	void setup() {
		arr1 = new Integer[] {1, 0, 2};
		arr2 = new Integer[] {25, 51, 37};
		arr3 = new Integer[] {9, 9, 9, 9, 9, 9, 9, 9, 999999999, 9, 9, 9, 1};
		arr4 = new Integer[] {9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 111, 1};
		
		for (int i = 0; i < 100; i++) {
			randArr[i] = (int) (Math.random() * 100); 
		}
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
		
	}
	
	@Test 
	void basicKthLargest() {
		
	}
	
	@Test
	void basicReadFile() {
		
	}
	
	// Exception Tests
	
	@Test
	void findLargestIntException() {
		assertThrows(OutOfRangeException.class, () -> {LargestNumberSolver.findLargestInt(arr3);});
	}
	
	@Test
	void findLargestLongException() {
		assertThrows(OutOfRangeException.class, () -> {LargestNumberSolver.findLargestLong(arr3);});
	}
	
	
	@Test
	void kthLargestIllegalArgument() {
		
	}
	
	// Other Tests
	@Test
	void basicComparatorTest() {
		
	}
	
}
