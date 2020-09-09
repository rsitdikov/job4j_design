package io;

import java.nio.file.Paths;
import java.util.Arrays;

public class ArgZip {

    private final String[] args;

    public ArgZip(String[] args) {
        this.args = args;
    }

    public boolean valid() {
        if (args.length != 3) {
            throw new IllegalArgumentException("Wrong number of arguments."
                    + System.lineSeparator()
                    + "Usage java -jar Zip.jar -d=ZIP_FOLDER "
                    + "-e=EXCLUDED_FILE_EXTENSIONS "
                    + "-o=OUTPUT_FILE");
        } else if (directory() == null || !Paths.get(directory()).toFile().isDirectory()) {
            throw new IllegalArgumentException("Zip folder is missing.");
        } else if (exclude() == null) {
            throw new IllegalArgumentException("Excluded file extensions are not specified.");
        } else if (output() == null) {
            throw new IllegalArgumentException("No output file specified.");
        }
        return true;
    }

    public String directory() {
        return takeValue("-d=");
    }

    public String exclude() {
        return takeValue("-e=");
    }

    public String output() {
        return takeValue("-o=");
    }
    private String takeValue(String key) {
        return Arrays.stream(args)
                .filter(arg -> arg.contains(key) && !arg.endsWith(key))
                .map(arg -> arg.split("=")[1])
                .findFirst()
                .orElse(null);
    }
}
