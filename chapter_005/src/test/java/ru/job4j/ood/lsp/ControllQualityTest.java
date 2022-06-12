package ru.job4j.ood.lsp;

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ControllQualityTest {

    @Test
    public void whenLessThan25PercentHasPassed() {
        LocalDate current = LocalDate.now();
        List<Food> foods = List.of(
                new Milk("Milk", current.plusDays(76), current.minusDays(24), 25, 0)
        );
        Store warehouse = new Warehouse(new ArrayList<>());
        Store shop = new Shop(new ArrayList<>());
        Store trash = new Trash(new ArrayList<>());
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
        Store warehouse = new Warehouse(new ArrayList<>());
        Store shop = new Shop(new ArrayList<>());
        Store trash = new Trash(new ArrayList<>());
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
        Store warehouse = new Warehouse(new ArrayList<>());
        Store shop = new Shop(new ArrayList<>());
        Store trash = new Trash(new ArrayList<>());
        List<Store> stores = List.of(warehouse, shop, trash);
        ControllQuality controllQuality = new ControllQuality();
        controllQuality.distribute(foods, stores);
        assertNotEquals(foods, warehouse.find(x -> true));
        assertEquals(foods, shop.find(x -> true));
        assertNotEquals(foods, trash.find(x -> true));
    }

    @Test
    public void whenTheExpirationDateHasExpired() {
        LocalDate current = LocalDate.now();
        List<Food> foods = List.of(
                new Milk("Milk", current.minusDays(4), current.minusDays(16), 25, 0)
        );
        Store warehouse = new Warehouse(new ArrayList<>());
        Store shop = new Shop(new ArrayList<>());
        Store trash = new Trash(new ArrayList<>());
        List<Store> stores = List.of(warehouse, shop, trash);
        ControllQuality controllQuality = new ControllQuality();
        controllQuality.distribute(foods, stores);
        assertNotEquals(foods, warehouse.find(x -> true));
        assertNotEquals(foods, shop.find(x -> true));
        assertEquals(foods, trash.find(x -> true));
    }

    @Test
    public void whenIsDiscountValid() {
        LocalDate current = LocalDate.now();
        List<Food> foods = List.of(
                new Sausage("Sausage", current, current.minusDays(100), 200, 30)
        );
        Store warehouse = new Warehouse(new ArrayList<>());
        Store shop = new Shop(new ArrayList<>());
        Store trash = new Trash(new ArrayList<>());
        List<Store> stores = List.of(warehouse, shop, trash);
        ControllQuality controllQuality = new ControllQuality();
        controllQuality.distribute(foods, stores);
        assertEquals(140.0, shop.find(x -> true).get(0).getPrice(), 0.001);
    }

    @Test
    public void whenSeveralDifferentFoods() {
        LocalDate current = LocalDate.now();
        Food milk = new Milk("Milk1", current.plusDays(1), current.minusDays(6), 60, 30);
        Food bread = new Bread("Bread", current.plusDays(2), current.minusDays(2), 35, 50);
        Food sausage = new Sausage("Sausage", current.plusDays(29), current.minusDays(1), 200, 40);
        Food spoiledMilk = new Milk("Milk2", current.minusDays(2), current.minusDays(9), 70, 35);
        List<Food> foods = List.of(milk, bread, sausage, spoiledMilk);
        Store warehouse = new Warehouse(new ArrayList<>());
        Store shop = new Shop(new ArrayList<>());
        Store trash = new Trash(new ArrayList<>());
        List<Store> stores = List.of(warehouse, shop, trash);
        ControllQuality controllQuality = new ControllQuality();
        controllQuality.distribute(foods, stores);
        assertEquals(List.of(sausage), warehouse.find(x -> true));
        assertEquals(List.of(milk, bread), shop.find(x -> true));
        assertEquals(List.of(spoiledMilk), trash.find(x -> true));
    }

    @Test
    public void whenStoresAreCleared() {
        LocalDate current = LocalDate.now();
        Food milk = new Milk("Milk1", current.plusDays(1), current.minusDays(6), 60, 30);
        Food bread = new Bread("Bread", current.plusDays(2), current.minusDays(2), 35, 50);
        Food sausage = new Sausage("Sausage", current.plusDays(29), current.minusDays(1), 200, 40);
        Food spoiledMilk = new Milk("Milk2", current.minusDays(2), current.minusDays(9), 70, 35);
        List<Food> foods = List.of(milk, bread, sausage, spoiledMilk);
        Store warehouse = new Warehouse(new ArrayList<>());
        Store shop = new Shop(new ArrayList<>());
        Store trash = new Trash(new ArrayList<>());
        List<Store> stores = List.of(warehouse, shop, trash);
        ControllQuality controllQuality = new ControllQuality();
        controllQuality.distribute(foods, stores);
        for (Store store : stores) {
            store.clear();
        }
        assertEquals(List.of(), warehouse.find(x -> true));
        assertEquals(List.of(), shop.find(x -> true));
        assertEquals(List.of(), trash.find(x -> true));
    }

    @Test
    public void whenFoodsIsResorted() {
        LocalDate current = LocalDate.now();
        Food milk = new Milk("Milk1", current.plusDays(1), current.minusDays(6), 60, 30);
        Food bread = new Bread("Bread", current.plusDays(2), current.minusDays(2), 35, 50);
        Food sausage = new Sausage("Sausage", current.plusDays(29), current.minusDays(1), 200, 40);
        Food spoiledMilk = new Milk("Milk2", current.minusDays(2), current.minusDays(9), 70, 35);
        List<Food> foods = List.of(milk, bread, sausage, spoiledMilk);
        Store warehouse = new Warehouse(new ArrayList<>());
        Store shop = new Shop(new ArrayList<>());
        Store trash = new Trash(new ArrayList<>());
        List<Store> stores = List.of(warehouse, shop, trash);
        ControllQuality controllQuality = new ControllQuality();
        controllQuality.distribute(foods, stores);
        controllQuality.resort(stores);
        assertEquals(List.of(sausage), warehouse.find(x -> true));
        assertEquals(List.of(milk, bread), shop.find(x -> true));
        assertEquals(List.of(spoiledMilk), trash.find(x -> true));
    }
}