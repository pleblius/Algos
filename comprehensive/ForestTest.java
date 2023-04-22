package comprehensive;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class is a JUnit test suite for our implementation of the disjoint forest
 * data structure in class DisjointForest.
 * 
 * @author Tyler Wilcox && Andrew Tolton
 * @version 20 April, 2023
 */
class ForestTest {

	List<Integer> numberNodes;
	List<String> fruitNodes;
	
	DisjointSet<Integer> numberSet;
	DisjointSet<String> fruitSet;
	
	@BeforeEach
	void setUp() throws Exception {
		numberNodes = new ArrayList<Integer>(1000);
		fruitNodes = new ArrayList<String>(Arrays.asList("apple", "blueberry", "canteloupe", "durian", "eggplant", "fig", "grape", "honeydew"));
		
		for (int i = 0; i < 10000; i++) {
			numberNodes.add(i);
		}
		
		numberSet = new DisjointForest<Integer>(numberNodes);
		fruitSet = new DisjointForest<String>(fruitNodes);
		
	}

	@Test
	void emptyTest() {
		DisjointSet<Integer> emptySet = new DisjointForest<>();
		
		assertThrows(NoSuchElementException.class, () -> emptySet.getRepresentative(1));
		assertThrows(NoSuchElementException.class, () -> emptySet.union(1, 2));
		
		emptySet.makeSet(1);
		
		assertThrows(NoSuchElementException.class, () -> emptySet.union(1, 2));
	}
	
	@Test
	void nullTest() {
		assertThrows(NullPointerException.class, () -> numberSet.makeSet(null));
		assertThrows(NullPointerException.class, () -> numberSet.getRepresentative(null));
		assertThrows(NullPointerException.class, () -> numberSet.union(1, null));
		assertThrows(NullPointerException.class, () -> numberSet.union(null, 1));
		assertThrows(NullPointerException.class, () -> numberSet.union(null, null));
	}
	
	@Test
	void notConnectedTest() {
		for (String f1 : fruitNodes) {
			for (String f2 : fruitNodes) {
				if (!f1.equals(f2))
					assertFalse(fruitSet.getRepresentative(f1) == fruitSet.getRepresentative(f2));
			}
		}
	}
	
	@Test
	void allConnectedTest() {
		for (int i = 1; i < fruitNodes.size(); i++) {
			fruitSet.union(fruitNodes.get(0), fruitNodes.get(i));
		}
		
		for (String f1 : fruitNodes) {
			for (String f2 : fruitNodes) {
				assertTrue(fruitSet.getRepresentative(f1) == fruitSet.getRepresentative(f2));
			}
		}
	}
	
	@Test
	void connectedLinkTest() {
		for (int i = 0; i < fruitNodes.size()-1; i++) {
			fruitSet.union(fruitNodes.get(i), fruitNodes.get(i+1));
		}
		
		for (String f1 : fruitNodes) {
			for (String f2 : fruitNodes) {
				assertTrue(fruitSet.getRepresentative(f1) == fruitSet.getRepresentative(f2));
			}
		}
	}
	
	@Test
	void someConnectedTest() {
		for (int i = 1; i < 4; i++) {
			fruitSet.union(fruitNodes.get(0), fruitNodes.get(i));
		}
		
		for (int i = 5; i < 8; i++) {
			fruitSet.union(fruitNodes.get(4), fruitNodes.get(i));
		}
		
		for (int i = 0; i < 8; i++) {
			String f1 = fruitSet.getRepresentative(fruitNodes.get(i));
			for (int j = 0; j < 8; j++) {
				String f2 = fruitSet.getRepresentative(fruitNodes.get(j));
				
				if (i < 4 && j < 4 || i >= 4 && j >= 4) {
					assertTrue(f1 == f2);
				}
				else {
					assertFalse(f1 == f2);
				}	
			}
		}	
	}
	
	@Test
	void evenOddConnectedTest() {
		for (int i = 0; i < 999; i+=2) {
			numberSet.union(numberNodes.get(0), numberNodes.get(i));
			numberSet.union(numberNodes.get(1), numberNodes.get(i+1));
		}
		
		for (int i = 0; i < 1000; i++) {
			int num1 = numberSet.getRepresentative(i);
			for (int j = 0; j < 1000; j++) {
				int num2 = numberSet.getRepresentative(j);
				if (i % 2 == 0 && j % 2 == 0) {
					assertTrue(num1 == num2);
				}
				else if (i % 2 != 0 && j % 2 != 0){
					assertTrue(num1 == num2);
				}
				else {
					assertFalse(num1 == num2);
				}
			}
		}
		
		numberSet.union(0, 999);
		for (int i = 0; i < 1000; i++) {
			int num1 = numberSet.getRepresentative(i);
			for (int j = 0; j < 1000; j++) {
				int num2 = numberSet.getRepresentative(j);
				
				assertTrue(num1 == num2);
			}
		}
	}
	
