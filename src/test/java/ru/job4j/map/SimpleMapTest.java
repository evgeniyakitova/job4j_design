package ru.job4j.map;

import org.junit.Test;
import ru.job4j.collection.User;

import java.util.Calendar;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void whenPutAndKeysAreInteger() {
        Map<Integer, String> map = new SimpleMap<>();
        assertTrue(map.put(25, "First value"));
        assertFalse(map.put(25, "Second value"));
        assertTrue(map.put(30, "Third value"));
    }

    @Test
    public void whenPutAndKeysAreUsers() {
        Map<User, String> map = new SimpleMap<>();
        Calendar date = Calendar.getInstance();
        date.setTimeInMillis(1461518397788L);
        assertTrue(map.put(new User("Katya", 2, date), "First value"));
        assertFalse(map.put(new User("Katya", 2, date), "Second value"));
        assertTrue(map.put(new User("Petr", 1, date), "Third value"));
    }

    @Test
    public void whenGetAndKeysAreIntegers() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(25, "First value");
        map.put(25, "Second value");
        map.put(30, "Third value");
        assertThat(map.get(25), is("First value"));
        assertThat(map.get(30), is("Third value"));
        assertNull(map.get(50));
    }

    @Test
    public void whenGetAndKeysAreUsers() {
        Map<User, String> map = new SimpleMap<>();
        Calendar date = Calendar.getInstance();
        map.put(new User("Katya", 2, date), "First value");
        map.put(new User("Katya", 2, date), "Second value");
        assertThat(map.get(new User("Katya", 2, date)), is("First value"));
        assertNull(map.get(new User("Petr", 1, Calendar.getInstance())));
    }

    @Test
    public void whenRemoveAndKeyIsInteger() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(25, "First value");
        assertTrue(map.remove(25));
        assertFalse(map.remove(25));
        assertNull(map.get(25));
    }

    @Test
    public void whenRemoveAndKeyIsUser() {
        Map<User, String> map = new SimpleMap<>();
        Calendar date = Calendar.getInstance();
        map.put(new User("Katya", 2, date), "First value");
        assertTrue(map.remove(new User("Katya", 2, date)));
        assertFalse(map.remove(new User("Katya", 2, date)));
        assertNull(map.get(new User("Katya", 2, date)));
    }

    @Test
    public void whenCheckIterator() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(25, "First value");
        Iterator<Integer> iterator = map.iterator();
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(25));
        assertFalse(iterator.hasNext());
    }

    @Test
    public void whenCheckIteratorWithTwoElements() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(25, "First value");
        map.put(70, "Second value");
        Iterator<Integer> iterator = map.iterator();
        iterator.next();
        iterator.next();
        assertFalse(iterator.hasNext());
    }


    @Test(expected = ConcurrentModificationException.class)
    public void whenPutAfterGetIteratorThenMustBeException() {
        Map<Integer, String> map = new SimpleMap<>();
        Iterator<Integer> iterator = map.iterator();
        map.put(25, "First value");
        iterator.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenRemoveAfterGetIteratorThenMustBeException() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(25, "First value");
        Iterator<Integer> iterator = map.iterator();
        map.remove(25);
        iterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetIteratorFromEmptyMapThenMustBeException() {
        Map<Integer, String> map = new SimpleMap<>();
        map.iterator().next();
    }
}