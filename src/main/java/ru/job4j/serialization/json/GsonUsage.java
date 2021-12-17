package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUsage {
    public static void main(String[] args) {
        Contact contact = new Contact("+7 908 345 67 89");
        String[] children = {"Katya", "Zhenya", "Dima"};
        Person person = new Person("Elena", true, 35, contact, children);
        Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(person));
        String personJson =
                "{"     + "\"name\":\"Elena\", "
                        + "\"isMarried\":true,"
                        + "\"age\":35,"
                        + "\"contact\":"
                        + "{"
                        + "\"phone\":\"+7 908 345 67 89\""
                        + "},"
                        + "\"children\":"
                        + "[\"Katya\",\"Zhenya\",\"Dima\"]"
                        + "}";
        Person desPerson = gson.fromJson(personJson, Person.class);
        System.out.println(desPerson);
    }
}
