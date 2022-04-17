package ru.job4j.ood.lsp;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Warehouse implements Store {
    private List<Food> foods = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        boolean rsl = false;
        if (accept(food)) {
            rsl = foods.add(food);
        }
        return rsl;
    }

    @Override
    public boolean accept(Food food) {
        return getPercentLifeExpired(food) < 25.0;
    }

    @Override
    public List<Food> find(Predicate<Food> predicate) {
        return foods.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }
}
