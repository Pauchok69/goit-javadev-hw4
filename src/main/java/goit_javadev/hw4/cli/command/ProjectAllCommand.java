package goit_javadev.hw4.cli.command;

import goit_javadev.hw4.dao.ProjectDaoService;
import goit_javadev.hw4.entity.Project;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProjectAllCommand extends Command {
    public static final String TITLE = "project:all";
    public static final String DESCRIPTION = "Shows information about all projects";

    ProjectDaoService projectDaoService;

    public ProjectAllCommand(Connection connection) throws SQLException {
        super(connection);

        projectDaoService = new ProjectDaoService(connection);
    }

    @Override
    public void run() {
        try {
            List<Project> projects = projectDaoService.findAllWithDevelopersCount();

            if (projects.isEmpty()) {
                consoleIO.printf("There are no projects");
            }
            consoleIO.print("Projects list:");
            projects.forEach(this::render);
        } catch (SQLException e) {
            consoleIO.print(e.getMessage());
        }
    }

    private void render(Project p) {
        consoleIO.printf("%s - %s - %d", p.getDateStart(), p.getName(), p.getDevelopersCount());
    }
}
