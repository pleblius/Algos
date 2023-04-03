package assign09;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HashTableTest {

	ArrayList<String> stringKeys;
	ArrayList<Integer> intKeys;
	
	ArrayList<String> stringVals;
	ArrayList<Integer> intVals;
	
	HashTable<String, Integer> stringInt;
	HashTable<String, String> stringString;
	HashTable<Integer, String> intString;
	HashTable<Integer, Integer> intInt;
	HashTable<Integer, Integer> empty;
	
	@BeforeEach
	void setUp() {
		stringKeys = new ArrayList<String>();
		stringVals = new ArrayList<String>();
		
		intKeys = new ArrayList<Integer>();
		intVals = new ArrayList<Integer>();
		
		stringInt = new HashTable<String, Integer>();
		stringString = new HashTable<String, String>();
		intString = new HashTable<Integer, String>();
		intInt = new HashTable<Integer, Integer>();
		empty = new HashTable<Integer, Integer>();
		
		
		for (int ii = 0; ii < 100; ii++) {
			stringKeys.add(Character.toChars(ii).toString());
			stringVals.add(Character.toChars(100-ii).toString());
			
			intKeys.add(ii);
			intVals.add(100 - ii);
			
			stringInt.put(stringKeys.get(ii), intVals.get(ii));
			stringString.put(stringKeys.get(ii), stringVals.get(ii));
			intString.put(intKeys.get(ii), stringVals.get(ii));
			intInt.put(intKeys.get(ii), intVals.get(ii));
		}		
		
	}

	@Test
	void testIsEmpty() {
		assertTrue(empty.isEmpty());
		empty.put(1, 1);
		assertFalse(empty.isEmpty());
		
		assertFalse(stringInt.isEmpty());
	}
	
	@Test
	void emptyClear() {
		empty.clear();
		assertTrue(empty.isEmpty());
	}
	
	@Test
	void testClear() {
		stringString.clear();
		assertTrue(stringString.isEmpty());	
		assertTrue(stringString.entries().isEmpty());
	}
	
	@Test
	void testClearAddClear() {
		assertFalse(intString.isEmpty());
		intString.clear();
		assertTrue(intString.isEmpty());
		intString.put(1, "hi!");
		assertFalse(intString.isEmpty());
	}
	
	@Test
	void testGet() {
		
		for (int ii = 0; ii < 100; ii++) {
			assertEquals(100 - ii, intInt.get(ii));
		}

	}
	
	@Test
	void testGetAfterClear() {
		intInt.clear();
		
		assertEquals(null, intInt.get(10));
	}

	@Test
	void testContainsKey() {
		for (int ii = 0; ii < 100; ii++) {
			assertTrue(intInt.containsKey(ii));
		}
	}
	
	@Test
	void testRemove() {
		assertTrue(intInt.containsKey(10));
		intInt.remove(10);
		assertFalse(intInt.containsKey(10));
	}
	
	@Test
	void testContainsAfterRemove() {
		
	}
//
//	@Test
//	void testContainsValue() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testEntries() {
//		fail("Not yet implemented");
//	}
//
//
//
//
//	@Test
//	void testSize() {
//		fail("Not yet implemented");
//	}
//
}
