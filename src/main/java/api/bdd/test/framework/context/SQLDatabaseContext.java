package api.bdd.test.framework.context;

import api.bdd.test.framework.client.database.dto.ConnectionSource;
import api.bdd.test.framework.client.database.dto.SQLQueryResult;
import api.bdd.test.framework.client.database.dto.SQLQuery;
import lombok.Data;
import org.springframework.stereotype.Component;


@Data
@Component
public class SQLDatabaseContext {

    private ConnectionSource connectionSource;

    private SQLQuery query;

    private SQLQueryResult SQLQueryResult;

}