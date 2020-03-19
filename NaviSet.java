import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.SortedSet;

/**
 * Realizes set of elements which are sorted and may be navigated between
 * @author egor.barakhov
 *
 * @param <T> Type of elements stored in set
 */
public class NaviSet<T> extends AbstractSet<T> implements NavigableSet<T> {
	
	/**
	 * Prints message of exception
	 */
	private static final String ERROR_MESSAGE = "error message here";
	/**
	 * Collection of data stored in set
	 */
	private ArrayList<T> 		myCol;
	/**
	 * Indexes size of set
	 */
	private int 				size;
	/**
	 * Thing that compares elements between to sort and navigate
	 */
	private Comparator<T> 		naviComp;
	
	/**
	 * Standard constructor
	 * @param myCol - will be written as a data 
	 * @param setComp - initialize comparator
	 */
	public NaviSet(ArrayList<T> myCol, Comparator<T> setComp) {
		this.myCol = myCol;
		naviComp = setComp;
		size = myCol.size();
		sort(myCol, setComp);
	}
	
	/**
	 * Creates navigable set with empty data
	 * @param setComp - initialize comparator
	 */
	public NaviSet(Comparator<T> setComp) {
		naviComp = setComp;
		myCol = new ArrayList<>();
		size = 0;
	}
	
	/**
	 * Adds new element to the collection in the right place
	 * @param e - element which will be added
	 * @return true if collection doesn't contain this element
	 * @throws ClassCastException if type of parameter isn't equal to type stored in collection
	 */
	@Override
	public boolean add(T e) {
		if (!(e.getClass().getName().equals(this.getType(myCol).getClass().getName()))) throw new ClassCastException(ERROR_MESSAGE);
		int count = 0;
		if (size != 0) {
			T insert = null;
			while ((count < size) && (naviComp.compare(e, insert) <= 0)) {
				 insert = myCol.get(count);
				 if (naviComp.compare(e, insert) == 0) return false;
				 count++;
			}
		}
		myCol.add(count, e);
		size++;
		return true;
	}
	
	/**
	 * Private method gets class of elements to throw ClassCastException
	 * @param arrayList - data in set
	 * @return class of elements stored in set
	 */
	private Class<?> getType(ArrayList<T> arrayList) {
		Method[] methods = NaviSet.class.getDeclaredMethods();
		Type[] types = methods[1].getGenericParameterTypes();
		ParameterizedType pType = (ParameterizedType) types[0];
		Class<?> cls = (Class<?>) pType.getActualTypeArguments()[0];
		return cls;
	}

	/**
	 * Removes element from specified index
	 * @param index - shows index of element to be removed to
	 * @return removed element or null if index is greater then size
	 */
	public T remove(int index) {
		if (index >= size) return null;
		T getEl = myCol.get(index);
		myCol.remove(index);
		size--;
		return getEl;
	}
	
	/**
	 * Removes element which equal to specified object
	 * @param obj - object to be removed to
	 * @return true if collection contains this object
	 */
	public boolean remove(Object obj) {
		try {
			T enter = (T) obj;
			if (myCol.contains(enter)) {
				size--;
				return myCol.remove(enter);
			}
		} catch (ClassCastException ex) { }
		return false;
	}
		
	/**
	 * Creates iterator to enumerate elements in set
	 * @return new iterator for set based on current data 
	 */
	@Override
	public Iterator<T> iterator() {
		return new SetIterator<>(myCol);
	}
	
	/**
	 * Sets new comparator to compare elements between and sorts them
	 * @param setComp - comparator to be added
	 */
	public void setComp(Comparator<T> setComp) {
		naviComp = setComp;
		sort(myCol, naviComp);
	}

	/**
	 * Sorts elements with current comparator. Uses when adding new element or setting new comparator
	 * @param myCol - data to be sorted to
	 * @param naviComp - comparator used to sort
	 */
	private void sort(ArrayList<T> myCol, Comparator<T> naviComp) {
		for (int i = 1; i < size; i++) {
			if (naviComp.compare(myCol.get(i), myCol.get(i - 1)) < 0)
				swap(myCol.get(i), myCol.get(i - 1));
		}		
	}

	/**
	 * Auxiliary method to swap two elements between
	 * @param o1 - first element
	 * @param o2 - second element
	 */
	private void swap(T o1, T o2) {
		T tmp = o1;
		o1 = o2;
		o2 = tmp;
	}

	/**
	 * Returns current size of collection
	 * @return size
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns comparator which currently uses to sort element
	 * @return current comparator
	 */
	@Override
	public Comparator<? super T> comparator() {
		return naviComp;
	}

