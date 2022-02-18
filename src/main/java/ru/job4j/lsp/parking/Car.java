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

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        validate(size);
        this.size = size;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }
}
