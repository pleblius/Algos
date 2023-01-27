package assign03;

import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class SimplePriorityQueue<E> implements PriorityQueue<E> {
	private E[] queue;
	private int size;
	private Comparator<? super E> cmp;
	private boolean isComparable;
	
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
		isComparable = true;		
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
		isComparable = false;
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
	 * Searches through the queue to find the index of itemToCompare by priority
	 * 
	 * @param itemToCompare is the item that gets compared in the queue
	 * @return integer index by itemToCompare's priority
	 */
	@SuppressWarnings("unchecked")
	private int binarySearch(E itemToCompare) {
		
		int begin = 0;
		int end = size - 1;
		int middle = (begin + end) / 2;
		int compared;
		
		// Once we start repeating values, exit the loop
		while(middle != begin) 
		{
			if (isComparable) {
				compared = ((Comparable<? super E>)itemToCompare).compareTo(queue[middle]);
			}
			else {
				compared = cmp.compare(itemToCompare, queue[middle]);
			}
			if (compared == 0)
				return middle;
			else if (compared < 0)
				end = middle;
			else
				begin = middle;
			
			middle = (begin + end) / 2;
		}
		
		// Check if the item comparison is larger than the current index
		
		if (isComparable) {
			compared = ((Comparable<? super E>)itemToCompare).compareTo(queue[middle]);
		}
		else {
			compared = cmp.compare(itemToCompare, queue[middle]);
		}
		
		if (compared > 0)
			return end;
		else
			return begin;
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
	
	/**
	 * Inserts item into correct place in queue by priority
	 * 
	 * @param item of type E to be inserted
	 */	
	@Override
	public void insert(E item) {
		
		// Use binary search to compare passed item to items in the array until we find its correct spot
		int index = binarySearch(item);
		
		// Check if size needs to be doubled
		if (queue.length == size)
		{
			this.doubleArraySize();
		}
		
		// Shift all items above index to the right
		for (int i = size - 1; i > index; i--) {
			queue[i + 1] = queue[i];
		}
	
		// insert item
		queue[index] = item;		
		
		size++;
	}
	
	/**
	 * Inserts a collection of items into their correct places in queue by priority
	 * 
	 * @param collection of items of type E to be inserted
	 */	
	@Override
	public void insertAll(Collection<? extends E> coll) {
		
		for (E item : coll)
		{
			insert(item);
		}
		
	}
	
	/**
	 * This method searches the queue to find if the given object is contained within the queue.
	 * 
	 * This method assumes that the comparison based by a Comparable compareTo() method
	 * or a Comparator compare() method denotes two objects as equal if they have the same
	 * priority.
	 * 
	 * Behavior is not guaranted if item.compares() == 0 is not the same as item.equals()
	 * 
	 * @param item
	 * @return true if item is in the queue
	 */
	@Override
	public boolean contains(E item) {
		if (queue[binarySearch(item)].equals(item))
			return true;
		else
			return false;
	}

	/**
	 * Returns the number of the filled elements in the queue
	 * @return integer number of filled elements in the queue 
	 */
	@Override
	public int size() {
		return size; 
	}
	
	/**
	 * Returns a boolean saying if the array is empty
	 * @return true if queue is empty, false if not
	 */
	@Override
	public boolean isEmpty() {
		if (size == 0)
			return true; // Stub
		else
			return false;
	}
	
	/**
	 * Deletes all elements from the queue 
	 */
	@Override
	public void clear() {
		
	}
}
