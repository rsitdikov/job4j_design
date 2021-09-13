package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Company {
    private String name;
    private boolean isState;
    private int founded;
    private Address address;
    private String[] departments;

    public Company(String name, boolean isState, int founded, Address address, String[] departments) {
        this.name = name;
        this.isState = isState;
        this.founded = founded;
        this.address = address;
        this.departments = departments;
    }

    @Override
    public String toString() {
        return "Company{"
                + "name='" + name + '\''
                + ", isState=" + isState
                + ", founded=" + founded
                + ", address=" + address
                + ", departments=" + Arrays.toString(departments)
                + '}';
    }

     public static void main(String[] args) {
        final Company company = new Company(
                "ООО Недра", false, 1995, new Address("614064",
                "Пермь", "Льва Шатрова", "13а"),
                new String[] {"Администрация", "Бухгалтерия", "Плановый", "Производственный",
                        "Транспортный"});
        System.out.println(String.format("Объект до сериализации: %s", company));
        final Gson gson = new GsonBuilder().create();
        final String companyJson = gson.toJson(company);
        final Company companyFromJson = gson.fromJson(companyJson, Company.class);
        System.out.println(String.format("Объект после сериализации: %s", companyFromJson));
     }
}
