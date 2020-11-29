package api.bdd.test.framework.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SQLQueryResult {

    private List<Map<String, Object>> queryResult;

}
