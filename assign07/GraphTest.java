package assign07;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GraphTest {

	List<String> stringList1;
	List<String> stringList2;
	List<Integer> intList1;
	List<Integer> intList2;
	
	@BeforeEach
	void setup() {
		stringList1 = new LinkedList<String>();
		stringList2 = new LinkedList<String>();
		intList1 = new LinkedList<Integer>();
		intList2 = new LinkedList<Integer>();
		
		stringList1.add("a");
		stringList1.add("b");
		stringList1.add("c");
		stringList1.add("b");
		
		stringList2.add("b");
		stringList2.add("c");
		stringList2.add("d");
		stringList2.add("d");
		
		intList1.add(10);
		intList1.add(12);
		intList1.add(15);
		intList1.add(12);
		
		intList2.add(12);
		intList2.add(15);
		intList2.add(20);
		intList2.add(20);
	}
	
	@Test
	void AreConnectedThrowsTest() {
		assertThrows(IllegalArgumentException.class, () -> {GraphUtility.areConnected(stringList1, stringList2, "a", "e");});
		assertThrows(IllegalArgumentException.class, () -> {GraphUtility.areConnected(intList1, intList2, 10, 30);});
		
		assertThrows(IllegalArgumentException.class, () -> {GraphUtility.areConnected(stringList1, stringList2, "x", "d");});
		assertThrows(IllegalArgumentException.class, () -> {GraphUtility.areConnected(intList1, intList2, 7, 20);});
		
		stringList1.add("e");
		intList1.add(30);
		
		assertThrows(IllegalArgumentException.class, () -> {GraphUtility.areConnected(stringList1, stringList2, "a", "d");});
		assertThrows(IllegalArgumentException.class, () -> {GraphUtility.areConnected(intList1, intList2, 10, 20);});
	}
	
	@Test
	void ShortestPathThrowsTest() {
		assertThrows(IllegalArgumentException.class, () -> {GraphUtility.shortestPath(stringList1, stringList2, "a", "e");});
		assertThrows(IllegalArgumentException.class, () -> {GraphUtility.shortestPath(intList1, intList2, 10, 30);});
		
		assertThrows(IllegalArgumentException.class, () -> {GraphUtility.shortestPath(stringList1, stringList2, "x", "d");});
		assertThrows(IllegalArgumentException.class, () -> {GraphUtility.shortestPath(intList1, intList2, 7, 20);});
		
		assertThrows(IllegalArgumentException.class, () -> {GraphUtility.shortestPath(stringList1, stringList2, "d", "a");});
		assertThrows(IllegalArgumentException.class, () -> {GraphUtility.shortestPath(intList1, intList2, 15, 10);});
		
		stringList1.add("e");
		intList1.add(30);
		
		assertThrows(IllegalArgumentException.class, () -> {GraphUtility.shortestPath(stringList1, stringList2, "a", "d");});
		assertThrows(IllegalArgumentException.class, () -> {GraphUtility.shortestPath(intList1, intList2, 10, 20);});
	}
	
	@Test
	void areConnectedTest() {
		assertTrue(GraphUtility.areConnected(stringList1, stringList2, "a", "d"));
		assertTrue(GraphUtility.areConnected(stringList1, stringList2, "a", "c"));
		assertTrue(GraphUtility.areConnected(stringList1, stringList2, "a", "b"));
		assertTrue(GraphUtility.areConnected(stringList1, stringList2, "b", "c"));
		assertTrue(GraphUtility.areConnected(stringList1, stringList2, "b", "d"));
		assertTrue(GraphUtility.areConnected(stringList1, stringList2, "c", "d"));
		
		assertFalse(GraphUtility.areConnected(stringList1, stringList2, "c", "b"));
		
		
		assertTrue(GraphUtility.areConnected(intList1, intList2, 10, 12));
		assertTrue(GraphUtility.areConnected(intList1, intList2, 10, 15));
		assertTrue(GraphUtility.areConnected(intList1, intList2, 10, 20));
		assertTrue(GraphUtility.areConnected(intList1, intList2, 12, 15));
		assertTrue(GraphUtility.areConnected(intList1, intList2, 12, 20));
		assertTrue(GraphUtility.areConnected(intList1, intList2, 15, 20));
		
		assertFalse(GraphUtility.areConnected(intList1, intList2, 15, 12));
	}
	
	@Test
	void findShortestPathTest() {
		List<String> testList = new LinkedList<String>();
		
		testList.add("a");
		testList.add("b");
		testList.add("d");
		
		List<String> shortestPath = GraphUtility.shortestPath(stringList1, stringList2, "a", "d");
		
		assertEquals(testList.size(), shortestPath.size(), "Lists are a different size.");
		
		for (int i = 0; i < testList.size(); i++) {
			assertTrue(testList.get(i).equals(shortestPath.get(i)), "Different at index " + i);
		}
	}
}
