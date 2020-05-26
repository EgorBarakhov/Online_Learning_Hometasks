package ru.kpfu.itis.barakhov.units;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class ModIteratorTest {
    ModIterator<Integer> testModIter;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        System.out.println("Before executing iterator test");
        List<Integer> dataList = new ArrayList<>();
        dataList.add(3);
        dataList.add(42);
        dataList.add(-8);
        dataList.add(69);
        testModIter = (ModIterator<Integer>) new ModCollection<>(dataList).iterator();
    }

    @Test
    public void testHasNextNullCollection() {
        assertFalse(new ModCollection<Integer>().iterator().hasNext());
    }

    @Test
    public void testHasNext() {
        List<Integer> dataList = new ArrayList<>();
        dataList.add(0);
        ModIterator<Integer> expectedModIter = (ModIterator<Integer>) new ModCollection<>(dataList).iterator();
        for (int i = 0; i < testModIter.getLength() + 1; i++) {
            assertTrue(testModIter.hasNext() && expectedModIter.hasNext());
        }
    }

    @Test
    public void testNextNullCollection() {
        thrown.expect(NoSuchElementException.class);
        thrown.expectMessage(equalTo("error message here"));
        new ModCollection<Integer>().iterator().next();
        thrown = ExpectedException.none();
    }

    @Test
    public void testDoubleRemoveException() {
        thrown.expect(NoSuchElementException.class);
        thrown.expectMessage(equalTo("error message here"));
        ModCollection<Integer> expected = new ModCollection<Integer>();
        expected.add(12);
        ModIterator<Integer> expectedIterator = (ModIterator<Integer>) expected.iterator();
        expectedIterator.remove();
        expectedIterator.remove();
        thrown = ExpectedException.none();
    }

    @Test
    public void testEqualsAndHash() {
        List<Integer> dataList = new ArrayList<>();
        dataList.add(3);
        dataList.add(42);
        dataList.add(-8);
        dataList.add(69);
        ModIterator<Integer> expectedModIterator = (ModIterator<Integer>) new ModCollection<>(dataList).iterator();
        assertTrue(expectedModIterator.equals(testModIter) && testModIter.equals(expectedModIterator));
        assertEquals(expectedModIterator.hashCode(), testModIter.hashCode());
    }

    @After
    public void tearDown() throws Exception {
        testModIter = null;
        System.out.println("Test finished");
    }
}