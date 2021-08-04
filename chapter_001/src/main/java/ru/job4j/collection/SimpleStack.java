package ru.job4j.collection;

public class SimpleStack<T> {
    private SimpleLinked<T> linked = new SimpleLinked<>();

    public T pop() {
        return linked.deleteLast();
    }

    public void push(T value) {
        linked.add(value);
    }
}