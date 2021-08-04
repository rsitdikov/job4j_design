package ru.job4j.io;

import java.io.FileOutputStream;

public class Multiple {

    public static void main(String[] args) {
        String text = "";
        int max = 9;
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            for (int i = 1; i <= max; i++) {
                for (int j = 1; j <= max; j++) {
                    text =  text.concat(String.format("%d * %d = %d %n", i, j, i * j));
                }
                text = text.concat(System.lineSeparator());
            }
            out.write(text.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
