package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader in = new BufferedReader(new FileReader(this.path))) {
            in.lines()
                    .filter(a -> !a.isEmpty() && !a.contains("#"))
                    .forEach(a -> {
                        if (!a.contains("=")) {
                            throw new IllegalArgumentException(
                                    String.format("Line %s must be of the form: key=value", a));
                        }
                        String[] words = a.split("=", 2);
                        if (words[0].isEmpty() || words[1].isEmpty()) {
                            throw new IllegalArgumentException(
                                    String.format("Line %s must be of the form: key=value", a));
                        }
                        values.put(words[0], words[1]);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.getOrDefault(key,
                String.format("There is no such key: %s", key));
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
    }
}
