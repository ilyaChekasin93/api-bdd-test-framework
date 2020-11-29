package api.bdd.test.framework.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;


@Data
@AllArgsConstructor
public class SQLQueryResult {

    private List<Map<String, Object>> queryResult;

}
