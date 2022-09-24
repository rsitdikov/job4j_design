package ru.job4j.ood.isp;

/**
 * Нарушение принципа ISP:
 * Не все транспортные средства могут быть заправлены,
 * например, трамвай или троллейбус. Поэтому, для них
 * придется метод fill глушить.
 */
public interface Vehicle {
    void drive(double distention);

    void fill(double petrol);
}
