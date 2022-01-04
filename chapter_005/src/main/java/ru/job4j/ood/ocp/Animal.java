package ru.job4j.ood.ocp;

import java.util.List;

public class Animal {
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    /**
     * Неправильно спроектированный класс - метод
     * sound не соответствует принципу OCP:
     * при появлении новых видов животных, необходимо
     * вносить изменения в код.
     * @return rsl
     */
    public String sound() {
        String rsl = null;
        if ("cat".equals(name)) {
            rsl = "meow";
        } else if ("dog".equals(name)) {
            rsl = "bark";
        }
        return rsl;
    }
}
