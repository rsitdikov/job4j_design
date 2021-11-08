package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

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

    public String getName() {
        return name;
    }

    public boolean isState() {
        return isState;
    }

    public int getFounded() {
        return founded;
    }

    public Address getAddress() {
        return address;
    }

    public String[] getDepartments() {
        return departments;
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

        JSONObject jsonAddress = new JSONObject("{\"zip\":\"614064\""
                + ",\"city\":\"Пермь\",\"street\":\"Льва Шатрова\",\"building\":\"13а\"}");
        List<String> list = List.of("Администрация", "Бухгалтерия", "Плановый", "Производственный",
                "Транспортный");
        JSONArray jsonDepartments = new JSONArray(list);
        final Company company = new Company(
                "ООО Недра", false, 1995, new Address("620000",
                "Екатеринбург", "Малышева", "5"),
                new String[] {"Администрация", "Бухгалтерия", "Плановый"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", company.getName());
        jsonObject.put("isState", company.isState());
        jsonObject.put("founded", company.getFounded());
        jsonObject.put("address", jsonAddress);
        jsonObject.put("departments", jsonDepartments);
        System.out.println(jsonObject);
        System.out.println(new JSONObject(company));
    }
}
