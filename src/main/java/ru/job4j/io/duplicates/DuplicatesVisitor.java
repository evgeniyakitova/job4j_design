package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Collectors;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, List<Path>> files = new HashMap<>();

    public List<Path> getDuplicates() {
        return files.values()
                .stream()
                .filter(list -> list.size() > 1)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty newFile = new FileProperty(attrs.size(), file.toFile().getName());
        if (files.containsKey(newFile)) {
           files.get(newFile).add(file.toAbsolutePath());
        } else {
            List<Path> listOfPaths = new ArrayList<>();
            listOfPaths.add(file.toAbsolutePath());
            files.put(newFile, listOfPaths);
        }
        return FileVisitResult.CONTINUE;
    }
}
