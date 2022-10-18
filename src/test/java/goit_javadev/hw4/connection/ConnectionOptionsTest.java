package goit_javadev.hw4.connection;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionOptionsTest {
    static ConnectionOptions connectionOptions;

    @BeforeAll
    static void setUp() {
        connectionOptions = new ConnectionOptions();
    }

    @Test
    void getDbUrlIsCorrect() {
        assertNotNullAndIsString(connectionOptions.getDbUrl());
    }

    @Test
    void getDbUserIsCorrect() {
        assertNotNullAndIsString(connectionOptions.getDbUser());
    }

    @Test
    void getDbPassIsCorrect() {
        assertNotNullAndIsString(connectionOptions.getDbPass());
    }

    @Test
    void getDbNameIsCorrect() {
        assertNotNullAndIsString(connectionOptions.getDbName());
    }

    private static void assertNotNullAndIsString(Object data) {
        assertNotNull(data);
        assertInstanceOf(String.class, data);
    }
}