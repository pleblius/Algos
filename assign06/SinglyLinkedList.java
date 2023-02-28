package assign06;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class represents a singly-linked list of ordered objects of generic type.
 * Data elements are tracked and ordered by the use of generic nodes, which contain
 * a single pointer to the next node in the list.
 * 
 * Utilizes header and trailer structural nodes which contain no data elements.
 * 
 * Random access operations, including insertion, get, and delete methods
 * at random points in the list are done in O(N) time.
 * Operations on items at the front of the list are done in O(1) time.
 * 
 * @author Tyler Wilcox and Andrew Tolton
 * @version 28 February, 2023
 * 
 * @param <E> Generic data type to be stored in the list
 */
public class SinglyLinkedList<E> implements List<E> {

	Node<E> headerer;
	Node<E> header;
	Node<E> trailer;
	int size;
	
	/**
	 * Contructs a new, empty, generic SinglyLinkedList object.
	 */
	public SinglyLinkedList() {
		trailer = new Node<E>(null, null);
		header = new Node<E>(null, trailer);
		headerer = new Node<E>(null, header);

		size = 0;
	}
	
	/**
	 * Inserts the provided data element into the first node in the list.
	 * Runs in O(1) time.
	 * 
	 * @param element - The data element to be inserted into the list.
	 */
	@Override
	public void insertFirst(E element) {
		Node<E> firstNode = new Node<E>(element, header.next);
		
		header.next = firstNode;
		size++;
	}
	
	/**
	 * Inserts the provided data element at the given index location in the list.
	 * Data is 0-indexed, so a valid index is between 0 and the size of the list:
	 * index = 0 corresponds to insertion at the beginning of the list,
	 * index = size corresponds to insertion at the end of the list.
	 * 
	 * Runs in O(N) time.
	 * 
	 * @param index - the index location to insert the data element.
	 * @param element - the data element to be inserted into the list.
	 * @throws IndexOutOfBoundsException - if index < 0 or index > size
	 */
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
	
	/**
	 * Returns the data element stored in the first index in the list.
	 * 
	 * Runs in O(1) time.
	 * 
	 * @return the data element at index 0.
	 * @throws NoSuchElementException - if there is no data stored in the list.
	 */
	@Override
	public E getFirst() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException("There is no data in the list.");
		}
		return header.next.data;
	}

	/**
	 * Returns the data element stored at the given index location.
	 * Data is 0-indexed, and valid indexes are between 0 and size - 1.
	 * 
	 * Runs in O(N) time.
	 * 
	 * @param index - the index location of the data to be accessed.
	 * @return the data element at that index.
	 * @throws IndexOutOfBoundsException - if index < 0 or index >= size.
	 */
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

	/**
	 * Deletes and returns the data element stored at the first index in the list.
	 * 
	 * Runs in O(1) time.
	 * 
	 * @return the deleted data element.
	 * @throws NoSuchElementException - if there is no first element to delete.
	 */
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

	/**
	 * Deletes and returns the data element stored at the given index in the list.
	 * Data is 0-indexed, with valid indexes between 0 and size - 1.
	 * 
	 * Runs in O(N) time.
	 * 
	 * @param index - the index location to be deleted.
	 * @return the deleted data element.
	 * @throws IndexOutOfBoundsException - if index < 0 or index >= size.
	 */
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

	/**
	 * Gets the index location corresponding to the first node containing a data
	 * element equivalent to the parameter element.
	 * If the element is not contained in the list, a value of -1 is returned instead.
	 * 
	 * Runs in O(N) time.
	 * 
	 * @param element - the data element to search for.
	 * @return the index of the first matching node, or -1 if the element doesn't match any existing nodes.
	 */
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

	/**
	 * Gets the number of elements contained in the list.
	 * 
	 * @return size of the list.
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Checks if the list is currently empty, containing no data elements.
	 * 
	 * @return true if the list empty, false otherwise.
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Clears all data elements from the list.
	 */
	@Override
	public void clear() {
		size = 0;
		header.next = trailer;
	}

	/**
	 * Generates an array of type Object[] of all elements in this list in their
	 * respective order.
	 * The array is the same length as the number of elements in this list.
	 * 
	 * @return an ordered Object[] array of the elements in this list.
	 */
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

	/**
	 * Generates a generic iterator for traversing this list in order.
	 * 
	 * @return a new iterator.
	 */
	@Override
	public Iterator<E> iterator() {
		return new SinglyLinkedListIterator();
	}
	
	/**
	 * This class represents a data node in the linked list.
	 * It is a shell object to maintain the order of the list that contains
	 * fields with its respective data element and a pointer to the
	 * next node in the list.
	 * 
	 * @author Tyler Wilcox and Andrew Tolton
	 * @version 28 February, 2023
	 * 
	 * @param <otherE> - The generic type passed into this node.
	 */
	private class Node<otherE>{
		otherE data;
		Node<otherE> next;
		
		/**
		 * Creates a new node with the provided data element and a pointer to the 
		 * next node in the list.
		 * 
		 * @param data - The data element stored in this node.
		 * @param next - The next node in the list.
		 */
		public Node(otherE data, Node<otherE> next) {
			this.data = data;
			this.next = next;
		}
	}
	
	/**
	 * This class represents an iterator for traversing SinglyLinkedList objects,
	 * based on the Iterable<E> interface. It uses a boolean field to track if
	 * object removal is valid and pointers to the "previous" and "current" nodes.
	 * 
	 * The "current" node is the last node that the iterator has seen, and is the node
	 * that is removed when the remove() method is called.
	 * 
	 * The "previous" node is tracked so that removal can be done in constant time by
	 * bypassing the "current" node and setting the previous node's pointer field to
	 * the "next" node that the iterator would see.
	 * 
	 * Implements all three methods in the Iterator<E> interface, all of which
	 * run in O(1) time.
	 * 
	 * @author Tyler Wilcox and Andrew Tolton
	 * @version 28 February, 2023
	 */
	private class SinglyLinkedListIterator implements Iterator<E> {

		boolean canRemove;
		Node<E> previousNode;
		
		/**
		 * Generates a new iterator object for SinglyLinkedList objects.
		 */
		public SinglyLinkedListIterator() {
			previousNode = headerer;
			canRemove = false;
		}
		
		/**
		 * Checks if there are any nodes remaining that this iterator can iterate over.
		 * 
		 * Runs in O(1) time.
		 * 
		 * @return true if there is a next node, false otherwise.
		 */
		@Override
		public boolean hasNext() {
			return previousNode.next.next != trailer;
		}

		/**
		 * Iterates over the next node in this list, returning the data element
		 * stored in that node.
		 * This method will throw an exception if no valid nodes remain, so it is
		 * highly encouraged to place any calls to this method within a conditional
		 * that checks the hasNext() method.
		 * 
		 * Runs in O(1) time.
		 * 
		 * @return data stored in the traversed node.
		 * @throws NoSuchElementException - if there is not a next node to iterate over.
		 */
		@Override
		public E next() {
			if (!hasNext()) {
				throw(new NoSuchElementException("Out of bounds error"));
			}
			
			canRemove = true;
			
			previousNode = previousNode.next;
			
			return previousNode.next.data;
		}
		
		/**
		 * Removes the node that was most recently traversed by the iterator.
		 * This method will throw an exception if it is called before the iterator
		 * has been moved by the next() method.
		 * 
		 * Runs in O(1) time.
		 * 
		 * @throws IllegalStateException - if this method is called before the next() method.
		 */
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
