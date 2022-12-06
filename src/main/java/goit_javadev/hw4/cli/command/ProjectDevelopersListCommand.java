package goit_javadev.hw4.cli.command;

import goit_javadev.hw4.cli.helpers.GetProjectHelper;
import goit_javadev.hw4.dao.ProjectDaoService;
import goit_javadev.hw4.entity.Project;

import java.sql.Connection;
import java.sql.SQLException;

public class ProjectDevelopersListCommand extends Command {
    public static final String TITLE = "project:developers-list";
    public static final String DESCRIPTION = "Shows list of all developers on the project";
    private final GetProjectHelper getProjectHelper;
    private final ProjectDaoService projectDaoService;

    public ProjectDevelopersListCommand(Connection connection) throws SQLException {
        super(connection);

        getProjectHelper = new GetProjectHelper(connection);
        projectDaoService = new ProjectDaoService(connection);

    }

    @Override
    public void run() {
        Project project = getProjectHelper.run();
        consoleIO.printf("Found project with name: %s", project.getName());

        try {
            consoleIO.printf(
                    "The amount of developers' salaries in the project is %.2f USD",
                    projectDaoService.getDevelopersSalarySumByProject(project.getId())
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
