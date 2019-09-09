package ru.api.bdd.test.framework.summary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.api.bdd.test.framework.client.http.HttpClient;
import ru.api.bdd.test.framework.client.http.dto.Request;
import ru.api.bdd.test.framework.context.ScenarioContext;
import ru.api.bdd.test.framework.utils.json.JsonUtils;

import java.util.List;
import java.util.Map;


@Component
public class ScenarioRestAgregator {

    @Autowired
    private ScenarioContext context;

    @Autowired
    private HttpClient httpClient;

    @Autowired
    private JsonUtils jsonUtils;

    public void executeRequest(String resource, String method) {
        Request request = context.getRequest();
        request.setMethod(method);
        request.setResource(resource);
        context.setResponse(httpClient.executeRequest(request));
        request.update();
    }

    public void setBaseUrl(String url) {
        context.getRequest().setBaseUrl(url);
    }

    public void setRequestBody(Object body) {
        context.getRequest().setBody(body);
    }

    public String getResponseBody() {
        return context.getResponse().getBody().toString();
    }

    public void addRequestHeader(String headerName, String headerValue) {
        context.getRequest().getHeaders().add(headerName, headerValue);
    }

    public void addQueryParam(Map<String, String> params) {
        context.getRequest().setQueryParams(params);
    }

    public void addRequestHeaders(Map<String, String> headers) {
        context.getRequest().getHeaders().add(headers);
    }

    public int getResponseCode() {
        return context.getResponse().getStatusCode();
    }

    public List<String> getResponseHeader(String headerName) {
        return context.getResponse().getHeaders().getValue(headerName);
    }

    public Map<String, List<String>> getResponseHeaders() {
        return context.getResponse().getHeaders().getValues();
    }

    public Object getBodyValue(String bodyPath) {
        return jsonUtils.getValueByBodyPath(
                bodyPath,
                context.getResponse().getBody()
        );
    }

    public boolean responseBodyIsEmpty() {
        return context.getResponse().getBody().equals("{}");
    }

    public Object setValuesInPojo(String sheamName, Map<String, String> values) {
        return jsonUtils.setValuesByBodyPath(
                jsonUtils.getPojoValue(sheamName),
                values
        );
    }

}
