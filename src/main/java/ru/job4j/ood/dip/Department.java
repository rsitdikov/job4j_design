package ru.job4j.ood.dip;

import java.util.HashSet;
import java.util.Set;

/**
 * Первый пример нарушения принципа DIP:
 * Поле хранилища 'staff' не является абстракцией.
 * Для абстрагирования от типа хранилища, необходимо
 * создать отдельный интерфейс, поле инициализировать
 * через конструктор.
 */
public class Department {
    private Set<Employee> staff = new HashSet<>();

   public boolean someAction() {
       return true;
   }

    interface Employee {

        String getName();

        String getJobTitle();
    }
}
