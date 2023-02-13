package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertNull;


class AnalizyTest {

    @Test
    void whenTempFolderIsUsed(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("server.log").toFile();
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
        File target  = tempDir.resolve("target.txt").toFile();
        Analizy analizy = new Analizy();
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        List<String> result = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            result = in.lines().collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> expected = List.of("10:57:01;10:59:01", "11:01:02;11:02:02");
        assertThat(expected).isEqualTo(result);
    }

    @Test
    public void whenTwoPeriodsAreUnavailable() throws IOException {
        String source = "./data/server.log", target = "./data/unavailable.csv";
        Analizy analizy = new Analizy();
        analizy.unavailable(source, target);
        BufferedReader in = new BufferedReader(new FileReader(target));
        assertThat("10:57:01;10:59:01").isEqualTo(in.readLine());
        assertThat("11:01:02;11:02:02").isEqualTo(in.readLine());
        assertNull(in.readLine());
        in.close();
    }
}