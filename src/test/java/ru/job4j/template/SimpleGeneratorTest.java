package ru.job4j.template;

import java.util.Map;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@Disabled("Тесты отключены. Удалить аннотацию после реализации всех методов по заданию.")
class SimpleGeneratorTest {
    String string = "I am a ${name}, Who are ${subject}?";

    @Test
    void whenNumberKeys0ThenGetException() {
        SimpleGenerator simpleGenerator = new SimpleGenerator();
        Map<String, String> map = Map.of();
        assertThatThrownBy(() -> simpleGenerator.produce(string, map))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenNameAndAgeThenGetException() {
        SimpleGenerator simpleGenerator = new SimpleGenerator();
        Map<String, String> map = Map.of("name", "Petr Arsentev", "age", "30");
        assertThatThrownBy(() -> simpleGenerator.produce(string, map))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenNameAndSubjectThenCorrectString() {
        SimpleGenerator simpleGenerator = new SimpleGenerator();
        Map<String, String> map = Map.of("name", "Petr Arsentev", "subject", "you");
        String rsl = simpleGenerator.produce(string, map);
        assertThat(rsl).isEqualTo("I am a Petr Arsentev, Who are you?");
    }
}