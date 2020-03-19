package col;

import java.util.ArrayList;
import java.util.Iterator;

public class MapIter<K> implements Iterator<K> {

	private static final String ERROR_MESSAGE = "Error message here;
	ArrayList<K> keys;
	int cursor;
	
	public MapIter(ArrayList<K> keys) {
		this.keys = keys;
		cursor = 0;
	}
	
	@Override
	public boolean hasNext() {
		if (keys.get(cursor + 1) != null) return true;
		return false;
	}

	@Override
	public K next() {
		cursor++;
		return keys.get(cursor - 1);
	}

	public K remove() {
		if (keys.get(cursor) == null) throw new IllegalStateException(ERROR_MESSAGE);
		K keyPop = keys.get(cursor);
		keys.set(cursor, null);
		return keyPop;
	}
}
