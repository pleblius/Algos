package assign06;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<E> implements List<E> {

	Node<E> headerer;
	Node<E> header;
	Node<E> trailer;
	int size;
	
	public SinglyLinkedList() {
		headerer.next = header;
		header.next = trailer;
		trailer.next = null;

		size = 0;
	}
	
	@Override
	public void insertFirst(E element) {
		Node<E> firstNode = new Node<E>(element, header.next);
		
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
		
		Node<E> newElement = new Node<E>(element, current.next);
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
		if (index < 0 || index >= size) {
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
		if (size == 0) {
			throw new NoSuchElementException("The list is empty");
		}
		
		E temp = header.next.data;
		header.next = header.next.next;
		size--;
		
		return temp;
	}

	@Override
	public E delete(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("That index is outside the bounds of the list.");
		}
		
		Node<E> current = headerer.next;
		for (int ii = 0; ii < index; ii++){
			current = current.next;
		}
		
		E data = current.next.data;
		current.next = current.next.next;
		size--;
		
		return data;
	}

	@Override
	public int indexOf(E element) {
		Iterator<E> iter = iterator();
		Node<E> currentNode = header.next;
		
		int index = 0;
		while (iter.hasNext())
		{
			if (currentNode.data.equals(element)) {
				return index;
			}
			index++;
		}
		return -1;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void clear() {
		size = 0;
		header.next = trailer;
	}

	@Override
	public Object[] toArray() {
		Object[] arr = new Object[size];
		Node<E> currentNode = header.next;
		
		for (int i = 0; i < size; i++) {
			arr[i] = currentNode.data;
			currentNode = currentNode.next;
		}
		
		return arr;
	}

	@Override
	public Iterator<E> iterator() {
		return new SinglyLinkedListIterator();
	}
	
	private class Node<otherE>{
		otherE data;
		Node<otherE> next;
		
		public Node(otherE data, Node<otherE> next) {
			this.data = data;
			this.next = next;
		}
	}
	
	private class SinglyLinkedListIterator implements Iterator<E> {

		boolean canRemove;
		Node<E> previousNode;
		
		public SinglyLinkedListIterator() {
			previousNode = headerer;
			canRemove = false;
		}
		
		@Override
		public boolean hasNext() {
			return previousNode.next.next != trailer;
		}

		@Override
		public E next() {
			if (!hasNext()) {
				throw(new NoSuchElementException("Out of bounds error"));
			}
			
			canRemove = true;
			
			previousNode = previousNode.next;
			
			return previousNode.next.data;
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
