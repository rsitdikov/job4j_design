package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            throw new IllegalArgumentException("Wrong number of arguments."
                    + System.lineSeparator()
                    + "Usage java -jar DuplicatesFinder.jar ROOT_FOLDER");
        }
        Path start = Paths.get(args[0]);
        if (!start.toFile().isDirectory()) {
            throw new IllegalArgumentException(String.format(
                    "Directory '%s' is missing.", start)
            );
        }
        System.out.println("Duplicate files:");
        Files.walkFileTree(start, new DuplicatesVisitor());
    }
}
