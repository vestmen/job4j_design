package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validate(args);
        Path start = Paths.get(".");
        search(start, p -> p.toFile().getName().endsWith(".xml")).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void validate(String[] args) {
        File file = new File(args[0]);
        if (args.length != 2) {
            throw new IllegalArgumentException("The launch requires 2 parameters");
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        if (!args[1].startsWith(".") || args[1].length() < 2) {
            throw new IllegalArgumentException(String.format("Not an extension %s", args[1]));
        }
    }
}
