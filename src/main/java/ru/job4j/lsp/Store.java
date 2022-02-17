package ru.job4j.lsp;

import java.time.LocalDateTime;
import java.util.List;

import  static java.time.temporal.ChronoUnit.MILLIS;

public interface Store {
    boolean add(Food food);

    void delete(Food food);

    List<Food> getAll();

    void clear();

    default double getSpoilRate(Food product) {
        LocalDateTime created = product.getCreateDate();
        return (double) MILLIS.between(LocalDateTime.now(), created) / MILLIS.between(product.getExpiryDate(), created) * 100;
    }
}
