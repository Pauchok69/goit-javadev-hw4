package goit_javadev.hw4.cli;

import goit_javadev.hw4.cli.command.CommandInterface;
import goit_javadev.hw4.cli.command.ProjectDevelopersSalaryCommand;
import goit_javadev.hw4.cli.command.StopCommand;
import goit_javadev.hw4.exceptions.CommandNotFoundException;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.SQLException;

@RequiredArgsConstructor
public class CommandsProcessor {
    private final Connection connection;

    public CommandInterface defineCommand(String userInput) throws CommandNotFoundException, SQLException {
        CommandInterface command;

        switch (userInput.toLowerCase()) {
            case StopCommand.TITLE:
            case "exit":
            case "quit":
                command = new StopCommand();
                break;
            case ProjectDevelopersSalaryCommand.TITLE:
                command = new ProjectDevelopersSalaryCommand(connection);
                break;
            default:
                throw new CommandNotFoundException(userInput);

        }

        return command;
    }
}
