package goit_javadev.hw4.cli;

import goit_javadev.hw4.cli.command.CommandInterface;
import goit_javadev.hw4.cli.command.StopCommand;
import goit_javadev.hw4.exceptions.CommandNotFoundException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Console {
    private final Connection connection;
    private final CommandsProcessor commandsProcessor;

    public Console(Connection connection) {
        this.connection = connection;
        this.commandsProcessor = new CommandsProcessor(connection);
    }

    public void init() {
        while (true) {
            System.out.println("Type a command:");

            Scanner scanner = new Scanner(System.in);

            try {
                CommandInterface command = commandsProcessor.defineCommand(scanner.nextLine());

                if (command instanceof StopCommand) {
                    break;
                }

                command.run();
            } catch (CommandNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
