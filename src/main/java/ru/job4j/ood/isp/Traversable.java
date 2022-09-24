package ru.job4j.ood.isp;
import java.util.Iterator;

interface Traversable<T> {
    Iterator<T> preOrder();

    Iterator<T> inOrder();

    Iterator<T> postOrder();

    Iterator<T> bfs();

    Iterator<T> dfs();
}

interface TreeTraversable<T> {
    Iterator<T> preOrder();

    Iterator<T> inOrder();

    Iterator<T> postOrder();
}

interface GraphTraversable<T> {
    Iterator<T> bfs();

    Iterator<T> dfs();
}