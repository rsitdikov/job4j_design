package ru.job4j.ood.lsp;

import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class ControllQualityTest {

    @Test
    public void whenLessThan25PercentHasPassed() {
        LocalDate current = LocalDate.now();
        List<Food> foods = List.of(
                new Milk("Milk", current.plusDays(76), current.minusDays(24), 25, 0)
        );
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        List<Store> stores = List.of(warehouse, shop, trash);
        ControllQuality controllQuality = new ControllQuality();
        controllQuality.distribute(foods, stores);
        assertEquals(foods, warehouse.find(x -> true));
        assertNotEquals(foods, shop.find(x -> true));
        assertNotEquals(foods, trash.find(x -> true));
    }

    @Test
    public void whenMoreThanOrEqual25AndLessThanOrEqual75PercentHavePassed() {
        LocalDate current = LocalDate.now();
        List<Food> foods = List.of(
                new Bread("Bread", current.plusDays(75), current.minusDays(25), 50, 0)
        );
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        List<Store> stores = List.of(warehouse, shop, trash);
        ControllQuality controllQuality = new ControllQuality();
        controllQuality.distribute(foods, stores);
        assertNotEquals(foods, warehouse.find(x -> true));
        assertEquals(foods, shop.find(x -> true));
        assertNotEquals(foods, trash.find(x -> true));
        assertEquals(0, foods.get(0).getDiscount());
    }

    @Test
    public void whenMoreThan75AndLessThanOrEqual100PercentHavePassed() {
        LocalDate current = LocalDate.now();
        List<Food> foods = List.of(
                new Sausage("Sausage", current, current.minusDays(100), 100, 0)
        );
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        List<Store> stores = List.of(warehouse, shop, trash);
        ControllQuality controllQuality = new ControllQuality();
        controllQuality.distribute(foods, stores);
        assertNotEquals(foods, warehouse.find(x -> true));
        assertEquals(foods, shop.find(x -> true));
        assertNotEquals(foods, trash.find(x -> true));
        assertEquals(50, foods.get(0).getDiscount());
    }

    @Test
    public void whenTheExpirationDateHasExpired() {
        LocalDate current = LocalDate.now();
        List<Food> foods = List.of(
                new Milk("Milk", current.minusDays(4), current.minusDays(16), 25, 0)
        );
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        List<Store> stores = List.of(warehouse, shop, trash);
        ControllQuality controllQuality = new ControllQuality();
        controllQuality.distribute(foods, stores);
        assertNotEquals(foods, warehouse.find(x -> true));
        assertNotEquals(foods, shop.find(x -> true));
        assertEquals(foods, trash.find(x -> true));
    }
}