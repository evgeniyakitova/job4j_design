package ru.job4j.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class MyParking implements Parking {
    private int passengerCars;

    private int trucks;

    private List<Car> cars = new ArrayList<>();

    public MyParking(int passengerCarsCount, int trucksCount) {
        this.passengerCars = passengerCarsCount;
        this.trucks = trucksCount;
    }

    @Override
    public boolean park(Car car) {
        return false;
    }

    @Override
    public void freeUp(Car car) {

    }

    @Override
    public boolean isAvailable(Car car) {
        return false;
    }

    @Override
    public List<Car> getAll() {
        return null;
    }
}
