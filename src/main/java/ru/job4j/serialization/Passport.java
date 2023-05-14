package ru.job4j.serialization;

public class Passport {
    private final String id;

    public Passport(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Passport{"
                + "id='" + id + '\''
                + '}';
    }
}
