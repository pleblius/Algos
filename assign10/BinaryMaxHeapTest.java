/**
 * 
 */
package assign10;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test cases for the BinaryMaxHeap class
 * 
 * @author Tyler Wilcox and Andrew Tolton
 * @version 11 April, 2023
 */
class BinaryMaxHeapTest {
	BinaryMaxHeap<Integer> integerHeap;
	BinaryMaxHeap<Integer> testHeap;
	BinaryMaxHeap<String> stringHeap;
	
	Comparator<Integer> comp;
	Comparator<String> stringComp;
	
	BinaryMaxHeap<Integer> intListHeap;
	BinaryMaxHeap<String> stringListHeap;
	
	List<Integer> intList;
	List<String> stringList;
	
	@BeforeEach
	void setUp() {
		comp = (a,b) -> (b - a);
		stringComp = (a,b) -> (b.compareTo(a));
		
		integerHeap = new BinaryMaxHeap<>();
		stringHeap = new BinaryMaxHeap<>();
	
		intList = new ArrayList<Integer>();
		stringList = new ArrayList<String>();
		
		for (int i = 0; i < 100; i++) {
			intList.add(i);
		}
		
		intListHeap = new BinaryMaxHeap<>(intList);
		
		stringList = Arrays.asList("apple", "banana", "cantaloupe", "durian", "eggplant");
		
		stringListHeap = new BinaryMaxHeap<>(stringList);
	}

	// Test with blank, with comparator, with list, and with list and comparator
	
	@Test
	void sizeTest() {
		assertEquals(0, integerHeap.size());
		
		integerHeap.add(1);
		integerHeap.add(2);
		
		stringHeap.add("a");
		stringHeap.add("b");
		stringHeap.add("c");
	
		
		assertEquals(2, integerHeap.size());
		assertEquals(3, stringHeap.size());
	}
	
	@Test
	void testSizeLists() {
		assertEquals(100, intListHeap.size());
		assertEquals(5, stringListHeap.size());
	}
	
	@Test
	void testClear() {
		assertTrue(!intListHeap.isEmpty());
		
		intListHeap.clear();
		
		assertTrue(intListHeap.isEmpty());
		assertEquals(0, intListHeap.size());
	}
	
	// Test add
	@Test
	void testAddSimple() {
		integerHeap.add(1);
		integerHeap.add(2);
		
		assertEquals(2, integerHeap.size());
		
		assertEquals(2, integerHeap.peek());
		assertEquals(2, integerHeap.extractMax());
		
		assertEquals(1, integerHeap.peek());
		assertEquals(1, integerHeap.extractMax());
	}
	
	@Test
	void testAddAscending() {
		intListHeap = new BinaryMaxHeap<>(intList);
		
		for (int i = 0; i < 100; i++) {
			integerHeap.add(i);
		}
		
		for (int i = 0; i < 100; i++) {
			assertEquals(100 - i, integerHeap.size(), "Failed at index " + i);
			assertEquals(100 - i, intListHeap.size(), "Failed at index " + i);
			
			assertEquals(99 - i, integerHeap.peek(), "Failed at index " + i);
			assertEquals(99 - i, integerHeap.extractMax(), "Failed at index " + i);
			
			assertEquals(99 - i, intListHeap.peek(), "Failed at index " + i);
			assertEquals(99 - i, intListHeap.extractMax(), "Failed at index " + i);
		}
		
		assertTrue(integerHeap.isEmpty());
		assertTrue(intListHeap.isEmpty());
	}
	
	@Test
	void testAddDescending() {
		Collections.reverse(intList);
		
		intListHeap = new BinaryMaxHeap<>(intList);
		
		for (int i = 99; i >= 0; i--) {
			integerHeap.add(i);
		}
		
		for (int i = 99; i >=0; i--) {
			assertEquals(i + 1, integerHeap.size(), "Failed at index " + i);
			assertEquals(i + 1, intListHeap.size(), "Failed at index " + i);
			
			assertEquals(i, integerHeap.peek(), "Failed at index " + i);
			assertEquals(i, integerHeap.extractMax(), "Failed at index " + i);
			
			assertEquals(i, intListHeap.peek(), "Failed at index " + i);
			assertEquals(i, intListHeap.extractMax(), "Failed at index " + i);
		}
		
		assertTrue(integerHeap.isEmpty());
		assertTrue(intListHeap.isEmpty());
	}
	
	@Test
	void testAddWithDuplicates() {
		
	}
	
	@Test
	void testAddAndRemove() {
		
	}
	
	@Test
	void testManyInts() {
		
	}
	
	@Test
	void testAddManyIntsCustomComparator() {
		
	}
	
	@Test
	void testStringsCustomComparator() {
		
	}
	
	@Test
	void testToArrayValues() {
		
	}
	
	@Test
	void testToArrayMaintainsHeapQuality() {
		
	}
	
	@Test
	void testToArrayAfterRemove() {
		
	}
	
	// Test toArray (and test heap quality of toArray

}
