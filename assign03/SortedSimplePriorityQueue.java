package assign03;

import java.util.Comparator;

/**
 * Class extending SimplePriorityQueue but adds a constructor with the input
 * of a sorted array so you don't need to rely on the insert method
 * 
 * @author Andrew Tolton
 * @version Jan 28th, 2023
 */
public class SortedSimplePriorityQueue<E> extends SimplePriorityQueue<E>{

	public SortedSimplePriorityQueue(E[] sortedArr)
	{
		this.queue = sortedArr;
		this.size = sortedArr.length;
	}
	
	public SortedSimplePriorityQueue(E[] sortedArr, Comparator<? super E> cmp)
	{
		this(sortedArr);
		
		this.cmp = cmp;
		isComparable = false; 		
	}
}
