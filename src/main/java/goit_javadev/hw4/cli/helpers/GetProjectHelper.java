package goit_javadev.hw4.cli.helpers;

import goit_javadev.hw4.dao.ProjectDaoService;
import goit_javadev.hw4.entity.Project;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GetProjectHelper extends CommandHelper {
    ProjectDaoService projectDaoService;

    public GetProjectHelper(Connection connection) throws SQLException {
        projectDaoService = new ProjectDaoService(connection);
    }

    public Project run() {
        while (true) {
            consoleIO.print("Enter project name:");
            String userInput = consoleIO.getUserInput();

            if (!isUserInputValid(userInput)) {
                consoleIO.print("Ooops! Looks like you typed something wrong. Please try again.");
                continue;
            }

            try {
                List<Project> projects = projectDaoService.findByName(userInput);
                int projectsSize = projects.size();

                if (projectsSize < 1) {
                    consoleIO.printf("There is no projects with name: \"%s\"", userInput);
                } else if (projectsSize > 1) {
                    consoleIO.print("Found more than 1 projects with current name:");
                    projects.forEach(project -> consoleIO.print(project.getName()));
                    consoleIO.print("You need to specify a project name.");
                } else {
                    return projects.get(0);
                }
            } catch (SQLException e) {
                consoleIO.print("Something went wrong. Try another project name");
            }
        }
    }

    private boolean isUserInputValid(String userInput) {
        return !userInput.isEmpty();
    }
}
