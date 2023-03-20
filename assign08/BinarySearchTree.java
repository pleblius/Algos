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
	}
	
	public BinarySearchTree() {
		size = 0;
	}
	
	public BinarySearchTree(Node root) {
		this.root = root;
		size = 1;
	}
	
	
	@Override
	public boolean add(Type item) {
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
			if (current.leftChild == null) {
				current.leftChild = new Node(item, current);
				return true;
			}
				
			return add(item, current.leftChild);
		}
			
		// Check right child, if null, add item there
		else {
			if (current.rightChild == null) {
				current.rightChild = new Node(item, current);
				return true;
			}
			
			return add(item, current.rightChild);
		}
	}

	@Override
	public boolean addAll(Collection<? extends Type> items) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(Type item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<? extends Type> items) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Type first() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Type last() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Type item) {
		// TODO Auto-generated method stub
		return false;
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

}
