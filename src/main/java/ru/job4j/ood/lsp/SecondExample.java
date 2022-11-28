package ru.job4j.ood.lsp;

import java.util.Calendar;
import java.util.GregorianCalendar;

class Ship {
    protected double fuel;
    protected double consumptionRate;

    public Ship(double fuel, double consumptionRate) {
        this.fuel = fuel;
        this.consumptionRate = consumptionRate;
    }

    public void move(Calendar date, double distance) {
        int month = date.get(Calendar.MONTH);
        if (month > 10 || month < 4) {
            throw new IllegalArgumentException("Navigation is not possible this month!");
        }
        if (distance <= 0.0) {
            throw new IllegalArgumentException("Distance must be greater than zero!");
        }
        double consumption = consumptionRate * distance;
        if (consumption > fuel) {
            throw new IllegalArgumentException("Too much distance!");
        }
        fuel -= consumption;
        System.out.printf("The ship traveled %.1f miles.%n", distance);
    }
}

class Icebreaker extends Ship {

    public Icebreaker(double fuel, double consumptionRate) {
        super(fuel, consumptionRate);
    }

    /*
    Второй пример нарушения принципа LSP:
    постусловие ослаблено в подклассе.
    */
    @Override
    public void move(Calendar date, double distance) {
        if (distance <= 0.0) {
            throw new IllegalArgumentException("Distance must be greater than zero!");
        }
        double consumption = consumptionRate * distance;
        if (consumption > fuel) {
            throw new IllegalArgumentException("Too much distance!");
        }
        fuel -= consumption;
        System.out.printf("The ship traveled %.1f miles.%n", distance);
    }
}

public class SecondExample {

    public static void main(String[] args) {
        Calendar date = new GregorianCalendar();
        date.set(Calendar.YEAR, 2022);
        date.set(Calendar.MONTH, 0);
        date.set(Calendar.DAY_OF_MONTH, 1);
        Ship one = new Icebreaker(500, 10);
        Ship second = new Ship(500, 10);
        one.move(date, 20);
        second.move(date, 20);
    }

}
