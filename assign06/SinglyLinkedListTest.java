package assign06;

import static org.junit.jupiter.api.Assertions.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SinglyLinkedListTest {
	
	SinglyLinkedList<Integer> empty;
	SinglyLinkedList<Integer> hasOne;
	SinglyLinkedList<Integer> sequence;
	
	SinglyLinkedList<String> strings;
	SinglyLinkedList<Boolean> bools;
	boolean[] boolVals = {false, false, false, true, false, true, true};
	
	@BeforeEach
	void setup() {
		empty = new SinglyLinkedList<Integer>();
		hasOne = new SinglyLinkedList<Integer>();
			hasOne.insertFirst(1);
		sequence = new SinglyLinkedList<Integer>();
		
		strings = new SinglyLinkedList<String>();
			strings.insertFirst("World!");
			strings.insertFirst("Hello");
		bools = new SinglyLinkedList<Boolean>();
			bools.insertFirst(true);
			bools.insertFirst(true);
			bools.insertFirst(false);
			bools.insertFirst(true);
			bools.insertFirst(false);
			bools.insertFirst(false);
			bools.insertFirst(false);
	}
	
	/*
	 * Constructor Tests
	 */
	
	@Test
	void testInstantiation() {
		empty = new SinglyLinkedList<Integer>();
	}
	
	/*
	 * InsertFirst()
	 */
	
	@Test
	void testInsertFirstWhileEmpty() {
		empty.insertFirst(125);
		assertTrue(empty.getFirst().equals(125));
	}
	
	@Test
	void testInsertFirstWhileNotEmpty() {
		hasOne.insertFirst(2);
		bools.insertFirst(true);
		
		assertTrue(hasOne.getFirst().equals(2));
		assertTrue(bools.getFirst());	
	}
	
	@Test
	void testInsertFirstVariousTypes() {
		strings.insertFirst("hooray!");
		assertTrue(strings.getFirst().equals("hooray!"));
	}
	
	@Test
	void testInsertFirstCheckSize() {
		
		for (int ii = 0; ii < 100; ii ++) {
			assertTrue(sequence.size() == (ii));
			
			sequence.insertFirst(ii);
			assertTrue(sequence.getFirst().equals(ii));
		}
	}

	
	/*
	 * Insert()
	 */
	
	@Test
	void testInsertWhileEmpty() {
		empty.insertFirst(125);
		assertTrue(empty.getFirst().equals(125));
	}
	
	@Test
	void testInsertWhileNotEmpty() {
		for (int ii = 0; ii < 100; ii ++) {
			assertTrue(sequence.size() == (ii));
			
			sequence.insertFirst(ii);
			assertTrue(sequence.getFirst().equals(ii));
		}
	}
	
	@Test
	void testInsertExceptions() {
		assertThrows(IndexOutOfBoundsException.class, () -> {bools.insert(100, false);});
		assertThrows(IndexOutOfBoundsException.class, () -> {hasOne.insert(-1, 2);});
		assertThrows(IndexOutOfBoundsException.class, () -> {hasOne.insert(2, 2);});
		assertThrows(IndexOutOfBoundsException.class, () -> {empty.insert(1, 2);});
	}
	
	/*
	 * GetFirst()
	 */
	
	@Test
	void testGetFirstExceptions() {
		assertThrows(NoSuchElementException.class, () -> {empty.getFirst();});
	}
	
	@Test
	void testGetFirstVariousSizes() {
		for (int ii = 0; ii < 100; ii ++) {
			sequence.insertFirst(ii);
			assertTrue(sequence.getFirst().equals(ii));
		}
		
		for (int ii = 0; ii < 100; ii++) { 
			sequence.insert(5, 1);
			assertTrue(sequence.getFirst().equals(99));
		}
	}
	
	@Test
	void testGetFirstDifferentTypes() {
		strings.insertFirst("hooray!");
		assertTrue(strings.getFirst().equals("hooray!"));
		
		bools.insertFirst(true);
		assertTrue(bools.getFirst());
	}
	
	/*
	 * Get()
	 */
	
	@Test
	void testGetExceptions() {
		assertThrows(IndexOutOfBoundsException.class, () -> {bools.get(100);});
		assertThrows(IndexOutOfBoundsException.class, () -> {hasOne.get(-1);});
		assertThrows(IndexOutOfBoundsException.class, () -> {hasOne.get(1);});
		assertThrows(IndexOutOfBoundsException.class, () -> {empty.get(0);});
	}
	
	@Test
	void testGetVariousSizes() {
		for (int i = 999; i >= 0; i--) {
			sequence.insertFirst(i);
			
			for (int j = 0; j < 1000 - i; j++) {
				
				assertTrue(sequence.get(j).equals(i + j));
			}
		}
	}
	
	@Test
	void testGetDifferentTypes() {
		// TODO string, int, color, etc.
	}
	
	@Test
	void testGetLastIndex() {
		
	}
	
	/*
	 * DeleteFirst()
	 */
	
	@Test
	void testDeleteFirstExceptions() {
	
	}
	
	@Test
	void testDeleteFirstSizeConsistency() {
		
	}
	
	
	/*
	 * Delete()
	 */
	
	@Test
	void testDeleteExceptions() {
		
	}
	
	@Test
	void testDeleteVariousSizes() {
		
	}
	
	@Test
	void testDeleteDifferentTypes() {
		
	}
	
	@Test
	void testDeleteLastIndex() {
		
	}
	
	@Test
	void testDeleteFirstIndex() {
		// TODO check against deleteFirst()
	}
	
	/*
	 * IndexOf()
	 */

	@Test
	void testIndexOfExceptions() {
		
	}
	
	@Test
	void testIndexOfVariousSizes() {
		
	}
	
	@Test
	void testIndexOfVariousTypes() {
		
	}
	
	@Test
	void testIndexOfEdges() {
		
	}
	
	/*
	 * Size()
	 */
	
	@Test
	void testSizeEmpty() {
		
	}
	
	@Test
	void testSizeNotEmpty() {
		
	}
	
	/*
	 * IsEmpty()
	 */
	
	@Test
	void testIsEmptyIfEmpty() {
		
	}
	
	@Test
	void testIsEmptyIfNotEmpty() {
		
	}
	
	/*
	 * Clear()
	 */
	
	@Test
	void testClearIfEmpty() {
		
	}
	
	@Test
	void testClearIfNotEmpty() {
		
	}
	
	@Test
	void testClearInsertAfter() {
		
	}
	
	/*
	 * ToArray()
	 */
	
	@Test
	void testToArrayEmptyList() {
		
	}
	
	@Test
	void testToArraySingleTime() {
		
	}
	
	@Test
	void testToArrayMultipleItems() {
		
	}
	
	@Test
	void testToArrayCorrectSize() {
		
	}
	
	/*
	 * Iterator()
	 */
	
	@Test
	void testHasNextIfEmpty() {
		
	}
	
	@Test
	void testHasNextIfNotEmpty() {
		
	}
	
	@Test
	void testHasNextAtEndOfList() {
		
	}
	
	@Test
	void testNextExceptions() {
		
	}
	
	@Test
	void testNextIteratesOverEveryItem() {
		
	}
	
	@Test
	void testNextReturnItem() {
		
	}
	
	@Test
	void testRemoveExceptions() {
		
	}
	
	@Test
	void testRemoveProperlyRemoves() {
		
	}
	
	@Test
	void testRemoveDoesntSkipNextValues() {
		// TODO make sure values aren't skipped while deleting
	}
	
	@Test
	void testRemoveChangesSize() {
		
	}
}
	