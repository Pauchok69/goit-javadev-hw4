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
        String projectName = getUserInput();

        try {
            List<Project> projects = projectDaoService.findByName(projectName);
            System.out.println("projects = " + projects);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
