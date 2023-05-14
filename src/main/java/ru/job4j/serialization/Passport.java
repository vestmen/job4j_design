package ru.job4j.serialization;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "passport")
public class Passport {
    @XmlAttribute
    private String id;

    public Passport() {
    }

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
