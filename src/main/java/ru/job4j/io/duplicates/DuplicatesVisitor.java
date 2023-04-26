package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private List<FileProperty> paths = new ArrayList<>();
    private Map<FileProperty, Path> duplicates = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(attrs.size(), file.toFile().getName());
        if (paths.contains(fileProperty)) {
            duplicates.put(fileProperty, file);
            System.out.println(fileProperty);
            System.out.println(file.toAbsolutePath());
        } else {
            paths.add(fileProperty);
        }
        return super.visitFile(file, attrs);
    }
}
