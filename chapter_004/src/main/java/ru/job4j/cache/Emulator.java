package ru.job4j.cache;

import java.io.File;
import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Emulator {
    private String directory;
    private AbstractCache<String, String> cache = null;
    private List<MenuItem> menu;

    class MenuItem {
        private String name;
        private Supplier<Boolean> action;

        MenuItem(String name, Supplier<Boolean> action) {
            this.name = name;
            this.action = action;
        }
    }

    private boolean containsTextFile(String dir) {
        File folder = new File(dir);
        boolean rsl = false;
        for (File file : folder.listFiles()) {
            if (file.isFile() && file.getName().endsWith(".txt")) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    private boolean read() {
        if (directory != null) {
            System.out.println("Read cache...");
            String question = "Select text file from cache";
            String message = "This file cannot be in the cache.";
            Predicate<String> predicate =
                    s -> new File(String.join(
                            File.separator, directory, s)).exists()
                            && s.endsWith(".txt");
            String file = dataInput(question, message, predicate);
            System.out.println(cache.get(file));
            System.out.println(String.format("File '%s' readed.", file));
        } else {
            System.out.println("First select a directory and upload files.");
        }
        return true;
    }

    private boolean load() {
        if (directory != null) {
            System.out.println("Load file...");
            String question = "Select a text file";
            String message = "The file was not found "
                    + "or it is not a text file.";
            Predicate<String> predicate =
                    s -> new File(String.join(
                            File.separator, directory, s)).exists()
                            && s.endsWith(".txt");
            String file = dataInput(question, message, predicate);
            cache.put(file, cache.load(file));
            System.out.println(String.format("File '%s' loaded.", file));
        } else {
            System.out.println("First select the directory");
        }
        return true;
    }

    private boolean select() {
        System.out.println("Select directory...");
        String question = "Enter path";
        String message = "The path does not exist.";
        Predicate<String> predicate =
                s -> new File(s).exists()
                        && new File(s).isDirectory();
        String dir = dataInput(question, message, predicate);
        if (containsTextFile(dir)) {
            directory = dir;
            cache = new DirFileCache(directory);
            System.out.println(String.format(
                    "Directory '%s' selected.", dir));
        } else {
            System.out.println(String.format("Directory '%s' not selected %s"
                            + "because it does not contain text files.",
                    dir, System.lineSeparator()
            ));
        }
        return true;
    }

    private boolean quit() {
        System.out.println("Exit..");
        return false;
    }

    private String dataInput(String question, String message, Predicate<String> predicate) {
        Scanner scanner = new Scanner(System.in);
        boolean isLoop = true;
        String result = null;
        while (isLoop) {
            System.out.print(question + ": ");
            result = scanner.nextLine();
            if (predicate.test(result)) {
                isLoop = false;
            } else {
                System.out.println(message);
            }
        }
        return result;
    }

    private void printMenu() {
        System.out.println("*".repeat(20));
        System.out.println("Menu:");
        for (int index = 0; index < menu.size(); index++) {
            System.out.println(String.format(
                    "%d.%s", index, menu.get(index).name));
        }
        System.out.println("*".repeat(20));
    }

    public void execute() {
        boolean isLoop = true;
        while (isLoop) {
            printMenu();
            String question = String.format(
                    "Select menu item (0 - %d)", menu.size() - 1);
            String message = "This menu item was not found.";
            Predicate<String> predicate = s -> s.length() == 1
                    && s.charAt(0) >= '0'
                    && s.charAt(0) <= (char) (menu.size() - 1 + '0');
            String result = dataInput(question, message, predicate);

            isLoop = menu.get(Integer.parseInt(result)).action.get();
        }
    }

    public void init() {
        menu = List.of(
                new MenuItem("Select directory", this::select),
                new MenuItem("Load file", this::load),
                new MenuItem("Read cache", this::read),
                new MenuItem("Quit", this::quit)
        );
    }

    public static void main(String[] args) {
        Emulator emulator = new Emulator();
        emulator.init();
        emulator.execute();
    }
}
