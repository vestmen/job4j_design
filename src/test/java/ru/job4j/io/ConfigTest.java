package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
        assertThat(config.value("surname")).isEqualTo("There is no such key: surname");
    }

    @Test
    void whenPairWith2() {
        String path = "./data/pair.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("nameAndAge")).isEqualTo("Alex Nemtsev=27");
    }

    @Test
    void whenCommentsWithEmptyStrings() {
        String path = "./data/comments_with_empty_strings.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("#Comment1")).isEqualTo("There is no such key: #Comment1");
        assertThat(config.value("#Comment2")).isEqualTo("There is no such key: #Comment2");
        assertThat(config.value("#Empty Strings")).isEqualTo("There is no such key: #Empty Strings");
        assertThat(config.value(null)).isEqualTo("There is no such key: null");
    }

    @Test
    void whenException1() throws IllegalArgumentException {
        String path = "./data/exception1.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Line name= must be of the form: key=value");
    }

    @Test
    void whenException2() throws IllegalArgumentException {
        String path = "./data/exception2.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Line =Alex must be of the form: key=value");
    }

    @Test
    void whenException3() throws IllegalArgumentException {
        String path = "./data/exception3.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Line = must be of the form: key=value");
    }

    @Test
    void whenException4() throws IllegalArgumentException {
        String path = "./data/exception4.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Line nameAlex must be of the form: key=value");
    }
}