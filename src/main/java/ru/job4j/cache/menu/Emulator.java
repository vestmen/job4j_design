package ru.job4j.cache.menu;

import ru.job4j.cache.DirFileCache;

import java.io.IOException;
import java.util.Scanner;

public class Emulator {
    public static final String ENTER_THE_DIR = "Укажите директорию: ";
    public static final String MENU = "Введите имя файла для загрузки или exit для выхода: ";
    public static final String EXIT = "exit";

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(ENTER_THE_DIR);
        String dir = scanner.nextLine();
        DirFileCache dirFileCache = new DirFileCache(dir);
        System.out.println(MENU);
        String rsl = scanner.nextLine();
        while (!EXIT.equals(rsl)) {
            try {
                System.out.println(dirFileCache.get(rsl));
            } catch (IOException e) {
                System.out.println("Файл не найден");
            }
            System.out.println(MENU);
            rsl = scanner.nextLine();
        }
        System.out.println("Конец");
    }

    public static void main(String[] args) throws IOException {
        Emulator emulator = new Emulator();
        emulator.run();
    }
}
