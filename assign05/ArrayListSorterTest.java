package assign05;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArrayListSorterTest {

	ArrayList<Integer> smallOddArr;
	ArrayList<Integer> verySmallArray;
	ArrayList<Integer> bigArray;
	
	@BeforeEach
	void setup() {
		smallOddArr = ArrayListSorter.generatePermuted(5);
		verySmallArray = ArrayListSorter.generateDescending(2);
		bigArray = ArrayListSorter.generatePermuted(100);
	}
	
	@Test
	void smallOddMergeSortTest() {
		ArrayList<Integer> smallOddTest = ArrayListSorter.generateAscending(5);
		
		ArrayListSorter.mergesort(smallOddArr);
		for (int i = 0; i < smallOddTest.size(); i++) {
			assertEquals(smallOddTest.get(i), smallOddArr.get(i));
		}
		
	}
	
	@Test
	void verySmallMergeTest() {
		var verySmallTest = ArrayListSorter.generateAscending(2);
		
		ArrayListSorter.mergesort(verySmallArray);
		
		for (int i = 0; i < verySmallArray.size(); i++) {
			assertEquals(verySmallTest.get(i), verySmallArray.get(i));
		}
	}
	
	@Test
	void bigMergeTest() {
		var bigTest = ArrayListSorter.generateAscending(100);
		
		ArrayListSorter.mergesort(bigArray);
		
		for (int i = 0; i < bigArray.size(); i++) {
			assertEquals(bigTest.get(i), bigArray.get(i));
		}
	}
	
	@Test
	void smallOddQuickSortTest() {
		ArrayList<Integer> smallOddTest = ArrayListSorter.generateAscending(5);
		
		ArrayListSorter.quicksort(smallOddArr);
		for (int i = 0; i < smallOddTest.size(); i++) {
			assertEquals(smallOddTest.get(i), smallOddArr.get(i));
		}
		
	}
	
	@Test
	void verySmallQuickTest() {
		var verySmallTest = ArrayListSorter.generateAscending(2);
		
		ArrayListSorter.quicksort(verySmallArray);
		
		for (int i = 0; i < verySmallArray.size(); i++) {
			assertEquals(verySmallTest.get(i), verySmallArray.get(i));
		}
	}
	
	@Test
	void bigQuick() {
		var bigTest = ArrayListSorter.generateAscending(100);
		
		ArrayListSorter.quicksort(bigArray);
		
		for (int i = 0; i < bigArray.size(); i++) {
			assertEquals(bigTest.get(i), bigArray.get(i));
		}
	}
	
}
