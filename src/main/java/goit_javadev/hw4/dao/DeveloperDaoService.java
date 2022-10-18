package goit_javadev.hw4.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import goit_javadev.hw4.entity.Developer;

public class DeveloperDaoService extends DaoService {
    private final PreparedStatement findAllSt;

    public DeveloperDaoService(Connection connection) throws SQLException {
        super(connection);

        findAllSt = connection.prepareStatement("SELECT * FROM developers");
    }

    public List<Developer> findAll() throws SQLException {
        ResultSet resultSet = findAllSt.executeQuery();

        List<Developer> result = new ArrayList<>();

        while (resultSet.next()) {
            Developer developer = new Developer();

            developer.setId(resultSet.getLong("id"));
            developer.setFirstName(resultSet.getString("first_name"));
            developer.setLastName(resultSet.getString("last_name"));
            developer.setStatus(resultSet.getBoolean("status"));

            result.add(developer);
        }

        return result;
    }
}
