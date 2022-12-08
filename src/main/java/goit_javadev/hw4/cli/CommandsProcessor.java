package goit_javadev.hw4.cli;

import goit_javadev.hw4.cli.command.*;
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
            case HelpCommand.TITLE:
                command = new HelpCommand();
                break;
            case ProjectAllCommand.TITLE:
                command = new ProjectAllCommand(connection);
                break;
            case ProjectDevelopersSalaryCommand.TITLE:
                command = new ProjectDevelopersSalaryCommand(connection);
                break;
            case ProjectDevelopersListCommand.TITLE:
                command = new ProjectDevelopersListCommand(connection);
                break;
            case DeveloperSkillJavaCommand.TITLE:
                command = new DeveloperSkillJavaCommand(connection);
                break;
            case DeveloperSkillMiddleCommand.TITLE:
                command = new DeveloperSkillMiddleCommand(connection);
                break;
            default:
                throw new CommandNotFoundException(userInput);

        }

        return command;
    }
}
