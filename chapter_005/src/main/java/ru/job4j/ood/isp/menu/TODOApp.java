package ru.job4j.ood.isp.menu;

import java.util.Optional;
import java.util.Scanner;

public class TODOApp {
    public static final ActionDelegate STUB_ACTION = System.out::println;

    private final int addTask = 0;
    private final int selectTask = 1;
    private final int printTasks = 2;
    private final int exit = 3;

    public void execute(Scanner scanner) {
        Menu menu = new SimpleMenu();
        boolean run = true;
        while (run) {
            showMenu();
            System.out.print("Select: ");
            int select = Integer.parseInt(scanner.nextLine());
            if (select == addTask) {
                System.out.println("Add a new task:");
                System.out.println("Enter parent task name or ROOT: ");
                String parent = scanner.nextLine();
                if ("ROOT".equals(parent)) {
                    parent = Menu.ROOT;
                }
                System.out.println("Enter a name for the new task: ");
                String child = scanner.nextLine();
                if (menu.add(parent, child, STUB_ACTION)) {
                    System.out.println("Task added.");
                } else {
                    System.out.println("Task could not be added.");
                }
            } else if (select == selectTask) {
                System.out.println("Task selection:");
                System.out.println("Enter the name of the task: ");
                String task = scanner.nextLine();
                Optional<Menu.MenuItemInfo> info = menu.select(task);
                if (info.isPresent()) {
                    System.out.println("Task name: "
                    + info.get().getName());
                    System.out.println("Task number: "
                    + info.get().getNumber());
                } else {
                    System.out.println("Task not found.");
                }
            } else if (select == printTasks) {
                System.out.println("All Tasks:");
                SimpleMenuPrinter printer = new SimpleMenuPrinter();
                printer.print(menu);
            } else if (select == exit) {
                run = false;
            }
        }
    }

    private void showMenu() {
        String[] menu = {"Add Task", "Select Task", "Print All Tasks", "Exit"};
        System.out.println("Menu:");
        for (int i = 0; i < menu.length; i++) {
            System.out.println(i + ". " + menu[i]);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TODOApp app = new TODOApp();
        app.execute(scanner);
    }
}

