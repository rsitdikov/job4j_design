package ru.job4j.ood.srp;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Calendar;

public class ReportAccountantsTest {

    @Test
    public void whenGeneratedForAccontants() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        ReportAccountants engine = new ReportAccountants(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(String.format("%.2f руб", worker.getSalary())).append(";")
                .append(System.lineSeparator());
      assertEquals(engine.generate(em -> true), expect.toString());
    }
}