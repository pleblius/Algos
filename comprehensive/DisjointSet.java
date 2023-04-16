/**
 * 
 */
package comprehensive;

import java.util.NoSuchElementException;

/**
 * This interface represents the Disjoint Set ADT. It contains data in a collection
 * of sets, each of which is represented by a single member element and which can
 * only be accessed through that representative element.<br>
 * Data manipulation is restricted to:
 * <li>the creation of new single-element sets 
 * <li>combining the sets containing specific elements
 * <li>getting the representative of a set containing
 * a specific element.<br>
 * <br>
 * @author Tyler Wilcox && Andrew Tolton
 * @version 13 April, 2023
 */
public interface DisjointSet<E> {
	
	/**
	 * Creates a new set consisting of a single element.
	 * If there is already a set containing the given element, this method
	 * has no effect.
	 * 
	 * @param element - The element to be added to a new set.
	 */
	public void makeSet(E element);
	
	/**
	 * Gets the representative element for the set containing the given element.
	 * The representative element is not guaranteed to be any particular element, 
	 * it is only guaranteed that every set has exactly one representative.
	 * <br><br>
	 * This method will throw an exception if there is no set containing the given
	 * element.
	 * 
	 * @param element - The element whose representative is to be obtained.
	 * @return the representative of the given element.
	 * @throws NoSuchElementException If the given element is not contained in any set.
	 */
	public E getRepresentative(E element) throws NoSuchElementException;
	
	/**
	 * Combines the two sets containing the respective elements e1 and e2, placing
	 * all elements of both sets into a single set with a single representative. If
	 * the elements e1 and e2 are already contained in a single set, this method has no
	 * effect.
	 * <br><br>
	 * If either element is not already contained in a set, this method throws an exception.
	 * 
	 * @param e1 - The first element whose set is to be combined.
	 * @param e2 - The second element whose set is to be combined.
	 * @throws NoSuchElementException If either element e1 and/or e2 is not already
	 * contained in a set.
	 */
	public void union(E e1, E e2) throws NoSuchElementException;
}
