package assign09;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

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
		
		assertEquals(0, stringString.size());
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
		
		assertEquals(intInt.get(10), intInt.remove(10));
		
		assertFalse(intInt.containsKey(10));
		assertEquals(99, intInt.size());
		
		assertEquals(null, intInt.get(10));
	}
	
	@Test
	void testSizeAfterRemove() {
		for (int i = 0; i < 100; i++) {
			intString.remove(i);
			
			assertEquals(99 - i, intString.size());
		}
	}
	
	@Test
	void containsAfterRemove() {
		intInt.remove(10);
		
		for (int i = 0; i < 100; i++) {
			if (i == 10) continue;
			assertTrue(intInt.containsKey(i));
			assertEquals(100 - i, intInt.get(i));
		}
	}
	
	@Test
	void testContainsValue() {
		for (int i = 0; i < 100; i++) {
			assertTrue(intInt.containsValue(100 - i));
			
			intInt.remove(i);
			
			for (int j = 1; j < 99 - i; j++) {
				assertTrue(intInt.containsValue(j));
			}

			assertFalse(intInt.containsValue(100 - i));
		}
	}

	@Test
	void testEntries() {
		List<MapEntry<Integer, String>> newList = new ArrayList<MapEntry<Integer, String>>();
		List<MapEntry<String, Integer>> newList2 = new ArrayList<MapEntry<String, Integer>>();
		
		newList = intString.entries();
		newList2 = stringInt.entries();
		
		assertEquals(100, newList.size());
		assertEquals(100, newList2.size());
		
		for (MapEntry<Integer, String> entry : newList) {
			assertEquals(entry.getValue(), intString.remove(entry.getKey()));
		}
		
		assertTrue(intString.isEmpty());
		
		for (MapEntry<String, Integer> entry : newList2) {
			assertEquals(entry.getValue(), stringInt.remove(entry.getKey()));
		}
		
		assertTrue(stringInt.isEmpty());
	}
	
	@Test
	void testEmptyEntries() {
		List<MapEntry<Integer, Integer>> newList = new ArrayList<MapEntry<Integer, Integer>>();
		
		newList = empty.entries();
		
		assertTrue(newList.isEmpty());
	}

	@Test
	void testSize() {
		assertEquals(0, empty.size());
		
		empty.put(1, 1);
		
		assertEquals(1, empty.size());
		
		empty.put(1, 2);
		assertEquals(1, empty.size());
		
		empty.remove(1);
		assertEquals(0, empty.size());
		
		assertTrue(empty.isEmpty());
	}
	
	@Test
	void putOverrides() {
		empty.put(1, 1);
		
		empty.put(1,  2);
		
		assertEquals(2, empty.get(1));
	}
	
	@Test
	void duplicateValues() {
		empty.put(1, 1);
		empty.put(2, 1);
		
		assertTrue(empty.containsValue(1));
		
		empty.remove(2);
		
		assertTrue(empty.containsValue(1));
	}
	
	@Test
	void testGrowth() {
		for (int i = 0; i < 100_000; i++) {
			empty.put(i, i);
			assertEquals(i + 1, empty.size(), "failed at index " + i);
			
			assertEquals(i, empty.get(i), "failed at index " + i);
			
			if (i%100 == 0) {
				for (int j = 0; j < i; j++) {
					assertTrue(empty.containsKey(j));
				}
			}
		}
		
		for (int i = 0; i < 100_000; i++) {
			assertEquals(i, empty.remove(i));
			assertEquals(99_999 - i, empty.size());
		}
		
		assertTrue(empty.isEmpty());
	}
}