	/**
	 * Returns least element in set
	 * @return first element
	 * @throws NoSuchElementException - if set is empty
	 */
	@Override
	public T first() {
		try {
			return myCol.get(0);
		} catch (IndexOutOfBoundsException ex) {
			throw new NoSuchElementException(ERROR_MESSAGE);
		}
	}

	/**
	 * Returns largest element in set
	 * @return last element
	 * @throws NoSuchElementException - if set is empty
	 */
	@Override
	public T last() {
		try {
			return myCol.get(size - 1);
		} catch (IndexOutOfBoundsException ex) {
			throw new NoSuchElementException(ERROR_MESSAGE);
		}
	}

	/**
	 * Returns the greatest element in this set strictly less than the given element, or null if there is no such element
	 * @param e - the value to match
	 * @return the greatest element less than e, or null if there is no such element
	 * @throws ClassCastException - if the specified element cannot be compared with the elements currently in the set
	 * @throws NullPointerException - if the specified element is null and this set does not permit null elements
	 */
	@Override
	public T lower(T e) {
		if (e == null) throw new NullPointerException(ERROR_MESSAGE);
		if (!(e.getClass().getName().equals(this.getType(myCol).getClass().getName()))) throw new ClassCastException(ERROR_MESSAGE);
		int count = 0;
		try {
			while (naviComp.compare(e, myCol.get(count)) >= 0) {
				count++;
			}
			count--;
			if (naviComp.compare(e, myCol.get(count)) == 0) return myCol.get(count - 1);
			return myCol.get(count);
		} catch (IndexOutOfBoundsException ex) {
			if (count <= 0) return null;
			count--;
			if (naviComp.compare(e, myCol.get(count)) == 0) return myCol.get(count - 1);
			return myCol.get(count);
		}
	}

	/**
	 * Returns the greatest element in this set less than or equal to the given element, or null if there is no such element
	 * @param e - the value to match
	 * @return the greatest element less than or equal to e, or null if there is no such element
	 * @throws ClassCastException - if the specified element cannot be compared with the elements currently in the set
	 * @throws NullPointerException - if the specified element is null and this set does not permit null elements
	 */
	@Override
	public T floor(T e) {
		if (e == null) throw new NullPointerException(ERROR_MESSAGE);
		if (!(e.getClass().getName().equals(this.getType(myCol).getClass().getName()))) throw new ClassCastException(ERROR_MESSAGE);
		int count = 0;
		try {
			while (naviComp.compare(e, myCol.get(count)) >= 0) {
				count++;
			}
			if (naviComp.compare(e, myCol.get(count)) == 0) return myCol.get(count);
			return myCol.get(count - 1);
		} catch (IndexOutOfBoundsException ex) {
			if (count == 0) return null;
			return myCol.get(size - 1);
		}
	}

	/**
	 * Returns the least element in this set greater than or equal to the given element, or null if there is no such element
	 * @param e - the value to match
	 * @return the least element greater than or equal to e, or null if there is no such element
	 * @throws ClassCastException - if the specified element cannot be compared with the elements currently in the set
	 * @throws NullPointerException - if the specified element is null and this set does not permit null elements
	 */
	@Override
	public T ceiling(T e) {
		if (e == null) throw new NullPointerException(ERROR_MESSAGE);
		if (!(e.getClass().getName().equals(this.getType(myCol).getClass().getName()))) throw new ClassCastException(ERROR_MESSAGE);
		int count = 0;
		try {
			while (naviComp.compare(e, myCol.get(count)) >= 0) {
				count++;
			}
			if (naviComp.compare(e, myCol.get(count - 1)) == 0) return myCol.get(count - 1);
			return myCol.get(count);
		} catch (IndexOutOfBoundsException ex) {
			if (count == 0) return myCol.get(0);
			if (naviComp.compare(e, myCol.get(count - 1)) == 0) return myCol.get(count - 1);
			return null;
		}  
	}

	/**
	 * Returns the least element in this set strictly greater than the given element, or null if there is no such element
	 * @param e - the value to match
	 * @return the least element greater than e, or null if there is no such element
	 * @throws ClassCastException - if the specified element cannot be compared with the elements currently in the set
	 * @throws NullPointerException - if the specified element is null and this set does not permit null elements
	 */
	@Override
	public T higher(T e) {
		if (e == null) throw new NullPointerException(ERROR_MESSAGE);
		if (!(e.getClass().getName().equals(this.getType(myCol).getClass().getName()))) throw new ClassCastException(ERROR_MESSAGE);
		int count = 0;
		try {
			while (naviComp.compare(e, myCol.get(count)) >= 0) {
				count++;
			}
			return myCol.get(count);
		} catch (IndexOutOfBoundsException ex) {
			return null;
		}
	}

