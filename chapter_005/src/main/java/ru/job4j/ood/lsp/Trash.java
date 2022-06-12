package ru.job4j.ood.lsp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Trash implements Store {
    private List<Food> foods;

    public Trash(List<Food> foods) {
        this.foods = foods;
    }

    @Override
    public boolean add(Food food) {
        boolean rsl = false;
        if (accept(food)) {
            rsl = foods.add(food);
        }
        return rsl;
    }

    @Override
    public List<Food> find(Predicate<Food> predicate) {
        return foods.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    @Override
    public boolean accept(Food food) {
        return LocalDate.now().isAfter(food.getExpiryDate());
    }

    @Override
    public List<Food> clear() {
        List<Food> rsl = List.copyOf(foods);
        foods.clear();
        return rsl;
    }

}
