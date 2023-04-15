package ru.job4j.array;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.job4j.array.ArrayEx.*;

class ArrayExTest {

    @Test
    void arrayN2Complexity() {
        int[] input = new int[]{2, 4, 5, 3, 3, 5};
        List<Integer> expected = List.of(2, 4, 4, 2, 3, 3, 3, 3);
        List<Integer> rsl = arrayN2(input, 6);
        assertEquals(rsl, expected);
    }

    @Test
    void arrayNLogNComplexity() {
        int[] input = new int[]{2, 4, 5, 3, 3, 5};
        List<Integer> expected = List.of(2, 4, 3, 3);
        List<Integer> rsl = arrayNLogN(input, 6);
        assertEquals(rsl, expected);
    }

    @Test
    void arrayNComplexity() {
        int[] input = new int[]{2, 5, 3, 1, 8, 9, 2, 4, 0, 13, -5};
        List<Integer> expected = List.of(3, 5, 0, 8, -5, 13);
        List<Integer> rsl = arrayN(input, 8);
        assertEquals(rsl, expected);
    }
}