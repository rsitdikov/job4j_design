package it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter {

    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {

        return new Iterator<Integer>() {
            private Iterator<Integer> current = it.next();

            @Override
            public boolean hasNext() {
                while (!current.hasNext() && it.hasNext()) {
                        current = it.next();
                }
                return current.hasNext();
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw  new NoSuchElementException();
                }
                return current.next();
            }
        };
    }
}