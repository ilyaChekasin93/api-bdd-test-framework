package api.bdd.test.framework.client.database;

import api.bdd.test.framework.client.dto.ConnectionSource;
import api.bdd.test.framework.client.dto.SQLQueryResult;
import api.bdd.test.framework.client.dto.SQLQuery;

public interface SQLClient {

    SQLQueryResult executeQuery(ConnectionSource connectionSource, SQLQuery query);

}
