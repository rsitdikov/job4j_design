package ru.job4j.ood.lsp.parking;

public class SimpleParking implements Parking {
    private int carsPlaces;
    private int trucksPlaces;
    private Vehicle[] carsSpace;
    private Vehicle[] trucksSpace;

    public SimpleParking(int carsPlaces, int trucksPlaces) {
        this.carsPlaces = carsPlaces;
        this.trucksPlaces = trucksPlaces;
        carsSpace = new Vehicle[carsPlaces];
        trucksSpace = new Vehicle[trucksPlaces];
    }

    @Override
    public boolean add(Vehicle vehicle) {
        return false;
    }

    @Override
    public boolean drop(Vehicle vehicle) {
        return false;
    }
}
