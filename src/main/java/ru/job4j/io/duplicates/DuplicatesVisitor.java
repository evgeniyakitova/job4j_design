package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Set<FileProperty> files = new HashSet<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        long size = file.toFile().length();
        String name = file.toFile().getName();
        if (!files.add(new FileProperty(size, name))) {
            System.out.println(file.toAbsolutePath());
        }
        return FileVisitResult.CONTINUE;
    }
}
