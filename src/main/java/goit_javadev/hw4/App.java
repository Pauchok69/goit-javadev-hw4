package goit_javadev.hw4;


import java.sql.Connection;
import java.sql.SQLException;

import goit_javadev.hw4.cli.Console;
import goit_javadev.hw4.connection.DbConnectionProvider;
import goit_javadev.hw4.dao.DeveloperDaoService;
import goit_javadev.hw4.entity.Developer;

public class App {
    public static void main(String[] args) {
        try (DbConnectionProvider connectionProvider = new DbConnectionProvider()) {
            Connection connection = connectionProvider.createConnection();

            new Console(connection).init();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
