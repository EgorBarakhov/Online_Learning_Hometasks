import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class MyCollection<E> extends AbstractCollection<E>{
	
	private static final String ERROR_MESSAGE = "error message";
	private final int CAPACITY = 1000;
	private E[] data;
	private int size;
	private MyIterator<E> myIter;
	
	public MyCollection() {
		this.size = 0;
		data = (E[]) new Object[0];
		myIter = new MyIterator<E>(data, size);
	}
	
	public MyCollection(Collection<? extends E> incol) throws IllegalArgumentException{
		if (incol.size() > CAPACITY) throw new IllegalArgumentException(ERROR_MESSAGE);
		this.size = 0;
		data = (E[]) new Object[incol.size()];
		for (E el : incol) {
			data[size++] = el;
		}
		myIter = new MyIterator<E>(data, size);
	}
	
	public void print() {
		for (E el : data) {
			System.out.println(el);
		}
	}
	
	@Override
	public Iterator<E> iterator() {
		return myIter;
	}

	@Override
	public int size() {
		return size;
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
		MyCollection<?> other = (MyCollection<?>) obj;
		if (!Arrays.equals(data, other.data))
			return false;
		if (size != other.size)
			return false;
		return true;
	}
}
