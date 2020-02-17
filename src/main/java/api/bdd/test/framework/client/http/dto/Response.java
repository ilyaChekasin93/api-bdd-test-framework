package api.bdd.test.framework.client.http.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    private Object body;

    private int statusCode;

    private List<Header> headers;

}
