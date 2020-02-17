package api.bdd.test.framework.client.http;

import api.bdd.test.framework.client.http.dto.Request;
import api.bdd.test.framework.client.http.dto.Response;

public interface HttpClient {

    Response executeRequest(Request request);

}
