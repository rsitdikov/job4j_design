package ru.job4j.ood.ocp;

/**
 * Первый пример нарушения принципа OCP:
 * В методе sound, при появлении новых видов животных,
 * необходимо вносить изменения в код.
 */
public class Animal {
    private String name;

    public Animal(String name) {
        this.name = name;
    }

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
