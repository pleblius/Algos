package assign07;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GraphTest {
	
	List<String> stringList1;
	List<String> stringList2;
	List<Integer> intList1;
	List<Integer> intList2;
	List<Integer> topoSrcs;
	List<Integer> topoDsts;
	
	List<String> unitList1;
	List<String> unitList2;
	
	List<String> emptyList1;
	List<String> emptyList2;
	
	List<String> testList;
	
	List<String> loopList1;
	List<String> loopList2;
	
	Random rng;
	
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
		
		topoSrcs = new LinkedList<Integer>();
		topoDsts = new LinkedList<Integer>();
		
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
		
		topoSrcs.add(0);
		topoSrcs.add(0);
		topoSrcs.add(0);
		topoSrcs.add(1);
		topoSrcs.add(2);
		topoSrcs.add(3);
		
		topoDsts.add(1);
		topoDsts.add(2);
		topoDsts.add(3);
		topoDsts.add(4);
		topoDsts.add(4);
		topoDsts.add(4);
		
		for (int i = 0; i < stringList1.size(); i++) {
			loopList1.add(stringList1.get(i));
			loopList2.add(stringList2.get(i));
		}
		
		rng = new Random();

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
	
	@Test
	void topoForwardTest() {
		List<Integer> path = GraphUtility.sort(topoSrcs, topoDsts);
		List<Integer> possiblePath = new LinkedList<Integer>();
			possiblePath.add(0);
			possiblePath.add(1);
			possiblePath.add(2);
			possiblePath.add(3);
			possiblePath.add(4);
		
		
		assertTrue(path.get(0).equals(possiblePath.get(0)), "Failed on first vertex");
		
		for (int ii = 1; ii < 4; ii++) {
			Integer node = path.get(ii);
			assertTrue(node.equals(possiblePath.get(1)) || node.equals(possiblePath.get(2)) || node.equals(possiblePath.get(3)), "Failed on index " + ii);
		}
		
		assertTrue(path.get(4).equals(4));
	}
	
	@Test
	void topoBackwardTest() {
		Collections.reverse(topoSrcs);
		Collections.reverse(topoDsts);
		
		List<Integer> path = GraphUtility.sort(topoSrcs, topoDsts);
		List<Integer> possiblePath = new LinkedList<Integer>();
			possiblePath.add(0);
			possiblePath.add(1);
			possiblePath.add(2);
			possiblePath.add(3);
			possiblePath.add(4);
		
		
		assertTrue(path.get(0).equals(possiblePath.get(0)), "Failed on first vertex");
		
		for (int ii = 1; ii < 4; ii++) {
			Integer node = path.get(ii);
			assertTrue(node.equals(possiblePath.get(1)) || node.equals(possiblePath.get(2)) || node.equals(possiblePath.get(3)), "Failed on index " + ii);
		}
		
		assertTrue(path.get(4).equals(4));
	}
	
	@Test 
	void topoBigTest() {
		HashSet<Integer> middleSet = new HashSet<Integer>();	
		
		LinkedList<Integer> topoSrcs = new LinkedList<Integer>();
		LinkedList<Integer> topoDsts = new LinkedList<Integer>();
		
		LinkedList<Integer> middleNodes = new LinkedList<Integer>();
		
		// Building directed acyclic graph
		
		for (int ii = 0; ii < 1000; ii++) {
			topoSrcs.add(0);			
			middleNodes.add(ii + 1);
		}
		
		middleSet.addAll(middleNodes);
		Collections.shuffle(middleNodes);
		topoDsts.addAll(middleNodes);
		
		Collections.shuffle(middleNodes);
		topoSrcs.addAll(middleNodes);
		
		for (int ii = 0; ii < 1000; ii++) {
			topoDsts.add(10000);
		}
		
		// Testing toposort
		List<Integer> path = GraphUtility.sort(topoSrcs, topoDsts);
		
		assertTrue(path.get(0).equals(0), "Failed on first vertex");
		
		for (int ii = 1; ii <= 1000; ii++) {
			assertTrue(middleSet.contains(path.get(ii)), "Failed on index " + ii);
		}
		
		assertTrue(path.get(1001).equals(10000));
	}
	
	@Test 
	void topoSingleCycleTest() {
		// A single cycle
		List<Integer> singleCyclic = new LinkedList<Integer>();
		singleCyclic.add(1);
		
		assertThrows(IllegalArgumentException.class, () -> {GraphUtility.sort(singleCyclic, singleCyclic);});	
	}
	
	@Test
	void topoOnePointerTest() {
		// Only 1 pointer
		List<Integer> singlePointerSrc = new LinkedList<Integer>();
		List<Integer> singlePointerDst = new LinkedList<Integer>();
		singlePointerSrc.add(1);
		singlePointerDst.add(2);
		
		var path = GraphUtility.sort(singlePointerSrc, singlePointerDst);
		
		assertTrue(path.get(0).equals(1));
		assertTrue(path.get(1).equals(2));
	}
	
	@Test
	void topoAcyclicTest() {
		
		for (int ii = 0; ii < 1000; ii++) {
			var directionLists = RandomGraphLists.generateRandomAcyclicLists(ii, 2*ii);
			var srcList = directionLists.get(0);
			var dstList = directionLists.get(1);
			
			GraphUtility.sort(srcList, dstList);
		}
	}
	
	@Test
	void topoThrowsOnCyclicTest() {
		
		HashSet<Integer> middleSet = new HashSet<Integer>();	
		
		LinkedList<Integer> topoSrcs = new LinkedList<Integer>();
		LinkedList<Integer> topoDsts = new LinkedList<Integer>();
		
		LinkedList<Integer> middleNodes = new LinkedList<Integer>();
		
		// Building directed cyclic graphs
		
		for (int n = 2; n < 15; n++) {
		
			for (int ii = 0; ii < n; ii++) {
				topoSrcs.add(0);			
				middleNodes.add(ii + 1);
			}
			
			middleSet.addAll(middleNodes);
			Collections.shuffle(middleNodes);
			topoDsts.addAll(middleNodes);
			
			Collections.shuffle(middleNodes);
			topoSrcs.addAll(middleNodes);
			
			for (int ii = 0; ii < n; ii++) {
				topoDsts.add(10000);
			}
			
			topoSrcs.add(rng.nextInt(1, n));
			topoDsts.add(0);
		
			assertThrows(IllegalArgumentException.class, () -> {GraphUtility.sort(topoSrcs, topoDsts);}, "Failed at graph #" + n);
		}
	}
	
}


