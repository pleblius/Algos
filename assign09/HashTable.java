package assign09;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * This class represents a hashtable that stores a dictionary of key-value pairs
 * using a backing array and hash-table implementation. Resolves collisions
 * using separate chaining via linked lists.
 * 
 * @author Tyler Wilcox and Andrew Tolton
 * @version 02 April, 2023
 * 
 * @param <K> - Generic Type of Map Keys
 * @param <V> - Generic Type of Map Values
 */
public class HashTable<K, V> implements Map<K, V> {
	
	private int size;
	private int capacity;
	
	private ArrayList<List<MapEntry<K, V>>> backingArray;
	
	/**
	 * Instantiates an empty HashTable object with a default capacity of 20.
	 */
	public HashTable() {
		size = 0;
		capacity = 20;
		
		backingArray = new ArrayList<List<MapEntry<K,V>>>();
		
		for (int i = 0; i < capacity; i++) {
			backingArray.add(new LinkedList<MapEntry<K,V>>());
		}
	}
	
	/**
	 * Removes all entries from this hashmap and resets its backing
	 * data structures.
	 */
	@Override
	public void clear() {
		size = 0;
		capacity = 20;
		backingArray = new ArrayList<List<MapEntry<K,V>>>();
		
		for (int i = 0; i < capacity; i++) {
			backingArray.add(new LinkedList<MapEntry<K,V>>());
		}
	}

	/**
	 * Checks if the given key is contained within this hashmap.
	 * 
	 * @param key - The key being searched for.
	 * @return true if the key is contained in the map, false otherwise.
	 */
	@Override
	public boolean containsKey(K key) {
		int index = compressHash(key.hashCode());
		
		for (MapEntry<K, V> entry : backingArray.get(index)) {
			if (entry.getKey().equals(key))
				return true;
		}
		
		return false;
	}

	/**
	 * Checks if the given value is contained within this hashmap.
	 * 
	 * @param value - The value being searched for.
	 * @return true if the value is contained in the map, false otherwise.
	 */
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

	/**
	 * Creates a list of MapEntry objects corresponding to all key-value pairs
	 * contained within this hashmap.
	 * Order is generated arbitrarily and is not guaranteed.
	 * 
	 * @return A list of all entries in this map in arbitrary order.
	 */
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

	/**
	 * Gets the value of the entry corresponding to the given key.
	 * 
	 * @param key - The key of the entry
	 * @return the value of the associated entry
	 */
	@Override
	public V get(K key) {
		int index = compressHash(key.hashCode());
		
		for (MapEntry<K, V> entry : backingArray.get(index)) {
			if (entry.getKey().equals(key))
				return entry.getValue();
		}
		
		return null;
	}
	
	/**
	 * Checks if this map is empty
	 * 
	 * @return true if empty, false otherwise.
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Puts a new entry into this map with the associated key-value pairing.
	 * If the key is already associated with a value, it will override that value
	 * and return the old value.
	 * Otherwise it will create a new entry with the given key-value pairing and
	 * return null.
	 * 
	 * @param key - The key associated with the data entry
	 * @param value - The value associated with the data entry
	 * @return the previous 
	 */
	@Override
	public V put(K key, V value) {
		int index = compressHash(key.hashCode());
		V returnValue = null;
		
		// Check if entry already exists
		
		for (MapEntry<K, V> entry : backingArray.get(index)) {
			if (entry.getKey().equals(key)) {
				returnValue = entry.getValue();
				entry.setValue(value);
				
				return returnValue;
			}
		}
		
		// Entry does not exist
		size++;
		backingArray.get(index).add(new MapEntry<K, V>(key, value));
		
		if (getLoadFactor() > 5.0)
			doubleCapacity();
			
		return returnValue;
	}

	/**
	 * Removes the element associated with the provided key from this map and
	 * returns the value that was contained there.
	 * If there was no previous value, it returns null instead.
	 * 
	 * @param key - the key associated with the data entry to be removed.
	 * @return the previous value at that key or null if no value existed.
	 */
	@Override
	public V remove(K key) {
		int index = compressHash(key.hashCode());
		V returnValue = null;
		
		for (MapEntry<K, V> entry : backingArray.get(index)) {
			if (entry.getKey().equals(key)) {
				returnValue = entry.getValue();
				backingArray.get(index).remove(entry);
				size--;
				
				break;
			}
		}
		
		return returnValue;
	}

	/**
	 * @return number of elements contained in this map
	 */
	@Override
	public int size() {
		return size;
	}
	
	/*
	 * Helper Functions
	 */
	
	/**
	 * Compresses the hash code into an index that can fit into the backing array.
	 * 
	 * @param hash - The hash code to be compressed.
	 * @return an index to be stored in the backing array.
	 */
	private int compressHash(int hash) {
		
		return Math.abs(hash%capacity);
	}
	
	/**
	 * Doubles the capacity of the backing array and re-hashes all elements
	 * currently stored in this map.
	 */
	private void doubleCapacity() {
		List<MapEntry<K, V>> itemList = entries();
		int newCapacity = capacity*2;
		
		clear();
		
		capacity = newCapacity;

		for (int i = 20; i < capacity; i++) {
			backingArray.add(new LinkedList<MapEntry<K,V>>());
		}
		
		for (MapEntry<K,V> entry : itemList) {
			put(entry.getKey(),entry.getValue());
		}
	}
	
	/**
	 * Gets the load factor of this map, which is the number of elements
	 * divided by the current capacity of the backing array.
	 * 
	 * @return a float size divided by capacity
	 */
	private float getLoadFactor() {
		return ((float)(size))/((float)(capacity));
	}
}