	/**
	 * Retrieves and removes the first (lowest) element, or returns null if this set is empty
	 * @return the first element, or null if this set is empty
	 */
	@Override
	public T pollFirst() {
		try {
			T getEl = myCol.get(0);
			myCol.remove(0);
			size--;
			return getEl;
		} catch (IndexOutOfBoundsException ex) {
			return null;
		}
	}

	/**
	 * Retrieves and removes the last (highest) element, or returns null if this set is empty
	 * @return the last element, or null if this set is empty
	 */
	@Override
	public T pollLast() {
		try {
			T getEl = myCol.get(size - 1);
			myCol.remove(size - 1);
			size--;
			return getEl;
		} catch (IndexOutOfBoundsException ex) {
			return null;
		}
	}

	/**
	 * Returns a reverse order view of the elements contained in this set. The descending set is backed by this set, so changes to the set are reflected in the descending set, and vice-versa. If either set is modified while an iteration over either set is in progress (except through the iterator's own remove operation), the results of the iteration are undefined.
	 * The returned set has an ordering equivalent to Collections.reverseOrder(comparator()). The expression s.descendingSet().descendingSet() returns a view of s essentially equivalent to s
	 * @return a reverse order view of this set
	 */
	@Override
	public NavigableSet<T> descendingSet() {
		NavigableSet<T> answer = new NaviSet<>(naviComp);
		for (int i = size - 1; i >= 0; i--) {
			T el = myCol.get(i);
			answer.add(el);
		}
		return answer;
	}

	/**
	 * Returns an iterator over the elements in this set, in descending order. Equivalent in effect to descendingSet().iterator()
	 * @return an iterator over the elements in this set, in descending order
	 */
	@Override
	public Iterator<T> descendingIterator() {
		NavigableSet<T> descend = this.descendingSet();
		return descend.iterator();
	}

	/**
	 * Returns a view of the portion of this set whose elements range from fromElement to toElement. If fromElement and toElement are equal, the returned set is empty unless fromInclusive and toInclusive are both true. The returned set is backed by this set, so changes in the returned set are reflected in this set, and vice-versa. The returned set supports all optional set operations that this set supports.
	 * The returned set will throw an IllegalArgumentException on an attempt to insert an element outside its range
	 * @param fromElement - low endpoint of the returned set
	 * @param fromInclusive - true if the low endpoint is to be included in the returned view
	 * @param toElement - high endpoint of the returned set
	 * @param toInclusive - true if the high endpoint is to be included in the returned view
	 * @return a view of the portion of this set whose elements range from fromElement, inclusive, to toElement, exclusive
	 * @throws ClassCastException - if fromElement and toElement cannot be compared to one another using this set's comparator
	 * @throws NullPointerException - if fromElement or toElement is null and this set does not permit null elements
	 * @throws IllegalArgumentException - if fromElement is greater than toElement; or if this set itself has a restricted range, and fromElement or toElement lies outside the bounds of the range
	 */
	@Override
	public NavigableSet<T> subSet(T fromElement, boolean fromInclusive, T toElement, boolean toInclusive) {
		if ((toElement == null) || (fromElement == null)) throw new NullPointerException(ERROR_MESSAGE);
		if (!((toElement.getClass().getName().equals(this.getType(myCol).getClass().getName())) || 
				(fromElement.getClass().getName().equals(this.getType(myCol).getName())))) throw new ClassCastException(ERROR_MESSAGE);
		try {
			if ((naviComp.compare(toElement, myCol.get(0)) < 0) || 
					(naviComp.compare(fromElement, myCol.get(size - 1)) > 0) || 
					(naviComp.compare(fromElement, toElement) < 0)) throw new IllegalArgumentException(ERROR_MESSAGE);
		} catch (IndexOutOfBoundsException ex) {
			throw new NoSuchElementException(ERROR_MESSAGE);
		}
		NavigableSet<T> answer = new NaviSet<>(naviComp);
		int count = 0;
		try {
			while (naviComp.compare(fromElement, myCol.get(count)) > 0) {
				count++;
			}
			if ((fromInclusive) && (naviComp.compare(fromElement, myCol.get(count)) == 0)) {
				answer.add(myCol.get(count));
				count++;
			}
			while (naviComp.compare(toElement, myCol.get(count)) > 0) {
				answer.add(myCol.get(count));
				count++;
			}
			if ((toInclusive) && (naviComp.compare(toElement, myCol.get(count)) == 0)) answer.add(myCol.get(count));
		} catch (IndexOutOfBoundsException ex) { }
		return answer;
	}