	@Test
	void largeConnectedTest() {
		for (int i = 0; i < 5000; i++) {
			for (int j = 5000; j > 0; j/=2) {
				numberSet.union(i, i + j);
			}
		}
		
		for (int f1 : numberNodes) {
			for (int f2 : numberNodes) {
				assertTrue(numberSet.getRepresentative(f1) == numberSet.getRepresentative(f2));
			}
		}
	}
	
	@Test
	void largeListIntermediateListBSTest() {
		var list = new ArrayList<Integer>();
		
		for (int i = 0; i < 10_000_000; i++) {
			list.add(i);
		}
		
		var forest = new DisjointForest<Integer>(list);
		
		for (int i = 0; i < 5000000; i++) {
			forest.union(0, i);
		}
		for (int i = 5000000; i < 10000000; i++) {
			forest.union(5000000, i);
		}
		
		forest.union(1, 10000000-1);
		
		for (int i = 0; i < 10000000 - 1; i++) {
			var f1 = forest.getRepresentative(i);
			var f2 = forest.getRepresentative(i + 1);
			
			assertTrue(f1 == f2);
		}
	}
	
	@Test
	void a1000setsof1000setsTest() {
		var list = new ArrayList<Integer>();
		var forest = new DisjointForest<Integer>();
		
		for (int i = 0; i < 1_000_000; i++) {
			forest.makeSet(i);
		}
		
		for (int i = 0; i < 1_000_000; i+=1000) {
			for (int j = 0; j < 1_000; j++) {
				forest.union(i, i + j);
			}
		}
		
		for (int i = 0; i < 1_000_000; i+=1000) {
			var f1 = forest.getRepresentative(i);
			for (int j = 0; j < 1_000_000; j++) {
				var f2 = forest.getRepresentative(j);
				if (i <= j && j < i + 1000) {
					assertTrue(f1 == f2);
				}
				else
					assertFalse(f1 == f2);
			}
		}
		
		for (int i = 0; i < 998_999; i+=1000) {
			forest.union(i, i + 1000);
		}
		
		for (int i = 0; i < 999999; i++) {
			var f1 = forest.getRepresentative(i);
			var f2 = forest.getRepresentative(i + 1);
			
			assertEquals(f1,f2);
		}
	}
	
	@Test
	void chainedTest() {
		DisjointSet<String> discreteSet = new DisjointForest<>();
        discreteSet.makeSet("A");
        discreteSet.makeSet("B");
        discreteSet.makeSet("C");
        discreteSet.makeSet("D");

        discreteSet.union("A", "B");
        discreteSet.union("B", "C");
        discreteSet.union("C", "D");

        assertEquals(discreteSet.getRepresentative("A"), discreteSet.getRepresentative("D"));
    }
	
	@Test
	void circularTest() {
		DisjointSet<String> discreteSet = new DisjointForest<>();
        discreteSet.makeSet("A");
        discreteSet.makeSet("B");
        discreteSet.makeSet("C");

        discreteSet.union("A", "B");
        discreteSet.union("B", "C");
        discreteSet.union("C", "A");

        assertEquals(discreteSet.getRepresentative("A"), discreteSet.getRepresentative("C"));
    }
	
	@Test
	void randomTest() {
        DisjointSet<String> discreteSet = new DisjointForest<>();
        discreteSet.makeSet("A");
        discreteSet.makeSet("B");
        discreteSet.makeSet("C");
        discreteSet.makeSet("D");

        discreteSet.union("A", "B");
        discreteSet.union("C", "D");

        assertNotEquals(discreteSet.getRepresentative("A"), discreteSet.getRepresentative("C"));

        discreteSet.makeSet("E");
        discreteSet.union("D", "E");

        assertEquals(discreteSet.getRepresentative("C"), discreteSet.getRepresentative("E"));

        discreteSet.union("A", "E");

        assertEquals(discreteSet.getRepresentative("A"), discreteSet.getRepresentative("E"));
        assertEquals(discreteSet.getRepresentative("B"), discreteSet.getRepresentative("D"));
    }
}
