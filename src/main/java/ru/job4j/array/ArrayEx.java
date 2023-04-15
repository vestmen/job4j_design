package ru.job4j.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArrayEx {

    public static List<Integer> arrayN2(int[] array, int sum) {
        List<Integer> rsl = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (i != j && array[i] + array[j] == sum) {
                    rsl.add(array[i]);
                    rsl.add(array[j]);
                }
            }
        }
        return rsl;
    }

    public static List<Integer> arrayNLogN(int[] array, int sum) {
        List<Integer> rsl = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] + array[j] == sum) {
                    rsl.add(array[i]);
                    rsl.add(array[j]);
                }
            }
        }
        return rsl;
    }

    public static List<Integer> arrayN(int[] array, int sum) {
        List<Integer> rsl = new ArrayList<>();
        Map<Integer, Integer> pairs = new HashMap<>();
        for (int j : array) {
            if (pairs.containsKey(j)) {
                if (pairs.get(j) != null) {
                    rsl.add(j);
                    rsl.add(sum - j);
                }
                pairs.put(sum - j, null);
            } else if (!pairs.containsValue(j)) {
                pairs.put(sum - j, j);
            }
        }
        return rsl;
    }
}
