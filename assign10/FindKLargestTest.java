/**
 * 
 */
package assign10;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FindKLargestTest {
	
	List<Integer> intList;
	Comparator<Integer> comp;
	
	@BeforeEach
	void setup() {
		comp = (a,b) -> (b - a);
		
		intList = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			intList.add(i);
		}
	}
	
	
    @Test
    void testThrows() {
    	List<Integer> emptyList = new ArrayList<Integer>();
    	
    	assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestHeap(emptyList, 1));
    	assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestSort(emptyList, 1));
    	assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestHeap(emptyList, 1, comp));
    	assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestSort(emptyList, 1, comp));
    	
    	assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestHeap(intList, -1));
    	assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestHeap(intList, -1));
    	assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestHeap(intList, -1, comp));
    	assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestHeap(intList, -1, comp));
    }
    
    @Test
    void testHeap() {
    	List<Integer> comparisonList = new ArrayList<>();
    	
    	var testList1 = FindKLargest.findKLargestHeap(intList, 1);
    	var testList2 = FindKLargest.findKLargestHeap(intList, 10);
    	var testList3 = FindKLargest.findKLargestHeap(intList, 100);
    	
    	for (int i = 0; i < intList.size(); i++) {
    		comparisonList.add(intList.get(i));
    	}
    	
    	Collections.reverse(comparisonList);
    	
    	for (int i = 0; i < 1; i++) {
    		assertEquals(comparisonList.get(i), testList1.get(i));
    	}
    	
    	for (int i = 0; i < 10; i++) {
    		assertEquals(comparisonList.get(i), testList2.get(i));
    	}
    	
    	for (int i = 0; i < 100; i++) {
    		assertEquals(comparisonList.get(i), testList3.get(i));
    	}
    }
    
    @Test
    void testSort() {
    	List<Integer> comparisonList = new ArrayList<>();
    	
    	var testList1 = FindKLargest.findKLargestSort(intList, 1);
    	var testList2 = FindKLargest.findKLargestSort(intList, 10);
    	var testList3 = FindKLargest.findKLargestSort(intList, 100);
    	
    	for (int i = 0; i < intList.size(); i++) {
    		comparisonList.add(intList.get(i));
    	}
    	
    	Collections.reverse(comparisonList);
    	
    	for (int i = 0; i < 1; i++) {
    		assertEquals(comparisonList.get(i), testList1.get(i));
    	}
    	
    	for (int i = 0; i < 10; i++) {
    		assertEquals(comparisonList.get(i), testList2.get(i));
    	}
    	
    	for (int i = 0; i < 100; i++) {
    		assertEquals(comparisonList.get(i), testList3.get(i));
    	}
    }
    
    @Test
    void testHeapComp() {
    	List<Integer> comparisonList = new ArrayList<>();
    	
    	var testList1 = FindKLargest.findKLargestHeap(intList, 1, comp);
    	var testList2 = FindKLargest.findKLargestHeap(intList, 10, comp);
    	var testList3 = FindKLargest.findKLargestHeap(intList, 100, comp);
    	
    	for (int i = 0; i < intList.size(); i++) {
    		comparisonList.add(intList.get(i));
    	}
    	
    	for (int i = 0; i < 1; i++) {
    		assertEquals(comparisonList.get(i), testList1.get(i));
    	}
    	
    	for (int i = 0; i < 10; i++) {
    		assertEquals(comparisonList.get(i), testList2.get(i));
    	}
    	
    	for (int i = 0; i < 100; i++) {
    		assertEquals(comparisonList.get(i), testList3.get(i));
    	}
    }
    
    @Test
    void testSortComp() {
    	List<Integer> comparisonList = new ArrayList<>(100);
    	
    	var testList1 = FindKLargest.findKLargestSort(intList, 1, comp);
    	var testList2 = FindKLargest.findKLargestSort(intList, 10, comp);
    	var testList3 = FindKLargest.findKLargestSort(intList, 100, comp);
    	
    	for (int i = 0; i < intList.size(); i++) {
    		comparisonList.add(intList.get(i));
    	}
    	
    	for (int i = 0; i < 1; i++) {
    		assertEquals(comparisonList.get(i), testList1.get(i));
    	}
    	
    	for (int i = 0; i < 10; i++) {
    		assertEquals(comparisonList.get(i), testList2.get(i));
    	}
    	
    	for (int i = 0; i < 100; i++) {
    		assertEquals(comparisonList.get(i), testList3.get(i));
    	}
    }
    
    @Test
    void testUnsortedInputHeap() {
    	List<Integer> comparisonList = new ArrayList<>();
    	
    	Collections.shuffle(intList);
    	
    	var testList1 = FindKLargest.findKLargestHeap(intList, 1);
    	var testList2 = FindKLargest.findKLargestHeap(intList, 10);
    	var testList3 = FindKLargest.findKLargestHeap(intList, 100);
    	
    	for (int i = 0; i < intList.size(); i++) {
    		comparisonList.add(intList.get(i));
    	}
    	
    	Collections.sort(comparisonList);
    	Collections.reverse(comparisonList);
    	
    	for (int i = 0; i < 1; i++) {
    		assertEquals(comparisonList.get(i), testList1.get(i));
    	}
    	
    	for (int i = 0; i < 10; i++) {
    		assertEquals(comparisonList.get(i), testList2.get(i));
    	}
    	
    	for (int i = 0; i < 100; i++) {
    		assertEquals(comparisonList.get(i), testList3.get(i));
    	}
    }
    
    @Test
    void testUnsortedInputSort() {
    	List<Integer> comparisonList = new ArrayList<>();
    	
    	Collections.shuffle(intList);
    	
    	var testList1 = FindKLargest.findKLargestSort(intList, 1);
    	var testList2 = FindKLargest.findKLargestSort(intList, 10);
    	var testList3 = FindKLargest.findKLargestSort(intList, 100);
    	
    	for (int i = 0; i < intList.size(); i++) {
    		comparisonList.add(intList.get(i));
    	}
    	
    	Collections.sort(comparisonList);
    	Collections.reverse(comparisonList);
    	
    	for (int i = 0; i < 1; i++) {
    		assertEquals(comparisonList.get(i), testList1.get(i));
    	}
    	
    	for (int i = 0; i < 10; i++) {
    		assertEquals(comparisonList.get(i), testList2.get(i));
    	}
    	
    	for (int i = 0; i < 100; i++) {
    		assertEquals(comparisonList.get(i), testList3.get(i));
    	}
    }
}
