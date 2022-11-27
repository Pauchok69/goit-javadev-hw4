package goit_javadev.hw4.cli;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import goit_javadev.hw4.cli.command.CommandInterface;
import goit_javadev.hw4.cli.command.ProjectDevelopersSalaryCommand;
import goit_javadev.hw4.cli.command.StopCommand;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Console {
    private final Connection connection;

    public void init() {
        CommandInterface command = null;

        while (true) {
            System.out.println("Type a command:");

            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("stop")) {
                command = new StopCommand();
            } else if (input.equalsIgnoreCase("project:developers-salary")) {
                try {
                    command = new ProjectDevelopersSalaryCommand(connection);
                } catch (SQLException e) {
                    System.out.println("Exception = " + e);
                }
            }

            if (command != null) {
                command.run();

                if (command instanceof StopCommand) {
                    break;
                }
            }
        }
    }
}
