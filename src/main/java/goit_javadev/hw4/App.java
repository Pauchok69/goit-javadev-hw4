package goit_javadev.hw4;


import goit_javadev.hw4.connection.DbConnectionProvider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
    public static void main(String[] args) {
        try (DbConnectionProvider connectionProvider = new DbConnectionProvider()) {
            Connection connection = connectionProvider.createConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM developers");
            while (resultSet.next()) {
                System.out.println("resultSet.getString(\"first_name\") = " + resultSet.getString("first_name"));
            }
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
