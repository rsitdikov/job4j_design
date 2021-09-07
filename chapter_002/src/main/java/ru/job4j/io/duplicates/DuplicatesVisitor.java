package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Set;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Set<FileProperty> originals = new HashSet<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty property = new FileProperty(
                file.toFile().length(),
                file.toFile().getName()
        );
        if (originals.contains(property)) {
            System.out.println(file.toAbsolutePath());
        } else {
            originals.add(property);
        }
        return super.visitFile(file, attrs);
    }
}