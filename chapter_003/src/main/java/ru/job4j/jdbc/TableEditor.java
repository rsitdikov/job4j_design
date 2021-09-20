package ru.job4j.jdbc;

import java.io.FileReader;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) throws SQLException,
            ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(properties.getProperty("url"),
                properties.getProperty("login"),
                properties.getProperty("password")
        );
    }

    private void execute(String sql) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    public void createTable(String tableName) throws Exception {
        execute(String.format("create table if not exists %s();", tableName));
    }

    public void dropTable(String tableName) throws Exception {
        execute(String.format("drop table if exists %s;", tableName));
    }

    public void addColumn(String tableName, String columnName, String type)
            throws Exception {
        execute(String.format("alter table %s add  %s %s;", tableName,
                columnName, type)
        );
    }

    public void dropColumn(String tableName, String columnName)
            throws Exception {
        execute(String.format("alter table %s drop %s;", tableName, columnName)
        );
    }

    public void renameColumn(String tableName, String columnName,
                             String newColumnName) throws Exception {
        execute(String.format("alter table %s rename column %s to %s;", tableName,
                columnName, newColumnName)
                );
    }

    public static String getTableScheme(Connection connection, String tableName)
            throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    public void info(String tableName) throws Exception {
       try {
           System.out.printf("Table schema:%s %s",
                   System.lineSeparator(),
                   getTableScheme(connection, tableName));
       } catch (Exception e) {
           System.out.println("Table schema not available");
       }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        try (FileReader reader = new FileReader(
                "./chapter_003/app.properties")) {
            properties.load(reader);
        }
        try (TableEditor tableEditor = new TableEditor(properties)) {
            String tableName = "testTable";
            System.out.println("Table creation...");
            tableEditor.createTable(tableName);
            tableEditor.info(tableName);
            String columnName = "data";
            String type = "varchar(255)";
            System.out.println("Adding a column...");
            tableEditor.addColumn(tableName, columnName, type);
            tableEditor.info(tableName);
            String newColumnName = "results";
            System.out.println("Rename a column...");
            tableEditor.renameColumn(tableName, columnName, newColumnName);
            tableEditor.info(tableName);
            System.out.println("Deleting a column...");
            tableEditor.dropColumn(tableName, newColumnName);
            tableEditor.info(tableName);
            System.out.println("Deleting a table");
            tableEditor.dropTable(tableName);
            tableEditor.info(tableName);
        }
    }
}
