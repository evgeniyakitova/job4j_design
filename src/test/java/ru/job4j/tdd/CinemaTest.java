package ru.job4j.tdd;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

@Ignore
public class CinemaTest {

    @Test
    public void whenBuy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, Calendar.OCTOBER, 10, 23, 0);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(new Ticket3D()));
    }

    @Test
    public void whenFind() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(Arrays.asList(new Session3D())));
    }

    @Test
    public void whenAdd() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        cinema.add(new MorningSession());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions.size(), is(2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenBuyTicketForInvalidSeatThenException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, Calendar.OCTOBER, 10, 23, 0);
        cinema.buy(account, 30, 5, date);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenBuyTicketForInvalidDateThenException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2023, Calendar.OCTOBER, 10, 23, 0);
        cinema.buy(account, 5, 5, date);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenBuyTicketAndSeatIsOccupiedThenException() {
        Account firsAccount = new AccountCinema();
        Account secondAccount = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2022, Calendar.OCTOBER, 10, 23, 0);
        cinema.buy(firsAccount, 5, 5, date);
        cinema.buy(secondAccount, 5, 5, date);
    }
}