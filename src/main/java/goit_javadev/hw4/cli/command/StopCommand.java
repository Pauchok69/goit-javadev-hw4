package goit_javadev.hw4.cli.command;

import java.sql.Connection;

public class StopCommand extends Command {
    public StopCommand(Connection connection) {
        super(connection);
    }

    @Override
    public void run() {
        System.out.println("getConnection() = " + getConnection());
        System.out.println("Good bye!");
    }
}
