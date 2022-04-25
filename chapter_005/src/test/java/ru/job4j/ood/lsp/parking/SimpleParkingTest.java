package ru.job4j.ood.lsp.parking;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleParkingTest {
    private SimpleParking parking = null;

    @Before
    public void init() {
        parking = new SimpleParking(2, 1);
    }

    @Ignore
    @Test
    public void when2CarsAnd1TruckAreAdded() {
        assertTrue(parking.add(new Car()));
        assertTrue(parking.add(new Truck(3)));
        assertTrue(parking.add(new Car()));
    }

    @Ignore
    @Test
    public void when2TrucksOfSize2AreAdded() {
        parking.add(new Truck(2));
        assertTrue(parking.add(new Truck(2)));
    }

    @Ignore
    @Test
    public void when3CarsAreAdded() {
        parking.add(new Car());
        parking.add(new Car());
        assertFalse(parking.add(new Car()));
    }

    @Ignore
    @Test
    public void when1CarsAnd2TrucksAreAdded() {
        parking.add(new Car());
        parking.add(new Truck(2));
        assertFalse(parking.add(new Truck(2)));
    }

    @Ignore
    @Test
    public void when2TrucksOfSize3AreAdded() {
        parking.add(new Truck(3));
        assertFalse(parking.add(new Truck(3)));
    }

    @Ignore
    @Test
    public void whenCarIsDroppedFromTruckSpace() {
        Vehicle truck = new Truck(3);
        parking.add(new Car());
        parking.add(truck);
        assertTrue(parking.drop(truck));
    }

    @Ignore
    @Test
    public void whenCarIsDroppedFromCarSpace() {
        Vehicle truck = new Truck(2);
        parking.add(new Truck(5));
        parking.add(truck);
        assertTrue(parking.drop(truck));
    }

    @Ignore
    @Test
    public void whenThereIsNoSuchVehicle() {
        Vehicle truck = new Truck(2);
        parking.add(new Truck(5));
        assertFalse(parking.drop(truck));
    }

    @Ignore
    @Test
    public void whenThereAreNoVehicles() {
        assertFalse(parking.drop(new Car()));
    }
}