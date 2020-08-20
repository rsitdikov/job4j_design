package io;

import java.io.File;

public class Dir {

    private static long getDirectorySize(File directory) {
        long result = 0L;
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                result += file.isDirectory() ? getDirectorySize(file) : file.length();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        File file = new File("c:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        System.out.println(String.format("size : %s", file.getTotalSpace()));
        for (File subfile : file.listFiles()) {
            System.out.println(String.format("name: '%s' size: %d", subfile.getName(),
                    subfile.isDirectory() ? getDirectorySize(subfile) : subfile.length()));
        }
    }
}