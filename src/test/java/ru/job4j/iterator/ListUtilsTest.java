package ru.job4j.iterator;

import org.junit.Test;
import static org.hamcrest.Matchers.is;

import java.util.*;

import static org.junit.Assert.*;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test
    public void whenAddBeforeFirst() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 0, 5);
        assertThat(input, is(Arrays.asList(5, 1, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenRemoveIfEven() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5));
        ListUtils.removeIf(input, x -> x % 2 == 0);
        assertThat(input, is(Arrays.asList(1, 3, 5)));
    }

    @Test
    public void whenReplaceIfMoreThenFive() {
        List<Integer> input = new ArrayList<>(Arrays.asList(4, 6, 2, 7, 4, 8));
        ListUtils.replaceIf(input, x -> x > 5, 0);
        assertThat(input, is(Arrays.asList(4, 0, 2, 0, 4, 0)));
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> list = new ArrayList<>(Arrays.asList(5, 6, 13, 1, 1, 4, 0));
        List<Integer> elements = new ArrayList<>(Arrays.asList(13, 1, 0));
        ListUtils.removeAll(list, elements);
        assertThat(list, is(Arrays.asList(5, 6, 4)));
    }
}