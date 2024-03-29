package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;
    private final List<String> log = new ArrayList<>();

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        boolean stop = false;
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        List<String> phrases = readPhrases();
        String ph;
        do {
            ph = input.nextLine();
            if (!STOP.equals(ph) && !CONTINUE.equals(ph) && !OUT.equals(ph) && !stop) {
                log.add(ph);
                String answer = phrases.get(random.nextInt(readPhrases().size()));
                System.out.println(answer);
                log.add(answer);
            }
            if (STOP.equals(ph)) {
                stop = true;
                log.add(STOP);
            }
            if (CONTINUE.equals(ph)) {
                stop = false;
                log.add(CONTINUE);
            }
        }
        while (!OUT.equals(ph));
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers))) {
            in.lines().map(s -> s + System.lineSeparator()).forEach(rsl::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter out = new PrintWriter(new FileWriter(path))) {
            log.forEach(out::println);
        } catch (IOException e) {
                e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("data/dialogue.txt", "data/botAnswers.txt");
        cc.run();
    }
}
