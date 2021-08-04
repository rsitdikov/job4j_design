package ru.job4j.collection;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleHashMapTest {

    @Test
    public void whenInsertedDataThenResultTrue() {
        int key = 1;
        String value = "one";
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        assertTrue(map.insert(key, value));
    }

    @Test
    public void whenCollisionShouldResultFalse() {
        int key = 1;
        String value = "one";
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(key, value);
        assertFalse(map.insert(17, value));
    }

    @Test
    public void whenChangeValueThereShouldResult() {
        String key = "one";
        int value = 1;
        int expected = 2;
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        map.insert(key, value);
        map.insert(key, expected);
        assertThat(expected, is(map.get(key)));
    }

    @Test
    public void whenDeletedDataThereShouldResultTrue() {
        String key = "one";
        int value = 1;
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        map.insert(key, value);
        assertTrue(map.delete(key));
    }

    @Test
    public void whenThereNoDataDeleteShouldReturnFalse() {
        String key = "one";
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        assertFalse(map.delete(key));
    }

    @Test
    public void whenInsertedDataShouldReturnedValues() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(1, "one");
        map.insert(2, "second");
        map.insert(3, "third");
        assertThat("one", is(map.get(1)));
        assertThat("second", is(map.get(2)));
        assertThat("third", is(map.get(3)));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        map.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        map.insert("first", 1);
        Iterator<Integer> it = map.iterator();
        map.insert("second", 2);
        it.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenAddThenIter() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(1, "one");
        map.insert(3, "third");
        map.insert(2, "second");
        Iterator<String> it = map.iterator();
        assertThat("one", is(it.next()));
        assertThat("second", is(it.next()));
        assertThat("third", is(it.next()));
        it.next();
    }
}