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
		
		public boolean hasOneChild() {
			if (this.hasLeftChild() && !this.hasRightChild())
				return true;
			if (this.hasRightChild() && !this.hasLeftChild())
				return true;
			
			return false;
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
		if (current.isLeaf()) {
			return false;
		}
		
		else if (current.leftChild.data.equals(item)) {
			current.leftChild = adoptChild(current, current.leftChild);
			size--;
			return true;
		}
		else if (current.rightChild.data.equals(item)) {
			current.rightChild = adoptChild(current, current.rightChild);
			size--;
			return true;
		}
		
		else if (item.compareTo(current.data) < 0) {
			return remove(item, current.leftChild);
		}
		
		else {
			return remove(item, current.rightChild);
		}
		
	}
	
	private Node adoptChild(Node parent, Node oldChild) {
		// Is leaf
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
		
		traverse(list, this.root);
		return list;
	}
	
	private void traverse(ArrayList<Type> list, Node current) {
		if (current.isLeaf()) {
			list.add(current.data);
			return;
		}
		
		traverse(list, current.leftChild);
		list.add(current.data);
		traverse(list, current.rightChild);
	}
}
