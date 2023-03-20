package assign08;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BinarySearchTreeTest {
	BinarySearchTree<Integer> emptyTree;
	BinarySearchTree<Integer> unitTree;
	BinarySearchTree<Integer> intTree;
	BinarySearchTree<String> stringTree;
	
	ArrayList<String> stringList;
	ArrayList<String> stringList2;
	
	List<Integer> testList;
	List<Integer> treeList;
	
	int testSize;
	
	@BeforeEach
	void setup() {
		emptyTree = new BinarySearchTree<Integer>();
		unitTree = new BinarySearchTree<Integer>();
		unitTree.add(1);
		
		intTree = new BinarySearchTree<Integer>();
		stringTree = new BinarySearchTree<String>();
		
		testSize = 1000;
		
		testList = new ArrayList<Integer>();
		
		stringList = new ArrayList<String>();
		stringList2 = new ArrayList<String>();
	}
	
	/*
	 * Clear test
	 */
	@Test
	void clearTest() {
		unitTree.clear();
		assertEquals(0, unitTree.size());
		
		for (int i = 0; i < testSize; i++) {
			intTree.add(i);
			if ((int)(Math.random()*5)%2 == 0) {
				intTree.clear();
				assertEquals(0, intTree.size(), "Failed at index " + i);
			}
		}
	}
	/*
	 * Size tests
	 */
	
	@Test
	void emptySizeTest() {
		assertEquals(0, emptyTree.size());
	}
	
	@Test
	void unitSizeTest() {
		assertEquals(1, unitTree.size());
	}
	
	@Test
	void multipleSizeTest() {
		for (int i = 0; i < 3; i++) {
			intTree.add(i);
			stringTree.add(" " + i);
		}
		
		assertEquals(3, intTree.size());
		assertEquals(3, stringTree.size());
	}
	
	/*
	 * Add Tests
	 */
	
	@Test
	void emptyTreeAddTest() {
		assertTrue(emptyTree.add(2));
		
		assertTrue(emptyTree.contains(2));
	}
	
	@Test
	void stringAddTest() {
		assertTrue(stringTree.add("a"));
		assertTrue(stringTree.add("b"));
		
		assertEquals(2, stringTree.size());
		assertTrue(stringTree.contains("a"));
		assertTrue(stringTree.contains("b"));
	}
	
	@Test
	void manyAddTest() {
		for (int i = 0; i < testSize; i++) {
			assertTrue(intTree.add(i));
		}
		
		for (int i = 0; i < testSize; i++) {
			assertTrue(intTree.contains(i));
		}
	}
	
	@Test
	void addInvalidTest() {
		assertFalse(unitTree.add(1));
		
		for (int i = 0; i < testSize; i++) {
			intTree.add(i);
		}
		for (int i = 0; i < testSize; i++) {
			assertFalse(intTree.add(i));
		}
	}
	
	/*
	 * First Tests
	 */
	
	@Test
	void firstExceptionTest() {
		assertThrows(NoSuchElementException.class, () -> {emptyTree.first();});
	}
	
	@Test
	void unitFirstTest() {
		assertEquals(1, unitTree.first());
	}
	
	@Test
	void stringFirstTest() {
		stringTree.add("b");
		stringTree.add("a");
		stringTree.add("c");
		
		assertEquals("a", stringTree.first());
	}
	
	@Test
	void multipleIntFirstTest() {
		for (int i = testSize; i > 0; i--) {
			intTree.add(i);
		}
		
		for (int i = 0; i < testSize; i++) {
			assertEquals(1, intTree.first());
		}
	}
	
	/**
	 * Last Tests
	 */
	
	@Test
	void lastExceptionTest() {
		assertThrows(NoSuchElementException.class, () -> {emptyTree.last();});
	}
	
	@Test
	void unitLastTest() {
		assertEquals(1, unitTree.last());
	}
	
	@Test
	void stringLastTest() {
		stringTree.add("b");
		stringTree.add("a");
		stringTree.add("c");
		stringTree.add("d");
		stringTree.add("aa");
		
		assertEquals("d", stringTree.last());
	}
	
	@Test
	void multipleIntLastTest() {
		for (int i = 0; i < testSize; i++) {
			intTree.add(i);
		}
		
		for (int i = 0; i < testSize; i++) {
			assertEquals(testSize - 1, intTree.last());
		}
	}
	
	
	/*
	 * Is Empty Tests
	 */
	
	@Test
	void isEmptyTest() {
		assertTrue(emptyTree.isEmpty());
	}
	
	@Test
	void removeIsEmptyTest() {
		unitTree.remove(1);
		
		assertTrue(unitTree.isEmpty());
		
		stringTree.add("a");
		stringTree.add("b");
		stringTree.remove("a");
		stringTree.remove("b");
		
		assertTrue(stringTree.isEmpty());
		
		intTree.add(1);
		intTree.add(2);
		intTree.remove(1);
		intTree.remove(2);
	}
	
	/*
	 * Remove Tests
	 */
	
	@Test
	void removeEmptyTest() {
		assertFalse(emptyTree.remove(1));
	}
	
	@Test
	void removeInvalidTest() {
		assertFalse(unitTree.remove(2));
		
		stringTree.add("a");
		assertFalse(stringTree.remove("b"));
		
		intTree.add(testSize);
		for (int i = 0; i < testSize; i++) {
			assertFalse(intTree.remove(i));
		}
	}
	
	@Test
	void removeUnitTest() {
		assertTrue(unitTree.remove(1));
		assertTrue(unitTree.isEmpty());
	}
	
	@Test
	void removeMultipleTest() {
		for (int i = 0; i < testSize; i++) {
			intTree.add(i);
		}
		
		for (int i = 0; i < testSize; i++) {
			assertTrue(intTree.remove(i), "Remove boolean failed at index " + i);
			assertEquals(testSize - 1 - i, intTree.size(), "Size failed at index " + i);
		}
	}
	
	@Test
	void removeStringTests() {
		stringTree.add("a");
		assertTrue(stringTree.remove("a"));
		assertTrue(stringTree.isEmpty());
	}
	
	@Test
	void removeMultipleStringsTest() {
		stringTree.add("a");
		stringTree.add("b");
		stringTree.add("c");
		
		assertTrue(stringTree.remove("c"));
		assertTrue(stringTree.remove("b"));
		assertTrue(stringTree.remove("a"));
	}
	/*
	 * Collections Tests
	 */
	
	@Test
	void unitArrayTest() {
		testList.add(1);
		
		treeList = unitTree.toArrayList();
		
		for (int i = 0; i < testList.size(); i++)
			assertEquals(testList.get(i), treeList.get(i), "Failed at index " + i);
	}
	
	@Test
	void emptyArrayTest() {
		treeList = emptyTree.toArrayList();
		assertTrue(treeList.isEmpty());
	}
	
	@Test
	void multipleIntTest() {
		for (int i = 0; i < testSize; i++) {
			testList.add(i);
		}
		
		Collections.shuffle(testList);
		
		for (int i = 0; i < testList.size(); i++) {
			intTree.add(testList.get(i));
		}
		
		Collections.sort(testList);
		
		treeList = intTree.toArrayList();
		
		for (int i = 0; i < testList.size(); i++) {
			assertEquals(testList.get(i), treeList.get(i), "Failed at index " + i);
		}
	}
	
	@Test
	void multipleStringTest() {
		
		for (int i = 0; i < testSize; i++) {
			stringList.add(" " + i);
		}
		
		Collections.shuffle(stringList);
		
		for (int i = 0; i < stringList.size(); i++) {
			stringTree.add(stringList.get(i));
		}
		
		Collections.sort(stringList);
		
		stringList2 = stringTree.toArrayList();
		
		for (int i = 0; i < stringList.size(); i++) {
			assertEquals(stringList.get(i), stringList2.get(i), "Failed at index " + i);
		}
	}
	
	/*
	 * Add All
	 */
	
	@Test
	void addAllIntsTest() {
		for (int i = 0; i < testSize; i++) {
			testList.add(i);
		}
		
		assertTrue(intTree.addAll(testList));
		
		for (int i = 0; i < testSize; i++) {
			assertTrue(intTree.contains(i), "Failed at index " + i);
		}
		
		assertTrue(intTree.add(testSize));
		assertTrue(intTree.contains(testSize));
	}
	
	@Test
	void addAllStringsTest() {
		for (int i = 0; i < testSize; i++) {
			stringList.add(" " + i);
		}
		
		assertTrue(stringTree.addAll(stringList));
		
		for (int i = 0; i < testSize; i++) {
			assertTrue(stringTree.contains(" " + i), "Failed at index " + i);
		}
		
		assertTrue(stringTree.add(" " + testSize));
		assertTrue(stringTree.contains(" " + testSize));
	}
	
	@Test
	void addAllOverlapTest() {
		for (int i = 0; i < testSize; i++) {
			testList.add(i);
		}
		
		assertTrue(unitTree.addAll(testList));
		
		assertEquals(testSize, unitTree.size());
		
		for (int i = 0; i < testSize; i++) {
			assertTrue(unitTree.contains(i), "Failed at index " + i);
		}
	}
	
	@Test
	void addAllFalseTest() {
		for (int i = 0; i < testSize; i++) {
			intTree.add(i);
			testList.add(i);
		}
		
		assertEquals(testSize, intTree.size());
		assertFalse(intTree.addAll(testList));
		assertEquals(testSize, intTree.size());
	}
	
	/*
	 * Remove All
	 */
	
	@Test
	void removeAllUnitTest() {
		testList.add(1);
		assertTrue(unitTree.removeAll(testList));
		assertEquals(0, unitTree.size());
	}
	
	@Test
	void removeAllInvalidTest() {
		testList.add(2);
		assertFalse(unitTree.removeAll(testList));
		assertEquals(1, unitTree.size());
	}
	
	@Test
	void removeAllManyTest() {
		for (int i = 0; i < testSize; i++) {
			testList.add(i);
			intTree.add(i);
		}
		
		assertTrue(intTree.removeAll(testList));
		assertEquals(0, intTree.size());
	}
	
	@Test
	void removeAllManyInvalidTest() {
		for (int i = 0; i < testSize; i++) {
			intTree.add(i);
		}
		for (int i = testSize; i < 2*testSize; i++) {
			testList.add(i);
		}
		
		assertFalse(intTree.removeAll(testList));
		assertEquals(testSize, intTree.size());
	}
	
	@Test
	void removeAllOverlapTest() {
		for (int i = 0; i < testSize; i++) {
			intTree.add(i);
			if (i%2 == 0)
				testList.add(i);
		}
		
		assertTrue(intTree.removeAll(testList));
		assertEquals(testSize/2, intTree.size());
		
		for (int i = 0; i < testSize; i++) {
			if (i%2 == 0) {
				assertFalse(intTree.contains(i), "Failed at index " + i);
			}
			if (i%2 != 0) {
				assertTrue(intTree.contains(i), "Failed at index " + i);
			}
		}
	}
	
	/*
	 * Contains All Test
	 */
	
	@Test
	void containsAllUnitTest() {
		testList.add(1);
		assertTrue(unitTree.containsAll(testList));
	}
	
	@Test
	void containsAllEmptyTest() {
		testList.add(1);
		assertFalse(emptyTree.containsAll(testList));
	}
	
	@Test
	void containsAllMultiTest() {
		for (int i = 0; i < testSize; i++) {
			testList.add(i);
			intTree.add(i);
		}
		
		assertTrue(intTree.containsAll(testList));
	}
	
	@Test
	void containsAllMultiSub() {
		for (int i = 0; i < testSize; i++) {
			intTree.add(i);
			if (i%2 == 0)
				testList.add(i);
		}
		
		assertTrue(intTree.containsAll(testList));
	}
	
	@Test
	void containsAllFails() {
		for (int i = 0; i < testSize; i++) {
			testList.add(i);
			if (i%2 == 0)
				intTree.add(i);
		}
		
		assertFalse(intTree.containsAll(testList));
	}
}
