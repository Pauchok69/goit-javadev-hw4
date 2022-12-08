package goit_javadev.hw4.cli.command;

public class HelpCommand extends Command {
    public static final String TITLE = "help";
    public static final String DESCRIPTION = "Show information about all commands";

    @Override
    public void run() {
        render(HelpCommand.TITLE, HelpCommand.DESCRIPTION);
        render(StopCommand.TITLE, StopCommand.DESCRIPTION);

        consoleIO.print("project");
        render(ProjectAllCommand.TITLE, ProjectAllCommand.DESCRIPTION);
        render(ProjectDevelopersSalaryCommand.TITLE, ProjectDevelopersSalaryCommand.DESCRIPTION);
        render(ProjectDevelopersListCommand.TITLE, ProjectDevelopersListCommand.DESCRIPTION);

        consoleIO.print("developer");
        render(DeveloperSkillJavaCommand.TITLE, DeveloperSkillJavaCommand.DESCRIPTION);
        render(DeveloperSkillMiddleCommand.TITLE, DeveloperSkillMiddleCommand.DESCRIPTION);
    }

    private void render(String title, String description) {
        consoleIO.printf("%-40s %s",title, description);
    }
}
