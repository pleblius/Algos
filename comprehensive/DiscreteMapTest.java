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
 * data structure in class DiscreteMap.
 * 
 * @author Tyler Wilcox && Andrew Tolton
 * @version 20 April, 2023
 */
class DiscreteMapTest {

	List<Integer> numberNodes;
	List<String> fruitNodes;
	
	DisjointSet<Integer> numberSet;
	DisjointSet<String> fruitSet;
	DisjointSet<Integer> emptySet;
	
	@BeforeEach
	void setUp() throws Exception {
		numberNodes = new ArrayList<Integer>(1000);
		fruitNodes = new ArrayList<String>(Arrays.asList("apple", "blueberry", "canteloupe", "durian", "eggplant", "fig", "grape", "honeydew"));
		
		for (int i = 0; i < 10000; i++) {
			numberNodes.add(i);
		}
		
		numberSet = new DiscreteMap<Integer>(numberNodes);
		fruitSet = new DiscreteMap<String>(fruitNodes);
		emptySet = new DiscreteMap<>();
	}

	@Test
	void emptyTest() {
		assertThrows(NoSuchElementException.class, () -> emptySet.getRepresentative(1));
		assertThrows(NoSuchElementException.class, () -> emptySet.union(1, 2));
		assertEquals(0, emptySet.size());
		
		emptySet.makeSet(1);
		
		assertThrows(NoSuchElementException.class, () -> emptySet.union(1, 2));
	}
	
    @Test
    public void emptyListTest() {
        List<Integer> emptyList = new ArrayList<Integer>();
        
        emptySet = new DiscreteMap<Integer>(emptyList);
        
        assertEquals(0, emptySet.size());
    }
    
    @Test
    public void duplicateListTest() {
    	emptySet = new DiscreteMap<>(Arrays.asList(1, 2, 3, 2, 3, 1, 2, 3));
        assertEquals(3, emptySet.size());
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
    public void duplicateTest() {
        emptySet.makeSet(1);
        emptySet.makeSet(2);
        emptySet.makeSet(1);
        assertEquals(2, emptySet.size());
    }
    
    @Test
    public void unitTest() {
        emptySet.makeSet(2);
        assertEquals(2, emptySet.getRepresentative(2));
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
	void sizeAfterUnionTest() {
		emptySet.makeSet(1);
		emptySet.makeSet(2);
		
		assertEquals(2, emptySet.size());
		
		emptySet.union(1, 2);
		
		assertEquals(2, emptySet.size());
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
    public void alreadyMergedTest() {
    	emptySet.makeSet(1);
    	emptySet.makeSet(2);
    	emptySet.union(1, 2);

        Integer f1 = emptySet.getRepresentative(1);
        Integer f2 = emptySet.getRepresentative(2);

        emptySet.union(2, 1);
        Integer f3 = emptySet.getRepresentative(1);
        Integer f4 = emptySet.getRepresentative(2);

        assertEquals(f1, f3);
        assertEquals(f2, f4);
    }
    
    @Test
    public void unionThenDuplicateTest() {
    	emptySet.makeSet(1);
    	emptySet.makeSet(2);
    	
    	var f1 = emptySet.getRepresentative(1);
    	var f2 = emptySet.getRepresentative(2);
    	
    	assertFalse(f1 == f2);
    	
    	emptySet.union(1, 2);
    	
    	f1 = emptySet.getRepresentative(1);
    	f2 = emptySet.getRepresentative(2);
    	
    	assertTrue(f1 == f2);
    	
    	emptySet.makeSet(1);
    	emptySet.makeSet(2);
    	
    	f1 = emptySet.getRepresentative(1);
    	f2 = emptySet.getRepresentative(2);
    	
    	assertTrue(f1 == f2);
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
		
		var forest = new DiscreteMap<Integer>(list);
		
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
		var forest = new DiscreteMap<Integer>();
		
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
		DisjointSet<String> discreteSet = new DiscreteMap<>();
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
		DisjointSet<String> discreteSet = new DiscreteMap<>();
        discreteSet.makeSet("A");
        discreteSet.makeSet("B");
        discreteSet.makeSet("C");

        discreteSet.union("A", "B");
        discreteSet.union("B", "C");
        discreteSet.union("C", "A");
        
        String f1 = discreteSet.getRepresentative("A");
        String f2 = discreteSet.getRepresentative("C");
        assertTrue(f1 == f2);
    }
	
	@Test
	void randomTest() {
        DisjointSet<String> discreteSet = new DiscreteMap<>();
        discreteSet.makeSet("A");
        discreteSet.makeSet("B");
        discreteSet.makeSet("C");
        discreteSet.makeSet("D");

        discreteSet.union("A", "B");
        discreteSet.union("C", "D");

        String f1 = discreteSet.getRepresentative("A");
        String f2 = discreteSet.getRepresentative("C");
        assertFalse(f1 == f2);
        
        discreteSet.makeSet("E");
        discreteSet.union("D", "E");
        
        f2 = discreteSet.getRepresentative("C");
        String f3 = discreteSet.getRepresentative("E");
        
        assertTrue(f2 == f3);

        discreteSet.union("A", "E");

        assertTrue(discreteSet.getRepresentative("A") == discreteSet.getRepresentative("E"));
        assertTrue(discreteSet.getRepresentative("B") == discreteSet.getRepresentative("D"));
    }
}
