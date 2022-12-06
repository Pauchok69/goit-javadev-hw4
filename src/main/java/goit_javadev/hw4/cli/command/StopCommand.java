package goit_javadev.hw4.cli.command;

public class StopCommand extends Command {
    public static final String TITLE = "stop";
    public static final String DESCRIPTION = "Exits from application";

    @Override
    public void run() {
        print("Good bye!");
    }
}
