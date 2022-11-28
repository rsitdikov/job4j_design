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
            throws IndexOutOfBoundsException, NullPointerException {
        T rsl = value.get(0);
        for (int index = 1; index < value.size(); index++) {
            T other = value.get(index);
            if (condition.test(comparator.compare(rsl, other))) {
                rsl = other;
            }
        }
        return rsl;
    }
}
