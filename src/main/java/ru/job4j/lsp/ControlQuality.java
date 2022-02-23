package ru.job4j.lsp;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private final List<Store> storages;

    public ControlQuality(List<Store> storages) {
        this.storages = storages;
    }

    public void addFood(Food product) {
        for (Store store : storages) {
            if (store.accept(product)) {
                store.add(product);
                break;
            }
        }
    }

    public void resort() {
        List<Food> products = new ArrayList<>();
        for (Store store : storages) {
            products.addAll(store.getAll());
            store.clear();
        }
        for (Food product : products) {
            addFood(product);
        }
    }
}
