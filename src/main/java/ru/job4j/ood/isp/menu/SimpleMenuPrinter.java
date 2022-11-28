package ru.job4j.ood.isp.menu;

public class SimpleMenuPrinter implements MenuPrinter {
    private static final String REPETITION = "---";
    private static final String DELIMITER = "\\.";

    @Override
    public void print(Menu menu) {
        menu.forEach(i -> System.out.println(
                REPETITION.repeat(calculate(i.getNumber()))
                        + i.getNumber()
                        + i.getName()));
    }

    private int calculate(String string) {
        return string.split(DELIMITER).length - 1;
    }
}
