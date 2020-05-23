package collection;

import java.util.Iterator;

public class SimpleSet<E> implements Iterable<E> {
    private final SimpleArrayList<E> data = new SimpleArrayList<>();

    public void add(E e) {
        Iterator<E> iterator = data.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(e)) {
                return;
            }
        }
        data.add(e);
    }

    @Override
    public Iterator<E> iterator() {
        return data.iterator();
    }
}
