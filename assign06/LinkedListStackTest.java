package assign06;

import static org.junit.jupiter.api.Assertions.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class LinkedListStackTest {
	
	LinkedListStack<Integer> empty;
	LinkedListStack<String> oneString;
	
	@BeforeEach
	void setup() {
		empty = new LinkedListStack<Integer>();
		oneString = new LinkedListStack<String>();
		
		oneString.push("Hello!");
	}
		
	
	/*
	 * Peek Tests 
	 */
	
	@Test
	void testPeekExceptions() {
		assertThrows(NoSuchElementException.class, () -> {empty.peek();});
		oneString.pop();
		assertThrows(NoSuchElementException.class, () -> {oneString.peek();});
	}
	
	@Test
	void testPeek() {
		assertEquals("Hello!", oneString.peek());
		assertEquals(1, oneString.size());
		
		empty.push(1);
		empty.push(2);
		empty.push(3);
		empty.push(4);
		
		assertEquals(4, empty.peek());
		assertEquals(4, empty.peek());
		assertEquals(4, empty.peek());
		assertEquals(4, empty.peek());
	}
	
	/*
	 * Pop Tests
	 */
	
	@Test
	void testPopExceptions() {
		assertThrows(NoSuchElementException.class, () -> {empty.pop();});
		oneString.pop();
		assertThrows(NoSuchElementException.class, () -> {oneString.pop();});
	}
	
	@Test
	void testPop() {
		assertEquals("Hello!", oneString.pop());
		assertEquals(0, oneString.size());
		
		empty.push(1);
		empty.push(2);
		empty.push(3);
		empty.push(4);
		
		assertEquals(4, empty.pop());
		assertEquals(3, empty.pop());
		assertEquals(2, empty.pop());
		assertEquals(1, empty.pop());
	}
	
	/*
	 * Push Tests
	 */
	
	@Test 
	void testPush() {
		for (int ii = 0; ii < 1000; ii++) {
			empty.push(ii);
			assertEquals(ii + 1, empty.size());
			assertEquals(ii, empty.peek());
		}
	}
	
	/*
	 * Clear tests
	 */
	
	@Test
	void testClear() {
		oneString.clear();
		assertThrows(NoSuchElementException.class, () -> {oneString.pop();});
		assertEquals(0, oneString.size());
		
		oneString.push("World!");
		assertEquals("World!", oneString.peek());
	}
	
	/*
	 * Size
	 */
	
	@Test
	void testSize() {
		for (int ii = 0; ii < 1000; ii++) {
			empty.push(ii);
			
			assertEquals(ii + 1, empty.size());
		}
		
		for (int ii = 999; ii >= 0; ii--) {
			empty.pop();
			
			assertEquals(ii, empty.size());
		}
	}
	
	/*
	 * isEmpty
	 */
	
	@Test
	void testIsEmpty() {
		assertTrue(empty.isEmpty());
		assertFalse(oneString.isEmpty());
		oneString.pop();
		assertTrue(oneString.isEmpty());
	}
}
