package ru.job4j.lsp.parking;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@Ignore
public class MyParkingTest {

    @Test
    public void whenParkTwoCarsAndOneTruckThenTrue() {
        MyParking parking = new MyParking(2, 1);
        PassengerCar firstCar = new PassengerCar("Toyota", 2010);
        PassengerCar secondCar = new PassengerCar("BMW", 2010);
        Truck truck = new Truck("Lada", 10, 2000);
        assertTrue(parking.park(firstCar));
        assertTrue(parking.park(secondCar));
        assertTrue(parking.park(truck));
    }

    @Test
    public void whenParkTruckWithSize2AtTwoCarPlacesThenTrue() {
        MyParking parking = new MyParking(2, 0);
        Truck truck = new Truck("Lada", 2, 2000);
        assertTrue(parking.park(truck));
    }

    @Test
    public void whenParkTruckWithSize2AtOneCarPlaceThenFalse() {
        MyParking parking = new MyParking(1, 0);
        Truck truck = new Truck("Lada", 2, 2000);
        assertFalse(parking.park(truck));
    }

    @Test
    public void whenCarsCountAtParkingIsTwo() {
        MyParking parking = new MyParking(1, 1);
        PassengerCar car = new PassengerCar("Toyota", 2010);
        Truck firstTruck = new Truck("Lada", 2, 2000);
        Truck secondTruck = new Truck("Volvo", 1, 2000);
        parking.park(car);
        parking.park(firstTruck);
        parking.park(secondTruck);
        assertThat(parking.getAll().size(), is(2));
    }

    @Test
    public void whenCarsCountAtParkingWasTwoAndFreeUpPlaceThenCountIsOne() {
        MyParking parking = new MyParking(1, 1);
        PassengerCar car = new PassengerCar("Toyota", 2010);
        Truck truck = new Truck("Lada", 2, 2000);
        parking.park(car);
        parking.park(truck);
        assertThat(parking.getAll().size(), is(2));
        parking.freeUp(truck);
        assertThat(parking.getAll().size(), is(1));
    }
}