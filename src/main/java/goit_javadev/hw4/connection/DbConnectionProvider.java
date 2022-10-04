package goit_javadev.hw4.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbConnectionProvider implements AutoCloseable {
    private final List<Connection> connections;

    public DbConnectionProvider() {
        this.connections = new ArrayList<>();
    }

    public Connection createConnection() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://0.0.0.0:5433/hw4", "user", "pass");
        connection.setSchema("hw4");
        connections.add(connection);

        return connection;
    }

    @Override
    public void close() throws SQLException {
        for (Connection connection : connections) {
            if (!connection.isClosed()) {
                connection.close();
            }
        }
    }
}
