package ru.job4j.lsp;

import java.time.LocalDateTime;

public class Milk extends Food {
    public Milk(String name, LocalDateTime expiryDate, LocalDateTime createDate, double price) {
        super(name, expiryDate, createDate, price);
    }
}
