package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONJavaUsage {
    public static void main(String[] args) {
        Contact contact = new Contact("+7 908 345 67 89");
        String[] children = {"Katya", "Zhenya", "Dima"};
        Person person = new Person("Elena", true, 35, contact, children);
        JSONObject jsonObject = new JSONObject(person);
        System.out.println(jsonObject);
        JSONObject jsonContact = new JSONObject("{\"phone\":\"+7(908)345-67-89\"}");
        JSONArray jsonArray = new JSONArray(children);
        JSONObject personJson = new JSONObject();
        personJson.put("name", person.getName());
        personJson.put("age", person.getAge());
        personJson.put("isMarried", person.getIsMarried());
        personJson.put("contact", jsonContact);
        personJson.put("children", jsonArray);
        System.out.println(personJson);

    }
}
