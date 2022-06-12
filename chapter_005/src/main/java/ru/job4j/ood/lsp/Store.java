package ru.job4j.ood.lsp;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.Predicate;

public interface Store {

    boolean add(Food food);

    List<Food> find(Predicate<Food> predicate);

    boolean accept(Food food);

    default double getPercentLifeExpired(Food food) {
        LocalDate current = LocalDate.now();
        LocalDate create = food.getCreateDate();
        LocalDate expire = food.getExpiryDate();
        return  100.0 * ChronoUnit.DAYS.between(create, current)
                / ChronoUnit.DAYS.between(create, expire);
    }

    List<Food> clear();

}
