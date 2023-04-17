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
                        String[] words = a.split("=", 2);
                        if (!a.contains("=")
                                || words[0].equals("")
                                || words[1].equals("")) {
                            throw new IllegalArgumentException("All lines must be of the form: key=value");
                        }
                        values.put(words[0], words[1]);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.getOrDefault(key, "There is no such key");
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