	/**
	 * Returns a view of the portion of this set whose elements are less than (or equal to, if inclusive is true) toElement. The returned set is backed by this set, so changes in the returned set are reflected in this set, and vice-versa. The returned set supports all optional set operations that this set supports. 
	 * The returned set will throw an IllegalArgumentException on an attempt to insert an element outside its range
	 * @param toElement - high endpoint of the returned set
	 * @param inclusive - true if the high endpoint is to be included in the returned view
	 * @return     a view of the portion of this set whose elements are less than (or equal to, if inclusive is true) toElement
	 * @throws ClassCastException - if toElement cannot be compared to one another using this set's comparator
	 * @throws NullPointerException - if toElement is null and this set does not permit null elements
	 * @throws IllegalArgumentException - if this set itself has a restricted range, and toElement lies outside the bounds of the range
	 */
	@Override
	public NavigableSet<T> headSet(T toElement, boolean inclusive) {
		if (toElement == null) throw new NullPointerException(ERROR_MESSAGE);
		if (!(toElement.getClass().getName().equals(this.getType(myCol).getClass().getName()))) throw new ClassCastException(ERROR_MESSAGE);
		try {
			if (naviComp.compare(toElement, myCol.get(0)) < 0) throw new IllegalArgumentException(ERROR_MESSAGE);
		} catch (IndexOutOfBoundsException ex) {
			throw new NoSuchElementException(ERROR_MESSAGE);
		}
		NavigableSet<T> answer = new NaviSet<>(naviComp);
		int count = 0;
		try {
			while (naviComp.compare(toElement, myCol.get(count)) > 0) {
				answer.add(myCol.get(count));
				count++;
			}
			if ((inclusive) && (naviComp.compare(toElement, myCol.get(count)) == 0)) answer.add(myCol.get(count));
		} catch (IndexOutOfBoundsException ex) { }
		return answer;
	}

	/**
	 * Returns a view of the portion of this set whose elements are greater than (or equal to, if inclusive is true) fromElement. The returned set is backed by this set, so changes in the returned set are reflected in this set, and vice-versa. The returned set supports all optional set operations that this set supports. 
	 * The returned set will throw an IllegalArgumentException on an attempt to insert an element outside its range
	 * @param fromElement - low endpoint of the returned set
	 * @param inclusive - true if the low endpoint is to be included in the returned view
	 * @return a view of the portion of this set whose elements are greater than or equal to fromElement
	 * @throws ClassCastException - if fromElement cannot be compared to one another using this set's comparator
	 * @throws NullPointerException - if fromElement is null and this set does not permit null elements
	 * @throws IllegalArgumentException - if this set itself has a restricted range, and fromElement lies outside the bounds of the range
	 */
	@Override
	public NavigableSet<T> tailSet(T fromElement, boolean inclusive) {
		if (fromElement == null) throw new NullPointerException(ERROR_MESSAGE);
		if (!(fromElement.getClass().getName().equals(this.getType(myCol).getName()))) throw new ClassCastException(ERROR_MESSAGE);
		try {
			if (naviComp.compare(fromElement, myCol.get(size - 1)) > 0) throw new IllegalArgumentException(ERROR_MESSAGE);
		} catch (IndexOutOfBoundsException ex) {
			throw new NoSuchElementException(ERROR_MESSAGE);
		}
		NavigableSet<T> answer = new NaviSet<>(naviComp);
		int count = 0;
		try {
			while (naviComp.compare(fromElement, myCol.get(count)) > 0) {
				count++;
			}
			if ((inclusive) && (naviComp.compare(fromElement, myCol.get(count)) == 0)) answer.add(myCol.get(count));
			count++;
			while (count < size) {
				answer.add(myCol.get(count));
				count++;
			}
		} catch (IndexOutOfBoundsException ex) { }
		return answer;
	}

	/**
	 * Returns a view of the portion of this set whose elements range from fromElement, inclusive, to toElement, exclusive. (If fromElement and toElement are equal, the returned set is empty.) The returned set is backed by this set, so changes in the returned set are reflected in this set, and vice-versa. The returned set supports all optional set operations that this set supports. 
	 * The returned set will throw an IllegalArgumentException on an attempt to insert an element outside its range. 
	 * Equivalent to subSet(fromElement, true, toElement, false)
	 * The returned set will throw an IllegalArgumentException on an attempt to insert an element outside its range. 
	 * @param fromElement - low endpoint of the returned set
	 * @param toElement - high endpoint of the returned set
	 * @return a view of the portion of this set whose elements range from fromElement, inclusive, to toElement, exclusive
	 * @throws ClassCastException - if fromElement and toElement cannot be compared to one another using this set's comparator
	 * @throws NullPointerException - if fromElement or toElement is null and this set does not permit null elements
	 * @throws IllegalArgumentException - if fromElement is greater than toElement; or if this set itself has a restricted range, and fromElement or toElement lies outside the bounds of the range
	 */
	@Override
	public SortedSet<T> subSet(T fromElement, T toElement) {
		return subSet(fromElement, true, toElement, false);
	}

