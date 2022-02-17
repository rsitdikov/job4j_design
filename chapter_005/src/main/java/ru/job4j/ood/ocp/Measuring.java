package ru.job4j.ood.ocp;

/**
 * Второй пример нарушения принципа OCP:
 * параметры и реализация метода distance
 * зависят от реализации класса Point,
 * а не от абстракции.
 */
public class Measuring {

    public static double distance(Point first, Point second) {
        return Math.sqrt(Math.pow(second.getX() - first.getX(), 2)
                        + Math.pow(second.getY() - first.getY(), 2)
        );
    }
}

class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
