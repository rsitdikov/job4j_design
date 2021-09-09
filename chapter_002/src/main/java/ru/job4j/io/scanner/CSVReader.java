package ru.job4j.io.scanner;

import ru.job4j.io.ArgsName;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {
    private ArgsName arguments = new ArgsName();
    private List<List<String>> data = new ArrayList<>();

    private void validate(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Wrong number of arguments."
                                        + System.lineSeparator()
                                        + "Usage java -jar csvReader.jar -path=SOURCE_FILE -delimiter=DELIMITER "
                                        + "-out=TARGET_FILE -filter=COLUMNS.");
        }
        arguments = ArgsName.of(args);
        if (!Paths.get(arguments.get("path")).toFile().isFile()) {
            throw new IllegalArgumentException(String.format("File '%s' is missing.", arguments.get("path")));
        }
        if (!arguments.get("delimiter").equals(";") && !arguments.get("delimiter").equals(",")) {
            throw new IllegalArgumentException("Wrong delimiter. Usage ',' or ';'.");
        }
        if (!arguments.get("out").equals("stdout") && new File(arguments.get("out")).isFile()) {
            throw new IllegalArgumentException(String.format("The output file '%s'exists. "
                    + "Give a different filename"));
        }
        if (arguments.get("filter").split(",").length < 1) {
            throw new IllegalArgumentException("No filter set for output.");
        }
    }

    private void read() throws FileNotFoundException {
        List<String> headlines;
        Scanner first = new Scanner(Paths.get(arguments.get("path")).toFile());
        if (first.hasNext()) {
            headlines = List.of(first.nextLine()
                    .split(arguments.get("delimiter")));
            data.add(headlines);
            Scanner second = first.useDelimiter(arguments.get("delimiter") + "|\r\n");
            while (second.hasNext()) {
                List<String> line = new ArrayList<>();
                for (int index = 0; index < headlines.size(); index++) {
                    if (second.hasNext()) {
                        line.add(second.next());
                    }
                }
                data.add(line);
            }
        }
    }

    private void write() throws IOException {
        StringBuilder builder = new StringBuilder();
        List<String> headlines = data.get(0);
        List<String> filters = List.of(arguments.get("filter").split(","));
        for (List<String> line : data) {
            for (String headline : headlines) {
                for (String filter : filters) {
                    if (headline.equals(filter)) {
                        int index = headlines.indexOf(headline);
                        builder.append(line.get(index));
                        builder.append(arguments.get("delimiter"));
                    }
                }
            }
            int index = builder.lastIndexOf(arguments.get("delimiter"));
            builder.replace(index, index + 1, System.lineSeparator());
        }
        try (OutputStream os = arguments.get("out").equals("stdout")
                ? System.out
                : new FileOutputStream(arguments.get("out"));
             PrintWriter writer = new PrintWriter(os);
        ) {
            writer.println(builder.toString());
        }
    }

    public void execute(String[] args) throws IOException {
        validate(args);
        read();
        write();
    }

    public static void main(String[] args) throws IOException {
        new CSVReader().execute(args);
    }
}
