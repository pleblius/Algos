package assign03;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

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
	}
	
	@Test
	void containsTests() {
		
	}
	
	@Test
	void isEmptyTests() {
		
	}
	
	@Test
	void clearTests() {
		
	}

}
