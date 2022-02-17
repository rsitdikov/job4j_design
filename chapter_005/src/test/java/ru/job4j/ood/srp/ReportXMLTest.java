package ru.job4j.ood.srp;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.StringJoiner;

import static org.junit.Assert.*;

public class ReportXMLTest {

    @Test
    public void whenGeneratedXML() {
            MemStore store = new MemStore();
            Calendar now = Calendar.getInstance();
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
            String date = formatter.format(now.getTime());
            Employee worker = new Employee("Ivan", now, now, 100);
            store.add(worker);
            Report report = new ReportXML(store);
            String result = report.generate(employee -> true);
            StringJoiner expected = new StringJoiner("\n");
            expected.add("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>")
                    .add("<report>")
                    .add("    <employee>")
                    .add(String.format("        <fired>%s</fired>", date))
                    .add(String.format("        <hired>%s</hired>", date))
                    .add("        <name>Ivan</name>")
                    .add("        <salary>100.0</salary>")
                    .add("    </employee>")
                    .add("</report>\n");
            assertEquals(result, expected.toString());
    }
}