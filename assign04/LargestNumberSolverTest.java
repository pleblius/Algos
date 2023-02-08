package assign04;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;


public class LargestNumberSolverTest {

	@BeforeEach
	void setup() {
		Integer[] arr1 = {1, 0, 2};
		Integer[] arr2 = {15, 25, 73, 3};
	}
	
	// Basic Tests
	
	@Test
	void basicInsertionSort() {
		assertTrue(Arrays.equals(arr1, new Integer[] {1, 0, 2}));
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
	
	
	
}
