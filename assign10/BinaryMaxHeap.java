package assign10;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This class represents an implementation of a binary max heap, which is a binary
 * heap that prioritizes higher-valued items.
 * 
 * Contains constructors to build the heap from scratch or from a pre-fabricated list using
 * either the natural ordering of a comparable generic type E or using a comparator
 * to define the ordering of the element type.
 * 
 * @param <E> - Generic type, must either be comparable OR a constructor that takes
 * a comparator type must be called.
 * @author Tyler Wilcox and Andrew Tolton
 * @version 08 April 2023
 *
 */
public class BinaryMaxHeap<E> implements PriorityQueue<E> {

	private int size;
	private E[] heap;
	private int capacity;
	private Comparator<? super E> cmp;
	
	/**
	 * Creates a new binary max heap. Elements in this heap are prioritized in order
	 * from the highest value to the lowest value, using their natural ordering.
	 * 
	 * This constructor assumes that the type of value stored in this heap implements
	 * the Comparable interface. 
	 */
	@SuppressWarnings("unchecked")
	public BinaryMaxHeap() {
		this((l,r) -> ((Comparable<? super E>)l).compareTo(r));
	}

	/**
	 * Creates a new binary max heap. Elements in this heap are prioritized in order
	 * from the highest value to the lowest value, using the provided Comparator 
	 * to control their ordering.
	 * 
	 * The comparator is assumed to follow standard ordering, arranging elements in
	 * ascending order, and this heap then prioritizes the highest-value elements
	 * according to this ordering.
	 * 
	 * As an example, for integers with a standard ordering, the comparator
	 * should return negative values for the lower-valued integer, which would
	 * result in an equivalent sorting {1, 2, 3, ...}. This heap would then prioritize
	 * the number 3, then the number 2, then the number 1. 
	 * 
	 * @param cmp - A comparator of type <? super E> to control the desired ordering
	 * of the elements stored in this heap. cmp.compare(arg1, arg2) should return a negative
	 * number if arg1 should be to the left of arg2 in an ascending list.
	 */
	@SuppressWarnings("unchecked")
	public BinaryMaxHeap(Comparator<? super E> cmp) {
		this.cmp = (l, r) -> cmp.compare(l, r);
		size = 0;
		capacity = 10;
		heap = (E[]) new Object[capacity];
	}
	
	/**
	 * Creates a new binary max heap from the elements contained in list. 
	 * Elements in this heap are prioritized in order from the highest value to
	 * the lowest value, using their natural ordering.
	 * 
	 * This constructor assumes that the type of value stored in this heap implements
	 * the Comparable interface. 
	 * 
	 * @param list - a list of elements to be added to this binary max heap.
	 */
	@SuppressWarnings("unchecked")
	public BinaryMaxHeap(List<? extends E> list) {
		this(list, (l,r) -> ((Comparable<? super E>)l).compareTo(r));
	}
	
	/**
	 * Creates a new binary max heap from the elements contained in list.
	 * Elements in this heap are prioritized in order from the highest value to 
	 * the lowest value, using the provided Comparator to control their ordering.
	 * 
	 * The comparator is assumed to follow standard ordering, arranging elements in
	 * ascending order, and this heap then prioritizes the highest-value elements
	 * according to this ordering.
	 * 
	 * As an example, for integers with a standard ordering, the comparator
	 * should return negative values for the lower-valued integer, which would
	 * result in an equivalent sorting {1, 2, 3, ...}. This heap would then prioritize
	 * the number 3, then the number 2, then the number 1. 
	 * 
	 * @param list - A list of objects to be added to this heap.
	 * @param cmp - A comparator of type <? super E> to control the desired ordering
	 * of the elements stored in this heap. cmp.compare(arg1, arg2) should return a negative
	 * number if arg1 should be to the left of arg2 in an ascending list.
	 */
	@SuppressWarnings("unchecked")
	public BinaryMaxHeap(List<? extends E> list, Comparator<? super E> cmp) {
		this.cmp = (l, r) -> cmp.compare(l, r);
		size = list.size();
		capacity = 2*list.size();
		heap = (E[]) new Object[capacity];
		
		heapify(list);
	}
	
	/**
	 * Adds the provided item to the heap, giving it the appropriate priority.
	 * 
	 * @param item - The item to be added.
	 */
	@Override
	public void add(E item) {
		heap[size] = item;
		percolateUp(size++);

		if (size == capacity) {
			growCapacity();
		}
	}

	/**
	 * Gets the highest priority element stored in this heap without removing it.
	 * 
	 * @throws NoSuchElementException - If the heap is empty.
	 * @return The highest priority element in the heap.
	 */
	@Override
	public E peek() throws NoSuchElementException {
		if (size == 0)
			throw new NoSuchElementException("This data structure is currently empty.");
		
		return heap[0];
	}

	/**
	 * Gets the highest priority element in this heap and removes it from the heap.
	 * 
	 * @throws NoSuchElementException - If the heap is empty.
	 * @return The highest priority element in the heap.
	 */
	@Override
	public E extractMax() throws NoSuchElementException {
		if (size == 0)
			throw new NoSuchElementException("This data structure is currently empty.");
		
		E retValue = heap[0];
		
		heap[0] = heap[size-1];
		heap[size-1] = null;
		
		size--;
		percolateDown(0);
		
		return retValue;
	}

