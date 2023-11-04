package ru.job4j.kiss.fool;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class FoolTest {

    @Test
    void when31520ThenFizzFizzBuzzBuzz() {
        List<Integer> list = List.of(3, 15, 20);
        List<String> rsl = new ArrayList<>(list.size());
        for (Integer i : list) {
            rsl.add(Fool.answer(i));
        }
        assertThat(rsl).containsExactly("Fizz", "FizzBuzz", "Buzz");
    }

    @Test
    void when5101530333626Then2Buzz2FizzBuzz2Fizz26() {
        List<Integer> list = List.of(5, 10, 15, 30, 33, 36, 26);
        List<String> rsl = new ArrayList<>(list.size());
        for (Integer i : list) {
            rsl.add(Fool.answer(i));
        }
        assertThat(rsl).containsExactly("Buzz", "Buzz", "FizzBuzz", "FizzBuzz", "Fizz", "Fizz", "26");
    }

    @Test
    void whenNumbersThenNumbers() {
        List<Integer> list = List.of(1, 4, 7, 8, 11, 14, 16, 17, 19, 22, 28, 37, 46, 49, 52, 56, 58, 59);
        List<String> rsl = new ArrayList<>(list.size());
        for (Integer i : list) {
            rsl.add(Fool.answer(i));
        }
        assertThat(rsl).containsExactly("1", "4", "7", "8", "11", "14", "16", "17", "19",
                "22", "28", "37", "46", "49", "52", "56", "58", "59");
    }
}