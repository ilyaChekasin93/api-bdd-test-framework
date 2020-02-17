package api.bdd.test.framework.client.http.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;


@Data
@AllArgsConstructor
public class Request {

    private String baseUrl;

    private String resource;

    private Method method;

    private Object body;

    private Map<String, List<String>> headers;

    private Map<String, String> queryParams;

    private Map<String, String> urlParams;

    public Request() {
        body = "{}";
        headers = new HashMap<>();
        queryParams = new HashMap<>();
        urlParams = new HashMap<>();
    }

}
