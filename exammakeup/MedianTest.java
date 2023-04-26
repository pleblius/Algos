package exammakeup;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MedianTest {
	int[] unitArray;
	int[] twoArray;
	int[] evenUnsortedArray;
	int[] oddUnsortedArray;
	int[] evenSortedArray;
	int[] oddSortedArray;
	int[] evenReverseArray;
	int[] oddReverseArray;
	int[] oddNegativeArray;
	int[] evenNegativeArray;
	int[] randomArray;
	
	@BeforeEach
	void setup() {
		unitArray = new int[] {1};
		twoArray = new int[] {1, 2};
		evenUnsortedArray = new int[] {3, 0, 1, 9, 5, 7};
		oddUnsortedArray = new int[] {7, 3, 5, 16, 4};
		evenSortedArray = new int[] {1, 2, 3, 4, 5, 6};
		oddSortedArray = new int[] {1, 2, 3, 4, 5};
		evenReverseArray = new int[] {6, 5, 4, 3, 2, 1};
		oddReverseArray = new int[] {5, 4, 3, 2, 1};
		oddNegativeArray = new int[] {-1, -2, -3, -4, -5};
		evenNegativeArray = new int[] {-1, -2, -3, -4, -5, -6};
	}
	
	@Test
	void testUnitArray() {
		assertEquals(1, Median.median(unitArray));
	}
	
	@Test
	void testTwoArray() {
		assertEquals(2, Median.median(twoArray));
	}
	
	@Test
	void testUnsortedArrays() {
		assertEquals(5, Median.median(evenUnsortedArray), "Even unsorted array failed.");
		assertEquals(5, Median.median(oddUnsortedArray), "Odd unsorted array failed.");
	}
	
	@Test
	void testSortedArrays() {
		assertEquals(4, Median.median(evenSortedArray), "Even sorted array failed.");
		assertEquals(3, Median.median(oddSortedArray), "Odd sorted array failed.");
	}
	
	@Test
	void testReverseArrays() {
		assertEquals(4, Median.median(evenReverseArray), "Even reverse array failed.");
		assertEquals(3, Median.median(oddReverseArray), "Odd reverse array failed.");
	}
	
	@Test
	void testNegativeArrays() {
		assertEquals(-3, Median.median(evenNegativeArray), "Even negative array failed.");
		assertEquals(-3, Median.median(oddNegativeArray), "Odd negative array failed.");
	}
	
	@Test
	void testRandomArraySemisorted() {
		randomArray = new int[1000];
		for (int i = 0; i < randomArray.length; i++) {
			randomArray[i] = (int)(Math.random()*randomArray.length + 1);
		}
		
		var med = Median.median(randomArray);
		for (int i = 0; i < randomArray.length/2; i++) {
			assertTrue(randomArray[i] <= med, "Random sort check failed at index " + i);
		}
		for (int i = randomArray.length/2 + 1; i < randomArray.length; i++) {
			assertTrue(randomArray[i] >= med, "Random sort check failed at index " + i);
		}
	}
	
	@Test
	void testReverseArraySemisorted() {
		evenReverseArray = new int[1000];
		for (int i = 0; i < evenReverseArray.length; i++) {
			evenReverseArray[i] = evenReverseArray.length - i;
		}
		
		var med = Median.median(evenReverseArray);
		for (int i = 0; i < evenReverseArray.length/2; i++) {
			assertTrue(evenReverseArray[i] < med, "Reverse sort check failed at index " + i);
		}
		for (int i = evenReverseArray.length/2 + 1; i < evenReverseArray.length; i++) {
			assertTrue(evenReverseArray[i] > med, "Reverse sort check failed at index " + i);
		}
	}
}
