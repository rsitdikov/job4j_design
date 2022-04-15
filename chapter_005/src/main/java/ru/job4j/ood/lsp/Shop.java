package ru.job4j.ood.lsp;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Shop implements Store {
    List<Food> foods = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        boolean rsl = false;
        LocalDate current = LocalDate.now();
        LocalDate create = food.getCreateDate();
        LocalDate expire = food.getExpiryDate();
        double percent = 100.0 * ChronoUnit.DAYS.between(create, current)
                / ChronoUnit.DAYS.between(create, expire);
        if (percent >= 25.0 && percent <= 100.0) {
            if (percent > 75.0) {
                food.setDiscount((int) food.getPrice() / 2);
            }
            foods.add(food);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public List<Food> find(Predicate<Food> predicate) {
        return foods.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }
}
