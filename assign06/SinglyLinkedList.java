package assign06;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<E> implements List<E> {

	Node<E> headerer;
	Node<E> header;
	Node<E> trailer;
	int size;
	
	public void SinglyLinkedList() {
		headerer.next = header;
		header.next = trailer;
		trailer.next = null;

		size = 0;
	}
	
	@Override
	public void insertFirst(E element) {
		Node<E> firstNode = new Node(element, header.next);
		
		header.next = firstNode;
		size++;
	}

	@Override
	public void insert(int index, E element) throws IndexOutOfBoundsException {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("That index is out of bounds!");
		}
		
		Node<E> current = header.next;
		for (int ii = 0; ii < index; ii++){
			current = current.next;
		}
		
		Node<E> newElement = new Node(element, current.next);
		current.next = newElement;
		size++;
	}

	@Override
	public E getFirst() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException("There is no data in the list.");
		}
		return header.next.data;
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("That index is out of bounds!");
		}
		
		Node<E> current = header.next;
		for (int ii = 0; ii < index; ii++){
			current = current.next;
		}
		
		return current.data;
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
		return new SinglyLinkedListIterator<E>();
	}
	
	private class Node<otherE>{
		otherE data;
		Node<otherE> next;
		
		public Node(otherE data, Node<otherE> next) {
			this.data = data;
			this.next = next;
		}
	}
	
	private class SinglyLinkedListIterator<F> implements Iterator<F> {

		boolean canRemove;
		Node<F> previousNode;
		
		@SuppressWarnings("unchecked")
		public SinglyLinkedListIterator() {
			previousNode = headerer;
			canRemove = false;
		}
		
		@Override
		public boolean hasNext() {
			return previousNode.next.next != trailer;
		}

		
		@SuppressWarnings("unchecked")
		@Override
		public F next() {
			if (!hasNext()) {
				throw(new NoSuchElementException("Out of bounds error"));
			}
			
			canRemove = true;
			
			previousNode = previousNode.next;
			
			return (F) previousNode.next.data;
		}
		
		@Override
		public void remove() {
			if (!canRemove)
				throw new IllegalStateException("Element already removed.");
			canRemove = false;
			
			previousNode.next = previousNode.next.next;
			
			size--;
		}
	}
}
