package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, List<Path>> files = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(attrs.size(), file.toFile().getName());
        if (files.containsKey(fileProperty)) {
            files.get(fileProperty).add(file);
        } else {
            List<Path> paths = new ArrayList<>();
            paths.add(file);
            files.put(fileProperty, paths);
        }
        return super.visitFile(file, attrs);
    }

    public void getDuplicates() {
        files.entrySet().stream()
                .filter(a -> a.getValue().size() > 1)
                .forEach(a -> {
                    System.out.println(a.getKey());
                    System.out.println(a.getValue());
                });
    }
}
