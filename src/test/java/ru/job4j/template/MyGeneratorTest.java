package ru.job4j.template;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

@Ignore
public class MyGeneratorTest {
    private final Generator generator = new MyGenerator();
    private final String template = "I am a ${name}, Who are ${subject}?";

    @Test
    public void whenProduceAndStringsAreEquals() {
        Map<String, String> args = Map.of("name", "Dmitriy", "subject", "you");
        String expected = "I am a Dmitriy, Who are you?";
        assertEquals(expected, generator.produce(template, args));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenProduceAndThereAreNotKeyThenException() {
        Map<String, String> args = Map.of("name", "Dmitriy");
        generator.produce(template, args);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenProduceAndThereAreMoreKeysThenException() {
        Map<String, String> args = Map.of("name", "Dmitriy", "subject", "you", "age", "21");
        generator.produce(template, args);
    }
}