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

	
	public BinaryMaxHeap() {
		// TODO
	}
	
	public BinaryMaxHeap(Comparator<? super E> cmp) {
		// TODO
	}
	
	public BinaryMaxHeap(List<? extends E> list) {
		// TODO
	}
	
	public BinaryMaxHeap(List<? extends E> list, Comparator<? super E> cmp) {
		// TODO
	}
	
	@Override
	public void add(E item) {
		// TODO Auto-generated method stub

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

}
