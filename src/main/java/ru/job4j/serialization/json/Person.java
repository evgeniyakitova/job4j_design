package ru.job4j.serialization.json;

import java.util.Arrays;

public class Person {
    private final String name;
    private final boolean isMarried;
    private final int age;
    private final Contact contact;
    private final String[] children;

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
