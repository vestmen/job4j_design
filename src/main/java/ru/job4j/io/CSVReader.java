package ru.job4j.io;

import java.io.*;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        List<String> lines = new ArrayList<>();
        var scanner = new Scanner(new FileInputStream(argsName.get("path"))).useDelimiter("\r\n");
        while (scanner.hasNext()) {
            lines.add(scanner.next());
        }
        List<Integer> index = getIndex(lines.get(0), argsName.get("delimiter"), argsName.get("filter"));
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(argsName.get("out"))))) {
            for (String line : lines) {
                String[] parts = line.split(argsName.get("delimiter"));
                for (int j = 0; j < index.size(); j++) {
                    out.append(parts[index.get(j)]).append(j == index.size() - 1
                            ? System.lineSeparator()
                            : argsName.get("delimiter"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public static void main(String[] args) throws Exception {
        /* здесь добавьте валидацию принятых параметров*/
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}
