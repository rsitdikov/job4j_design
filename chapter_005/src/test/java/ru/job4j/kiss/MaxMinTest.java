package ru.job4j.kiss;

import org.checkerframework.checker.units.qual.C;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

public class MaxMinTest {
    public MaxMin maxMin;

    @Before
    public void init() {
        maxMin = new MaxMin();
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenListIsEmpty() {
        maxMin.min(new ArrayList<>(),
                        Byte::compareTo);
    }

    @Test (expected = NullPointerException.class)
    public void whenCellsContainNullAndComparatorNotNullSafe() {
        List<String> list = new ArrayList<>();
        list.add(null);
        list.add("First");
        list.add(null);
        list.add("Second");
        list.add(null);
        list.add("Third");
        list.add(null);
        maxMin.min(list, String::compareTo);
    }

    @Test
    public void whenCellsContainNullAndComparatorNullSafe() {
        List<String> list = new ArrayList<>();
        list.add(null);
        list.add("First");
        list.add(null);
        list.add("Second");
        list.add(null);
        list.add("Third");
        list.add(null);
        assertEquals("Third",
                maxMin.max(list, Comparator.nullsFirst(String::compareTo)));
    }

    @Test
    public void whenMaximumOfNumbersIsCalculated() {
        assertEquals(Integer.valueOf(45),
                maxMin.max(List.of(10, 45, 1), Integer::compareTo));
    }

    @Test
    public void whenMinimumOfStringsIsCalculated() {
        assertEquals("First",
                maxMin.min(List.of("First", "Second", "Third"), String::compareTo));
    }

    @Test
    public void whenMaximumIsCalculatedFromLengths() {
        assertEquals("Washington",
                maxMin.max(List.of("Moscow", "Washington", "London"),
                        Comparator.comparingInt(String::length)));
    }

    @Test
    public void whenMinimumOfSymbolsIsCalculated() {
        assertEquals(Character.valueOf('a'),
                maxMin.min(List.of('c', 'z', 'a'),
                        Character::compareTo));
    }

    @Test
    public void whenMaximumOfBytesIsCalculated() {
        assertEquals(Byte.valueOf((byte) 120),
                maxMin.max(List.of((byte) 100, (byte) 120, (byte) 5),
                        Byte::compareTo));
    }
}