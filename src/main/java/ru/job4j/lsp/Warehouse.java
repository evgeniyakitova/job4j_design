package ru.job4j.lsp;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Store {
    private final List<Food> food = new ArrayList<>();

    public boolean add(Food product) {
        boolean result = accept(product);
        if (result) {
            food.add(product);
        }
        return result;
    }

    public boolean accept(Food product) {
        return getSpoilRate(product) < 25;
    }

    public void delete(Food product) {
        food.remove(product);
    }

    public List<Food> getAll() {
        return food;
    }

    public void clear() {
        food.clear();
    }
}
