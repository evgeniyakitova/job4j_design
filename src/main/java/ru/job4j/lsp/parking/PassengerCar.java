package ru.job4j.lsp.parking;

public class PassengerCar extends Car {
    public static final int SIZE = 1;

    public PassengerCar(String brand, int productionYear) {
        super(brand, SIZE, productionYear);
    }

    @Override
    protected void validate(int size) {
        if (size != 1) {
            throw new IllegalArgumentException();
        }
    }
}
