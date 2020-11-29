package api.bdd.test.framework.client.database.impl;

import api.bdd.test.framework.client.database.SQLClient;
import api.bdd.test.framework.client.database.dto.ConnectionSource;
import api.bdd.test.framework.client.database.dto.SQLQueryResult;
import api.bdd.test.framework.client.database.dto.SQLQuery;
import api.bdd.test.framework.exception.DriverNotSupportedException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
public class SQLClientImpl implements SQLClient {

    private JdbcTemplate jdbcTemplate;

    public SQLClientImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public SQLQueryResult executeQuery(ConnectionSource connectionSource, SQLQuery query) {
        DriverManagerDataSource dataSource = initDataSource(connectionSource);
        jdbcTemplate.setDataSource(dataSource);
        List<Map<String, Object>> result = jdbcTemplate.queryForList(query.getQuery());

        return new SQLQueryResult(result);
    }

    private DriverManagerDataSource initDataSource(ConnectionSource connectionSource) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        String driverClassName = getDriverByDatabaseName(connectionSource.getDatabaseName()).getName();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(connectionSource.getUrl());
        dataSource.setUsername(connectionSource.getUsername());
        dataSource.setPassword(connectionSource.getPassword());

        return dataSource;
    }

    private Class<? extends java.sql.Driver> getDriverByDatabaseName(String databaseName) {
        switch (databaseName.toUpperCase()) {
            case "POSTGRES":
                return org.postgresql.Driver.class;
            case "H2":
                return org.h2.Driver.class;
            case "MYSQL" :
                return com.mysql.jdbc.Driver.class;
            default:
                throw new DriverNotSupportedException(databaseName);
        }
    }

}
