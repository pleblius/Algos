package assign09;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HashTable<K, V> implements Map<K, V> {
	
	private int size;
	private int capacity;
	
	private ArrayList<List<MapEntry<K, V>>> backingArray;
	
	public HashTable() {
		size = 0;
		capacity = 20;
		
		backingArray = new ArrayList<List<MapEntry<K,V>>>();
		
		for (int i = 0; i < capacity; i++) {
			backingArray.add(new LinkedList<MapEntry<K,V>>());
		}
	}
	
	@Override
	public void clear() {
		size = 0;
		capacity = 20;
		backingArray = new ArrayList<List<MapEntry<K,V>>>();
		
		for (int i = 0; i < capacity; i++) {
			backingArray.add(new LinkedList<MapEntry<K,V>>());
		}
	}

	@Override
	public boolean containsKey(K key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsValue(V value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<MapEntry<K, V>> entries() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V get(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public V put(K key, V value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V remove(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		return size;
	}
	
	/*
	 * Helper Functions
	 */
	
	private int compressHash(int hash) {
		return hash%capacity;
	}
	
	private void doubleCapacity() {
		ArrayList<List<MapEntry<K,V>>> newArray = new ArrayList<List<MapEntry<K,V>>>();
		
		capacity *= 2;
		
		for (int i = 0; i < capacity; i++) {
			newArray.add(new LinkedList<MapEntry<K,V>>());
		}
		
		for (List<MapEntry<K,V>> list : newArray) {
			for (MapEntry<K,V> entry : list) {
				put(entry.getKey(), entry.getValue());
			}
		}
		
		backingArray = newArray;
	}
	
	private float getLoadFactor() {
		return ((float)(size))/((float)(capacity));
	}
}
