package it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIt implements Iterator {
    private final int[] data;
    private int point;

    public EvenIt(int[] data) {
        this.data = data;
        point = 0;
    }

    @Override
    public boolean hasNext() {
        return nextIndex() < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw  new NoSuchElementException();
        }
        point = nextIndex();
        return data[point++];
    }

    private int nextIndex() {
        int index = point;
        while (index < data.length && data[index] % 2 != 0) {
            index++;
        }
        return index;
    }
}
