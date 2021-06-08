package io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class AnalizyTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenTempFolderIsUsed() throws IOException {
        File source = folder.newFile("server.log");
        File target = folder.newFile("unavailable.csv");
        List<String> expected = List.of("10:57:01;10:59:01", "11:01:02;11:02:02");
        List<String> result = new ArrayList<>();
        try (PrintWriter out = new PrintWriter(source)) {
            String log = String.join(System.lineSeparator(),
                    "200 10:56:01",
                    "500 10:57:01",
                    "400 10:58:01",
                    "200 10:59:01",
                    "500 11:01:02",
                    "200 11:02:02",
                    ""
            );
            out.write(log);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Analizy analizy = new Analizy();
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            result = in.lines().collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(result, expected);
    }

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