package goit_javadev.hw4.cli.command;

import goit_javadev.hw4.cli.ConsoleIO;
import goit_javadev.hw4.cli.ConsoleIOInterface;
import lombok.Getter;
import lombok.Setter;

import java.sql.Connection;
import java.util.Scanner;

public abstract class Command implements CommandInterface {
    @Getter
    @Setter
    private Connection connection = null;

    protected ConsoleIOInterface consoleIO = new ConsoleIO();

    protected Command() {
    }

    protected Command(Connection connection) {
        this.connection = connection;
    }
}
