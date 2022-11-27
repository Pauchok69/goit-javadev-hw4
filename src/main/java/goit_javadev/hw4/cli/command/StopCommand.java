package goit_javadev.hw4.cli.command;

import java.sql.Connection;

public class StopCommand extends Command {
    @Override
    public void run() {
        print("Good bye!");
    }
}
