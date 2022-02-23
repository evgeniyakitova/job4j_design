package ru.job4j.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class MyParking implements Parking {
    private int carPlaces;

    private int truckPlaces;

    private List<Car> passengerCars = new ArrayList<>();

    private List<Car> trucks = new ArrayList<>();

    public MyParking(int carPlaces, int truckPlaces) {
        this.carPlaces = carPlaces;
        this.truckPlaces = truckPlaces;
    }

    @Override
    public boolean park(Car car) {
        boolean result = isAvailable(car);
        if (result) {
            if (car.getSize() == PassengerCar.SIZE || truckPlaces == 0) {
                passengerCars.add(car);
                carPlaces = carPlaces - car.getSize();
            } else {
                trucks.add(car);
                truckPlaces--;
            }
        }
        return result;
    }

    @Override
    public void freeUp(Car car) {
        if (passengerCars.remove(car)) {
            carPlaces = carPlaces + car.getSize();
        } else if (trucks.remove(car)) {
            truckPlaces++;
        }
    }

    @Override
    public boolean isAvailable(Car car) {
        int size = car.getSize();
        boolean result;
        if (size == PassengerCar.SIZE) {
            result = carPlaces > 0;
        } else {
            result = truckPlaces > 0 || carPlaces >= size;
        }
        return result;
    }

    @Override
    public List<Car> getAll() {
        List<Car> result = new ArrayList<>();
        result.addAll(passengerCars);
        result.addAll(trucks);
        return result;
    }
}