	/**
	 * Returns a view of the portion of this set whose elements are strictly less than toElement. The returned set is backed by this set, so changes in the returned set are reflected in this set, and vice-versa. The returned set supports all optional set operations that this set supports. 
	 * The returned set will throw an IllegalArgumentException on an attempt to insert an element outside its range. 
	 * Equivalent to headSet(toElement, false)
	 * @param toElement - high endpoint of the returned set
	 * @return a view of the portion of this set whose elements are strictly less than toElement
	 * @throws ClassCastException - if fromElement and toElement cannot be compared to one another using this set's comparator
	 * @throws NullPointerException - if fromElement or toElement is null and this set does not permit null elements
	 * @throws IllegalArgumentException - if fromElement is greater than toElement; or if this set itself has a restricted range, and fromElement or toElement lies outside the bounds of the range
	 */
	@Override
	public SortedSet<T> headSet(T toElement) {
		return headSet(toElement, false);
	}

	/**
	 * Returns a view of the portion of this set whose elements are greater than or equal to fromElement. The returned set is backed by this set, so changes in the returned set are reflected in this set, and vice-versa. The returned set supports all optional set operations that this set supports. 
	 * The returned set will throw an IllegalArgumentException on an attempt to insert an element outside its range. 
	 * Equivalent to tailSet(fromElement, true)
	 * @param fromElement - low endpoint (inclusive) of the returned set
	 * @return a view of the portion of this set whose elements are greater than or equal to fromElement
	 * @throws ClassCastException - if fromElement and toElement cannot be compared to one another using this set's comparator
	 * @throws NullPointerException - if fromElement or toElement is null and this set does not permit null elements
	 * @throws IllegalArgumentException - if fromElement is greater than toElement; or if this set itself has a restricted range, and fromElement or toElement lies outside the bounds of the range
	 */
	@Override
	public SortedSet<T> tailSet(T fromElement) {
		return tailSet(fromElement, true);
	}

	/**
	 * Returns the hash code value for this set. The hash code of a set is defined to be the sum of the hash codes of the elements in the set, where the hash code of a null element is defined to be zero. This ensures that s1.equals(s2) implies that s1.hashCode()==s2.hashCode() for any two sets s1 and s2, as required by the general contract of Object.hashCode(). 
	 * This implementation iterates over the set, calling the hashCode method on each element in the set, and adding up the results
	 * @return the hash code value for this set
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((myCol == null) ? 0 : myCol.hashCode());
		result = prime * result + ((naviComp == null) ? 0 : naviComp.hashCode());
		result = prime * result + size;
		return result;
	}

	/**
	 * Compares the specified object with this set for equality. Returns true if the given object is also a set, the two sets have the same size, and every member of the given set is contained in this set. This ensures that the equals method works properly across different implementations of the Set interface.
	 * This implementation first checks if the specified object is this set; if so it returns true. Then, it checks if the specified object is a set whose size is identical to the size of this set; if not, it returns false. If so, it returns containsAll((Collection) o)
	 * @param o - object to be compared for equality with this set 
	 * @return true if the specified object is equal to this set
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		NaviSet<?> other = (NaviSet<?>) obj;
		if (myCol == null) {
			if (other.myCol != null)
				return false;
		} else if (!myCol.equals(other.myCol))
			return false;
		if (naviComp == null) {
			if (other.naviComp != null)
				return false;
		} else if (!naviComp.equals(other.naviComp))
			return false;
		if (size != other.size)
			return false;
		return true;
	}
	
	/**
	 * Returns a string representation of this collection. The string representation consists of a list of the collection's elements in the order they are returned by its iterator, enclosed in square brackets ("[]"). Adjacent elements are separated by the characters ", " (comma and space). Elements are converted to strings as by String.valueOf(Object)
	 * @return a string representation of this collection
	 */
	@Override
	public String toString() {
		return "NaviSet [myCol=" + myCol.toString() + ", size=" + size + ", naviComp=" + naviComp.toString() + "]";
	}
	
}
