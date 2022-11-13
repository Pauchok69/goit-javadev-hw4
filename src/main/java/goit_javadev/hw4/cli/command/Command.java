package goit_javadev.hw4.cli.command;

import java.sql.Connection;

import lombok.Getter;

public abstract class Command implements CommandInterface {
    @Getter
    private Connection connection = null;

    protected Command() {
    }

    protected Command(Connection connection) {
        this.connection = connection;
    }
}
