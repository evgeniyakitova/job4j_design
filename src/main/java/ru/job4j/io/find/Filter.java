package ru.job4j.io.find;

import java.nio.file.Path;
import java.util.function.Predicate;

public interface Filter {
    Predicate<Path> getPredicate(String name);
}
