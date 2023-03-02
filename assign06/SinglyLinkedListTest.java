package assign06;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
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
	
	Iterator<Integer> emptyIter;
	Iterator<Integer> hasOneIter;
	Iterator<Integer> sequenceIter;
	Iterator<String> stringsIter;
	Iterator<Boolean> boolsIter;
	
	
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
			
		emptyIter = empty.iterator();
		hasOneIter = hasOne.iterator();
		stringsIter = strings.iterator();
		boolsIter = bools.iterator();
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
		strings.insert(2, "hooray!");
		assertTrue(strings.get(0).equals("Hello"));
		assertTrue(strings.get(1).equals("World!"));
		assertTrue(strings.get(2).equals("hooray!"));
		
		for (int i = 0; i < boolVals.length; i++) {
			assertEquals(boolVals[i], bools.get(i));
		}
	}
	
	@Test
	void testGetLastIndex() {
		for (int i = 0; i < 1000; i++) {
			sequence.insert(i, i);
			assertEquals(sequence.get(i),i, "Failed at index " + i);
		}
	}
	
	/*
	 * DeleteFirst()
	 */
	
	@Test
	void testDeleteFirstExceptions() {
		assertThrows(NoSuchElementException.class, () -> {empty.deleteFirst();});
	}
	
	@Test
	void testDeleteFirstSizeConsistency() {
		for (int i = 0; i < 1000; i++) {
			sequence.insertFirst(i);
		}
		for (int i = 0; i < 1000; i++) {
			sequence.deleteFirst();
			assertEquals(999 - i, sequence.size(), "Failed at index " + i);
		}
	}
	
	@Test
	void testDeleteFirstReturnValue() {
		for (int i = 0; i < 1000; i++) {
			sequence.insertFirst(i);
		}
		for (int i = 0; i < 1000; i++) {
			assertEquals(999 - i, sequence.deleteFirst(), "Failed at index " + i);
		}
	}
	
	
	/*
	 * Delete()
	 */
	
	@Test
	void testDeleteExceptions() {
		assertThrows(IndexOutOfBoundsException.class, () -> {bools.delete(100);});
		assertThrows(IndexOutOfBoundsException.class, () -> {hasOne.delete(-1);});
		assertThrows(IndexOutOfBoundsException.class, () -> {hasOne.delete(1);});
		assertThrows(IndexOutOfBoundsException.class, () -> {empty.delete(0);});
	}
	
	@Test
	void testDeleteVariousSizes() {
		for (int i = 0; i < boolVals.length/2; i++) {
			assertTrue(bools.delete(i) == boolVals[2*i]);
		}
		
		for (int i = 1; i < boolVals.length; i += 2) {
			assertTrue(bools.get(i / 2) == boolVals[i]);
		}
	}
	
	@Test
	void testDeleteDifferentTypes() {
		assertTrue(strings.delete(1).equals("World!"));
		
		assertTrue(hasOne.delete(0).equals(1));
	}
	
	@Test
	void testDeleteAll() {
		for (int ii = 0; ii < 1000; ii++) {
			sequence.insertFirst(ii);
		}
		
		for (int ii = 0; ii < 1000; ii++) {
			sequence.delete(0);
		}
		
		assertTrue(sequence.size() == 0);
	}

	
	/*
	 * IndexOf()
	 */

	
	@Test
	void testIndexOfVariousSizes() {
		for (int ii = 0; ii < 1000; ii++) {
			sequence.insert(ii, ii);
		}
		
		for (int jj = 0; jj < 1000; jj++) {
			assertTrue(sequence.indexOf(jj) == jj);
		}
	}
	
	@Test
	void testIndexOfVariousTypes() {
		assertTrue(bools.indexOf(true) == 3);
		assertTrue(bools.indexOf(false) == 0);
	}
	
	@Test
	void testIndexOfEdges() {
		assertTrue(strings.indexOf("Hello") == 0);
		assertTrue(strings.indexOf("World!") == 1);
	}
	
	/*
	 * Size()
	 */
	
	@Test
	void testSizeEmpty() {
		assertTrue(empty.size() == 0);
	}
	
	@Test
	void testSizeNotEmpty() {
		for (int ii = 0; ii < 1000; ii++) {
			sequence.insertFirst(ii);
			assertEquals(ii + 1, sequence.size());
		}
	}
	
	/*
	 * IsEmpty()
	 */
	
	@Test
	void testIsEmptyIfEmpty() {
		assertTrue(empty.isEmpty());
	}
	
	@Test
	void testIsEmptyIfNotEmpty() {
		assertFalse(hasOne.isEmpty());
		assertFalse(strings.isEmpty());
	}
	
	/*
	 * Clear()
	 */
	
	@Test
	void testClearIfEmpty() {
		empty.clear();
		assertTrue(empty.isEmpty());
	}
	
	@Test
	void testClearIfNotEmpty() {
		hasOne.clear();
		strings.clear();
		
		assertTrue(hasOne.isEmpty());
		assertTrue(strings.isEmpty());
	}
	
	@Test
	void testClearInsertAfter() {
		hasOne.clear();
		strings.clear();
		
		assertTrue(hasOne.isEmpty());
		assertTrue(strings.isEmpty());
		
		hasOne.insert(0, 1);
		assertFalse(hasOne.isEmpty());
	}
	
	/*
	 * ToArray()
	 */
	
	@Test
	void testToArrayEmptyList() {
		assertTrue(empty.toArray().length == 0);
	}
	
	@Test
	void testToArraySingleItem() {
		assertTrue(hasOne.toArray()[0].equals(1));
		assertTrue(hasOne.toArray().length == 1);
	}
	
	@Test
	void testToArrayMultipleItems() {
		Integer[] arr;
		Object[] listArr;
		
		for (int ii = 0; ii < 1000; ii++) {
			arr = new Integer[ii];
			sequence.insert(ii, ii);
			listArr = sequence.toArray();
			
			for (int jj = 0; jj < ii; jj++) {
				arr[jj] = jj;
			}
				
			for (int kk = 0; kk < ii; kk++) {
				assertEquals(arr[kk], listArr[kk]);
			}
			
			assertTrue(listArr.length == ii + 1);
				
		}
	}
	
	/*
	 * Iterator()
	 */
	
	@Test
	void testHasNextIfEmpty() {
		assertFalse(emptyIter.hasNext());
	}
	
	@Test
	void testHasNextIfNotEmpty() {
		assertTrue(hasOneIter.hasNext());
		assertTrue(boolsIter.hasNext());
		assertTrue(stringsIter.hasNext());
	}
	
	@Test
	void testHasNextAtEndOfList() {
		for (int i = 0; i < hasOne.size(); i++) {
			hasOneIter.next();
		}
		for (int i = 0; i < bools.size(); i++) {
			boolsIter.next();
		}
		for (int i = 0; i < strings.size(); i++) {
			stringsIter.next();
		}

		assertFalse(hasOneIter.hasNext());
		assertFalse(boolsIter.hasNext());
		assertFalse(stringsIter.hasNext());
	}
	
	@Test
	void testNextExceptions() {
		for (int i = 0; i < hasOne.size(); i++) {
			hasOneIter.next();
		}
		for (int i = 0; i < bools.size(); i++) {
			boolsIter.next();
		}
		for (int i = 0; i < strings.size(); i++) {
			stringsIter.next();
		}
		
		assertThrows(NoSuchElementException.class, () -> {hasOneIter.next();});
		assertThrows(NoSuchElementException.class, () -> {stringsIter.next();});
		assertThrows(NoSuchElementException.class, () -> {boolsIter.next();});
		assertThrows(NoSuchElementException.class, () -> {emptyIter.next();});
	}
	
	@Test
	void testNextIteratesOverEveryItem() {
		for (int i = 0; i < 1000; i++) {
			sequence.insert(i,  i);
		}
		
		sequenceIter = sequence.iterator();
		
		int testIndex = 0;
		while(sequenceIter.hasNext()) {
			assertEquals(testIndex++, sequenceIter.next());
		}
	}
	
	@Test
	void testRemoveExceptions() {
		assertThrows(IllegalStateException.class, () -> {emptyIter.remove();});
		assertThrows(IllegalStateException.class, () -> {boolsIter.remove();});
		assertThrows(IllegalStateException.class, () -> {boolsIter.next(); boolsIter.remove(); boolsIter.remove();});
	}
	
	@Test
	void testRemoveProperlyRemoves() {
		stringsIter.next();
		stringsIter.remove();
		assertEquals("World!",strings.getFirst());
		
		for (int i = 0; i < 5; i++) {
			sequence.insert(i, i);
		}
		sequenceIter = sequence.iterator();
		
		sequenceIter.next();
		sequenceIter.next();
		sequenceIter.next();
		sequenceIter.remove();
		assertEquals(3, sequenceIter.next());
	}
	
	@Test
	void testRemoveDoesntSkipNextValues() {
		for (int i = 0; i < 1000; i++ ) {
			sequence.insert(i, i);
		}
		sequenceIter = sequence.iterator();
		for (int i = 0; i < 1000; i++) {
			assertEquals(i, sequenceIter.next(), "Failed at index " + i);
			sequenceIter.remove();
		}
		
		assertEquals(0, sequence.size());
	}
}
	