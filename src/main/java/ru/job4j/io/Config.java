package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        Map<String, String> tmp = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String str = br.readLine();
            while (str != null) {
                if (!str.isEmpty() && !str.startsWith("#") && stringValidation(str)) {
                    String[] arr = str.split("=");
                    tmp.put(arr[0], arr[1]);
                }
                str = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        values.putAll(tmp);
    }

    public String value(String key) {
        return values.get(key);
    }

    private boolean stringValidation(String str) {
        String[] arr = str.split("=");
        if (arr.length != 2 || arr[0].isEmpty() || arr[1].isEmpty()) {
            throw new IllegalArgumentException();
        }
        return true;
    }
}
