package comprehensive;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;


/**
 * This class represents an implementation of the Disjoint Set ADT, using a hash-map
 * as the backing data structure. Data in this object is stored as elements in
 * abstract sets, each of which can be accessed through a representative data element
 * that is contained in that set.<br>
 * Data manipulation in this class is restricted to:
 * <li>the creation of new single-element sets 
 * <li>combining the sets containing specific elements
 * <li>getting the representative of a set containing a specific element.<br>
 * <br>
 * In this implementation, data is stored in key-value pairs in a hash-map, with 
 * the data elements being the key and their respective set's representative being
 * the value.<br>
 * <br>
 * @version 16 April, 2023
 * @author Tyler Wilcox && Andrew Tolton
 * @param <E>
 */
public class DiscreteMap<E> implements DisjointSet<E> {

	private Map<E, Set<E>> dataMap;
	private int size;
	
	public DiscreteMap() {
		dataMap = new HashMap<E, Set<E>>();
	}
	
	/**
	 * Creates a new set consisting of a single element.
	 * If there is already a set containing the given element, this method
	 * has no effect.
	 * 
	 * @param element - The element to be added to a new set.
	 */
	@Override
	public void makeSet(E element) {
		Set<E> newSet = new HashSet<E>();
		newSet.add(element);
		
		dataMap.put(element, newSet);
		
		size++;
	}

	/**
	 * Gets the representative element for the set containing the given element.
	 * The representative element is not guaranteed to be any particular element, 
	 * it is only guaranteed that every set has exactly one representative.<br>
	 * Note: This representative <b>will change</b> when sets are unioned.
	 * <br><br>
	 * This method will throw an exception if there is no set containing the given
	 * element.
	 * 
	 * @param element - The element whose representative is to be obtained.
	 * @return the representative of the given element.
	 * @throws NoSuchElementException If the given element is not contained in any set.
	 */
	@Override
	public E getRepresentative(E element) throws NoSuchElementException {
		if (!dataMap.containsKey(element))
			throw new NoSuchElementException("Element doesn't exist.");
		
		Set<E> set = dataMap.get(element);
		
		return set.iterator().next();
	}

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
	@Override
	public void union(E e1, E e2) throws NoSuchElementException {
		if (!dataMap.containsKey(e1))
			throw new NoSuchElementException("Element 1 is not contained in this structure.");
		if (!dataMap.containsKey(e2))
			throw new NoSuchElementException("Element 2 is not contained in this structure.");
		
		Set<E> set1 = dataMap.get(e1);
		Set<E> set2 = dataMap.get(e2);
		
		int size1 = set1.size();
		int size2 = set2.size();
		
		if (size1 >= size2) {
			set1.addAll(set2);
			
			for (E data : set2) {
				dataMap.put(data, set1);
			}
		}
		else {
			set2.addAll(set1);
			
			for (E data: set1) {
				dataMap.put(data, set2);
			}
		}
	}
	
	/**
	 * Gets the number of elements currently in the structure
	 * @return size of the discrete map
	 */
	public int size() {
		return size;
	}
}
