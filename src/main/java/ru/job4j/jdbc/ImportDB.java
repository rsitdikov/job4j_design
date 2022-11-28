package ru.job4j.jdbc;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {

    private Properties cfg;
    private String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            rd.lines()
                    .map(line -> line.split(";"))
                    .filter(data -> data.length > 1)
                    .forEach(data -> users.add(new User(data[0], data[1])));
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            Statement st = cnt.createStatement();
            st.executeUpdate("CREATE TABLE IF NOT EXISTS users (id serial primary key, name varchar(255), email varchar(255))");
            st.close();
            String sql = "INSERT INTO users (name, email) VALUES ((?), (?))";
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement(sql)) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.executeUpdate();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (FileInputStream in = new FileInputStream("chapter_003/resources/app.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "chapter_003/resources/dump.txt");
        db.save(db.load());
    }
}
