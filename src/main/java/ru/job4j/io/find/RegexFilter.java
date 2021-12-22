package ru.job4j.io.find;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class RegexFilter implements Filter {
    @Override
    public Predicate<Path> getPredicate(String name) {
        return path -> Pattern.matches(name, path.getFileName().toString());
    }
}
