package collection;

import org.jetbrains.annotations.NotNull;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 8. Реализовать собственную структуру данных - HashMap [#268918]
 * Ассоциативный массив на базе хэш-таблицы должен быть унифицирован через генерики и иметь методы:
 * boolean insert(K key, V value);
 *      V get(K key);
 *      boolean delete(K key);
 * Реализовывать итератор.
 * Внутренняя реализация должна использовать массив. Нужно обеспечить фиксированное время вставки и получение.
 * Предусмотрите возможность роста хэш-таблицы при нехватке места для нового элемента.
 * Методы разрешения коллизий реализовывать не надо. Например: если при добавлении ключ уже есть, то возвращать false.
 */

public class SimpleHashMap<K, V> implements Iterable<V> {
    private int size = 0;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private int modCount = 0;
    private Entry[] table;

    public SimpleHashMap() {
        table = new Entry[16];
    }

    boolean insert(K key, V value) {
        boolean rsl = key != null;
        if (rsl) {
            int index = hash(key);
            if (table[index] != null && !table[index].getKey().equals(key)) {
                rsl = false;
            } else {
                size = table[index] == null ? size + 1 : size;
                table[index] = new Entry<>(key, value);
                if ((float) size / (float) table.length >= DEFAULT_LOAD_FACTOR) {
                    resize();
                }
                modCount++;
            }
        }
        return rsl;
    }

    boolean delete(K key) {
        boolean rsl = key != null;
        if (rsl) {
            int index = hash(key);
            if (table[index] != null && table[index].getKey().equals(key)) {
                table[index] = null;
                size--;
                modCount++;
            } else {
                rsl = false;
            }
        }
        return rsl;
    }

    public V get(K key) {
        V rsl = null;
        if (key != null) {
            int index = hash(key);
            if (table[index].getKey().equals(key)) {
                rsl = (V) table[index].getValue();
            }
        }
        return rsl;
    }

    private int hash(K key) {
        return key.hashCode() & (table.length - 1);
    }

    private void resize() {
        if (Integer.numberOfLeadingZeros(size) < 30) {
            int position = 0;
            Entry<K, V>[] old = table;
            int oldLength = table.length;
            table = new Entry[oldLength << 1];
            while (position < oldLength) {
                Entry<K, V> entry = old[position];
                if (entry != null) {
                    table[hash(entry.getKey())] = entry;
                }
                position++;
            }
        }
    }

    @NotNull
    @Override
    public Iterator<V> iterator() {
        return new Iterator<>() {
            int position = 0;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                boolean rsl = false;
                while (position < table.length) {
                    if (table[position] != null) {
                        rsl = true;
                        break;
                    }
                    position++;
                }
                return rsl;
            }

            @Override
            public V next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (V) table[position++].getValue();
            }
        };
    }

    private static class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {

            return value;
        }
    }
}
