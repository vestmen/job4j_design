package ru.job4j.serialization;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
public class Student {
    @XmlAttribute
    private boolean goldMedal;
    @XmlAttribute
    private String name;
    @XmlAttribute
    private int age;

    private Passport passport;

    private String[] subjects;

    public Student() {
    }

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

    public static void main(String[] args) throws JAXBException {

        final Student student = new Student(false, "Ivan Ivanov", 18, new Passport("3814 256487"),
                new String[]{"Mathematics", "Literature", "Probability theory"});

        JAXBContext context = JAXBContext.newInstance(Student.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(student, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
