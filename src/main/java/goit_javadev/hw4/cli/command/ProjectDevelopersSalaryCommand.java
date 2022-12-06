package goit_javadev.hw4.cli.command;

import goit_javadev.hw4.dao.ProjectDaoService;
import goit_javadev.hw4.entity.Project;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProjectDevelopersSalaryCommand extends Command {
    public static final String TITLE = "project:developers-salary";
    public static final String DESCRIPTION = "Shows salary summary of all developers on the project";
    ProjectDaoService projectDaoService;

    public ProjectDevelopersSalaryCommand(Connection connection) throws SQLException {
        super(connection);

        projectDaoService = new ProjectDaoService(getConnection());
    }

    @Override
    public void run() {
        while (true) {
            print("Enter project name:");
            String userInput = getUserInput();

            if (!isUserInputValid(userInput)) {
                System.out.println("Ooops! Looks like you typed something wrong. Please try again.");
                continue;
            }

            try {
                List<Project> projects = projectDaoService.findByName(userInput);
                int projectsSize = projects.size();

                if (projectsSize < 1) {
                    printf("There is no projects with name: \"%s\"", userInput);
                } else if (projectsSize > 1) {
                    print("Found more than 1 projects with current name:");
                    projects.forEach(project -> print(project.getName()));
                    print("You need to specify a project name.");
                } else {
                    Project project = projects.get(0);
                    printf("Found project with name: %s", project.getName());
                    printf(
                            "The amount of developers' salaries in the project is %.2f USD",
                            projectDaoService.getDevelopersSalarySumByProject(project.getId())
                    );

                    break;
                }
            } catch (SQLException e) {
                print("Something went wrong. Try another name");
            }
        }
    }

    private boolean isUserInputValid(String userInput) {
        return !userInput.isEmpty();
    }
}
