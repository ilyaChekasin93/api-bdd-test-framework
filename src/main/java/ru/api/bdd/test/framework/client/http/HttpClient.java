package ru.api.bdd.test.framework.client.http;

import ru.api.bdd.test.framework.client.http.dto.Request;
import ru.api.bdd.test.framework.client.http.dto.Response;

public interface HttpClient {

    Response executeRequest(Request request);

}
