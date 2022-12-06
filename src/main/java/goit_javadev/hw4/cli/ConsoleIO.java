package goit_javadev.hw4.cli;

import java.util.Scanner;

public class ConsoleIO implements ConsoleIOInterface {
    private Scanner scanner = null;

    @Override
    public void print(String text) {
        System.out.println(text);
    }

    @Override
    public void printf(String format, Object... args) {
        String formatWithNewLine = format + "%n";

        System.out.printf(formatWithNewLine, args);
    }

    @Override
    public String getUserInput() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }

        return scanner.nextLine();
    }
}
