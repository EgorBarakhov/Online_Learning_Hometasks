package ru.kpfu.itis.barakhov.units;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class MyIterator<T> implements Iterator<T> {

    private static final String ERROR_MESSAGE = "error message here";
    private final T[] data;
    private int cursor;
    private final int length;

    public MyIterator(T[] data, int size) {
        this.data = data;
        cursor = -1;
        length = size;
    }

    @Override
    public boolean hasNext() {
        return cursor < length - 1;
    }

    @Override
    public T next() {
        if (hasNext()) {
            return data[++cursor];
        }
        throw new NoSuchElementException(ERROR_MESSAGE);
    }

    public void remove() {
        try {
            T el = data[cursor + 1];
            if (el == null) {
                throw new NoSuchElementException(ERROR_MESSAGE);
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new NoSuchElementException(ERROR_MESSAGE);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyIterator)) return false;
        MyIterator<?> that = (MyIterator<?>) o;
        return cursor == that.cursor &&
                getLength() == that.getLength() &&
                Arrays.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(cursor, getLength());
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    @Override
    public String toString() {
        return "MyIterator { " +
                "data = " + Arrays.toString(data) +
                ", cursor = " + cursor +
                ", length = " + length +
                " }";
    }

    public int getLength() {
        return length;
    }

}