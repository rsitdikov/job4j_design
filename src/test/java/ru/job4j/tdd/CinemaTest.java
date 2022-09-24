package ru.job4j.tdd;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class CinemaTest {

    @Ignore
    @Test
    public void buy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.add(date.HOUR, 1);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(new Ticket3D()));
    }

    @Ignore
    @Test
    public void find() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(Arrays.asList(new Session3D())));
    }

    @Ignore
    @Test
    public void whenSessionAddedSizeShouldIncrease() {
        Cinema cinema = new Cinema3D();
        assertThat(cinema.find(session -> true).size(), is(0));
        cinema.add(new Session3D());
        assertThat(cinema.find(session -> true).size(), is(1));
    }

    @Ignore
    @Test
    public void whenSessionIsFalseThenListIsEmpty() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> false);
        assertTrue(sessions.isEmpty());
    }

    @Ignore
    @Test
    public void whenCinemaIsEmptyThenListIsEmpty() {
        Cinema cinema = new Cinema3D();
        List<Session> sessions = cinema.find(session -> true);
        assertTrue(sessions.isEmpty());
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenPlaceIsNotFree() {
        Account first = new AccountCinema();
        Account second = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.add(date.HOUR, 1);
        Ticket ticket = cinema.buy(first, 1, 1, date);
        cinema.buy(second, 1, 1, date);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenPlaceIsNotCorect() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.add(date.HOUR, 1);
        cinema.buy(account, -1, -1, date);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenTimePassed() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.add(date.HOUR, -1);
        cinema.buy(account, 1, 1, date);
    }
}