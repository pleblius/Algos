package comprehensive;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class is a JUnit test suite for our custom discrete set
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
	
	@BeforeEach
	void setUp() throws Exception {
		numberNodes = new ArrayList<Integer>(1000);
		fruitNodes = new ArrayList<String>(Arrays.asList("apple", "blueberry", "canteloupe", "durian", "eggplant", "fig", "grape", "honeydew"));
		
		for (int i = 0; i < 1000; i++) {
			numberNodes.add(i);
		}
		
		numberSet = new DiscreteMap<Integer>(numberNodes);
		fruitSet = new DiscreteMap<String>(fruitNodes);
		
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
		for (int i = 0; i < 500; i++) {
			for (int j = 500; j > 0; j/=2) {
				numberSet.union(i, i + j);
			}
		}
		
		for (int f1 : numberNodes) {
			for (int f2 : numberNodes) {
				assertTrue(numberSet.getRepresentative(f1) == numberSet.getRepresentative(f2));
			}
		}
	}
}