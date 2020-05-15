package collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinked<E> implements Iterable<E> {
    private Node<E> head;
    private int modCount = 0;
    private int position = 0;

    public void add(E value) {
        position++;
        modCount++;
        Node<E> node = new Node<E>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<E> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public E get(int index) {
        Objects.checkIndex(index, position);
        Node<E> tail = head;
        while (index > 0) {
            index--;
            tail = tail.next;
        }
        return tail.value;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> node = head;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return node != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}