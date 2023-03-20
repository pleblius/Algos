package assign08;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * 
 * @author Tyler Wilcox and Andrew Tolton
 * @version 20 March, 2023
 * @param <Type>
 */
public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type> {

	private Node root;
	private int size;
		
	private class Node {
		
		private Type data;
		private Node leftChild;
		private Node rightChild; 
		private Node parent;
		
		public Node(Type data, Node parent) {
			this.data = data;
			this.parent = parent;
		}
		
		public boolean hasLeftChild() {
			return this.leftChild != null;
		}
		
		public boolean hasRightChild() {
			return this.rightChild != null;
		}
		
		public boolean isLeaf() {
			return !hasLeftChild() && !hasRightChild();
		}
		
		public boolean hasOneChild() {
			if (this.hasLeftChild() && !this.hasRightChild())
				return true;
			if (this.hasRightChild() && !this.hasLeftChild())
				return true;
			
			return false;
		}
		
		public Node getLeftMostNode() {
			if (!this.hasLeftChild())
				return this;
			
			return this.getLeftMostNode();
		}
		
		public Node getRightMostNode() {
			if (!this.hasRightChild())
				return this;
			
			return this.getRightMostNode();
		}
		
		public Node getPredecessor() throws NoSuchElementException {
			if (!this.hasLeftChild())
				throw new NoSuchElementException("This node does not have a predecessor.");
			
			return this.leftChild.getRightMostNode();
		}
		
		public Node getSuccessor() throws NoSuchElementException {
			if (!this.hasRightChild())
				throw new NoSuchElementException("This node does not have a successor.");
			return this.rightChild.getLeftMostNode();
		}
	}
	
	public BinarySearchTree() {
		size = 0;
	}
	
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

	@Override
	public boolean addAll(Collection<? extends Type> items) {
		boolean addAny = false;
		
		for (Type t : items) {
			if (add(t))
				addAny = true;
		}
		
		return addAny;
	}

	@Override
	public void clear() {
		size = 0;
		root = null;
	}

	@Override
	public boolean contains(Type item) {
		if (this.isEmpty())
			return false;
		
		return containsNode(item, root);
	}
	
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

	@Override
	public boolean containsAll(Collection<? extends Type> items) {
		boolean containsAll = true;
		
		for (Type t : items) {
			if (!contains(t))
				containsAll = false;
		}
		
		return containsAll;
	}

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

	@Override
	public Type last() throws NoSuchElementException {
		if (this.isEmpty())
			throw new NoSuchElementException("The set is empty.");
		
		return root.getRightMostNode().data;
	}

	@Override
	public boolean remove(Type item) {
		// Check for 0 items in tree
		if (this.isEmpty())
			return false;
		// Check for 1 item in tree
		else if (root.isLeaf()) {
			if (root.data.equals(item)) {
				this.clear();
				return true;
			}
			else {
				return false;
			}
		}
		
		boolean didRemove = removeNode(item, root);
		
		if (didRemove)
			size--;
		
		return didRemove;
	}
	
	private boolean removeNode(Type item, Node current) {
		// Check if at bottom level
		if (current.isLeaf()) {
			return false;
		}
		// Check if left child is data point in question
		else if (current.leftChild.data.equals(item)) {
			current.leftChild = adoptChild(current, current.leftChild);
			return true;
		}
		// Check if right child is data point in question
		else if (current.rightChild.data.equals(item)) {
			current.rightChild = adoptChild(current, current.rightChild);
			return true;
		}
		// Recurse left
		else if (item.compareTo(current.data) < 0) {
			return removeNode(item, current.leftChild);
		}
		// Recurse right
		else {
			return removeNode(item, current.rightChild);
		}
	}
	
	private Node adoptChild(Node parent, Node oldChild) {
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

	@Override
	public boolean removeAll(Collection<? extends Type> items) {
		boolean removed = false;
		
		for (Type t : items) {
			if (remove(t))
				removed = true;
		}
		
		return removed;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public ArrayList<Type> toArrayList() {
		ArrayList<Type> list = new ArrayList<Type>();
		
		if (!this.isEmpty()) 
			inOrderTraverse(list, this.root);
		
		return list;
	}
	
	private void inOrderTraverse(ArrayList<Type> list, Node current) {
		if (current.isLeaf()) {
			list.add(current.data);
			return;
		}
		
		inOrderTraverse(list, current.leftChild);
		list.add(current.data);
		inOrderTraverse(list, current.rightChild);
	}
}
