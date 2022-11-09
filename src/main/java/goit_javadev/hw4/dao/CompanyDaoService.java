package goit_javadev.hw4.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import goit_javadev.hw4.entity.Company;

public class CompanyDaoService extends DaoService {
    private final PreparedStatement findAllSt;
    private final PreparedStatement findSt;
    private final PreparedStatement insertSt;
    private final PreparedStatement deleteSt;
    private final PreparedStatement updateSt;

    public CompanyDaoService(Connection connection) throws SQLException {
        super(connection);

        insertSt = connection.prepareStatement(
                "INSERT INTO companies (name, status, date_start) VALUES(?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
        );
        updateSt = connection.prepareStatement(
                "UPDATE companies SET name = ?, status = ?, date_start = ? WHERE id = ?"
        );
        findSt = connection.prepareStatement("SELECT * FROM companies WHERE id = ?");
        deleteSt = connection.prepareStatement("DELETE FROM companies WHERE id = ?");
        findAllSt = connection.prepareStatement("SELECT * FROM companies");
    }

    protected Company hydrate(ResultSet resultSet) throws SQLException {
        Company company = new Company();

        company.setId(resultSet.getLong("id"));
        company.setName(resultSet.getString("name"));
        company.setStatus(resultSet.getBoolean("status"));
        company.setDateStart(resultSet.getDate("salary").toLocalDate());

        return company;
    }

    public long insert(Company company) throws SQLException {
        insertSt.setString(1, company.getName());
        insertSt.setBoolean(2, company.getStatus());
        insertSt.setDate(3, Date.valueOf(company.getDateStart()));

        int affectedRows = insertSt.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating company failed, no rows affected.");
        }

        try (ResultSet generatedKeys = insertSt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return generatedKeys.getLong(1);
            } else {
                throw new SQLException("Creating company failed, no ID obtained.");
            }
        }
    }

    public void delete(long id) throws SQLException {
        deleteSt.setLong(1, id);
        deleteSt.executeUpdate();
    }

    public void update(Company company) throws SQLException {
        updateSt.setString(1, company.getName());
        updateSt.setBoolean(2, company.getStatus());
        updateSt.setDate(3, Date.valueOf(company.getDateStart()));
        updateSt.setLong(4, company.getId());

        updateSt.executeUpdate();
    }

    public List<Company> findAll() throws SQLException {
        ResultSet resultSet = findAllSt.executeQuery();

        List<Company> result = new ArrayList<>();

        while (resultSet.next()) {
            result.add(hydrate(resultSet));
        }

        return result;
    }

    public Company find(long id) throws SQLException {
        findSt.setLong(1, id);

        ResultSet resultSet = findSt.executeQuery();

        if (resultSet.next()) {
            return hydrate(resultSet);
        }

        return null;
    }
}
