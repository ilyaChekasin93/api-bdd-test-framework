package api.bdd.test.framework.context;

import api.bdd.test.framework.client.http.dto.Request;
import lombok.Data;
import org.springframework.stereotype.Component;
import api.bdd.test.framework.client.http.dto.Response;


@Data
@Component
public class RestContext {

    private Request request;

    private Response response;


    public RestContext() {
        request = new Request();
    }

}
