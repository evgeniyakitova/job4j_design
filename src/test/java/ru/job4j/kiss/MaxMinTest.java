package ru.job4j.kiss;

import org.junit.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

public class MaxMinTest {

    @Test
    public void whenFindMaxThen10() {
        List<Integer> list = List.of(4, 5, 7, 10);
        int expected = 10;
        int result = MaxMin.max(list, Integer::compare);
        assertEquals(result, expected);
    }

    @Test
    public void whenFindMinThen2() {
        List<Integer> list = List.of(2, 3, 7, 20);
        int expected = 2;
        int result = MaxMin.min(list, Integer::compare);
        assertEquals(result, expected);
    }
}