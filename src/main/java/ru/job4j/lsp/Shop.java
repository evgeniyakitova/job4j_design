package ru.job4j.lsp;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Store {
    private final List<Food> food = new ArrayList<>();

    public boolean add(Food product) {
        double rate = getSpoilRate(product);
        boolean result = rate >= 25 && rate < 100;
        if (result) {
            if (rate > 75) {
                product.setDiscount(0.5);
            }
            food.add(product);
        }
        return result;
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
