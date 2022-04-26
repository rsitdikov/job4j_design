package ru.job4j.ood.lsp.parking;

import java.util.Arrays;
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
        if (isTruckPark(vehicle)) {
            rsl = execute(trucksSpace, null, vehicle, 1);
        } else if (isCarPark(vehicle)) {
            rsl = execute(carsSpace, null, vehicle, vehicle.getSize());
        }
        return rsl;
    }

    @Override
    public boolean drop(Vehicle vehicle) {
        boolean rsl = false;
        if (isCarDepark(vehicle)) {
            rsl = execute(carsSpace, vehicle, null, vehicle.getSize());
        } else if (isTruckDepark(vehicle)) {
            rsl = execute(trucksSpace, vehicle, null, 1);
        }
        return rsl;
    }

    private boolean isTruckPark(Vehicle vehicle) {
        return vehicle.getSize() > 1 && trucksPlaces > 0;
    }

    private boolean isCarPark(Vehicle vehicle) {
        return vehicle.getSize() <= carsPlaces;
    }

    private boolean isTruckDepark(Vehicle vehicle) {
        return vehicle.getSize() > 1 && trucksPlaces < trucksSpace.length;
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
            setCounter(space, from, places);
        }
        return rsl;
    }

    private int indexOf(Vehicle[] space, Vehicle from, int places) {
        int rsl = -1;
        Predicate<Vehicle> predicate = x -> x == from;
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

    void setCounter(Vehicle[] space, Vehicle vehicle, int places) {
        if (Arrays.equals(space, carsSpace)) {
            if (vehicle == null) {
                carsPlaces -= places;
            } else {
                carsPlaces += places;
            }
        } else if (Arrays.equals(space, trucksSpace)) {
            if (vehicle == null) {
                trucksPlaces -= places;
            } else {
                trucksPlaces += places;
            }
        }
    }
}
