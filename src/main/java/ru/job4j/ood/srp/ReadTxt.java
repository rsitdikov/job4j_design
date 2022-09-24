package ru.job4j.ood.srp;

import java.io.FileInputStream;

public class ReadTxt {
    private static StringBuilder text;

    public ReadTxt() {
        text = null;
    }

    /**
     * метод read имеет лишнюю функциональность:
     * кроме чтения текстового файла
     * выполняет его вывод на консоль.
     */
    public static void read(String file) {
        try (FileInputStream in = new FileInputStream(file)) {
            text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            System.out.println(text.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
