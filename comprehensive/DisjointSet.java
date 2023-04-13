/**
 * 
 */
package comprehensive;

/**
 * @author Tyler Wilcox && Andrew Tolton
 * @version 13 April, 2023
 */
public interface DisjointSet<E> {
	
	public void makeSet(E element);
	
	public E getRepresentative(E element);
	
	public void union(E e1, E e2);
}
