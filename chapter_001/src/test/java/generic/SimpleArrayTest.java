package generic;

import org.junit.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {
    @Test
    public void whenSimpleArrayWithStringThenResultString() {
        SimpleArray<String> array = new SimpleArray<>(5);
        array.add("100");
        String expected = "100";
        assertEquals(array.get(0), expected);
    }

    @Test
    public void whenSimpleArrayWithIntegerThenResultInteger() {
        SimpleArray<Integer> array = new SimpleArray<>(5);
        array.add(100);
        int expected = 100;
        assertThat(array.get(0), is(expected));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenDataGetOutOfBounds() {
        SimpleArray<Integer> array = new SimpleArray<>(5);
        array.add(100);
        array.get(1);
    }

    @Test
    public void whenSet5ThenGet5() {
        SimpleArray<Integer> array = new SimpleArray<>(5);
        array.add(100);
        array.set(0, 5);
        int expected = 5;
        assertThat(array.get(0), is(expected));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenDataSetOutOfBounds() {
        SimpleArray<Integer> array = new SimpleArray<>(5);
        array.add(100);
        array.set(1, 5);
    }

    @Test
    public void whenMultiCallhasNextThenTrue() {
        SimpleArray<Integer> array = new SimpleArray<>(10);
        array.add(100);
        Iterator<Integer> iterator = array.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.hasNext(), is(true));
    }

    @Test
    public void  whenReadSequence() {
        SimpleArray<Integer> array = new SimpleArray<>(5);
        array.add(100);
        array.add(200);
        array.add(300);
        array.add(400);
        array.add(500);
        Iterator<Integer> iterator = array.iterator();
        assertThat(iterator.next(), is(100));
        assertThat(iterator.next(), is(200));
        assertThat(iterator.next(), is(300));
        assertThat(iterator.next(), is(400));
        assertThat(iterator.next(), is(500));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNextFromEmpty() {
        SimpleArray<Integer> array = new SimpleArray<>(5);
        Iterator<Integer> iterator = array.iterator();
        iterator.next();
    }
}