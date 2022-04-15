package ru.job4j.ood.lsp;

import java.util.List;

public class ControllQuality {
    public void distribute(List<Food> foods, List<Store> stores) {
        for (Food food : foods) {
            for (Store store : stores) {
                if (store.add(food)) {
                    break;
                }
            }
        }
    }
}
