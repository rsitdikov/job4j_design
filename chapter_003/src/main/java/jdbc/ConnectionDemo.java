package jdbc;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        final String file = "./chapter_003/app.properties";
        final Map<String, String> properties = new HashMap<>();
        Class.forName("org.postgresql.Driver");
        try (BufferedReader read = new BufferedReader(new FileReader(file))) {
            read.lines()
                    .map(line -> line.split("="))
                    .filter(data -> data.length > 1)
                    .forEach(data -> properties.put(data[0], data[1]));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(
                properties.get("url"), properties.get("login"), properties.get("password"))) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}
