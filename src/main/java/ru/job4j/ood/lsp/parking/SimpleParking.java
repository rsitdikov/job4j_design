package ru.job4j.ood.lsp.parking;

import java.util.function.Predicate;

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
        boolean rsl = false;
        if (isTruckPark(vehicle) && execute(trucksSpace, null, vehicle, 1)) {
            rsl = true;
            trucksPlaces--;
        } else if (isCarPark(vehicle) && execute(carsSpace, null, vehicle, vehicle.getSize())) {
            rsl = true;
            carsPlaces -= vehicle.getSize();
        }
        return rsl;
    }

    @Override
    public boolean drop(Vehicle vehicle) {
        boolean rsl = false;
        if (isCarDepark(vehicle) && execute(carsSpace, vehicle, null, vehicle.getSize())) {
            rsl = true;
            carsPlaces += vehicle.getSize();
        } else if (isTruckDepark(vehicle) && execute(trucksSpace, vehicle, null, 1)) {
            rsl = true;
            trucksPlaces++;
            }
        return rsl;
    }

    private boolean isTruckPark(Vehicle vehicle) {
        return vehicle.getSize() > Car.SIZE && trucksPlaces > 0;
    }

    private boolean isCarPark(Vehicle vehicle) {
        return vehicle.getSize() <= carsPlaces;
    }

    private boolean isTruckDepark(Vehicle vehicle) {
        return vehicle.getSize() > Car.SIZE && trucksPlaces < trucksSpace.length;
    }

    private boolean isCarDepark(Vehicle vehicle) {
        return carsPlaces < carsSpace.length
                && indexOf(carsSpace, vehicle, vehicle.getSize()) != -1;
    }

    private boolean execute(Vehicle[] space, Vehicle from,
                            Vehicle to, int places) {
        boolean rsl = false;
        int index = indexOf(space, from, places);
        if (index != -1) {
            fill(space, to, index, places);
            rsl = true;
        }
        return rsl;
    }

    private int indexOf(Vehicle[] space, Vehicle from, int places) {
        int rsl = -1;
        Predicate<Vehicle> predicate = from == null
                ? x -> x == from
                : x -> x != null && from.getNumber().equals(x.getNumber());
        for (int index = 0; index + places <= space.length; index++) {
            if (predicate.test(space[index])
                    && checkArea(space, places, index, predicate) == index) {
                rsl = index;
                break;
            }
        }
        return rsl;
    }

    private int checkArea(Vehicle[] space, int number,
                          int index, Predicate<Vehicle> predicate) {
        int rsl = index;
        for (int shift = 0; shift < number; shift++) {
            if (!predicate.test(space[index + shift])) {
                rsl = -1;
                break;
            }
        }
        return rsl;
    }

    private void fill(Vehicle[] space, Vehicle to, int index, int size) {
        for (int shift = 0; shift < size; shift++) {
            space[index + shift] = to;
        }
    }
}
