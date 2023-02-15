package assign05;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArrayListSorterTest {

	ArrayList<Integer> smallOddArr;
	
	@BeforeEach
	void setup() {
		smallOddArr = ArrayListSorter.generatePermuted(5);
	}
	
	@Test
	void smallOddMergeSortTest() {
		ArrayList<Integer> smallOddTest = ArrayListSorter.generateAscending(5);
		
		ArrayListSorter.mergesort(smallOddArr);
		assertTrue(smallOddTest.equals(smallOddArr));
		
	}
	

	
}
