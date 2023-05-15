package ru.job4j.serialization.pojo;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.job4j.serialization.Passport;
import ru.job4j.serialization.Student;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        JSONObject jsonPassport = new JSONObject("{\"id\":\"3814 256487\"}");

        List<String> list = new ArrayList<>();
        list.add("Mathematics");
        list.add("Literature");
        list.add("Probability theory");
        JSONArray jsonSubjects = new JSONArray(list);

        final Student student = new Student(false, "Ivan Ivanov", 18, new Passport("3814 256487"),
                new String[]{"Mathematics", "Literature", "Probability theory"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("goldMedal", student.isGoldMedal());
        jsonObject.put("name", student.getName());
        jsonObject.put("age", student.getAge());
        jsonObject.put("passport", jsonPassport);
        jsonObject.put("subjects", jsonSubjects);

        System.out.println(jsonObject);
        System.out.println(new JSONObject(student));
        }
}
