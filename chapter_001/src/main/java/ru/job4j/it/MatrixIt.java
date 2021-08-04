package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator {
    private final int[][] data;
    private int row = 0;
    private int cell = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return row < data.length;
    }

    @Override
    public Integer next() {
        while (row < data.length && data[row].length == 0) {
            row++;
        }
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int rsl = data[row][cell++];
        if (cell == data[row].length) {
            row++;
            cell = 0;
        }
        return rsl;
    }
}
