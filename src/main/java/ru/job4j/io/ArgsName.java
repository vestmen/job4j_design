package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(String.format("This key: '%s' is missing", key));
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        Arrays.stream(args)
                .filter(ArgsName::validate)
                .map(s -> s.substring(1))
                .map(a -> a.split("=", 2))
                .forEach(m -> values.put(m[0], m[1]));
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static boolean validate(String arg) {
        if (!arg.startsWith("-")) {
            throw new IllegalArgumentException(
                    String.format("Error: This argument '%s' does not start with a '-' character", arg));
        }
        String a = arg.substring(1);
        if (!a.contains("=")) {
            throw new IllegalArgumentException(
                    String.format("Error: This argument '%s' does not contain an equal sign", arg));
        }
        String[] words = a.split("=", 2);
        if (words[0].isEmpty()) {
            throw new IllegalArgumentException(String.format("Error: This argument '%s' does not contain a key", arg));
        }
        if (words[1].isEmpty()) {
            throw new IllegalArgumentException(String.format("Error: This argument '%s' does not contain a value", arg));
        }
        return true;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
