package ru.job4j.ood.ocp;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Третий пример нарушения принципа OCP:
 * Поле tasks представляет собой не абстракцию,
 * а реализацию интерфейса List - ArrayList.
 */
public class ToDo {
    ArrayList<Task> tasks = new ArrayList<>();

    public boolean add(Task task) {
        return tasks.add(task);
    }

    public boolean remove(Task task) {
        return tasks.remove(task);
    }

    public boolean replace(Task old, Task now) {
        boolean rsl = false;
        int index = tasks.indexOf(old);
        if (index != -1) {
            tasks.set(index, now);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public String toString() {
        return "ToDo{"
                + "tasks=" + tasks
                + '}';
    }

    public static void main(String[] args) {
        ToDo todo = new ToDo();
        Task first = new SimpleTask("first", "no comment", Calendar.getInstance());
        todo.add(first);
        System.out.println(todo);
        Task second = new SimpleTask("second", " ", Calendar.getInstance());
        todo.replace(first, second);
        System.out.println(todo);
        todo.remove(second);
        System.out.println(todo);
    }
}

abstract class Task {

}

class SimpleTask extends Task {
    private String caption;
    private String notes;
    private Calendar due;

    public SimpleTask(String caption, String notes, Calendar due) {
        this.caption = caption;
        this.notes = notes;
        this.due = due;
    }

    @Override
    public String toString() {
        return "SimpleTask{"
                + "caption='" + caption + '\''
                + ", notes='" + notes + '\''
                + ", due=" + due + '}';
    }
}
