package ru.job4j.ood.lsp.parking;

import java.util.Objects;

public class Truck implements Vehicle {
    private int size;
    private String number;

    public Truck(String number, int size) {
        if (size <= Car.SIZE) {
            throw new IllegalArgumentException("Size must be greater than 1.");
        }
        this.number = number;
        this.size = size;
    }

    @Override
    public String getNumber() {
        return number;
    }

    @Override
    public int getSize() {
        return size;
    }
}
