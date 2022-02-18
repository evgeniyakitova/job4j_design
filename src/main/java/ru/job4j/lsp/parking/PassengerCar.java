package ru.job4j.lsp.parking;

public class PassengerCar extends Car {
    public PassengerCar(String brand, int size, int productionYear) {
        super(brand, size, productionYear);
    }

    @Override
    protected void validate(int size) {
        if (size != 1) {
            throw new IllegalArgumentException();
        }
    }
}
