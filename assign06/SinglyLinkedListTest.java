package assign06;

import static org.junit.jupiter.api.Assertions.*;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SinglyLinkedListTest {
	
	
	@BeforeEach
	void setup() {
		
	}
	
	
	/*
	 * Constructor Tests
	 */
	
	@Test
	void testInstantiation() {
		// TODO Various generics, actually runs, check size
	}
	
	/*
	 * InsertFirst()
	 */
	
	@Test
	void testInsertFirstWhileEmpty() {
		
	}
	
	@Test
	void testInsertFirstWhileNotEmpty() {
		
	}
	
	@Test
	void testInsertFirstVariousTypes() {
		
	}
	
	@Test
	void testInsertFirstCheckSize() {
		
	}

	
	/*
	 * Insert()
	 */
	
	@Test
	void testInsertWhileEmpty() {
		// TODO various tests while array
	}
	
	@Test
	void testInsertWhileNotEmpty() {
		// TODO various tests while array is full
		// First
		// Last
		// Other positions
	}
	
	@Test
	void testInsertExceptions() {
		// TODO test for exceptions
	}
	
	/*
	 * GetFirst()
	 */
	
	@Test
	void testGetFirstExceptions() {
		
	}
	
	@Test
	void testGetFirstVariousSizes() {
		
	}
	
	@Test
	void testGetFirstDifferentTypes() {
		
	}
	
	/*
	 * Get()
	 */
	
	@Test
	void testGetExceptions() {
		// TODO negative, empty, too large
	}
	
	@Test
	void testGetVariousSizes() {
		for (int i = 1; i < 1000; i++) {
			for (int j = 0; j < i; j++) {
				// TODO test gets of J
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
	