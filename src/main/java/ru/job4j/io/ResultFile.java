package ru.job4j.io;

import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class ResultFile {
    public static void main(String[] args) {
        int[][] array = multiple(3);
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            for (int[] arr : array) {
                out.write(Arrays.toString(arr).getBytes(StandardCharsets.UTF_8));
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static int[][] multiple(int size) {
        int[][] array = new int[size][size];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = (i + 1) * (j + 1);
            }
        }
        return array;
    }
}
