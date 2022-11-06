package goit_javadev.hw4.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import goit_javadev.hw4.entity.Developer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class DaoService {
    @Getter
    private final Connection connection;

    protected abstract Object hydrate(ResultSet resultSet) throws SQLException;
}
