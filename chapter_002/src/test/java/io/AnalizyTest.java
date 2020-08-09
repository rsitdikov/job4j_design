package io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class AnalizyTest {

    @Test
    public void whenTwoPeriodsAreUnavailable() throws IOException {
        String source = "./data/server.log", target = "./data/unavailable.csv";
        Analizy analizy = new Analizy();
        analizy.unavailable(source, target);
        BufferedReader in = new BufferedReader(new FileReader(target));
        assertEquals(in.readLine(), ("10:57:01;10:59:01"));
        assertEquals(in.readLine(), ("11:01:02;11:02:02"));
        assertNull(in.readLine());
        in.close();
    }
}