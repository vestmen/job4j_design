package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.function.Predicate;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 0, 0);
        assertThat(input).hasSize(5).containsSequence(0, 1, 2, 3, 4);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 4, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 3, 5);
        assertThat(input).hasSize(5).containsSequence(1, 2, 3, 4, 5);
    }

    @Test
    void whenReplaceIf() {
        Predicate<Integer> filter = a -> a % 2 != 0;
        ListUtils.replaceIf(input, filter, 0);
        assertThat(input).hasSize(4).containsSequence(0, 2, 0, 4);
    }

    @Test
    void whenRemoveAll() {
        List<Integer> remove = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.removeAll(input, remove);
        assertThat(input).hasSize(2).containsSequence(2, 4);
    }

    @Test
    void whenRemoveIf() {
        Predicate<Integer> filter = a -> a % 2 == 0;
        ListUtils.removeIf(input, filter);
        assertThat(input).hasSize(2).containsSequence(1, 3);
    }
}