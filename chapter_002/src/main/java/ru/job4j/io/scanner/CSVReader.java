package ru.job4j.io.scanner;

import ru.job4j.io.ArgsName;

import java.io.*;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.StringJoiner;

public class CSVReader {
    private static ArgsName arguments = new ArgsName();

    private static void validate(ArgsName arguments) {
        String[] parameters = {"Path", "Delimiter", "Out", "Filter"};
        for (String parameter : parameters) {
            if (arguments.get(parameter.toLowerCase()) == null) {
                throw new IllegalArgumentException(String.format(
                        "%s parameter not found.", parameter));
            }
        }
        CSVReader.arguments = arguments;
        if (!Paths.get(arguments.get("path")).toFile().isFile()) {
            throw new IllegalArgumentException(String.format("File '%s' is missing.",
                    arguments.get("path")));
        }
        if (!arguments.get("delimiter").equals(";") && !arguments.get("delimiter").equals(",")) {
            throw new IllegalArgumentException("Wrong delimiter. Usage ',' or ';'.");
        }
    }

    private static boolean check(String value, String[] filters) {
        boolean rsl = false;
        for (String filter : filters) {
            if (filter.equals(value)) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    private static void processData() throws FileNotFoundException {
        String[] filters = arguments.get("filter").split(",");
        try (Scanner scaner = new Scanner(Paths.get(arguments.get("path")).toFile());
             PrintWriter writer = new PrintWriter(arguments.get("out").equals("stdout")
                     ? System.out
                     : new FileOutputStream(arguments.get("out")))) {
            if (scaner.hasNext()) {
                String[] columns = scaner.nextLine()
                        .split(arguments.get("delimiter"));
                StringJoiner row = new StringJoiner(arguments.get("delimiter"));
                for (String column : columns) {
                    if (check(column, filters)) {
                        row.add(column);
                    }
                }
                if (row.toString().equals("")) {
                    throw new IllegalArgumentException("Wrong filter values.");
                }
                writer.println(row.toString());
                scaner.useDelimiter(arguments.get("delimiter")
                        + "|"
                        + System.lineSeparator());
                while (scaner.hasNext()) {
                    row = new StringJoiner(arguments.get("delimiter"));
                    for (String column : columns) {
                        if (scaner.hasNext()) {
                            String data = scaner.next();
                            if (check(column, filters)) {
                                row.add(data);
                            }
                        }
                    }
                    writer.println(row.toString());
                }
            }
        }
    }

    public static void handle(ArgsName arguments) throws IOException {
        validate(arguments);
        processData();
    }

    public static void main(String[] args) throws IOException {
         CSVReader.handle(ArgsName.of(args));
    }
}
