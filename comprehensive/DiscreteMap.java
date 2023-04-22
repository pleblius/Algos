package comprehensive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;


/**
 * This class represents an implementation of the Disjoint Set ADT, using a hash-map
 * of lists as the backing structure. Data in this object is stored as elements in
 * arraylists.<br>
 * Data manipulation in this class is restricted to:
 * <li>the creation of new single-element sets 
 * <li>combining the sets containing specific elements
 * <li>getting the representative of a set containing a specific element.<br>
 * <br>
 * In this implementation, data is stored as the key of a key-value pair, with the
 * value being an arraylist that also contains that data element. When sets are
 * unioned, lists are combined both literally and in the map representation.<br>
 * <br>
 * @version 20 April, 2023
 * @author Tyler Wilcox && Andrew Tolton
 * @param <E>
 */
public class DiscreteMap<E> implements DisjointSet<E> {

	private Map<E, List<E>> dataMap;
	private int size;
	
	/**
	 * Creates a new, empty DiscreteMap object.
	 */
	public DiscreteMap() {
		dataMap = new HashMap<E, List<E>>();
		size = 0;
	}
	
	/**
	 * Creates a new DiscreteMap object, then adds all elements in the provided
	 * list to the discrete map, each in its own unique set.
	 * 
	 * @param list - The list of elements to be added to this map.
	 */
	public DiscreteMap(List<? extends E> list) {
		this();
		
		for (E data : list) {
			makeSet(data);
		}
	}
	
	/**
	 * Creates a new set consisting of a single element.
	 * If there is already a set containing the given element, this method
	 * has no effect. If the element is null, this method throws an exception.
	 * 
	 * @param element - The element to be added to a new set.
	 * @throws NullPointerEception If a null element is passed to the method.
	 */
	@Override
	public void makeSet(E element) throws NullPointerException {
		if (element == null)
			throw new NullPointerException();
		if (dataMap.containsKey(element))
			return;
		
		List<E> newSet = new ArrayList<E>();
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
	 * element, or if the element is null.
	 * 
	 * @param element - The element whose representative is to be obtained.
	 * @return the representative of the given element.
	 * @throws NoSuchElementException If the given element is not contained in any set.
	 * @throws NullPointerEception If a null element is passed to the method.
	 */
	@Override
	public E getRepresentative(E element) throws NoSuchElementException, NullPointerException {
		if (element == null)
			throw new NullPointerException();
		if (!dataMap.containsKey(element))
			throw new NoSuchElementException("Element doesn't exist.");
		
		List<E> list = dataMap.get(element);
		
		return list.get(0);
	}

	/**
	 * Combines the two sets containing the respective elements e1 and e2, placing
	 * all elements of both sets into a single set with a single representative. If
	 * the elements e1 and e2 are already contained in a single set, this method has no
	 * effect.
	 * <br><br>
	 * If either element is not already contained in a set, or if either element is null,
	 * this method throws an exception.
	 * 
	 * @param e1 - The first element whose set is to be combined.
	 * @param e2 - The second element whose set is to be combined.
	 * @throws NoSuchElementException If either element e1 and/or e2 is not already
	 * contained in a set.
	 * @throws NullPointerEception If a null element is passed to the method.
	 */
	@Override
	public void union(E e1, E e2) throws NoSuchElementException, NullPointerException {
		if (e1 == null || e2 == null)
			throw new NullPointerException();
		if (!dataMap.containsKey(e1))
			throw new NoSuchElementException("Element 1 is not contained in this structure.");
		if (!dataMap.containsKey(e2))
			throw new NoSuchElementException("Element 2 is not contained in this structure.");
		
		E rep1 = getRepresentative(e1);
		E rep2 = getRepresentative(e2);
		
		if (rep1 == rep2)
			return;
		
		List<E> list1 = dataMap.get(e1);
		List<E> list2 = dataMap.get(e2);
		
		int size1 = list1.size();
		int size2 = list2.size();
		
		if (size1 >= size2) {
			for (E data : list2) {
				list1.add(data);
				dataMap.put(data, list1);
			}
		}
		else {
			for (E data: list1) {
				list2.add(data);
				dataMap.put(data, list2);
			}
		}
	}
	
	/**
	 * Gets the number of elements currently in the structure
	 * @return the size of the discrete map
	 */
	public int size() {
		return size;
	}
}
