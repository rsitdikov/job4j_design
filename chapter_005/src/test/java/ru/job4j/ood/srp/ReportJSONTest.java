package ru.job4j.ood.srp;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.StringJoiner;

import static org.junit.Assert.*;

public class ReportJSONTest {

    @Test
    public void whenGeneratedJSON() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report report = new ReportJSON(store);
        String result = report.generate(employee -> true);
        StringJoiner expected = new StringJoiner(",")
                .add("[{\"name\":\"Ivan\"")
                .add(String.format("\"hired\":{\"year\":%s", now.get(Calendar.YEAR)))
                .add(String.format("\"month\":%s", now.get(Calendar.MONTH)))
                .add(String.format("\"dayOfMonth\":%s", now.get(Calendar.DAY_OF_MONTH)))
                .add(String.format("\"hourOfDay\":%s", now.get(Calendar.HOUR_OF_DAY)))
                .add(String.format("\"minute\":%s", now.get(Calendar.MINUTE)))
                .add(String.format("\"second\":%s}", now.get(Calendar.SECOND)))
                .add(String.format("\"fired\":{\"year\":%s", now.get(Calendar.YEAR)))
                .add(String.format("\"month\":%s", now.get(Calendar.MONTH)))
                .add(String.format("\"dayOfMonth\":%s", now.get(Calendar.DAY_OF_MONTH)))
                .add(String.format("\"hourOfDay\":%s", now.get(Calendar.HOUR_OF_DAY)))
                .add(String.format("\"minute\":%s", now.get(Calendar.MINUTE)))
                .add(String.format("\"second\":%s}", now.get(Calendar.SECOND)))
                .add("\"salary\":100.0}]");
        assertEquals(result, expected.toString());
    }
}