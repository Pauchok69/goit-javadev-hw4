package goit_javadev.hw4;


import goit_javadev.hw4.cli.Console;
import goit_javadev.hw4.connection.DbConnectionProvider;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.TimeZone;

public class App {
    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Kiev"));

        try (DbConnectionProvider connectionProvider = new DbConnectionProvider()) {
            Connection connection = connectionProvider.createConnection();

            new Console(connection).init();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
