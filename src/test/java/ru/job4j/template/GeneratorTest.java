package ru.job4j.template;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class GeneratorTest {

    @Ignore
    @Test
    public void whenNumberAndNamesOfKeysMatch() {
        Generator generator = new SimpleGenerator();
        String template = "I'm a ${name}, who are ${subject}?";
        Map<String, String> map = Map.of(
                "name", "Ramil",
                "subject", "you"
        );
        String expected = "I'm a Ramil, who are you?";
        assertEquals(expected,
                generator.produce(template, map));
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenKeyNamesDoNotMatch() {
        Generator generator = new SimpleGenerator();
        String template = "I'm a ${name}, who are ${subject}?";
        Map<String, String> map = Map.of(
                "profession", "doctor",
                "subject", "you"
        );
        generator.produce(template, map);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenThereAreMoreKeysInMap() {
        Generator generator = new SimpleGenerator();
        String template = "I'm a ${name}, who are ${subject}?";
        Map<String, String> map = Map.of(
                "name", "Ramil",
                "profession", "doctor",
                "subject", "you"
        );
        generator.produce(template, map);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenThereAreMoreKeysInTemplate() {
        Generator generator = new SimpleGenerator();
        Map<String, String> map = Map.of(
                "subject", "you"
        );
        String template = "I'm a ${name}, who are ${subject}?";
        generator.produce(template, map);
    }
}