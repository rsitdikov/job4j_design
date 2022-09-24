package ru.job4j.ood.dip;

import java.util.Iterator;
import java.util.List;

public class Distance {

    /**
     * Второй пример нарушения принципа DIP:
     * аргументы метода не являются абстракциями.
     * Кроме того, есть зависимость от реализации класса Math.
     */
    public static double calculate(Point2D first, Point2D second) {
        Iterator<Double> itFirst = first.getCoord().iterator();
        Iterator<Double> itSecond = second.getCoord().iterator();
        double square = 0.0;
        while (itFirst.hasNext() && itSecond.hasNext()) {
            square += Math.pow(itSecond.next() - itFirst.next(), 2.0);
        }
        return Math.sqrt(square);
    }

    class Point2D {
        private double x;
        private double y;

        public Point2D(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public Iterable<Double> getCoord() {
            return List.of(x, y);
        }
    }
}
