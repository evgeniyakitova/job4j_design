package ru.job4j.io.find;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class MaskFilter implements Filter {
    @Override
    public Predicate<Path> getPredicate(String name) {
        String regex = name.replace("?", ".").replace("*", ".*");
        return path -> Pattern.matches(regex, path.getFileName().toString());
    }
}
