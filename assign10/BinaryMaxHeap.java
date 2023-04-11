/**
 * 
 */
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
	
	@SuppressWarnings("unchecked")
	public BinaryMaxHeap() {
		this((l,r) -> ((Comparable<? super E>)l).compareTo(r));
	}
	
	@SuppressWarnings("unchecked")
	public BinaryMaxHeap(Comparator<? super E> cmp) {
		this.cmp = (l, r) -> cmp.compare(r, l);
		size = 0;
		capacity = 10;
		heap = (E[]) new Object[capacity];
	}
	
	@SuppressWarnings("unchecked")
	public BinaryMaxHeap(List<? extends E> list) {
		this(list, (l,r) -> ((Comparable<? super E>)l).compareTo(r));
	}
	
	@SuppressWarnings("unchecked")
	public BinaryMaxHeap(List<? extends E> list, Comparator<? super E> cmp) {
		this.cmp = (l, r) -> cmp.compare(r, l);
		size = list.size();
		capacity = 2*list.size();
		heap = (E[]) new Object[capacity];
		
		heapify(list);
	}
	
	@Override
	public void add(E item) {
		heap[size] = item;
		percolateUp(size++);

		if (size == capacity) {
			growCapacity();
		}
	}

	@Override
	public E peek() throws NoSuchElementException {
		if (size == 0)
			throw new NoSuchElementException("This data structure is currently empty.");
		
		return heap[0];
	}

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

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void clear() {
		size = 0;
		capacity = 10;
		
		heap = (E[]) new Object[capacity];
	}

	@Override
	public Object[] toArray() {
		return Arrays.copyOf(heap, size);
	}
	
	/*
	 * Helper methods
	 */
	
	private void percolateUp(int index) {
		int parent = getParent(index);
		
		while (index > 0 && compare(index, parent) < 0) {
			swap(index, parent);
			index = parent;
			parent = getParent(index);
		}
	}
	
	private void percolateDown(int index) {
		int smallerChild;
		
		while(!isIndexOkay(index)) {
			smallerChild = getSmallerChild(index);
			swap(index, getSmallerChild(index));
			
			index = smallerChild;
		}		
	}
	
	private boolean isIndexOkay(int index) {
		int left = getLeft(index);
		int right = getRight(index);
		
		if (size <= 1) {
			return true;
		}
		else if (left < size && compare(index, left) > 0)
			return false;
		else if (right < size && compare(index, right) > 0) {
			return false;
		}
		else {
			return true;
		}
	}
	
	private int getSmallerChild(int index) {
		int left = getLeft(index);
		int right = getRight(index);
		
		// One-Child
		if (right >= size) 
			return left;
		
		// Two Children
		else {
			if(compare(left, right) < 0)
				return left;
			else
				return right;
		}
	}
	
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
	
	private int getLeft(int index) {
		return 2*index + 1;
	}
	
	private int getRight(int index) {
		return 2*index + 2;
	}
	
	private int getParent(int index) {
		return (index - 1)/2;
	}
	
	private int compare(int left, int right) {
		return cmp.compare(heap[left], heap[right]);
	}
	
	private void swap(int n1, int n2) {
		E temp = heap[n1];
		heap[n1] = heap[n2];
		heap[n2] = temp;
	}
	
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
