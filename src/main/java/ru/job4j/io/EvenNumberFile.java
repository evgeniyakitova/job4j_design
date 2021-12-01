package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        StringBuilder text = new StringBuilder();
        try (FileInputStream in = new FileInputStream("even.txt")) {
            int ch = in.read();
            while (ch != -1) {
                text.append((char) ch);
                ch = in.read();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] lines = text.toString().split(System.lineSeparator());
        for (String line : lines) {
            boolean rsl = Integer.parseInt(line) % 2 == 0;
            System.out.println(line + "-" + rsl);
        }
    }
}
