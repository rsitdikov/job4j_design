package ru.job4j.ood.srp;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Calendar;

public class ReportHRTest {

    @Test
    public void whenGeneratedForHR() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee first = new Employee("Ivan", now, now, 100);
        Employee second = new Employee("Stepan", now, now, 200);
        store.add(first);
        store.add(second);
        ReportHR engine = new ReportHR(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(second.getName()).append(";")
                .append(second.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(first.getName()).append(";")
                .append(first.getSalary()).append(";")
                .append(System.lineSeparator());
        assertEquals(engine.generate(em -> true), expect.toString());
    }
}
