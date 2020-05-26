package ru.kpfu.itis.barakhov.units;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MyCollectionTest {
    MyCollection<Integer> testMyCollection;

    @Before
    public void setUp() throws Exception {
        System.out.println("Before executing collection test");
        List<Integer> dataList = new ArrayList<>();
        dataList.add(3);
        dataList.add(42);
        dataList.add(-8);
        dataList.add(69);
        testMyCollection = new MyCollection<>(dataList);
    }

    @After
    public void tearDown() throws Exception {
        testMyCollection = null;
        System.out.println("Test finished");
    }

    @Test
    public void testSize() {
        int actual = 4;
        assertEquals(testMyCollection.size(), actual);
    }

    @Test
    public void testEqualsAndHash() {
        List<Integer> dataList = new ArrayList<>();
        dataList.add(3);
        dataList.add(42);
        dataList.add(-8);
        dataList.add(69);
        MyCollection<Integer> expectedMyCollection = new MyCollection<>(dataList);
        assertTrue(expectedMyCollection.equals(testMyCollection) && testMyCollection.equals(expectedMyCollection));
        assertEquals(expectedMyCollection.hashCode(), testMyCollection.hashCode());
    }
}