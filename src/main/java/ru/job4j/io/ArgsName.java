package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        String result = values.get(key);
        if (result == null) {
            throw new IllegalArgumentException();
        }
        return result;
    }

    private void parse(String[] args) {
        for (String str : args) {
            if (!str.isEmpty()) {
                String[] strings = stringValidation(str);
                values.put(strings[0], strings[1]);
            }
        }
    }

    private String[]  stringValidation(String str) {
        String[] strings = str.substring(1).split("=");
        if (strings.length != 2 || strings[0].isEmpty() || strings[1].isEmpty()) {
            throw new IllegalArgumentException();
        }
        return strings;
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("There are no arguments");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
