package goit_javadev.hw4.cli.command;

import lombok.Getter;
import lombok.Setter;

import java.sql.Connection;
import java.util.Scanner;

public abstract class Command implements CommandInterface {
    private Scanner scanner = null;
    @Getter
    @Setter
    private Connection connection = null;

    protected Command() {
    }

    protected Command(Connection connection) {
        this.connection = connection;
    }

    protected String getUserInput() {
        if (this.scanner == null) {
            this.scanner = new Scanner(System.in);
        }

        return scanner.nextLine();
    }

    protected void print(String text) {
        System.out.println(text);
    }

    protected void printf(String text, Object... args) {
        String textWithNewLine = text + "%n";

        System.out.printf(textWithNewLine, args);
    }
}
