package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private int position = 0;
    private Object[] data;

    public SimpleArray(int size) {
        data =  new Object[size];
    }

    public void add(T model) {
        Objects.checkIndex(position, data.length);
        data[position++] = model;
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, position);
        data[index] = model;
    }

    public void remove(int index) {
        Objects.checkIndex(index, position);
        System.arraycopy(data, index + 1, data, index, data.length  - index - 1);
        position--;
    }

    public T get(int index) {
        Objects.checkIndex(index, position);
        return (T) data[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int point = 0;
            @Override
            public boolean hasNext() {
                return point < position;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) data[point++];
            }
        };
    }
}
