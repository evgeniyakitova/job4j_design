package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final List<String> log = new ArrayList<>();

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> phrases = readPhrases();
        log.add("Добро пожаловать в чат!");
        System.out.println(log.get(0));
        Scanner scanner = new Scanner(System.in);
        String select = scanner.nextLine();
        log.add(select);
        boolean isWork = true;
        while (!OUT.equals(select)) {
            if (STOP.equals(select) && isWork) {
                isWork = false;
            }
            if (CONTINUE.equals(select) && !isWork) {
                isWork = true;
            }
            if (isWork) {
                int index = (int) (Math.random() * phrases.size());
                System.out.println(phrases.get(index));
                log.add(phrases.get(index));
            }
            select = scanner.nextLine();
            log.add(select);
        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            in.lines().forEach(phrases::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter out = new PrintWriter(path, StandardCharsets.UTF_8)) {
            log.forEach(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("data/botLog.txt", "data/botAnswers.txt");
        cc.run();
    }
}
