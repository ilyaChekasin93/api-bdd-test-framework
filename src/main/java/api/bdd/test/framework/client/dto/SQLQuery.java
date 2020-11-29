package api.bdd.test.framework.client.dto;

import lombok.Data;

import java.util.Map;


@Data
public class SQLQuery {

    private String query;

    private Map<String, String> params;

}
