package ru.job4j.lsp;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ControlQualityTest {
    private static Store trash;
    private static Store shop;
    private static Store warehouse;
    private static ControlQuality controller;

    @BeforeClass
    public static void init() {
        trash = new Trash();
        shop = new Shop();
        warehouse = new Warehouse();
        controller = new ControlQuality(List.of(trash, shop, warehouse));
    }

    @After
    public void clear() {
        trash.clear();
        shop.clear();
        warehouse.clear();
    }

    @Test
    public void whenSpoilRateIs50ThenToShopAndNoDiscount() {
        LocalDateTime now = LocalDateTime.now();
        Food milk = new Milk("MuMu",
                now.plusDays(10),
                now.minusDays(10),
                70);
        controller.addFood(milk);
        assertThat(shop.getAll().size(), is(1));
        assertThat(shop.getAll().get(0).getDiscount(), is(0.0));
        assertThat(trash.getAll().size(), is(0));
        assertThat(warehouse.getAll().size(), is(0));

    }

    @Test
    public void whenSpoilRateIs90ThenToShopAndGiveDiscount() {
        LocalDateTime now = LocalDateTime.now();
        Food milk = new Milk("MuMu",
                now.plusDays(1),
                now.minusDays(10),
                70);
        controller.addFood(milk);
        assertThat(shop.getAll().size(), is(1));
        assertThat(shop.getAll().get(0).getDiscount(), is(0.5));
        assertThat(trash.getAll().size(), is(0));
        assertThat(warehouse.getAll().size(), is(0));

    }

    @Test
    public void whenSpoilRateIs100ThenToTrash() {
        LocalDateTime now = LocalDateTime.now();
        Food milk = new Milk("MuMu",
                now,
                now.minusDays(10),
                70);
        controller.addFood(milk);
        assertThat(shop.getAll().size(), is(0));
        assertThat(trash.getAll().size(), is(1));
        assertThat(warehouse.getAll().size(), is(0));

    }

    @Test
    public void whenSpoilRateIs0ThenToWarehouse() {
        LocalDateTime now = LocalDateTime.now();
        Food milk = new Milk("MuMu",
                now.plusDays(10),
                now,
                70);
        controller.addFood(milk);
        assertThat(shop.getAll().size(), is(0));
        assertThat(trash.getAll().size(), is(0));
        assertThat(warehouse.getAll().size(), is(1));

    }
}