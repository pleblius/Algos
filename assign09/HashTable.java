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
		int index = compressHash(key.hashCode());
		
		for (MapEntry<K, V> entry : backingArray.get(index)) {
			if (entry.getKey().equals(key))
				return true;
		}
		
		return false;
	}

	@Override
	public boolean containsValue(V value) {
		
		for (List<MapEntry<K,V>> list : backingArray) {
			for (MapEntry<K,V> entry : list) {
				if (entry.getValue().equals(value))			
					return true;
			}
		}
		
		return false;
	}

	@Override
	public List<MapEntry<K, V>> entries() {
		
		ArrayList<MapEntry<K,V>> returnList = new ArrayList<MapEntry<K,V>>();
		
		for (List<MapEntry<K,V>> list : backingArray) {
			for (MapEntry<K,V> entry : list) {
				returnList.add(entry);
			}
		}
		
		return returnList;
	}

	@Override
	public V get(K key) {
		int index = compressHash(key.hashCode());
		
		for (MapEntry<K, V> entry : backingArray.get(index)) {
			if (entry.getKey().equals(key))
				return entry.getValue();
		}
		
		return null;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public V put(K key, V value) {
		int index = compressHash(key.hashCode());
		V returnValue = null;
		
		for (MapEntry<K, V> entry : backingArray.get(index)) {
			if (entry.getKey().equals(key)) {
				returnValue = entry.getValue();
			
				entry.setValue(value);
				break;
			}
		}
		
		return returnValue;
	}

	@Override
	public V remove(K key) {
		int index = compressHash(key.hashCode());
		V returnValue = null;
		
		for (MapEntry<K, V> entry : backingArray.get(index)) {
			if (entry.getKey().equals(key)) {
				returnValue = entry.getValue();
				backingArray.get(index).remove(entry);
				break;
			}
		}
		
		return returnValue;
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
