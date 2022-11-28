package ru.job4j.ood.srp;

/**
 * На класс Rectangle возлагаются две ответственности:
 * первая - определение площади прямоугольника,
 * вторая - вывод изображения прямоугольника на консоль.
 */
public class Rectangle {
    private int width;
    private int height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public long area() {
        return (long) width * height;
    }

    public void draw() {
        if (height > 0) {
            StringBuilder image = new StringBuilder("*".repeat(width));
            for (int row = 1; row <= height - 2; row++) {
                image.append(System.lineSeparator())
                        .append("*");
                image.append(" ".repeat(Math.max(0, width - 2)));
                if (width - 2 >= 0) {
                    image.append("*");
                }
            }
            if (height - 2 >= 0) {
                image.append(System.lineSeparator())
                        .append("*".repeat(width));
            }
            System.out.println(image.toString());
        }
    }
}
