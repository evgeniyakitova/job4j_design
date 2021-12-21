package ru.job4j.serialization.json;

import java.util.Arrays;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {
    private String name;
    private boolean isMarried;
    private int age;
    private Contact contact;
    @XmlElementWrapper
    @XmlElement(name = "child")
    private String[] children;

    public Person() {

    }

    public Person(String name, boolean isMarried, int age, Contact contact, String[] children) {
        this.name = name;
        this.isMarried = isMarried;
        this.age = age;
        this.contact = contact;
        this.children = children;
    }

    @Override
    public String toString() {
        return "Person{"
                + "name=" + name
                + "isMarried=" + isMarried
                + ", age=" + age
                + ", contact=" + contact
                + ", children=" + Arrays.toString(children)
                + '}';
    }
}
