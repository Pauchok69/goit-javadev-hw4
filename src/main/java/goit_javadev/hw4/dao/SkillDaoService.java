package goit_javadev.hw4.dao;

import goit_javadev.hw4.entity.Skill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SkillDaoService extends DaoService {
    private final PreparedStatement findAllSt;
    private final PreparedStatement findSt;
    private final PreparedStatement insertSt;
    private final PreparedStatement deleteSt;
    private final PreparedStatement updateSt;

    public SkillDaoService(Connection connection) throws SQLException {
        super(connection);

        insertSt = connection.prepareStatement(
                "INSERT INTO skills (scope, level, status) VALUES(?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
        );
        updateSt = connection.prepareStatement(
                "UPDATE skills SET scope = ?, level = ?, status = ? WHERE id = ?"
        );
        findSt = connection.prepareStatement("SELECT * FROM skills WHERE id = ?");
        deleteSt = connection.prepareStatement("DELETE FROM skills WHERE id = ?");
        findAllSt = connection.prepareStatement("SELECT * FROM skills");
    }

    protected Skill hydrate(ResultSet resultSet) throws SQLException {
        Skill skill = new Skill();

        skill.setId(resultSet.getLong("id"));
        skill.setScope(Skill.Scope.valueOf(resultSet.getString("scope").toUpperCase()));
        skill.setLevel(Skill.Level.valueOf(resultSet.getInt("level")));
        skill.setStatus(resultSet.getBoolean("status"));

        return skill;
    }

    public long insert(Skill skill) throws SQLException {
        insertSt.setString(1, String.valueOf(skill.getScope()));
        insertSt.setInt(2, skill.getLevel().getValue());
        insertSt.setBoolean(3, skill.getStatus());


        int affectedRows = insertSt.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating skill failed, no rows affected.");
        }

        try (ResultSet generatedKeys = insertSt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return generatedKeys.getLong(1);
            } else {
                throw new SQLException("Creating skill failed, no ID obtained.");
            }
        }
    }

    public void delete(long id) throws SQLException {
        deleteSt.setLong(1, id);
        deleteSt.executeUpdate();
    }

    public void update(Skill skill) throws SQLException {
        updateSt.setString(1, skill.getScope().name());
        updateSt.setInt(2, skill.getLevel().getValue());
        updateSt.setBoolean(3, skill.getStatus());
        updateSt.setLong(4, skill.getId());

        updateSt.executeUpdate();
    }

    public List<Skill> findAll() throws SQLException {
        ResultSet resultSet = findAllSt.executeQuery();

        List<Skill> result = new ArrayList<>();

        while (resultSet.next()) {
            result.add(hydrate(resultSet));
        }

        return result;
    }

    public Skill find(long id) throws SQLException {
        findSt.setLong(1, id);

        ResultSet resultSet = findSt.executeQuery();

        if (resultSet.next()) {
            return hydrate(resultSet);
        }

        return null;
    }
}
