package ru.job4j.ood.lsp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ControllQuality {

    public void distribute(List<Food> foods, List<Store> stores) {
        for (Food food : foods) {
            for (Store store : stores) {
                if (store.accept(food)) {
                    store.add(food);
                    break;
                }
            }
        }
    }

    public void resort(List<Store> stores) {
        List<Food> foods = stores.stream()
                .flatMap(x -> x.clear().stream())
                .collect(Collectors.toList());
        distribute(foods, stores);
    }
}
