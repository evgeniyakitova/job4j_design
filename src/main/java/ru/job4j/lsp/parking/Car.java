package ru.job4j.lsp.parking;

public abstract class Car {
    private String brand;
    private int size;
    private int productionYear;

    public Car(String brand, int size, int productionYear) {
        this.brand = brand;
        this.productionYear = productionYear;
        validate(size);
        this.size = size;
    }

    protected abstract void validate(int size);

    public String getBrand() {
        return brand;
    }

    public int getSize() {
        return size;
    }

    public int getProductionYear() {
        return productionYear;
    }
}
