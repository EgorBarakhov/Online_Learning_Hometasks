import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SetIterator<T> implements Iterator<T> {
	
	private static final String ERROR_MESSAGE = "error message here";
	private ArrayList<T> myList;
	private int cursor;

	public SetIterator(ArrayList<T> myList) {
		this.myList = myList;
		cursor = 0;
	}
	
	@Override
	public boolean hasNext() {
		if (myList.get(cursor) != null) return true;
		return false;
	}

	@Override
	public T next() {
		try {
			return myList.get(++cursor);
		} catch (IndexOutOfBoundsException ex) {
			throw new NoSuchElementException(ERROR_MESSAGE);
		}
	}

}
