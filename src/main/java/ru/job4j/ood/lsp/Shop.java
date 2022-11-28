package ru.job4j.ood.lsp;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Shop implements Store {
    private List<Food> foods = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        boolean rsl = false;
        if (accept(food)) {
            if (getPercentLifeExpired(food) > 75.0) {
                food.setPrice(
                        food.getPrice() * (100.0 - food.getDiscount()) / 100.0
                );
            }
            rsl = foods.add(food);
        }
        return rsl;
    }

    @Override
    public boolean accept(Food food) {
        double percent = getPercentLifeExpired(food);
        return percent >= 25.0 && percent <= 100.0;
    }

    @Override
    public List<Food> clear() {
        List<Food> rsl = List.copyOf(foods);
        foods.clear();
        return rsl;
    }

    @Override
    public List<Food> find(Predicate<Food> predicate) {
        return foods.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }
}
