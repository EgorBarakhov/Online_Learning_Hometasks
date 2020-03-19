package modified;

import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class ModCollection<T> extends AbstractCollection<T> {
	
	private static final String ERROR_MESSAGE = "error message";
	private final int CAPACITY = 1000;
	private T[] data;
	private int size;
	private ModIterator<T> modIter;
	
	public ModCollection() {
		this.size = 0;
		data = (T[]) new Object[CAPACITY];
		modIter = new ModIterator<T>(data, size);
	}
	
	public ModCollection(Collection<? extends T> incol) throws IllegalArgumentException{
		if (incol.size() > CAPACITY) throw new IllegalArgumentException(ERROR_MESSAGE);
		this.size = 0;
		data = (T[]) new Object[incol.size()];
		for (T el : incol) {
			data[++size] = el;
		}
		modIter = new ModIterator<T>(data, size);
	}
	
	@Override
	public Iterator<T> iterator() {
		return modIter;
	}

	public void print() {
		for (T el : data) {
			System.out.println(el);
		}
	}
	
	@Override
	public int size() {
		return size;
	}
	
	public boolean add(T el) {
		try {
			el = data[++size];
			return true;
		} catch (ArrayIndexOutOfBoundsException ex) {
			return false;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(data);
		result = prime * result + size;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModCollection<?> other = (ModCollection<?>) obj;
		if (!Arrays.equals(data, other.data))
			return false;
		if (size != other.size)
			return false;
		return true;
	}
}
