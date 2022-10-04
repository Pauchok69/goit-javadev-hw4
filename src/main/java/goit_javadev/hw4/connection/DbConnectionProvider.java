package goit_javadev.hw4.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbConnectionProvider implements AutoCloseable {
    private final ConnectionOptions connectionOptions;
    private final List<Connection> connections;

    public DbConnectionProvider() {
        this.connections = new ArrayList<>();
        this.connectionOptions = new ConnectionOptions();
    }

    public Connection createConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(
                connectionOptions.getDbUrl(),
                connectionOptions.getDbUser(),
                connectionOptions.getDbPass()
        );
        connection.setSchema(connectionOptions.getDbName());
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
