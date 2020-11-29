package api.bdd.test.framework.client.database.impl;

import api.bdd.test.framework.client.database.SQLClient;
import api.bdd.test.framework.client.dto.ConnectionSource;
import api.bdd.test.framework.client.dto.SQLQueryResult;
import api.bdd.test.framework.client.dto.SQLQuery;
import api.bdd.test.framework.exception.DriverNotSupportedException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
public class SQLClientImpl implements SQLClient {

    public SQLQueryResult executeQuery(ConnectionSource connectionSource, SQLQuery query) {
        SimpleDriverDataSource dataSource = initDataSource(connectionSource);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> result = jdbcTemplate.queryForList(query.getQuery());

        return new SQLQueryResult(result);
    }

    private SimpleDriverDataSource initDataSource(ConnectionSource connectionSource) {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        Class<? extends java.sql.Driver> driverClass = getDriverByDatabaseName(connectionSource.getDatabaseName());
        dataSource.setDriverClass(driverClass);
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
