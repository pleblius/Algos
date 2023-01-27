package assign03;

import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class SimplePriorityQueue<E> implements PriorityQueue {
	private E[] queue;
	private int size;
	private Comparator<? super E> cmp;
	
	/**
	 * Creates a new simple priority queue for elements of generic type E.
	 * For this constructor, E must implement the Comparable interface.
	 * 
	 * Queues created using this constructor order elements based on the natural ordering
	 * specificed by their compareTo() implementation.
	 */
	@SuppressWarnings("unchecked")
	public SimplePriorityQueue () {
		size = 0;
		queue = (E[]) new Object[16];
	}
	
	/**
	 * Creates a new simple priority queue for elements of generic type E.
	 * For this constructor, a Comparator object is passed as a parameter to define the
	 * ordering method to sort the objects in the queue.
	 * 
	 * @param cmp A comparator object specifying the queue's ordering method. 
	 */
	@SuppressWarnings("unchecked")
	public SimplePriorityQueue (Comparator<? super E> cmp) {
		size = 0;
		queue = (E[]) new Object[16];
		this.cmp = cmp;
	}
	
	/**
	 * Doubles the size of the queue array to allow expansion and addition of new elements.
	 */
	private void doubleArraySize() {
		@SuppressWarnings("unchecked")
		E[] newQueue = (E[]) new Object[queue.length*2];
		
		for (int i = 0; i < size; i++) {
			newQueue[i] = queue[i];
		}
		
		queue = newQueue;
	}
	
	/**
	 * Searches through the queue to find the corresponding location
	 */
	private E binarySearch(E itemToCompare) {
		return null; // Stub
	}
	
	/**
	 * Returns the highest priority element in the queue.
	 * Throws an exception if there are no items in the queue to return.
	 * 
	 * @return highest element in the queue
	 * @throws NoSuchElementException
	 */
	@Override
	public E findMax() throws NoSuchElementException {
		if (queue[size - 1] == null) {
			throw new NoSuchElementException("There are no items in the queue.");
		}
		return queue[size - 1];
	}
	
	/**
	 * Returns the highest priority element in the queue and removes it from the queue.
	 * Throws an exception if there are no items in the queue to return.
	 * 
	 * @return highest element in the queue
	 * @throws NoSuchElementException
	 */
	@Override
	public E deleteMax() throws NoSuchElementException {
		if (queue[size - 1] == null) {
			throw new NoSuchElementException("There are no items in the queue.");
		}
		
		E tempItem = queue[size - 1];
		
		queue[size - 1] = null;
		size--;
		
		return tempItem;
	}
	
	@Override
	public void insert(E item) {
		// Use binary search to compare passed item to items int he arry until we find its correct spot
		
	}
	
	@Override
	public void insertAll(Collection<? extends E> coll) {
		
	}
	
	@Override
	public boolean contains(E item) {
		return false; // Stub
	}
	
	@Override
	public int size() {
		return 0; // Stub
	}
	
	@Override
	public boolean isEmpty() {
		return false; // Stub
	}
	
	@Override
	public void clear() {
		
	}
}
