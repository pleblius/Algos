package assign06;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<E> implements List<E> {

	Node head;
	Node tail;
	int size;
	
	public void SinglyLinkedList() {
		
	}
	
	@Override
	public void insertFirst(E element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(int index, E element) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public E getFirst() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E deleteFirst() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E delete(int index) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf(E element) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private class Node<E>{
		E data;
		Node next;
		
		public Node(E data, Node next) {
			this.data = data;
			this.next = next;
		}
		
	}
	
	private class SinglyLinkedListIterator implements Iterator<E> {

		int nextIndex;
		boolean canRemove;
		
		@Override
		public boolean hasNext() {
			return nextIndex < size;
		}

		@Override
		public E next() {
			if (!hasNext()) {
				throw(new NoSuchElementException("Out of bounds error"));
			}
			
			canRemove = true;
			;
			return ;
		}
		
		@Override
		public void remove() {
			
		}
		
		
		
	}
	
}
