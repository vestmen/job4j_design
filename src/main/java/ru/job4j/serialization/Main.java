package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Student student = new Student(false, "Ivan Ivanov", 18, new Passport("3814 256487"),
                new String[]{"Mathematics", "Literature", "Probability theory"});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(student));

        final String studentJson =
                "{"
                        + "\"goldMedal\":false,"
                        + "\"name\":\"Ivan Ivanov\","
                        + "\"age\":18,"
                        + "\"passport\":"
                        + "{"
                        + "\"id\":\"3814 256487\""
                        + "},"
                        + "\"subjects\":"
                        + "[\"mathematics\", \"literature\", \"probability theory\"]"
                        + "}";

        final Student studentMod = gson.fromJson(studentJson, Student.class);
        System.out.println(studentMod);
    }
}
