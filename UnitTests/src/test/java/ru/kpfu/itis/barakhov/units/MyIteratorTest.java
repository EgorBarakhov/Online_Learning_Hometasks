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

public class MyIteratorTest {
    MyIterator<Integer> testMyIter;

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
        testMyIter = (MyIterator<Integer>) new MyCollection<>(dataList).iterator();
    }

    @Test
    public void testHasNextNullCollection() {
        assertFalse(new MyCollection<Integer>().iterator().hasNext());
    }

    @Test
    public void testHasNext() {
        List<Integer> dataList = new ArrayList<>();
        dataList.add(0);
        MyIterator<Integer> expectedMyIter = (MyIterator<Integer>) new MyCollection<>(dataList).iterator();
        for (int i = 0; i < testMyIter.getLength() + 1; i++) {
            assertTrue(testMyIter.hasNext() && expectedMyIter.hasNext());
        }
    }

    @Test
    public void testNextNullCollection() {
        thrown.expect(NoSuchElementException.class);
        thrown.expectMessage(equalTo("error message here"));
        new MyCollection<Integer>().iterator().next();
        thrown = ExpectedException.none();
    }

    @Test
    public void testEqualsAndHash() {
        List<Integer> dataList = new ArrayList<>();
        dataList.add(3);
        dataList.add(42);
        dataList.add(-8);
        dataList.add(69);
        MyIterator<Integer> expectedMyIterator = (MyIterator<Integer>) new MyCollection<>(dataList).iterator();
        assertTrue(expectedMyIterator.equals(testMyIter) && testMyIter.equals(expectedMyIterator));
        assertEquals(expectedMyIterator.hashCode(), testMyIter.hashCode());
    }

    @After
    public void tearDown() throws Exception {
        testMyIter = null;
        System.out.println("Test finished");
    }

}