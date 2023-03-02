package assign06;

import java.util.NoSuchElementException;

/**
 * This class represents a generic stack that utilizes a linked list for its ordering
 * and operations. A stack is a last-in first-out (LIFO) data structure, and all
 * operations can only be conducted on items that are "on top" of the stack--meaning
 * the most-recent addition to the stack.
 * 
 * Because of implementation details for the linked list that this stack utilizes,
 * all stack operations run in O(1) time.
 * 
 * @author Tyler Wilcox and Andrew Tolton.
 * @version 28 February, 2023.
 * 
 * @param <E> - Generic type parameter of the elements contained in the stack.
 */
public class LinkedListStack<E> implements Stack<E> {
	private SinglyLinkedList<E> stack;
	
	/**
	 * Creates a new, empty LinkedListStack object.
	 */
	public LinkedListStack() {
		stack = new SinglyLinkedList<E>();
	}
	
	/**
	 * Removes all data elements from the stack.
	 */
	@Override
	public void clear() {
		stack.clear();
	}
	
	/**
	 * Checks if the stack is currently empty, containing no data elements.
	 * 
	 * @return true if the stack is empty, false otherwise.
	 */
	@Override
	public boolean isEmpty() {
		return stack.isEmpty();
	}

	/**
	 * Gets the data element from the top item on the stack without removing it
	 * from the stack.
	 * Throws an exception if the stack is empty.
	 * 
	 * @return the data element contained at the top of the stack.
	 * @throws NoSuchElementException - if the stack is empty.
	 */
	@Override
	public E peek() throws NoSuchElementException {
		if (this.isEmpty()) {
			throw new NoSuchElementException("There are no items in the stack.");
		}
		return stack.getFirst();
	}

	/**
	 * Gets the data element from the top item on the stack and removes it
	 * from the stack.
	 * Throws an exception if the stack is empty.
	 * 
	 * @return the data element contained at the top of the stack.
	 * @throws NoSuchElementException - if the stack is empty.
	 */
	@Override
	public E pop() throws NoSuchElementException {
		if (this.isEmpty()) {
			throw new NoSuchElementException("There are no items in the stack.");
		}
		
		return stack.deleteFirst();
	}

	/**
	 * Places the given element on top of the stack.
	 * 
	 * @param element - the data element to be placed on the stack.
	 */
	@Override
	public void push(E element) {
		stack.insertFirst(element);		
	}

	/**
	 * Gets the number of items currently on the stack.
	 * 
	 * @return the size of the stack.
	 */
	@Override
	public int size() {
		return stack.size();
	}

}
