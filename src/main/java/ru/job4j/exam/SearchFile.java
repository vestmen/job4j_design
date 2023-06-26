package ru.job4j.exam;

import ru.job4j.io.ArgsName;
import ru.job4j.io.Search;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class SearchFile {
    public static void main(String[] args) throws IOException {
        validate(args);
        ArgsName argsName = ArgsName.of(args);
        Path path = Path.of(argsName.get("d"));
        write(pathList(path, argsName), argsName);
    }

    public static List<Path> pathList(Path path, ArgsName argsName) throws IOException {
        return Search.search(path, getSearchCondition(argsName.get("t"), argsName.get("n")));
    }

    public static Predicate<Path> getSearchCondition(String t, String n) {
        Predicate<Path> rsl = null;
        if ("name".equals(t)) {
            rsl = p -> p.toFile().getName().equals(n);
        }
        if ("mask".equals(t)) {
            String reg = n.replace("?", ".?")
                    .replace("*", ".*?");
            Pattern pattern = Pattern.compile(reg);
            rsl = p -> pattern.matcher(p.toFile().getName()).find();
        }
        if ("regex".equals(t)) {
            Pattern pattern = Pattern.compile(n);
            rsl = p -> pattern.matcher(p.toFile().getName()).find();
        }
        return rsl;
    }

    public static void write(List<Path> paths, ArgsName argsName) {
        try (PrintWriter out = new PrintWriter(new FileWriter(argsName.get("o")))) {
            paths.stream()
                    .map(Path::toAbsolutePath)
                    .forEach(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void validate(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        File file = new File(argsName.get("d"));
        String t = argsName.get("t");
        if (args.length != 4) {
            throw new IllegalArgumentException("The launch requires 4 parameters");
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory: %s", file.getAbsoluteFile()));
        }
        if (!("mask".equals(t) || "name".equals(t) || "regex".equals(t))) {
            throw new IllegalArgumentException(String.format("Not mask, name or regex: %s", t));
        }
        if (!argsName.get("o").contains(".")) {
            throw new IllegalArgumentException(String.format("Not file: %s", argsName.get("o")));
        }
    }
}
