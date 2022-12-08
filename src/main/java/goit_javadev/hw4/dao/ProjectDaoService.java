package goit_javadev.hw4.dao;

import goit_javadev.hw4.entity.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDaoService extends DaoService {
    private final PreparedStatement findAllSt;
    private final PreparedStatement findSt;
    private final PreparedStatement insertSt;
    private final PreparedStatement deleteSt;
    private final PreparedStatement updateSt;
    private final PreparedStatement findByCaseSensitiveNameSt;
    private final PreparedStatement getDevelopersSalarySumByProjectSt;
    private final PreparedStatement findAllWithDevelopersCountSt;

    public ProjectDaoService(Connection connection) throws SQLException {
        super(connection);

        insertSt = connection.prepareStatement(
                "INSERT INTO projects (company_id, customer_id, name, budget, status, date_start, date_end, cost) VALUES(?, ?, ?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
        );
        updateSt = connection.prepareStatement(
                "UPDATE projects SET company_id = ?, customer_id = ?, name = ?, budget = ?, status = ?, date_start = ?, date_end = ?, cost = ? WHERE id = ?"
        );
        findSt = connection.prepareStatement("SELECT * FROM projects WHERE id = ?");
        deleteSt = connection.prepareStatement("DELETE FROM projects WHERE id = ?");
        findAllSt = connection.prepareStatement("SELECT * FROM projects");
        findAllWithDevelopersCountSt = connection.prepareStatement("SELECT p.*, COUNT(p.id) developers_count FROM projects p INNER JOIN developers_projects dp ON p.id = dp.project_id INNER JOIN developers d ON d.id = dp.developer_id GROUP BY p.id");
        findByCaseSensitiveNameSt = connection.prepareStatement("SELECT * FROM projects WHERE LOWER(name) LIKE LOWER(?) LIMIT 50");
        getDevelopersSalarySumByProjectSt = connection.prepareStatement(
                "SELECT SUM(d.salary) sum FROM projects p INNER JOIN developers_projects dp ON p.id = dp.project_id INNER JOIN developers d ON d.id = dp.developer_id WHERE p.id = ? GROUP BY p.id;"
        );
    }

    protected Project hydrate(ResultSet resultSet) throws SQLException {
        Project project = new Project();

        project.setId(resultSet.getLong("id"));
        project.setCompanyId(resultSet.getLong("company_id"));
        project.setCustomerId(resultSet.getLong("customer_id"));
        project.setName(resultSet.getString("name"));
        project.setBudget(resultSet.getDouble("budget"));
        project.setStatus(resultSet.getBoolean("status"));
        project.setDateStart(resultSet.getDate("date_start").toLocalDate());

        Date dateEnd = resultSet.getDate("date_end");

        if (dateEnd != null) {
            project.setDateEnd(dateEnd.toLocalDate());
        }
        project.setCost(resultSet.getDouble("cost"));

        return project;
    }

    public long insert(Project project) throws SQLException {
        insertSt.setLong(1, project.getCompanyId());
        insertSt.setLong(2, project.getCustomerId());
        insertSt.setString(3, project.getName());
        insertSt.setDouble(4, project.getBudget());
        insertSt.setBoolean(5, project.getStatus());
        insertSt.setDate(6, Date.valueOf(project.getDateStart()));
        insertSt.setDate(7, Date.valueOf(project.getDateEnd()));
        insertSt.setDouble(8, project.getCost());

        int affectedRows = insertSt.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating project failed, no rows affected.");
        }

        try (ResultSet generatedKeys = insertSt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return generatedKeys.getLong(1);
            } else {
                throw new SQLException("Creating project failed, no ID obtained.");
            }
        }
    }

    public void delete(long id) throws SQLException {
        deleteSt.setLong(1, id);
        deleteSt.executeUpdate();
    }

    public void update(Project project) throws SQLException {
        updateSt.setLong(1, project.getCompanyId());
        updateSt.setLong(2, project.getCustomerId());
        updateSt.setString(3, project.getName());
        updateSt.setDouble(4, project.getBudget());
        updateSt.setBoolean(5, project.getStatus());
        updateSt.setDate(6, Date.valueOf(project.getDateStart()));
        updateSt.setDate(7, Date.valueOf(project.getDateEnd()));
        updateSt.setDouble(8, project.getCost());
        updateSt.setLong(9, project.getId());

        updateSt.executeUpdate();
    }

    public List<Project> findAll() throws SQLException {
        ResultSet resultSet = findAllSt.executeQuery();

        List<Project> result = new ArrayList<>();

        while (resultSet.next()) {
            result.add(hydrate(resultSet));
        }

        return result;
    }

    public List<Project> findAllWithDevelopersCount() throws SQLException {
        ResultSet resultSet = findAllWithDevelopersCountSt.executeQuery();

        List<Project> result = new ArrayList<>();

        while (resultSet.next()) {
            Project project = hydrate(resultSet);
            project.setDevelopersCount(resultSet.getInt("developers_count"));

            result.add(project);
        }

        return result;
    }

    public Project find(long id) throws SQLException {
        findSt.setLong(1, id);

        ResultSet resultSet = findSt.executeQuery();

        if (resultSet.next()) {
            return hydrate(resultSet);
        }

        return null;
    }

    public List<Project> findByName(String name) throws SQLException {
        findByCaseSensitiveNameSt.setString(1, "%" + name + "%");

        ResultSet resultSet = findByCaseSensitiveNameSt.executeQuery();

        List<Project> result = new ArrayList<>();

        while (resultSet.next()) {
            result.add(hydrate(resultSet));
        }

        return result;
    }

    public Float getDevelopersSalarySumByProject(long id) throws SQLException {
        getDevelopersSalarySumByProjectSt.setLong(1, id);

        ResultSet resultSet = getDevelopersSalarySumByProjectSt.executeQuery();

        if (resultSet.next()) {
            return resultSet.getFloat("sum");
        }

        return 0f;
    }
}
