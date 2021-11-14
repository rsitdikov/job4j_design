package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return calculate(value, comparator, x -> x < 0);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return calculate(value, comparator, x -> x > 0);
    }

    private <T> T calculate(List<T> value, Comparator<T> comparator, Predicate<Integer> condition)
            throws NullPointerException {
        T rsl = null;
        for (T other : value) {
            if (other != null) {
                if (rsl == null
                        || (condition.test(comparator.compare(rsl, other)))) {
                    rsl = other;
                }
            }
        }
        if (rsl == null) {
            throw new NullPointerException("No data to compare.");
        }
        return rsl;
    }
}
