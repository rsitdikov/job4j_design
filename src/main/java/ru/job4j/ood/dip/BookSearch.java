package ru.job4j.ood.dip;

import java.util.*;
import java.util.function.Predicate;

public class BookSearch {
    private Library library;

    public BookSearch(Library library) {
        this.library = library;
    }

    /**
     * Третий пример нарушения принципа DIP:
     * Возвращаемый методом результат не является абстракцией.
     */
    private Library find(Predicate<Book> predicate) {
        Library result = new SimpleLibrary(new HashSet<>());
        for (Book book : library.getBooks()) {
            if (predicate.test(book)) {
                result.add(book);
            }
        }
        return result;
    }

    public Library findByName(String name) {
        return find(x -> name.equals(x.getName()));
    }

    public Library findByAuthor(String author) {
        return find(x -> author.equals(x.getAuthor()));
    }

    public Library findByYear(int year) {
        return find(x -> year == x.getYear());
    }

    interface Library {

        Collection<Book> getBooks();

        boolean add(Book book);

        boolean remove(Book book);
    }

    interface Book {

        String getName();

        String getAuthor();

        int getYear();
    }

    class SimpleLibrary implements Library {
        Collection<Book> books;

        public SimpleLibrary(Collection<Book> books) {
            this.books = books;
        }

        @Override
        public Collection<Book> getBooks() {
            return books;
        }

        @Override
        public boolean add(Book book) {
            return books.add(book);
        }

        @Override
        public boolean remove(Book book) {
            return books.remove(book);
        }
    }
}
