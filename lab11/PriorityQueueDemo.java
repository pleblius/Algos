package lab11;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Demonstration of Java's PriorityQueue class.
 * 
 * @author Erin Parker
 * @version April 8, 2022
 */
public class PriorityQueueDemo {

	public static void main(String[] args) {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(25);
		pq.add(50);
		pq.add(10);
		pq.add(25);
		pq.add(44);
		pq.add(36);
		pq.add(7);

		System.out.println("Array: " + Arrays.toString(pq.toArray()));
//
//		System.out.println("First item removed: " + pq.remove());
//		System.out.println("Second item removed: " + pq.remove());
//		System.out.println("Third item removed: " + pq.remove());
//		System.out.println("Fourth item removed: " + pq.remove());
//		System.out.println("Fifth item removed: " + pq.remove());
//		System.out.println("Sixth item removed: " + pq.remove());
//		System.out.println("Seventh item removed: " + pq.remove());
	}
}
