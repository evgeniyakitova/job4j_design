package ru.job4j.io.find;

import java.nio.file.Path;
import java.util.function.Predicate;

public class NameFilter implements Filter {
    @Override
    public Predicate<Path> getPredicate(String name) {
        return path -> name.equals(path.getFileName().toString());
    }
}
