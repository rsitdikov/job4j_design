package ru.job4j.collection;

import org.junit.Test;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class UserTest {

    @Test
    public void outputMap() {
        Calendar birthday = Calendar.getInstance();
        birthday.set(1980, 12, 31);
        User first = new User("Ivanov", 2, birthday);
        User second = new User("Ivanov", 2, birthday);
        Map<User, Object> map = new HashMap<>();
        map.put(first, "first");
        map.put(second, "second");
        for (Object value : map.values()) {
            System.out.println(value);
        }
    }
}