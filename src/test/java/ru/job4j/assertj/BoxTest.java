package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isNotEmpty() {
        Box box = new Box(4, 10);
        String name = box.whatsThis();
        assertThat(name).isNotEmpty()
                .startsWith("T");
    }

    @Test
    void isUnknownObject() {
        Box box = new Box(3, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
    }

    @Test
    void WhenNumberOfVertex3ThenLessThan0() {
        Box box = new Box(3, 10);
        int rsl = box.getNumberOfVertices();
        assertThat(rsl).isLessThan(0);
    }

    @Test
    void WhenNumberOfVertex4ThenPositive() {
        Box box = new Box(4, 10);
        int rsl = box.getNumberOfVertices();
        assertThat(rsl).isPositive();
    }

    @Test
    void IsNotExist() {
        Box box = new Box(3, 10);
        boolean rsl = box.isExist();
        assertThat(rsl).isFalse();
    }

    @Test
    void IsExist() {
        Box box = new Box(4, 10);
        boolean rsl = box.isExist();
        assertThat(rsl).isTrue();
    }

    @Test
    void WhenVertex4Edge10Then() {
        Box box = new Box(4, 10);
        double rsl = box.getArea();
        assertThat(rsl).isEqualTo(173.21d, withPrecision(0.006d));
    }

    @Test
    void WhenVertex3Edge10Then0() {
        Box box = new Box(3, 10);
        double rsl = box.getArea();
        assertThat(rsl).isEqualTo(0);
    }
}