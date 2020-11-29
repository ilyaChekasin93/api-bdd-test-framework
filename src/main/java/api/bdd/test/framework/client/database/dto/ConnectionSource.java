package api.bdd.test.framework.client.database.dto;

import lombok.Data;


@Data
public class ConnectionSource {

    private String url;

    private String username;

    private String password;

    private String databaseName;

}
