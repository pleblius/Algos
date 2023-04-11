/**
 * 
 */
package assign10;

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
		this((l,r) -> ((Comparable<? super E>)r).compareTo(l));
	}
	
	@SuppressWarnings("unchecked")
	public BinaryMaxHeap(Comparator<? super E> cmp) {
		this.cmp = cmp;
		size = 0;
		capacity = 10;
		heap = (E[]) new Object[capacity];
	}
	
	@SuppressWarnings("unchecked")
	public BinaryMaxHeap(List<? extends E> list) {
		this(list, (l,r) -> ((Comparable<? super E>)r).compareTo(l));
	}
	
	@SuppressWarnings("unchecked")
	public BinaryMaxHeap(List<? extends E> list, Comparator<? super E> cmp) {
		this.cmp = cmp;
		size = list.size();
		capacity = 2*list.size();
		heap = (E[]) new Object[capacity];
		
		heapify(list);
	}
	
	@Override
	public void add(E item) {
		
	}

	@Override
	public E peek() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E extractMax() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
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
	
	/*
	 * Helper methods
	 */
	
	private void percolateUp(int index) {
		int parent = getParent(index);
		
		while (index > 0 && compare(index, parent) > 0) {
			swap(index, parent);
			index = parent;
			parent = getParent(index);
		}
	}
	
	private void percolateDown(int index) {
		// TODO
	}
	
	private void heapify(List<?> list) {
		// TODO
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
}
