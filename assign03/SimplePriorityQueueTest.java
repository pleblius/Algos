package assign03;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SimplePriorityQueueTest {
	
	SimplePriorityQueue<Integer> intQueue;
	SimplePriorityQueue<String> stringQueue;
	SimplePriorityQueue<Point> comparatorQueue;
	SimplePriorityQueue<Integer> largeQueue;
	
	@BeforeEach
	void setup() {
		intQueue = new SimplePriorityQueue<Integer>();
		stringQueue = new SimplePriorityQueue<String>();
		comparatorQueue = new SimplePriorityQueue<Point>((p1, p2) -> ((Integer)(p1.y)).compareTo((Integer)(p2.y)));
		largeQueue = new SimplePriorityQueue<Integer>();
	}
	
	@Test
	void insertSingleTests() {
		intQueue.insert(2);
		assertFalse(intQueue.isEmpty());
		
		stringQueue.insert("This is a string");
		assertFalse(intQueue.isEmpty());
	}
	
	@Test
	void sizeTests() {
		intQueue.insert(0);
		assertEquals(1,intQueue.size());
		intQueue.insert(1);
		assertEquals(2,intQueue.size());
		
		for (int i = 0; i < 1000; i++) {
			largeQueue.insert((Integer) i);
		}
		
		assertEquals(1000,largeQueue.size());
	}
	
	@Test
	void findMaxTests() {
		intQueue.insert(1);
		intQueue.insert(3);
		
		assertEquals(3, intQueue.findMax());
		
		stringQueue.insert("abc");
		stringQueue.insert("zxy");
		stringQueue.insert("eee");
		
		assertEquals("zxy", stringQueue.findMax());
	}
	
	@Test
	void insertMultiple() {
		for (int i= 1000; i > 0; i--) {
			largeQueue.insert((Integer) i);
		}
		assertEquals(1000, largeQueue.findMax());
		
		largeQueue.insert(-1);
		assertEquals(1000, largeQueue.findMax());
	}
	
	@Test
	void insertRandom() {
		for (int i = 0; i < 100; i++) {
			largeQueue.insert((int)(10*Math.random() + 1));
		}
		largeQueue.insert(15);
		largeQueue.insert(-5);
		assertEquals(15, largeQueue.findMax());
		
		for (int i = 0; i < 101; i++) {
			assertEquals(largeQueue.findMax(),largeQueue.deleteMax());
		}
		assertEquals(-5, largeQueue.findMax());
	}

	@Test
	void insertAllTests() {
		var intArray = new ArrayList<Integer>();
		
		for (int i = 0; i < 10; i++) {
			intArray.add(i);
		}
		
		for (int i = 0; i < 100; i++) {
			largeQueue.insert(0);
		}
		
		largeQueue.insertAll(intArray);
		assertEquals(9, largeQueue.findMax());
		
		List<Integer> intArray2 = Arrays.asList(-4, 8);
		largeQueue.insertAll(intArray2);
		
		assertEquals(9, largeQueue.deleteMax());
		assertEquals(8, largeQueue.deleteMax());
		assertEquals(8, largeQueue.deleteMax());

		for (int i = 7; i >= 0; i--) {
			assertEquals(i, largeQueue.deleteMax());
		}
		
		for (int i = 0; i < 100; i++) {
			assertEquals(0, largeQueue.deleteMax());
		}
		
		assertEquals(-4, largeQueue.findMax());
	}
	
	@Test
	void insertComparatorTests() {
		comparatorQueue.insert(new Point(1, 2));
		comparatorQueue.insert(new Point(-4, 5));
		comparatorQueue.insert(new Point(18, 1));
		
		assertEquals(new Point(-4, 5), comparatorQueue.deleteMax());
		assertEquals(new Point(1, 2), comparatorQueue.deleteMax());
		assertEquals(new Point(18, 1), comparatorQueue.deleteMax());
	}
	
	@Test
	void containsTests() {
		intQueue.insert(5);
		assertTrue(intQueue.contains(5));
		
		stringQueue.insert("hello!");
		stringQueue.insert("the cats are coming");
		assertTrue(stringQueue.contains("hello!"));
		assertFalse(stringQueue.contains("I love cats!"));
		
		assertFalse(comparatorQueue.contains(new Point(2,3)));
		comparatorQueue.insert(new Point(2, 3));
		assertTrue(comparatorQueue.contains(new Point(2,3)));
	}
	
	@Test
	void isEmptyTests() {
		assertTrue(intQueue.isEmpty());
		intQueue.insert(1);
		assertFalse(intQueue.isEmpty());
	}
	
	@Test
	void clearTests() {
		assertTrue(intQueue.isEmpty());
		intQueue.insert(1);
		assertFalse(intQueue.isEmpty());
		intQueue.clear();
		assertTrue(intQueue.isEmpty());
	}
	
	@Test
	void exceptionTests() {
		assertThrows(NoSuchElementException.class, () -> {intQueue.findMax();});
		assertThrows(NoSuchElementException.class, () -> {intQueue.deleteMax();});
		
		intQueue.insert(null);
		assertThrows(NoSuchElementException.class, () -> {intQueue.findMax();});
		assertThrows(NoSuchElementException.class, () -> {intQueue.deleteMax();});
	}

}
