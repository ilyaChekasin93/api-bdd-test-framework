package api.bdd.test.framework.client.http.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    private Object body;

    private int statusCode;

    private Map<String, List<String>> headers;

}
