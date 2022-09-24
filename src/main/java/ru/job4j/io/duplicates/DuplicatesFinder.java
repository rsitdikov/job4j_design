package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DuplicatesFinder {
    private static Path validate(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("Wrong number of arguments."
                    + System.lineSeparator()
                    + "Usage java -jar DuplicatesFinder.jar ROOT_FOLDER");
        }
        Path rsl = Paths.get(args[0]);
        if (!rsl.toFile().isDirectory()) {
            throw new IllegalArgumentException(String.format(
                    "Directory '%s' is missing.", rsl)
            );
        }
        return rsl;
    }

    public static void main(String[] args) throws IOException {
        Path start = validate(args);
        System.out.println("Duplicate files:");
        Files.walkFileTree(start, new DuplicatesVisitor());
    }
}
