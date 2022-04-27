package ru.job4j.ood.lsp.parking;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleParkingTest {
    private SimpleParking parking = null;

    @Before
    public void init() {
        parking = new SimpleParking(2, 1);
    }

    @Test
    public void when2CarsAnd1TruckAreAdded() {
        assertTrue(parking.add(new Car("A001AA")));
        assertTrue(parking.add(new Truck("B002BB", 3)));
        assertTrue(parking.add(new Car("C003")));
    }

    @Test
    public void when2TrucksOfSize2AreAdded() {
        parking.add(new Truck("A001AA", 2));
        assertTrue(parking.add(new Truck("B002", 2)));
    }

    @Test
    public void when3CarsAreAdded() {
        parking.add(new Car("A001AA"));
        parking.add(new Car("B002BB"));
        assertFalse(parking.add(new Car("C003C")));
    }

    @Test
    public void when1CarsAnd2TrucksAreAdded() {
        parking.add(new Car("A001AA"));
        parking.add(new Truck("B002BB", 2));
        assertFalse(parking.add(new Truck("C003CC", 2)));
    }

    @Test
    public void when2TrucksOfSize3AreAdded() {
        parking.add(new Truck("A001AA", 3));
        assertFalse(parking.add(new Truck("B002BB", 3)));
    }

    @Test
    public void whenCarIsDroppedFromTruckSpace() {
        Vehicle truck = new Truck("A001AA", 3);
        parking.add(new Car("B002BB"));
        parking.add(truck);
        assertTrue(parking.drop(truck));
    }

    @Test
    public void whenCarIsDroppedFromCarSpace() {
        Vehicle truck = new Truck("A001AA", 2);
        parking.add(new Truck("B002BB", 5));
        parking.add(truck);
        assertTrue(parking.drop(truck));
    }

    @Test
    public void whenThereIsNoSuchVehicle() {
        Vehicle truck = new Truck("A001AA", 2);
        parking.add(new Truck("B002BB", 5));
        assertFalse(parking.drop(truck));
    }

    @Test
    public void whenThereAreNoVehicles() {
        assertFalse(parking.drop(new Car("A001AA")));
    }

    @Test
    public void whenThereAreGapsBetweenCarsThenTruckDoesNotFit() {
        parking = new SimpleParking(3, 1);
        Vehicle first = new Car("A001AA");
        Vehicle third = new Car("C003CC");
        parking.add(first);
        parking.add(new Car("B002BB"));
        parking.add(third);
        parking.add(new Truck("D004DD", 5));
        parking.drop(first);
        parking.drop(third);
        assertFalse(parking.add(new Truck("E005EE", 2)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenTruckSizeIsLessThan2() {
        Vehicle first = new Truck("A001AA", 1);
    }
}