package ru.job4j.ood.lsp;

import java.time.LocalDate;

public class Milk extends Food {

    public Milk(String name, LocalDate expiryDate, LocalDate createDate, double price, int discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}