package ru.job4j.collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UsingArrayList {
    public static void main(String[] args) {
        int count = 100000;
        List<String> array = new ArrayList<>();
        List<String> linked = new LinkedList<>();
        for (int index = 0; index < count; index++) {
            String text = "test_" + index;
            array.add(index, text);
            linked.add(index, text);
        }
        long begin = System.currentTimeMillis();
        for (int index = 0; index < count; index++) {
            array.set(index, array.get(index).toUpperCase());
        }
        long end = System.currentTimeMillis();
        System.out.println("ArrayList: " + (end - begin));
        begin = System.currentTimeMillis();
        for (int index = 0; index < count; index++) {
            linked.set(index, array.get(index).toUpperCase());
        }
        end = System.currentTimeMillis();
        System.out.println("LinkedList: " + (end - begin));
    }
}
