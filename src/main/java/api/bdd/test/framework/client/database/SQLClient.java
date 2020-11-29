package api.bdd.test.framework.client.database;

import api.bdd.test.framework.client.database.dto.ConnectionSource;
import api.bdd.test.framework.client.database.dto.SQLQueryResult;
import api.bdd.test.framework.client.database.dto.SQLQuery;

public interface SQLClient {

    SQLQueryResult executeQuery(ConnectionSource connectionSource, SQLQuery query);

}
