package ru.job4j.kiss.fool;

import java.util.Scanner;

public class Fool {
    public static String answer(int startAt) {
        StringBuilder stringBuilder = new StringBuilder();
        if (startAt % 3 == 0) {
            stringBuilder.append("Fizz");
        }
        if (startAt % 5 == 0) {
            stringBuilder.append("Buzz");
        }
        return stringBuilder.isEmpty() ? String.valueOf(startAt) : String.valueOf(stringBuilder);
    }

    public static void main(String[] args) {
        System.out.println("Игра FizzBuzz.");
        var startAt = 1;
        var io = new Scanner(System.in);
        while (startAt < 100) {
            System.out.println(answer(startAt));
            startAt++;
            var answer = io.nextLine();
            if (!answer(startAt).equals(answer)) {
                System.out.println("Ошибка. Начинай снова.");
                startAt = 0;
            }
            startAt++;
        }
    }
}
