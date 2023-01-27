package assign03;

import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class SimplePriorityQueue<E> implements PriorityQueue {
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
	 * Searches through the queue to find the index of itemToCompare
	 */
	@SuppressWarnings("unchecked")
	private int binarySearch(E itemToCompare) {
		
		int begin = 0;
		int end = size - 1;
		int middle = (begin + size) / 2;
		
		// Once we start repeating values, exit the loop
		while(middle != begin && middle != end) 
		{
			
			if (isComparable) {
				int compared = ((Comparable<? super E>)itemToCompare).compareTo(queue[middle]);
				if (compared == 0)
					return middle;
				else if (compared < 0)
					end = middle;
				else
					begin = middle;
			}
			
			middle = (begin + size) / 2;
		}

		
		
		
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
		// Use binary search to compare passed item to items in the array until we find its correct spot
		
		// Check if size needs to be doubled
		
		// Find the index of item above
		// Make a new array, 
		// insert item
		// add the rest of the array
		
		
	}
	
	@Override
	public void insertAll(Collection<? extends E> coll) {
		
		// inserts each item in coll
		
	}
	
	@Override
	public boolean contains(E item) {
		
		// Use binary search to find index of item
		
		
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
