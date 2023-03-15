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
	
	List<String> unitList1;
	List<String> unitList2;
	
	List<String> emptyList1;
	List<String> emptyList2;
	
	List<String> testList;
	
	List<String> loopList1;
	List<String> loopList2;
	
	@BeforeEach
	void setup() {
		stringList1 = new LinkedList<String>();
		stringList2 = new LinkedList<String>();
		intList1 = new LinkedList<Integer>();
		intList2 = new LinkedList<Integer>();
		
		unitList1 = new LinkedList<String>();
		unitList2 = new LinkedList<String>();
		unitList1.add("a");
		unitList2.add("a");
		
		emptyList1 = new LinkedList<String>();
		emptyList2 = new LinkedList<String>();
		
		loopList1 = new LinkedList<String>();
		loopList2 = new LinkedList<String>();
		
		testList = new LinkedList<String>();
		
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
		
		for (int i = 0; i < stringList1.size(); i++) {
			loopList1.add(stringList1.get(i));
			loopList2.add(stringList2.get(i));
		}
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
		
		assertThrows(IllegalArgumentException.class, () -> {GraphUtility.areConnected(emptyList1, emptyList2, "a", "b");});
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
		
		assertThrows(IllegalArgumentException.class, () -> {GraphUtility.shortestPath(emptyList1, emptyList2, "a", "b");});
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
		
		testList.add("a");
		testList.add("b");
		testList.add("d");
		
		List<String> shortestPath = GraphUtility.shortestPath(stringList1, stringList2, "a", "d");
		
		assertEquals(testList.size(), shortestPath.size(), "Lists are a different size.");
		
		for (int i = 0; i < testList.size(); i++) {
			assertTrue(testList.get(i).equals(shortestPath.get(i)), "Different at index " + i);
		}
	}
	
	@Test
	void areConnectedUnitTest() {
		assertTrue(GraphUtility.areConnected(unitList1, unitList2, "a", "a"));
	}
	
	@Test
	void shortestPathUnitTest() {
		testList.add("a");
		
		List<String> shortestPath = GraphUtility.shortestPath(unitList1, unitList2, "a", "a");
		
		assertEquals(testList.size(), shortestPath.size(), "Different sized lists.");
		assertEquals(1, shortestPath.size(), "Wrong sized lists.");
		assertTrue(testList.get(0).equals(shortestPath.get(0)), "Array indeces different values.");
	}
	
	@Test
	void areConnectedLoopTest() {
		loopList1.add("d");
		loopList2.add("a");
		
		assertTrue(GraphUtility.areConnected(loopList1, loopList2, "a", "b"));
		assertTrue(GraphUtility.areConnected(loopList1, loopList2, "a", "c"));
		assertTrue(GraphUtility.areConnected(loopList1, loopList2, "a", "d"));
		assertTrue(GraphUtility.areConnected(loopList1, loopList2, "b", "c"));
		assertTrue(GraphUtility.areConnected(loopList1, loopList2, "b", "d"));
		assertTrue(GraphUtility.areConnected(loopList1, loopList2, "b", "a"));
		assertTrue(GraphUtility.areConnected(loopList1, loopList2, "c", "d"));
		assertTrue(GraphUtility.areConnected(loopList1, loopList2, "c", "a"));
		assertTrue(GraphUtility.areConnected(loopList1, loopList2, "c", "b"));
		assertTrue(GraphUtility.areConnected(loopList1, loopList2, "d", "a"));
		assertTrue(GraphUtility.areConnected(loopList1, loopList2, "d", "b"));
		assertTrue(GraphUtility.areConnected(loopList1, loopList2, "d", "c"));
	}
	
	@Test
	void shortestPathTest() {
		loopList1.add("d");
		loopList2.add("a");
		List<String> ABList = new LinkedList<String>();
		List<String> BAList = new LinkedList<String>();
		List<String> BDList = new LinkedList<String>();
		List<String> DCList = new LinkedList<String>();
		
		ABList.add("a");
		ABList.add("b");
		
		BAList.add("b"); BAList.add("d"); BAList.add("a");
		
		BDList.add("b"); BDList.add("d");
		
		DCList.add("d"); DCList.add("a"); DCList.add("b"); DCList.add("c");
		
		List<String> pathAB = GraphUtility.shortestPath(loopList1, loopList2, "a", "b");
		
		assertEquals(ABList.size(), pathAB.size(), "AB Different Size");
		
		for (int i = 0; i < ABList.size(); i++) {
			assertTrue(ABList.get(i).equals(pathAB.get(i)), "Different at index " + i);
		}
		
		List<String> pathBA = GraphUtility.shortestPath(loopList1, loopList2, "b", "a");
		
		assertEquals(BAList.size(), pathBA.size(), "BA Different Size");
		
		for (int i = 0; i < BAList.size(); i++) {
			assertTrue(BAList.get(i).equals(pathBA.get(i)), "Different at index " + i);
		}
		
		List<String> pathBD = GraphUtility.shortestPath(loopList1, loopList2, "b", "d");
		
		assertEquals(BDList.size(), pathBD.size(), "BD Different Size");
		
		for (int i = 0; i < BDList.size(); i++) {
			assertTrue(BDList.get(i).equals(pathBD.get(i)), "Different at index " + i);
		}
		
		List<String> pathDC = GraphUtility.shortestPath(loopList1, loopList2, "d", "c");
		
		assertEquals(DCList.size(), pathDC.size(), "DC Different Size");
		
		for (int i = 0; i < DCList.size(); i++) {
			assertTrue(DCList.get(i).equals(pathDC.get(i)), "Different at index " + i);
		}
		
	}
}
