package goit_javadev.hw4.cli;

public interface ConsoleIOInterface {
    void print(String string);

    void printf(String string, Object... args);

    String getUserInput();
}
