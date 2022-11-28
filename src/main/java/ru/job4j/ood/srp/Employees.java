package ru.job4j.ood.srp;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "report")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employees {
    @XmlElement(name = "employee")
    List<Employee> employees;

    public Employees() {

    }

    public Employees(List<Employee> employees) {
        this.employees = employees;
    }
}
