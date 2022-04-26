package ru.job4j.ood.lsp.parking;

import java.util.Objects;

public class Truck implements Vehicle {
    private int size;

    public Truck(int size) {
        if (size <= Car.SIZE) {
            throw new IllegalArgumentException("Size must be greater than 1.");
        }
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }
}
