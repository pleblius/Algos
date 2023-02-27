package assign06;

import java.util.NoSuchElementException;

public class LinkedListStack<E> implements Stack<E> {
	SinglyLinkedList<E> stack;
	
	public LinkedListStack() {
		stack = new SinglyLinkedList<E>();
	}
	
	@Override
	public void clear() {
		stack = new SinglyLinkedList<E>();
	}

	@Override
	public boolean isEmpty() {
		return stack.isEmpty();
	}

	@Override
	public E peek() throws NoSuchElementException {
		if (this.isEmpty()) {
			throw new NoSuchElementException("There are no items in the stack.");
		}
		return stack.getFirst();
	}

	@Override
	public E pop() throws NoSuchElementException {
		if (this.isEmpty()) {
			throw new NoSuchElementException("There are no items in the stack.");
		}
		
		return stack.deleteFirst();
	}

	@Override
	public void push(E element) {
		stack.insertFirst(element);		
	}

	@Override
	public int size() {
		return stack.size();
	}

}
