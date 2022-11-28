package ru.job4j.ood.srp;

import java.util.function.Predicate;

public class ReportProgrammers implements Report {
    private Store store;

    public ReportProgrammers(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder html = new StringBuilder("<!DOCTYPE html>")
                .append(System.lineSeparator())
                .append("<html>").append(System.lineSeparator())
                .append("<head>").append(System.lineSeparator())
                .append("<meta charset=\"utf-8\">").append(System.lineSeparator())
                .append("<title>REPORT</title>").append(System.lineSeparator())
                .append("</head>").append(System.lineSeparator())
                .append("<body>").append(System.lineSeparator())
                .append(String.format("<p>%s</p>%s",
                        "Name; Hired; Fired; Salary;",
                        System.lineSeparator())
                );
        for (Employee employee : store.findBy(filter)) {
            html.append(String.format("<p>%s;%s;%s;%s;</p>%s",
                    employee.getName(),
                    employee.getHired(),
                    employee.getFired(),
                    employee.getSalary(),
                    System.lineSeparator()
            ));
        }
        html.append("</body>").append(System.lineSeparator()).append("</html>");
        return html.toString();
    }
}
