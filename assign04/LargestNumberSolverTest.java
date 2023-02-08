package assign04;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Comparator;


public class LargestNumberSolverTest {
	Integer[] arr1;
	Integer[] arr2;
	Comparator<Integer> basicCmp;
	
	@BeforeEach
	void setup() {
		arr1 = new Integer[] {1, 0, 2};
		arr2 = new Integer[] {25, 51, 37};
		basicCmp = (Integer lhs, Integer rhs) -> {return lhs.compareTo(rhs);};
	}
	
	// Basic Tests
	
	@Test
	void basicInsertionSort() {
		Integer[] testArr1 = {0, 1, 2};
		Integer[] testArr2 = {25, 37, 51};
		
		LargestNumberSolver.insertionSort(arr2, basicCmp);
		
		assertTrue(Arrays.equals(testArr1, arr1));
		assertTrue(Arrays.equals(testArr2, arr2));
	}
	
	@Test
	void basicFindLargestNumber() {

	}
	
	@Test
	void basicFindLargestInt() {
		
	}
	
	@Test
	void basicFindLargestLong() {
		
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
		
	}
	
	@Test
	void findLargestLongException() {
		
	}
	
	@Test
	void noFindLargestNumberException() {
		
	}
	
	@Test
	void kthLargestIllegalArgument() {
		
	}
	
	// Other Tests
	@Test
	void basicComparatorTest() {
		
	}
	
}
