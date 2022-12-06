package goit_javadev.hw4.dao;

import goit_javadev.hw4.entity.Developer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeveloperDaoService extends DaoService {
    private final PreparedStatement findAllSt;
    private final PreparedStatement findSt;
    private final PreparedStatement insertSt;
    private final PreparedStatement deleteSt;
    private final PreparedStatement updateSt;
    private final PreparedStatement findByProjectIdSt;

    public DeveloperDaoService(Connection connection) throws SQLException {
        super(connection);

        insertSt = connection.prepareStatement(
                "INSERT INTO developers (birth_date, first_name, last_name, salary, status) VALUES(?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
        );
        updateSt = connection.prepareStatement(
                "UPDATE developers SET birth_date = ?, first_name = ?, last_name = ?, salary = ?, status = ? WHERE id = ?"
        );
        findSt = connection.prepareStatement("SELECT * FROM developers WHERE id = ?");
        deleteSt = connection.prepareStatement("DELETE FROM developers WHERE id = ?");
        findAllSt = connection.prepareStatement("SELECT * FROM developers");
        findByProjectIdSt = connection.prepareStatement("SELECT d.* FROM developers d INNER JOIN developers_projects dp ON d.id = dp.developer_id INNER JOIN projects p ON p.id = dp.project_id WHERE p.id = ?");
    }

    protected Developer hydrate(ResultSet resultSet) throws SQLException {
        Developer developer = new Developer();

        developer.setId(resultSet.getLong("id"));
        developer.setFirstName(resultSet.getString("first_name"));
        developer.setLastName(resultSet.getString("last_name"));
        developer.setStatus(resultSet.getBoolean("status"));
        developer.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
        developer.setSalary(resultSet.getFloat("salary"));

        return developer;
    }

    public long insert(Developer developer) throws SQLException {
        insertSt.setDate(1, Date.valueOf(developer.getBirthDate()));
        insertSt.setString(2, developer.getFirstName());
        insertSt.setString(3, developer.getLastName());
        insertSt.setFloat(4, developer.getSalary());
        insertSt.setBoolean(5, developer.getStatus());

        int affectedRows = insertSt.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating developer failed, no rows affected.");
        }

        try (ResultSet generatedKeys = insertSt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return generatedKeys.getLong(1);
            } else {
                throw new SQLException("Creating developer failed, no ID obtained.");
            }
        }
    }

    public void delete(long id) throws SQLException {
        deleteSt.setLong(1, id);
        deleteSt.executeUpdate();
    }

    public void update(Developer developer) throws SQLException {
        updateSt.setDate(1, Date.valueOf(developer.getBirthDate()));
        updateSt.setString(2, developer.getFirstName());
        updateSt.setString(3, developer.getLastName());
        updateSt.setFloat(4, developer.getSalary());
        updateSt.setBoolean(5, developer.getStatus());
        updateSt.setLong(6, developer.getId());

        updateSt.executeUpdate();
    }

    public List<Developer> findAll() throws SQLException {
        ResultSet resultSet = findAllSt.executeQuery();

        List<Developer> result = new ArrayList<>();

        while (resultSet.next()) {
            result.add(hydrate(resultSet));
        }

        return result;
    }

    public Developer find(long id) throws SQLException {
        findSt.setLong(1, id);

        ResultSet resultSet = findSt.executeQuery();

        if (resultSet.next()) {
            return hydrate(resultSet);
        }

        return null;
    }

    public List<Developer> findByProjectId(long projectId) throws SQLException {
        findByProjectIdSt.setLong(1, projectId);

        ResultSet resultSet = findByProjectIdSt.executeQuery();

        List<Developer> result = new ArrayList<>();

        while (resultSet.next()) {
            result.add(hydrate(resultSet));
        }

        return result;

    }
}
