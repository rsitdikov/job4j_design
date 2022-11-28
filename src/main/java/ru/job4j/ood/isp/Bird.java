package ru.job4j.ood.isp;

/**
 * Нарушение принципа ISP:
 * не все птицы умеют летать, поэтому,
 * например, для страуса, метод fly придется глушить.
 */
public interface Bird {
    void eat();

    void fly();
}
