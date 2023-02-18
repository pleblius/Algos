package assign05;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A JUnit testing suite for testing the implementation of the quicksort,
 * mergesort, and array generation methods in the ArrayListSorter class.
 * 
 * @author Tyler Wilcox and Andrew Tolton
 * @version 17 February 2023
 */
public class ArrayListSorterTest {

	ArrayList<Integer> testArray;
	ArrayList<String> testStringArray;
	
	ArrayList<Integer> ascendingIntArray;
	ArrayList<Integer> descendingIntArray;
	ArrayList<Integer> randomIntArray;
	ArrayList<Integer> equalIntArray;
	
	int intSize = 1000;
	int stringSize = 1000;
	
	ArrayList<String> stringArray;
	ArrayList<String> equalStringArray;
	
	@BeforeEach
	void setup() {
		testArray = new ArrayList<Integer>();
		testStringArray = new ArrayList<String>();
		
		ascendingIntArray = new ArrayList<Integer>();
		descendingIntArray = new ArrayList<Integer>();
		randomIntArray = new ArrayList<Integer>();
		equalIntArray = new ArrayList<Integer>();
		
		for (int i = 0; i < intSize; i++) {
			equalIntArray.add(10);
		}
		
		stringArray = new ArrayList<String>();
		equalStringArray = new ArrayList<String>();
		
		int length;
		int ch;
		
		for (int i = 0; i < stringSize; i++) {
			length = (int)(Math.random()*5) + 1;
			String str = new String();
			
			for (int j = 0; j < length; j++) {
				ch = (int)(Math.random()*27) + 97;
				
				str+= (char)ch;
			}
			
			stringArray.add(str);
			equalStringArray.add("test");
		}
	}
	
	/*
	 * Array Generation Tests
	 */
	
	@Test
	void generateAscendingTest() {
		ascendingIntArray = ArrayListSorter.generateAscending(intSize);
		
		for (int i = 0; i < intSize; i++) {
			assertEquals(i + 1, ascendingIntArray.get(i), "Ascending test failed at index: " + i);
		}
	}
	
	@Test
	void generateDescendingTest() {
		descendingIntArray = ArrayListSorter.generateDescending(intSize);
		
		for (int i = intSize; i > 0; i--) {
			assertEquals(i, descendingIntArray.get(intSize - i), "Descending test failed at index: " + i);
		}
	}
	
	@Test
	void generatePermutedTest() {
		randomIntArray = ArrayListSorter.generatePermuted(intSize);
		
		int identCount = 0;
		
		for (int i = 0; i < intSize; i++) {
			if (i + 1 == randomIntArray.get(i))
				identCount++;
		}
		
		assertTrue(identCount < intSize/2, "Random array failed with too many consistent values.");
	}
	
	/*
	 * Integer Sorting Tests
	 */
	
	@Test
	void intQuicksortTest() {
		testArray = ArrayListSorter.generateAscending(intSize);
		
		for (int i = 0; i < intSize; i++) {
			ascendingIntArray = ArrayListSorter.generateAscending(i);
			descendingIntArray = ArrayListSorter.generateDescending(i);
			randomIntArray = ArrayListSorter.generatePermuted(i);
			
			ArrayListSorter.quicksort(ascendingIntArray);
			ArrayListSorter.quicksort(descendingIntArray);
			ArrayListSorter.quicksort(randomIntArray);
			
			for (int j = 0; j < i; j++) {
				assertEquals(testArray.get(j), ascendingIntArray.get(j),"Quicksort Int test failed with ascending array at index: " + i + " " + j);
				assertEquals(testArray.get(j), descendingIntArray.get(j),"Quicksort Int test failed with descending array at index: " + i + " " + j);
				assertEquals(testArray.get(j), randomIntArray.get(j),"Quicksort Int test failed with randomy array at index: " + i + " " + j);
			}
		}
	}
	
	@Test
	void intMergesortTest() {
		testArray = ArrayListSorter.generateAscending(intSize);
		
		for (int i = 0; i < intSize; i++) {
			ascendingIntArray = ArrayListSorter.generateAscending(i);
			descendingIntArray = ArrayListSorter.generateDescending(i);
			randomIntArray = ArrayListSorter.generatePermuted(i);
			
			ArrayListSorter.mergesort(ascendingIntArray);
			ArrayListSorter.mergesort(descendingIntArray);
			ArrayListSorter.mergesort(randomIntArray);
			
			for (int j = 0; j < i; j++) {
				assertEquals(testArray.get(j), ascendingIntArray.get(j),"Quicksort Int test failed with ascending array at index: " + i + " " + j);
				assertEquals(testArray.get(j), descendingIntArray.get(j),"Quicksort Int test failed with descending array at index: " + i + " " + j);
				assertEquals(testArray.get(j), randomIntArray.get(j),"Quicksort Int test failed with randomy array at index: " + i + " " + j);
			}
		}
	}
	
	/*
	 * Non-Integer Sorting Tests
	 */
	
	@Test
	void stringQuicksortTest() {
		ArrayListSorter.quicksort(stringArray);
		
		for (int i = 0; i < stringSize-1; i++) {
			assertTrue(stringArray.get(i).compareTo(stringArray.get(i+1)) <= 0, "String array quicksort test failed at index: " + i);
		}
	}
	
	@Test
	void stringMergesortTest() {
		ArrayListSorter.mergesort(stringArray);
		
		for (int i = 0; i < stringSize-1; i++) {
			assertTrue(stringArray.get(i).compareTo(stringArray.get(i+1)) <= 0, "String array mergesort test failed at index: " + i);
		}
	}
	
	/*
	 * Equal-value array tests
	 */
	
	@Test
	void equalIntQuicksortTest() {
		ArrayListSorter.quicksort(equalIntArray);
		
		for (int i = 0; i < intSize; i++) {
			assertEquals(10, equalIntArray.get(i), "Equal int quicksort test failed at index: " + i);
		}
	}
	
	@Test
	void equalIntMergesortTest() {
		ArrayListSorter.mergesort(equalIntArray);
		
		for (int i = 0; i < intSize; i++) {
			assertEquals(10, equalIntArray.get(i), "Equal int mergesort test failed at index: " + i);
		}
	}
	
	@Test
	void equalStringQuicksortTest() {
		ArrayListSorter.quicksort(equalStringArray);
		
		for (int i = 0; i < intSize; i++) {
			assertEquals("test", equalStringArray.get(i), "Equal string quicksort test failed at index: " + i);
		}
	}
	
	@Test
	void equalStringMergesortTest() {
		ArrayListSorter.mergesort(equalStringArray);
		
		for (int i = 0; i < intSize; i++) {
			assertEquals("test", equalStringArray.get(i), "Equal string mergesort test failed at index: " + i);
		}
	}
}
