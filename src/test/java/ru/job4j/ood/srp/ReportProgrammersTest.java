package ru.job4j.ood.srp;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;

public class ReportProgrammersTest {

    @Test
    public void whenGeneratedForProgrammers() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        ReportProgrammers engine = new ReportProgrammers(store);
        StringBuilder expect = new StringBuilder()
                .append("<!DOCTYPE html>").append(System.lineSeparator())
                .append("<html>").append(System.lineSeparator())
                .append("<head>").append(System.lineSeparator())
                .append("<meta charset=\"utf-8\">").append(System.lineSeparator())
                .append("<title>REPORT</title>").append(System.lineSeparator())
                .append("</head>").append(System.lineSeparator())
                .append("<body>").append(System.lineSeparator())
                .append(String.format("<p>%s</p>%s", "Name; Hired; Fired; Salary;", System.lineSeparator()))
                .append(String.format("<p>%s;%s;%s;%s;</p>%s",
                        worker.getName(),
                        worker.getHired(),
                        worker.getFired(),
                        worker.getSalary(),
                        System.lineSeparator()
                ))
                .append("</body>").append(System.lineSeparator())
                .append("</html>");
        assertEquals(engine.generate(em -> true), expect.toString());
    }
}