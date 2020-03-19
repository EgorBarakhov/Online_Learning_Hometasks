package col;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Map<K, V> extends AbstractMap<K, V> implements java.util.Map<K, V>{

	private static final String ERROR_MESSAGE = "Error message here";
	private ArrayList<K> keys;
	private ArrayList<V> values;
	private int size;
	
	
	public Map() {
		keys = new ArrayList<>();
		values = new ArrayList<>();
		size = 0;
	}
	
	public Iterator<K> iterator() {
		return new MapIter<K>(keys);
	}
	
	public boolean containsKey(Object key) {
		if (size == 0) throw new NoSuchElementException(ERROR_MESSAGE);
		if (key == null) throw new NullPointerException(ERROR_MESSAGE);
		try {
			K keyPop = (K) key;
			return keys.contains(key);
		} catch (ClassCastException ex) {
			throw new ClassCastException(ERROR_MESSAGE);
		}
	}
	
	public boolean containsValue(Object value) {
		if (size == 0) throw new NoSuchElementException(ERROR_MESSAGE);
		if (value == null) throw new NullPointerException(ERROR_MESSAGE);
		try {
			V valuePop = (V) value;
			return values.contains(value);
		} catch (ClassCastException ex) {
			throw new ClassCastException(ERROR_MESSAGE);
		}
	}
	
	public V get (Object key) {
		if (size == 0) throw new NoSuchElementException(ERROR_MESSAGE);
		if (key == null) throw new NullPointerException(ERROR_MESSAGE);
		try {
			K keyPop = (K) key;
			return values.get(keys.indexOf(key));
		} catch (ClassCastException ex) {
			throw new ClassCastException(ERROR_MESSAGE);
		} catch (IndexOutOfBoundsException ex) {
			return null;
		}
	}
	
	public boolean isEmpty() {
		if (size == 0) return true;
		return false;
	}
	
	public Set<K> keySet() {
		Set<K> keyPop = new HashSet<K>();
		for (K x : keys) {
			keyPop.add(x);
		}
		return keyPop;
	}
	
	public Collection<V> valueSet() {
		Collection<V> valPop = new ArrayList<V>();
		for (V x : values) {
			valPop.add(x);
		}
		return valPop;
	}
	
	public V put(K key, V value) {
		if (key == null  | value == null) throw new NullPointerException(ERROR_MESSAGE);
		try {
			K keyPop = (K) key;
			V valPop = (V) value;
			valPop = values.get(keys.indexOf(key));
			values.set(keys.indexOf(key), value);
			return valPop;
		} catch (ClassCastException ex) {
			throw new ClassCastException(ERROR_MESSAGE);
		} catch (IndexOutOfBoundsException ex) {
			values.set(keys.indexOf(key), value);
			return null;
		}
	}
	
	public V remove(Object key) {
		if (key == null) throw new NullPointerException(ERROR_MESSAGE);
		try {
			K keyPop = (K) key;
			int index = keys.indexOf(key);
			V valPop = values.remove(index);
			keys.remove(index);
			return valPop;
		} catch (ClassCastException ex) {
			throw new ClassCastException(ERROR_MESSAGE);
		} catch (IndexOutOfBoundsException ex) {
			return null;
		}
	}
	
	@Override
	public Set<Entry<K, V>> entrySet() {
		Set<Entry<K, V>> entrySet = new HashSet<>();
		Entry<K, V> entry = null;
		for (K key : keys) {
			entry.setValue(values.get(keys.indexOf(key)));
			entrySet.add(entry);
		}
		return entrySet;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((keys == null) ? 0 : keys.hashCode());
		result = prime * result + size;
		result = prime * result + ((values == null) ? 0 : values.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Map<?, ?> other = (Map<?, ?>) obj;
		if (keys == null) {
			if (other.keys != null)
				return false;
		} else if (!keys.equals(other.keys))
			return false;
		if (size != other.size)
			return false;
		if (values == null) {
			if (other.values != null)
				return false;
		} else if (!values.equals(other.values))
			return false;
		return true;
	}
}


