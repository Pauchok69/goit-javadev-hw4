package goit_javadev.hw4.cli;

import java.sql.Connection;
import java.util.Scanner;

import goit_javadev.hw4.cli.command.CommandInterface;
import goit_javadev.hw4.cli.command.StopCommand;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Console {
    private final Connection connection;

    public void init() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            CommandInterface command = null;

            if (input.equalsIgnoreCase("stop")) {
                command = new StopCommand(connection);
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
