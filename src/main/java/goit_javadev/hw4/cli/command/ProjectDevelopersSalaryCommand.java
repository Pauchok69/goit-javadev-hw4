package goit_javadev.hw4.cli.command;

import goit_javadev.hw4.dao.ProjectDaoService;
import goit_javadev.hw4.entity.Project;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProjectDevelopersSalaryCommand extends Command {
    ProjectDaoService projectDaoService;

    public ProjectDevelopersSalaryCommand(Connection connection) throws SQLException {
        super(connection);

        projectDaoService = new ProjectDaoService(getConnection());
    }

    @Override
    public void run() {
        print("Enter project name:");

        try {
            while (true) {
                String projectName = getUserInput();
                List<Project> projects = projectDaoService.findByName(projectName);

                int projectsSize = projects.size();

                if (projectsSize < 1) {
                    printf("There is no projects with name: \"%s\"%n", projectName);
                } else if (projectsSize > 1) {
                    print("Found more than 1 projects with current name:");
                    projects.forEach(project -> print(project.getName()));
                    print("You need to specify a project name.");
                } else {
                    Project project = projects.get(0);
                    print("project = " + project);

                    break;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
