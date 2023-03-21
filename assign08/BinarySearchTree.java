package assign08;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * This class represents a generic implementation of a binary search tree.
 * Data is stored in linked-nodes that can have up to two children: left and right.
 * Data is stored in sorted order from left to right based on the natural comparisons
 * specified by the generic type's compareTo method.
 * 
 * @author Tyler Wilcox and Andrew Tolton
 * @version 20 March, 2023
 * @param <Type>
 */
public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type> {

	private Node root;
	private int size;
	
	/**
	 * This class represents a linked node to wrap the data values stored in
	 * the binary search tree.
	 * Contains a data value of the type specified by the binary tree and references
	 * to the node's parent, left child, and right child nodes.
	 * 
	 * @author Tyler Wilcox and Andrew Tolton
	 * @version 20 March, 2023
	 */
	private class Node {
		
		private Type data;
		private Node leftChild;
		private Node rightChild; 
		private Node parent;
		
		/**
		 * Creates a new node object, storing the value data and the node-pointer
		 * to its parent node. The node's left and right children are initially declared
		 * null.
		 * </br>
		 * The root node of the tree should have a null pointer for its parent node.
		 * 
		 * @param data - The data value wrapped in this node.
		 * @param parent - The parent node of this new node.
		 */
		public Node(Type data, Node parent) {
			this.data = data;
			this.parent = parent;
		}
		
		/**
		 * @return true if node.leftChild is not null, false otherwise.
		 */
		public boolean hasLeftChild() {
			return this.leftChild != null;
		}
		
		/**
		 * @return true if node.rightChild is not null, false otherwise.
		 */
		public boolean hasRightChild() {
			return this.rightChild != null;
		}
		
		/**
		 * @return true if this node has no children, false otherwise.
		 */
		public boolean isLeaf() {
			return !hasLeftChild() && !hasRightChild();
		}
		
		/**
		 * @return true if this node has exactly one child, false otherwise.
		 */
		public boolean hasOneChild() {
			if (this.hasLeftChild() && !this.hasRightChild())
				return true;
			if (this.hasRightChild() && !this.hasLeftChild())
				return true;
			
			return false;
		}
		
		/**
		 * Gets the left-most node of the sub-tree rooted at this node.
		 * This node corresponds to the "smallest" value contained in this node's
		 * descendants.
		 * 
		 * @return reference to the left-most node of this node's sub-tree.
		 */
		public Node getLeftMostNode() {
			if (!this.hasLeftChild())
				return this;
			
			return this.leftChild.getLeftMostNode();
		}
		
		/**
		 * Gets the right-most node of the sub-tree rooted at this node.
		 * This node corresponds to the "largest" value contained in this node's
		 * descendants. 
		 * 
		 * @return reference to the right-most node of this node's sub-tree.
		 */
		public Node getRightMostNode() {
			if (!this.hasRightChild())
				return this;
			
			return this.rightChild.getRightMostNode();
		}
		
		/**
		 * Gets the predecessor node for this node. This corresponds to the
		 * "next-smallest" data element of this tree.
		 * 
		 * @return pointer to this node's preceding node
		 * @throws NoSuchElementException - If this node has no preceding nodes.
		 */
		public Node getPredecessor() throws NoSuchElementException {
			if (!this.hasLeftChild())
				throw new NoSuchElementException("This node does not have a predecessor.");
			
			return this.leftChild.getRightMostNode();
		}
		
		/**
		 * Gets the successor node for this node. This corresponds to the
		 * "next-largest" data element of this tree.
		 * 
		 * @return pointer to this node's succeeding node.
		 * @throws NoSuchElementException - If this node has no succeeding nodes.
		 */
		public Node getSuccessor() throws NoSuchElementException {
			if (!this.hasRightChild())
				throw new NoSuchElementException("This node does not have a successor.");
			return this.rightChild.getLeftMostNode();
		}
	}
	
	/**
	 * Creates a new, empty binary search tree.
	 */
	public BinarySearchTree() {
		size = 0;
	}
	
	/**
	 * Adds the data element item to this binary search tree, in sorted order,
	 * if it is not already contained in the tree.
	 * If the item is added to the tree, this method returns a value of true,
	 * otherwise it returns a value of false.
	 * 
	 * @param item - The data element to be added.
	 * @return true if the item was successfully added, false otherwise.
	 */
	@Override
	public boolean add(Type item) {
		if (this.isEmpty()) {
			this.root = new Node(item, null);
			size++;
			
			return true;
		}
		
		boolean addedItem = addNode(item, root);
		
		if (addedItem)
			size++;
		
		return addedItem;
	}
	
	/**
	 * Recursive method to add a node containing the specified data element at
	 * its proper sorted location in the tree.
	 * This method will recursively iterate through the tree's levels, searching for an
	 * appropriate empty spot to insert the new node, or ending if a node with an equivalent
	 * value is found.
	 * Will return true if the new node is added to the true, or it will return false
	 * if the node wasn't added due to it already being contained in the tree.
	 * 
	 * @param item The data value to be added to the tree.
	 * @param current The current node being examined, to determine whether to branch left or right.
	 * @return true if a new node was added to the tree, false otherwise.
	 */
	private boolean addNode(Type item, Node current) {
		
		// Duplicate item
		if (current.data.equals(item)) {
			return false;
		}
		
		// Check left child, if null, add item there
		else if (item.compareTo(current.data) < 0) {
			if (!current.hasLeftChild()) {
				current.leftChild = new Node(item, current);
				return true;
			}
				
			return addNode(item, current.leftChild);
		}
			
		// Check right child, if null, add item there
		else {
			if (!current.hasRightChild()) {
				current.rightChild = new Node(item, current);
				return true;
			}
			
			return addNode(item, current.rightChild);
		}
	}
	
	/**
	 * Adds all values in the given collection to this binary search tree. Any values
	 * already contained in this tree are discarded.
	 * This method will return true if <i>any</i> values are added to the tree,
	 * and it will return false if no values were added.
	 * 
	 * @param items - The collection of items to be added.
	 * @return true if any values were added to the tree, false otherwise.
	 */
	@Override
	public boolean addAll(Collection<? extends Type> items) {
		boolean addAny = false;
		
		for (Type t : items) {
			if (add(t))
				addAny = true;
		}
		
		return addAny;
	}
	
	/**
	 * Resets this binary search tree, deleting all data from it.
	 */
	@Override
	public void clear() {
		size = 0;
		root = null;
	}

	/**
	 * Checks if this tree contains a data element equal to item.
	 * 
	 * @param item - the value being searched for.
	 * @return true if the value exists in the tree, false otherwise.
	 */
	@Override
	public boolean contains(Type item) {
		if (this.isEmpty())
			return false;
		
		return containsNode(item, root);
	}
	
	/**
	 * A recursive search method to determine if the value item is contained in
	 * this tree, beginning at the current node.
	 * Recursively iterates down the appropriate path in the tree until either the
	 * appropriate data value, returning true, or an empty node is reached, returning
	 * false.
	 * 
	 * @param item - the value being searched for.
	 * @param current - The current node being examined.
	 * @return true if the data element is found, false otherwise.
	 */
	private boolean containsNode(Type item, Node current) {
		if (current.data.equals(item)) {
			return true;
		}
		else if(item.compareTo(current.data) < 0) {
			if (!current.hasLeftChild()) {
				return false;
			}
			
			return containsNode(item, current.leftChild);
		}
		else {
			if (!current.hasRightChild()) {
				return false;
			}
			
			return containsNode(item, current.rightChild);
		}	
	}
	
	/**
	 * Checks if this tree contains <i>all</i> elements contained in the
	 * collection items.
	 * 
	 * @param items - the collection containing all elements being searched for.
	 * @return true if the tree contains all elements in items, false otherwise.
	 */
	@Override
	public boolean containsAll(Collection<? extends Type> items) {
		boolean containsAll = true;
		
		for (Type t : items) {
			if (!contains(t))
				containsAll = false;
		}
		
		return containsAll;
	}
	
	/**
	 * Gets the first element contained in this tree, corresponding to the
	 * smallest data-value.
	 * 
	 * @return the smallest data element in this tree.
	 * @throws NoSuchElementException - If there is no first element (the tree is empty).
	 */
	@Override
	public Type first() throws NoSuchElementException {
		if (this.isEmpty())
			throw new NoSuchElementException("The set is empty.");
		
		return root.getLeftMostNode().data;
	}

	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}
	
	/**
	 * Gets the last element contained in this tree, corresponding to the largest
	 * data-value
	 * 
	 * @return the largest data element in this tree.
	 * @throws NoSuchElementException - If there is no last element (the tree is empty).
	 */
	@Override
	public Type last() throws NoSuchElementException {
		if (this.isEmpty())
			throw new NoSuchElementException("The set is empty.");
		
		return root.getRightMostNode().data;
	}
	
	/**
	 * Removes any instance of the given data element from the tree. Has no
	 * effect if the value is not contained in the tree.
	 * If the data element was found and removed, this method returns true, otherwise
	 * it will return false.
	 * 
	 * @param item - the value to be removed.
	 * @return true if the value was removed, false otherwise.
	 */
	@Override
	public boolean remove(Type item) {
		// Check for 0 items in tree
		if (this.isEmpty())
			return false;
		
		// Check root
		if (root.data.equals(item)) {
			root = adoptChild(root);
			size--;
			return true;
		}
		
		boolean didRemove = removeNode(item, root);
		
		if (didRemove)
			size--;
		
		return didRemove;
	}
	
	/**
	 * Recursive method to find and remove the appropriate node from this tree.
	 * Recursively iterates down the tree, following the appropriate path to find
	 * the node containing the given data value, starting at the tree's root.
	 * If no appropriate node is found, this method will return false.
	 * Otherwise, it will remove the selected data element from the tree, adjusting
	 * the parent-child relationships as necessary.
	 * 
	 * @param item - the data element to be removed.
	 * @param current - The current node being examined.
	 * @return true if the node was found and removed, false otherwise.
	 */
	private boolean removeNode(Type item, Node current) {
		// Check if at bottom level
		if (current.isLeaf()) {
			return false;
		}
		// Check left direction
		else if (item.compareTo(current.data) < 0) {
			// Check left child
			if (current.hasLeftChild()) {
				// Child is target
				if (current.leftChild.data.equals(item)) {
					current.leftChild = adoptChild(current.leftChild);
					return true;
				}
				// Recurse left
				else
					return removeNode(item, current.leftChild);
			}
			// Node doesn't exist
			else
				return false;
		}
		// Check right direction
		else {
			if (current.hasRightChild()) {
				// Child is target
				if (current.rightChild.data.equals(item)) {
					current.rightChild = adoptChild(current.rightChild);
					return true;
				}
				// Recurse right
				else
					return removeNode(item, current.rightChild);
			}
			// Node doesn't exist
			else
				return false;
		}
	}
	
	/**
	 * Deletes the provided node from the tree and adjusts its structure accordingly:
	 * </br>If oldChild is a leaf node, its reference is replaced with null.
	 * </br> if oldChild has one child node, its reference is replaced with that child's reference.
	 * </br> if oldChild had two children, its successor node replaces its spot in the tree, with
	 * that node then being deleted from its original location.
	 * 
	 * @param oldChild - The node being deleted.
	 * @return the node that will replace oldChild's position in the original structure.
	 */
	private Node adoptChild(Node oldChild) {
		// Leaf has no sub-children
		if (oldChild.isLeaf())
			return null;
		
		// One child
		else if (oldChild.hasOneChild()) {
			if (oldChild.hasLeftChild())
				return oldChild.leftChild;
			else
				return oldChild.rightChild;
		}
		
		// Two children
		else {
			Node successor = oldChild.getSuccessor();
			
			successor.leftChild = oldChild.leftChild;
			successor.rightChild = oldChild.rightChild;
			successor.parent.leftChild = null;
			
			return successor;
		}
	}
	
	/**
	 * Removes all elements from the collection items from this tree.
	 * Any elements that are not contained in the tree are discarded.
	 * This method will return true if <i>any</i> elements are deleted.
	 * 
	 * @param items - the Collection containing the items to be removed.
	 * @return true if any elements were deleted, false otherwise.
	 */
	@Override
	public boolean removeAll(Collection<? extends Type> items) {
		boolean removed = false;
		
		for (Type t : items) {
			if (remove(t))
				removed = true;
		}
		
		return removed;
	}
	
	/**
	 * @return the size of the binary search tree.
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * Converts the binary search tree into a sorted ArrayList with the appropriate
	 * data-type.
	 * 
	 * @return a sorted ArrayList of every element contained in this tree.
	 */
	@Override
	public ArrayList<Type> toArrayList() {
		ArrayList<Type> list = new ArrayList<Type>(this.size());
		
		if (!this.isEmpty()) 
			inOrderTraverse(list, this.root);
		
		return list;
	}
	
	/**
	 * A recursive method to add values to the provided list in sorted order.
	 * Performs an in-order traversal through the sub-tree rooted at node current,
	 * adding data values to the list if the current node is a leaf or after the
	 * node's left descendants have been traversed.
	 * 
	 * @param list - The ArrayList to contain the sorted data values from this tree.
	 * @param current - The node whose subtree is currently being traversed.
	 */
	private void inOrderTraverse(ArrayList<Type> list, Node current) {
		if (current.isLeaf()) {
			list.add(current.data);
			return;
		}
		
		if (current.hasLeftChild())
			inOrderTraverse(list, current.leftChild);
		list.add(current.data);
		if (current.hasRightChild())
			inOrderTraverse(list, current.rightChild);
	}
}