	/**
	 * @return The number of elements stored in the heap.
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Checks if the heap is empty.
	 * 
	 * @return True if the heap is empty, false otherwise.
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Clears and resets the heap to its original state, removing all stored data.
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void clear() {
		size = 0;
		capacity = 10;
		
		heap = (E[]) new Object[capacity];
	}

	/**
	 * Copies and returns the backing array for this heap. The order of the array is
	 * not guaranteed; for any given collection of elements the array can have multiple
	 * valid orderings. The array will maintain the heap property.
	 * 
	 * @return An element-by-element copy of the array backing the heap.
	 */
	@Override
	public Object[] toArray() {
		return Arrays.copyOf(heap, size);
	}
	
	/*
	 * Helper methods
	 */
	
	/*
	 * Percolation methods
	 */
	
	/**
	 * Percolates the data element at the provided array index up to the appropriate
	 * level to maintain the heap property for this heap.
	 * 
	 * @param index - The array index corresponding to the data value to be percolated.
	 */
	private void percolateUp(int index) {
		int parent = getParent(index);
		
		while (index > 0 && isBiggerThan(index, parent)) {
			swap(index, parent);
			index = parent;
			parent = getParent(index);
		}
	}
	
	/**
	 * Percolates the data element at the provided array index down to the appropriate
	 * level to maintain the heap property for this heap.
	 * At each descending level, compares this element to its children and swaps it
	 * with the highest-value child until there are no valid swaps.
	 * 
	 * @param index - The array index corresponding to the data value to be percolated.
	 */
	private void percolateDown(int index) {
		int smallerChild;
		
		while(!isIndexOkay(index)) {
			smallerChild = getLargerChild(index);
			swap(index, getLargerChild(index));
			
			index = smallerChild;
		}		
	}
	
	/**
	 * percolateDown helper method. Checks if the element at the provided 
	 * index is in a valid location to maintain the heap property based on its
	 * relation to its children.
	 * 
	 * @param index - The array index corresponding to the data element to be checked.
	 * @return True if the element is in a valid location, false if it needs to be
	 * percolated.
	 */
	private boolean isIndexOkay(int index) {
		int left = getLeft(index);
		int right = getRight(index);
		
		if (size <= 1) {
			return true;
		}
		else if (left < size && isBiggerThan(left, index))
			return false;
		else if (right < size && isBiggerThan(right, index)) {
			return false;
		}
		else {
			return true;
		}
	}
	
	/**
	 * percolateDown helper method. Gets the array index of the larger child of the
	 * the element at the given array index.
	 * 
	 * @param index - The array index whose children are to be compared.
	 * @return The array index corresponding to the higher-valued child.
	 */
	private int getLargerChild(int index) {
		int left = getLeft(index);
		int right = getRight(index);
		
		// One-Child
		if (right >= size) 
			return left;
		
		// Two Children
		else {
			if(isBiggerThan(left, right))
				return left;
			else
				return right;
		}
	}
	
	/**
	 * Converts the provided list into a valid heap, storing the generated array
	 * as the backing array of this heap.
	 * 
	 * @param list - The list to be heapified.
	 */
	private void heapify(List<? extends E> list) {
		int index = 0;
		for (E l : list) {
			heap[index] = l;
			index++;
		}
		
		for (int ii = size/2; ii >= 0; ii--) {
			percolateDown(ii);
		}
	}
	
	/*
	 * Reference getters
	 */
	
	/**
	 * Gets the array index of the provided element's left child.
	 * 
	 * @param index - The array index of the parent element.
	 * @return The array index of the parent's left child.
	 */
	private int getLeft(int index) {
		return 2*index + 1;
	}
	
	/**
	 * Gets the array index of the provided element's right child.
	 * 
	 * @param index - The array index of the parent element.
	 * @return The array index of the parent's right child.
	 */
	private int getRight(int index) {
		return 2*index + 2;
	}
	
	/**
	 * Gets the array index of the provided element's parent.
	 * @param index - The array index of the child element.
	 * @return The array index of the child's parent.
	 */
	private int getParent(int index) {
		return (index - 1)/2;
	}
	
	/*
	 * Miscellaneous
	 */
	
	/**
	 * Checks if the element stored at the array index baseIndex is larger 
	 * (by the ordering specified by the stored comparator) than the element
	 * stored at the array index compIndex.
	 * 
	 * @param baseIndex - the array index of the element being checked.
	 * @param compIndex - the array index of the element being compared to the base element.
	 * @return true if baseIndex > compIndex, false otherwise.
	 */
	private boolean isBiggerThan(int baseIndex, int compIndex) {
		if (cmp.compare(heap[baseIndex], heap[compIndex]) > 0)
			return true;
		else return false;
	}
	
	/**
	 * Swaps the elements stored at the array indexes n1 and n2.
	 * 
	 * @param n1 - Array index of the first element to be swapped.
	 * @param n2 - Array index of the second element to be swapped.
	 */
	private void swap(int n1, int n2) {
		E temp = heap[n1];
		heap[n1] = heap[n2];
		heap[n2] = temp;
	}
	
	/**
	 * Doubles the backing array sized to allow for continued element addition.
	 */
	@SuppressWarnings("unchecked")
	private void growCapacity() {
		capacity *= 2;
		E[] newArr = (E[]) new Object[capacity];
		
		for (int ii = 0; ii < size; ii++) {
			newArr[ii] = heap[ii];
		}
		
		heap = newArr;
	}
}
