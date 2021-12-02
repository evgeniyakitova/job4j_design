package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> strings = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            String str = in.readLine();
            while (str != null) {
                String[] arr = str.split(" ");
                if (arr[arr.length - 2].equals("404")) {
                    strings.add(str);
                }
                str = in.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strings;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        log.forEach(System.out::println);
    }
}
