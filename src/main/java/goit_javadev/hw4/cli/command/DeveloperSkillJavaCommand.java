package goit_javadev.hw4.cli.command;

import goit_javadev.hw4.dao.DeveloperDaoService;
import goit_javadev.hw4.entity.Developer;
import goit_javadev.hw4.entity.Skill;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DeveloperSkillJavaCommand extends Command {
    public static final String TITLE = "developer:skill:java";
    public static final String DESCRIPTION = "Shows list of all java developers";
    private final DeveloperDaoService developerDaoService;

    public DeveloperSkillJavaCommand(Connection connection) throws SQLException {
        super(connection);

        developerDaoService = new DeveloperDaoService(connection);

    }

    @Override
    public void run() throws SQLException {
        List<Developer> developers = developerDaoService.findBySkillScope(Skill.Scope.JAVA);

        if (developers.isEmpty()) {
            consoleIO.printf("There are no java developers");
        }
        consoleIO.print("Developers list:");
        developers.forEach(developer -> consoleIO.print(developer.getInfo()));
    }
}
