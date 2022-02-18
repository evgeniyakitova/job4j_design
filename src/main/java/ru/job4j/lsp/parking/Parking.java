package ru.job4j.lsp.parking;

import java.util.List;

public interface Parking {
    boolean park(Car car);

    void freeUp(Car car);

    boolean isAvailable(Car car);

    List<Car> getAll();
}
