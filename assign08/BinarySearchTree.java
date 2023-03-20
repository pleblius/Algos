package assign08;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

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
		
		public Node getLeftMostNode() {
			if (this.leftChild == null)
				return this;
			
			return this.getLeftMostNode();
		}
		
		public Node getRightMostNode() {
			if (this.rightChild == null)
				return this;
			
			return this.getRightMostNode();
		}
		
		public Node getPredecessor() throws NoSuchElementException {
			if (this.leftChild == null)
				throw new NoSuchElementException("This child does not exist.");
			
			return this.leftChild.getRightMostNode();
		}
		
		public Node getSuccessor() throws NoSuchElementException {
			if (this.rightChild == null)
				throw new NoSuchElementException("This child does not exist.");
			return this.rightChild.getLeftMostNode();
		}
	}
	
	public BinarySearchTree() {
		size = 0;
	}
	
	public BinarySearchTree(Type item) {
		this.root = new Node(item, null);
		size = 1;
	}
	
	
	@Override
	public boolean add(Type item) {
		if (size == 0) {
			this.root = new Node(item, null);
			size++;
			return true;
		}
		
		boolean addedItem = add(item, root);
		
		if (addedItem)
			size++;
		
		return addedItem;
	}
	
	private boolean add(Type item, Node current) {
		
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
				
			return add(item, current.leftChild);
		}
			
		// Check right child, if null, add item there
		else {
			if (!current.hasRightChild()) {
				current.rightChild = new Node(item, current);
				return true;
			}
			
			return add(item, current.rightChild);
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
		if (size == 0) return false;
		
		return contains(item, root);
	}
	
	private boolean contains(Type item, Node current) {
		if (current.data.equals(item)) {
			return true;
		}
		else if(item.compareTo(current.data) < 0) {
			if (!current.hasLeftChild()) {
				return false;
			}
			
			return contains(item, current.leftChild);
		}
		else {
			if (!current.hasRightChild()) {
				return false;
			}
			
			return contains(item, current.rightChild);
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
		if (size == 0)
			throw new NoSuchElementException("The set is empty.");
		
		return root.getLeftMostNode().data;
	}

	@Override
	public boolean isEmpty() {
		if (size == 0)
			return true;
		
		else return false;
	}

	@Override
	public Type last() throws NoSuchElementException {
		if (size == 0)
			throw new NoSuchElementException("The set is empty.");
		
		return root.getRightMostNode().data;
	}

	@Override
	public boolean remove(Type item) {
		if (size == 0) return false;
		if (size == 1) {
			if (root.data == item) {
				root.data = null;
				size--;
				return true;
			}
			
			else {
				return false;
			}
		}
		
		boolean didRemove = false;
		didRemove = remove(item, root);
		
		if (didRemove)
			size--;
		
		return didRemove;
	}
	
	private boolean remove(Type item, Node current) {
		Node toRemove;
		
		if (current.isLeaf()) {
			return false;
		}
		
		else if (current.leftChild.equals(item)) {
			return killChild(current, current.leftChild);
		}
		else if (current.rightChild.equals(item)) {
			return killChild(current, current.rightChild);
		}
		
		else if (item.compareTo(current.data) < 0) {
			return remove(item, current.leftChild);
		}
		
		else {
			return remove(item, current.rightChild);
		}
		
	}
	
	private boolean killChild(Node parent, Node toRemove) {
		
		// One-child node
		
		parent.child = toRemove.child
		
		// Two-child node
		parent.leftChild = toRemove.getPredecessor()
			// predesessor = null
		parent.rightChild = toRemove.getSuccessor()
			// successor = null
		
				
	}


	@Override
	public boolean removeAll(Collection<? extends Type> items) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Type> toArrayList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*
	 * Helper methods
	 */
	

}
