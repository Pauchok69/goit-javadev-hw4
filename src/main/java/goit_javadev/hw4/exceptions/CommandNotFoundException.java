package goit_javadev.hw4.exceptions;

public class CommandNotFoundException extends RuntimeException {
    public CommandNotFoundException(String message) {
        super(String.format("Command \"%s\" - not found", message));
    }
}
