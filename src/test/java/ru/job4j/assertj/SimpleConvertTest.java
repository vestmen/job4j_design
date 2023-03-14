package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five", "second", "six");
        assertThat(list).hasSize(7)
                .contains("second")
                .allMatch(e -> e.length() > 2)
                .containsAnyOf("zero", "second", "7")
                .last().isEqualTo("six");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("second", "three", "four", "five", "second", "six", "a", "");
        assertThat(set).filteredOn(a -> a.length() > 1)
                .hasSize(5)
                .contains("second")
                .allMatch(e -> e.length() < 20)
                .containsAnyOf("zero", "second", "7")
                .first().isNotNull();
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "three", "four", "five", "second", "six");
        assertThat(map).hasSize(6)
                .containsKeys("first", "five", "second")
                .containsValues(1, 2, 3)
                .doesNotContainKey("seven")
                .doesNotContainValue(5)
                .containsEntry("first", 0)
                .doesNotContainEntry("second", 5);
    }
}