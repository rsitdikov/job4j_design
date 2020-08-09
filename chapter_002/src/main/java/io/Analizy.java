package io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;


public class Analizy {
    public void unavailable(String source, String target) {
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            String line;
            boolean isFindBeginning = true;
            while ((line = in.readLine()) != null) {
                if (isFindBeginning && (line.contains("400 ") || line.contains("500 "))) {
                    rsl.append(line.split(" ")[1]);
                    isFindBeginning = false;
                } else if (!isFindBeginning && (line.contains("200 ") || line.contains("300 "))) {
                    rsl.append(String.join("", ";", line.split(" ")[1], System.lineSeparator()));
                    isFindBeginning = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            out.write(rsl.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
