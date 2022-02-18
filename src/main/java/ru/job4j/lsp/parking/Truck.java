package ru.job4j.lsp.parking;

public class Truck extends Car {
    public Truck(String brand, int size, int productionYear) {
        super(brand, size, productionYear);
    }

    @Override
    protected void validate(int size) {
        if (size < 2) {
            throw new IllegalArgumentException();
        }
    }
}
