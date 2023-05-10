package ru.job4j.io;

import java.io.*;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        String path = argsName.get("path");
        String delimiter = argsName.get("delimiter");
        String out = argsName.get("out");
        String filter = argsName.get("filter");
        validate(path, delimiter, out, filter);
        ArrayList<String> lines = new ArrayList<>();
        try (var scanner = new Scanner(new FileInputStream(path)).useDelimiter(System.lineSeparator())) {
            while (scanner.hasNext()) {
                lines.add(scanner.next());
            }
        }
        String rsl = result(lines, delimiter, filter);
        if ("stdout".equals(out)) {
            System.out.println(rsl);
        } else {
            try (PrintWriter printWriter = new PrintWriter(new BufferedOutputStream(new FileOutputStream(out)))) {
                printWriter.print(rsl);
            } catch (IOException e) {
            e.printStackTrace();
            }
        }
    }

    public static String result(ArrayList<String> lines, String delimiter, String filter) {
        List<Integer> index = getIndex(lines.get(0), delimiter, filter);
        StringBuilder out = new StringBuilder();
        for (String line : lines) {
            String[] parts = line.split(delimiter);
            for (int j = 0; j < index.size(); j++) {
                out.append(parts[index.get(j)]).append(j == index.size() - 1
                        ? System.lineSeparator()
                        : delimiter);
            }
        }
        return out.toString();
    }

    public static ArrayList<Integer> getIndex(String headString, String delimiter, String filter) {
        String[] filters = filter.split(",");
        String[] head = headString.split(delimiter);
        ArrayList<Integer> rsl = new ArrayList<>();
        for (String s : filters) {
            for (int j = 0; j < head.length; j++) {
                if (s.equals(head[j])) {
                    rsl.add(j);
                }
            }
        }
        return rsl;
    }

    private static void validate(String path, String delimiter, String out, String filter) {
        if (path  == null || delimiter == null
                || out == null || filter == null) {
            throw new IllegalArgumentException("The args must contain the path to the file \"path\", the delimiter \"delimiter\", the data receiver \"out\" and the filter by columns \"filter\"");
        }
        File file = new File(path);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!out.contains(".") && !out.equals("stdout")) {
            throw new IllegalArgumentException(String.format("The \"out\" key %s has only two valid values: stdout or the path to the file", out));
        }
        if (delimiter.length() != 1) {
            throw new IllegalArgumentException(String.format("Error: This argument '%s' is not a delimiter", delimiter));
        }

    }

    public static void main(String[] args) throws Exception {
        if (args.length != 4) {
            throw new IllegalArgumentException(String.format("Must be 4 arguments, but was:  %d", args.length));
        }
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}
