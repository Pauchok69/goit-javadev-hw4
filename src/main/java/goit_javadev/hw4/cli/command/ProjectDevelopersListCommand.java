package goit_javadev.hw4.cli.command;

import goit_javadev.hw4.cli.helpers.GetProjectHelper;
import goit_javadev.hw4.dao.DeveloperDaoService;
import goit_javadev.hw4.entity.Developer;
import goit_javadev.hw4.entity.Project;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProjectDevelopersListCommand extends Command {
    public static final String TITLE = "project:developers-list";
    public static final String DESCRIPTION = "Shows list of all developers on the project";
    private final GetProjectHelper getProjectHelper;
    private final DeveloperDaoService developerDaoService;

    public ProjectDevelopersListCommand(Connection connection) throws SQLException {
        super(connection);

        getProjectHelper = new GetProjectHelper(connection);
        developerDaoService = new DeveloperDaoService(connection);

    }

    @Override
    public void run() {
        Project project = getProjectHelper.getExistingProject();
        consoleIO.printf("Found project with name: %s", project.getName());

        try {
            List<Developer> developers = developerDaoService.findByProjectId(project.getId());

            if (developers.isEmpty()) {
                consoleIO.printf("There are no developers on the project \"%s\"", project.getName());
            }
            consoleIO.print("Developers list:");
            developers.forEach(developer -> consoleIO.print(developer.getInfo()));
        } catch (SQLException e) {
            consoleIO.print(e.getMessage());
        }
    }
}
