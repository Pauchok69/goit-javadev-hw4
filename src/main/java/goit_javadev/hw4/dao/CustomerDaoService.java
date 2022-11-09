package goit_javadev.hw4.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import goit_javadev.hw4.entity.Customer;

public class CustomerDaoService extends DaoService {
    private final PreparedStatement findAllSt;
    private final PreparedStatement findSt;
    private final PreparedStatement insertSt;
    private final PreparedStatement deleteSt;
    private final PreparedStatement updateSt;

    public CustomerDaoService(Connection connection) throws SQLException {
        super(connection);

        insertSt = connection.prepareStatement(
                "INSERT INTO customers (first_name, last_name, status) VALUES(?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
        );
        updateSt = connection.prepareStatement(
                "UPDATE customers SET first_name = ?, last_name = ?, status = ? WHERE id = ?"
        );
        findSt = connection.prepareStatement("SELECT * FROM customers WHERE id = ?");
        deleteSt = connection.prepareStatement("DELETE FROM customers WHERE id = ?");
        findAllSt = connection.prepareStatement("SELECT * FROM customers");
    }

    protected Customer hydrate(ResultSet resultSet) throws SQLException {
        Customer customer = new Customer();

        customer.setId(resultSet.getLong("id"));
        customer.setFirstName(resultSet.getString("first_name"));
        customer.setLastName(resultSet.getString("last_name"));
        customer.setStatus(resultSet.getBoolean("status"));

        return customer;
    }

    public long insert(Customer customer) throws SQLException {
        insertSt.setString(1, customer.getFirstName());
        insertSt.setString(2, customer.getLastName());
        insertSt.setBoolean(3, customer.getStatus());

        int affectedRows = insertSt.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating customer failed, no rows affected.");
        }

        try (ResultSet generatedKeys = insertSt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return generatedKeys.getLong(1);
            } else {
                throw new SQLException("Creating customer failed, no ID obtained.");
            }
        }
    }

    public void delete(long id) throws SQLException {
        deleteSt.setLong(1, id);
        deleteSt.executeUpdate();
    }

    public void update(Customer customer) throws SQLException {
        updateSt.setString(1, customer.getFirstName());
        updateSt.setString(2, customer.getLastName());
        updateSt.setBoolean(3, customer.getStatus());
        updateSt.setLong(4, customer.getId());

        updateSt.executeUpdate();
    }

    public List<Customer> findAll() throws SQLException {
        ResultSet resultSet = findAllSt.executeQuery();

        List<Customer> result = new ArrayList<>();

        while (resultSet.next()) {
            result.add(hydrate(resultSet));
        }

        return result;
    }

    public Customer find(long id) throws SQLException {
        findSt.setLong(1, id);

        ResultSet resultSet = findSt.executeQuery();

        if (resultSet.next()) {
            return hydrate(resultSet);
        }

        return null;
    }
}
