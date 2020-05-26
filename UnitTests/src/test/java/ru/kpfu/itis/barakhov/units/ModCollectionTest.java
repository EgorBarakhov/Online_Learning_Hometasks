package ru.kpfu.itis.barakhov.units;

import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

public class ModCollectionTest {

    public ModCollection<Integer> getTestModCollection() {
        return testModCollection;
    }

    private ModCollection<Integer> testModCollection;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        System.out.println("Before executing collection test");
        List<Integer> dataList = new ArrayList<>();
        dataList.add(3);
        dataList.add(42);
        dataList.add(-8);
        dataList.add(69);
        testModCollection = new ModCollection<>(dataList);
    }

    @Test
    public void testSize() {
        //int actual = 0;
        //for (Integer el : testModCollection) {
        //    actual++;
        //}
        int actual = 4;
        assertEquals(testModCollection.size(), actual);
    }

    @Test
    public void testAdd() {
        assertThat(testModCollection.size(), is(4));
        testModCollection.add(1488);
        assertThat(testModCollection.size(), is(5));
    }

    @Test
    public void testToString() {
        List<Integer> dataList = new ArrayList<>();
        dataList.add(3);
        dataList.add(42);
        dataList.add(-8);
        dataList.add(69);
        ModCollection<Integer> expectedModCollection = new ModCollection<>(dataList);
        String expected = expectedModCollection.toString();
        String actual = testModCollection.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testEqualsAndHash() {
        List<Integer> dataList = new ArrayList<>();
        dataList.add(3);
        dataList.add(42);
        dataList.add(-8);
        dataList.add(69);
        ModCollection<Integer> expectedModCollection = new ModCollection<>(dataList);
        assertTrue(expectedModCollection.equals(testModCollection) && testModCollection.equals(expectedModCollection));
        assertEquals(expectedModCollection.hashCode(), testModCollection.hashCode());
    }

    @After
    public void tearDown() throws Exception {
        testModCollection = null;
        System.out.println("Test finished");
    }
}