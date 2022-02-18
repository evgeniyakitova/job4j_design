package ru.job4j.lsp;

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
}
