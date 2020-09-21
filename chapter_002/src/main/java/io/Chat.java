package io;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Chat {
    private static final int CONTINUE = 0;
    private static final int PAUSE = 1;
    private static final int EXIT = 2;
    private final Map<String, Integer> dispath = Map.of(
            "продолжить", CONTINUE,
            "стоп", PAUSE,
            "закончить", EXIT
    );
    private final String output;
    private List<String> answers = new ArrayList<>();

    public Chat(String input, String output) {
        this.output = output;
        try (BufferedReader in = new BufferedReader(new FileReader(input))) {
            this.answers = in.lines().collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String fromUser() {
        System.out.print("You: ");
        String msg = new Scanner(System.in).nextLine();
        save("You: " + msg);
        return msg;
    }

    private void fromBot() {
        int index = new Random().nextInt(answers.size() - 1);
        String msg = "Bot: " + answers.get(index);
        System.out.println(msg);
        save(msg);
    }

    private void save(String msg) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(
                new FileOutputStream(output, true)))) {
            out.println(String.format("%s: %s", new Date().toString(), msg));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void execute() {
        int status = CONTINUE;
        do {
            String msg = fromUser().toLowerCase();
            status = dispath.getOrDefault(msg, status);
            if (status == CONTINUE) {
                fromBot();
            }
        } while (status != EXIT);
    }

    public static void main(String[] args) {
        new Chat("chapter_002/data/answers.txt", "chapter_002/data/log.log")
                .execute();
    }
}
