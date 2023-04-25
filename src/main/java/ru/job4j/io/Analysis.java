package ru.job4j.io;

import java.io.*;

public class Analysis {
    private boolean check = true;

    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
                in.lines()
                        .forEach(a -> {
                            String[] words = a.split(" ");
                            if ((check == (a.contains("400") || a.contains("500")))) {
                                out.append(words[1]).append(";").append(check ? "" : System.lineSeparator());
                                check = !check;
                            }
                        });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
