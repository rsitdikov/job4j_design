package ru.job4j.gc.ref;

import java.io.*;
import java.lang.ref.SoftReference;
import java.util.List;
import java.util.stream.Collectors;

public class CacheDemo {
    private SoftReference<List<String>> cache = new SoftReference<>(loadText());

    public CacheDemo() throws FileNotFoundException {
    }

    List<String> loadText() throws FileNotFoundException {
        return new BufferedReader(new FileReader("chapter_004/src/main/resources/UserGC.txt"))
                .lines()
                .collect(Collectors.toList());
    }

    private void safePrint() throws FileNotFoundException {
        List<String> data = this.cache.get();
        if (data == null) {
            data = loadText();
            this.cache = new SoftReference<>(data);
        }
        data.forEach(System.out::println);
    }

    public static void main(String[] args) throws FileNotFoundException {
        new CacheDemo().safePrint();
    }
}
