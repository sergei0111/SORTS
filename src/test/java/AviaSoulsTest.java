import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

public class AviaSoulsTest {
    Ticket ticket1 = new Ticket("Москва", "Новосибирск", 11_000, 20, 5);
    Ticket ticket2 = new Ticket("Москва", "Тюмень", 7_000, 19, 22);
    Ticket ticket3 = new Ticket("Москва", "Хабаровск", 13_000, 21, 9);
    Ticket ticket4 = new Ticket("Москва", "Хабаровск", 10_500, 5, 14);
    Ticket ticket5 = new Ticket("Москва", "Хабаровск", 16_000, 7, 19);

    AviaSouls manager = new AviaSouls();

    @BeforeEach
    public void setup() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
    }

    @Test
    public void testCompareTwoTicketsAndSortPrice() {

        Ticket[] tickets = {ticket2, ticket3};
        Arrays.sort(tickets);
        Ticket[] expected = {ticket2, ticket3};

        Assertions.assertArrayEquals(expected, tickets);
    }

    @Test
    public void testSearchTicketCityFromCityToAndSortPrice() {
        Ticket[] expected = {ticket4, ticket3};
        Ticket[] actual = manager.search("Москва", "Хабаровск");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchTicketCityFromCityToAndSortTimeFly() {
        Comparator<Ticket> comparator = new TicketTimeComparator();

        Ticket[] expected = {ticket4, ticket3};
        Ticket[] actual = manager.searchAndSortBy("Москва", "Хабаровск", comparator);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchNoneTicket() {
        Ticket[] expected = {};
        Ticket[] actual = manager.search("Сочи", "Краснодар");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchOneTicket() {
        Ticket[] expected = {ticket2};
        Ticket[] actual = manager.search("Москва", "Тюмень");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchSeveralTicket() {
        manager.add(ticket5);
        Ticket[] expected = {ticket4, ticket3, ticket5};
        Ticket[] actual = manager.search("Москва", "Хабаровск");

        Assertions.assertArrayEquals(expected, actual);
    }
}
