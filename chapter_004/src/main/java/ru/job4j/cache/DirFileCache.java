package ru.job4j.cache;

import java.io.*;
import java.util.stream.Collectors;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        String value = null;
        File file = new File(String.join(File.separator, cachingDir, key));
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            value = reader
                    .lines()
                    .collect(Collectors.joining(System.lineSeparator()));
            super.put(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }
}
