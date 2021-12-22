package ru.job4j.io.find;

import ru.job4j.io.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;
import java.util.List;
import java.util.function.Predicate;

public class Finder {

    public List<Path> findFiles(String type, String name, Path start) {
        PredicateSelector selector = PredicateSelector.init();
        Predicate<Path> predicate = selector.selectPredicate(type, name);
        return Search.search(start, predicate);
    }

    public void writeToFile(List<Path> files, String target) {
        try (PrintWriter writer = new PrintWriter(target)) {
            files.forEach(writer::println);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 4) {
            throw new IllegalArgumentException("Wrong number of arguments");
        }
        ArgsName arg = ArgsName.of(args);
        Path start = Path.of(arg.get("d"));
        if (!Files.isDirectory(start)) {
            throw new IOException("Invalid directory name for -d");
        }
        Finder finder = new Finder();
        List<Path> files = finder.findFiles(arg.get("t"), arg.get("n"), start);
        finder.writeToFile(files, arg.get("o"));
    }
}
