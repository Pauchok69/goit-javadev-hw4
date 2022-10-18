package goit_javadev.hw4.dao;

import java.sql.Connection;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class DaoService {
    @Getter
    private final Connection connection;
}
