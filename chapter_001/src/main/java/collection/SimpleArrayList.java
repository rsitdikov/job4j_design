package collection;

import java.util.*;

public class SimpleArrayList<T> implements Iterable<T> {
    private int increase = 10;
    private int modCount = 0;
    private int position = 0;
    private Object[] data;

    public SimpleArrayList() {
        data = new Object[increase];
    }

    public T get(int index) {
        Objects.checkIndex(index, position);
        return (T) data[index];
    }

    public void add(T model) {
        if (position == data.length) {
            data = Arrays.copyOf(data, data.length + increase);
        }
        data[position++] = model;
        modCount++;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int point = 0;
            private int expectedModCount = modCount;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
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
