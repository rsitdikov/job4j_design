package ru.job4j.ood.lsp;

import java.util.List;
import java.util.function.Predicate;

public interface Store {

    boolean add(Food food);

    List<Food> find(Predicate<Food> predicate);

}
