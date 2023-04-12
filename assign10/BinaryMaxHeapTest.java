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
import java.util.NoSuchElementException;

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
		for (int i = 0; i < 100; i++) {
			intList.add(i);
		}
		
		intListHeap = new BinaryMaxHeap<Integer>(intList);
		
		for (int i = 0; i < 100; i++) {
			assertEquals(200 - 2*i, intListHeap.size(), "Failed at index " + i);
			
			assertEquals(99 - i, intListHeap.peek(), "Failed at index " + i);
			assertEquals(99 - i, intListHeap.extractMax(), "Failed at index " + i);
			
			assertEquals(99 - i, intListHeap.peek(), "Failed at index " + i);
			assertEquals(99 - i, intListHeap.extractMax(), "Failed at index " + i);
		}
		
		
	}
	
	@Test
	void testAddManyIntsCustomComparator() {
		var minHeap = new BinaryMaxHeap<Integer>(comp);
		
		for (int i = 0; i < 100; i++) {
			minHeap.add(i);
		}
		
		for (int i = 0; i < 100; i++) {
			assertEquals(100 - i, minHeap.size());
			assertEquals(i, minHeap.peek());
			assertEquals(i, minHeap.extractMax());
		}
	}
	
	@Test
	void testStringsCustomComparator() {
		var minStrings = new BinaryMaxHeap<String>(stringList, stringComp);
		
		assertEquals("apple", minStrings.extractMax());
		assertEquals("banana", minStrings.extractMax());
		assertEquals("cantaloupe", minStrings.extractMax());
		assertEquals("durian", minStrings.extractMax());
		assertEquals("eggplant", minStrings.extractMax());
	}
	
	@Test
	void testToArrayValues() {
		Object[] list = intListHeap.toArray();
		Arrays.sort(list);
		
		for (int i = 0; i < list.length; i++) {
			assertEquals(i, list[i], "Failed at index " + i);
		}
	}
	
	@Test
	void testToArrayMaintainsHeapQuality() {
        Object[] array = intListHeap.toArray();
        int n = array.length;

        for (int i = 0; i < n/2; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < n) {
                assertTrue((Integer) array[i] >= (Integer) array[left]);
            }

            if (right < n) {
                assertTrue((Integer) array[i] >= (Integer) array[right]);
            }
        }
	}
	
	@Test
	void testToArrayAfterRemove() {
		intListHeap.extractMax();
		Object[] list = intListHeap.toArray();
		Arrays.sort(list);
		
		int n = list.length;
		
		for (int i = 0; i < n; i++) {
			assertEquals(i, list[i], "Failed at index " + i);
		}
		
        for (int i = 0; i < n; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < n) {
                assertTrue((Integer) n >= (Integer) list[left]);
            }

            if (right < n) {
                assertTrue((Integer) n >= (Integer) list[right]);
            }
        }
	}
	
	@Test
	void testNoSuchElementexceptions() {
		assertThrows(NoSuchElementException.class, () -> integerHeap.peek());
		assertThrows(NoSuchElementException.class, () -> integerHeap.extractMax());
	}

}
