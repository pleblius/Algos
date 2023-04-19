package comprehensive;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * This class represents an implementation of the Disjoint Set ADT, using a linked
 * disjoint forest as the backing data structure. Data in this object is stored as 
 * elements in abstract sets, each of which can be accessed through a representative 
 * data element that is contained in that set.<br>
 * Data manipulation in this class is restricted to:
 * <li>the creation of new single-element sets 
 * <li>combining the sets containing specific elements
 * <li>getting the representative of a set containing a specific element.<br>
 * <br>
 * In this implementation, data is stored in nodes that are contained in trees that
 * correspond to their respective sets, with the root element being the representative.
 * Trees are combined using union-by-rank, where the root of the smaller tree is
 * attached to the root of the larger tree. This class uses path compression to 
 * constantly re-assign nodes to the base root of their respective tree, adjusting
 * the tree's height to optimize run-time.<br>
 * <br>
 * @version 16 April, 2023
 * @author Tyler Wilcox && Andrew Tolton
 * @param <E>
 */



public class DisjointForest<E> implements DisjointSet<E> {
	
	Map<E, E> dataMap = new HashMap<E, E>();
	Map<E, Integer> rankMap = new HashMap<E, Integer>();

	/**
	 * Creates a new set consisting of a single element.
	 * If there is already a set containing the given element, this method
	 * has no effect.
	 * 
	 * @param element - The element to be added to a new set.
	 */
	@Override
	public void makeSet(E element) {
		dataMap.put(element, element);
		rankMap.put(element, 1);
	}

	/**
	 * Gets the representative element for the set containing the given element.
	 * The representative element is not guaranteed to be any particular element, 
	 * it is only guaranteed that every set has exactly one representative.<br>
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
		
		E parent = dataMap.get(element);
		
		// Base case
		if (parent == dataMap.get(parent)) {
			return parent;
		}
		
		// Step up the chain
		else {
			parent = getRepresentative(parent);
			dataMap.put(element, parent);
			return parent;
		}		
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
		E rep1 = getRepresentative(e1);
		E rep2 = getRepresentative(e2);
		
		int rank1 = rankMap.get(rep1);
		int rank2 = rankMap.get(rep2);

		if (rank1 < rank2) {
			dataMap.put(rep1, rep2);
		}
		else if (rank2 < rank1) {
			dataMap.put(rep2, rep1);
		}
		else {
			dataMap.put(rep1, rep2);
			rankMap.put(rep2, rank2 + 1);
		}
	}

}
