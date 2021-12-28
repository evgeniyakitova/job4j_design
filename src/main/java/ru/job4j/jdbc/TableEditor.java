package ru.job4j.jdbc;

import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        try {
            connection = DriverManager.getConnection(
                    properties.getProperty("db.url"),
                    properties.getProperty("db.login"),
                    properties.getProperty("db.password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName) {
            String sql = String.format(
                    "create table if not exists %s();",
                    tableName);
            runQuery(sql);
    }

    public void dropTable(String tableName) {
            String sql = String.format(
                    "drop table if exists %s;",
                    tableName);
            runQuery(sql);
    }

    public void addColumn(String tableName, String columnName, String type) {
            String sql = String.format(
                    "alter table %s add column %s %s;",
                    tableName, columnName, type);
            runQuery(sql);
    }

    public void dropColumn(String tableName, String columnName) {
            String sql = String.format(
                    "alter table %s drop column %s;",
                    tableName, columnName);
        runQuery(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
            String sql = String.format(
                    "alter table %s rename column %s to %s;",
                    tableName, columnName, newColumnName);
            runQuery(sql);
    }

    private void runQuery(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static String getTableScheme(Connection connection, String tableName) throws Exception {
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

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
