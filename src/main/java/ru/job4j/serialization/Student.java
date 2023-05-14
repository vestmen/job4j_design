package ru.job4j.serialization;

import java.util.Arrays;

public class Student {
    private final boolean goldMedal;
    private final String name;
    private final int age;
    private final Passport passport;
    private final String[] subjects;

    public Student(boolean goldMedal, String name, int age, Passport passport, String[] subjects) {
        this.goldMedal = goldMedal;
        this.name = name;
        this.age = age;
        this.passport = passport;
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "Student{"
                + "gold medal=" + goldMedal
                + "name=" + name
                + ", age=" + age
                + ", passport=" + passport
                + ", subjects=" + Arrays.toString(subjects)
                + '}';
    }
}
