package ru.job4j.it;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(Arrays.asList(1, 2, 3), Is.is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(Arrays.asList(0, 1, 2, 3), Is.is(input));
    }

    @Test
    public void whenRemoveIfZero() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 0, -2, 0, 3));
        ListUtils.removeIf(input, value -> value == 0);
        assertThat(Arrays.asList(1, -2, 3), Is.is(input));
    }

    @Test
    public void whenReplaceIfNegative() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, -1, -3, 0));
        ListUtils.replaceIf(input, value -> value < 0, 10);
        assertThat(Arrays.asList(0, 1, 2, 10, 10, 0), Is.is(input));
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 5, 7, 3, 2, 1, 9));
        List<Integer> elements = new ArrayList<>(Arrays.asList(1, 2, 3));
        ListUtils.removeAll(input, elements);
        assertThat(Arrays.asList(0, 5, 7, 9), Is.is(input));
    }
}
