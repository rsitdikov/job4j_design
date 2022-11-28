package ru.job4j.ood.srp;

import java.util.StringJoiner;

public class Person {
    private String name;
    private String surname;
    private String phone;
    private String address;

    public Person(String name, String surname, String phone, String address) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    /**
     * Метод info нарушает принцип SRP:
     * кроме основной ответственности — хранения данных,
     * появляется дополнительная ответственность, связанная
     * с отображением этих данных.
     */
    public void info() {
        System.out.println(new StringJoiner("\t")
            .add(name)
            .add(surname)
            .add(phone)
            .add(address)
            .toString()
        );
    }
}
