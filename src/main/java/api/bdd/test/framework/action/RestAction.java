package api.bdd.test.framework.action;

import api.bdd.test.framework.client.http.HttpClient;
import api.bdd.test.framework.client.http.dto.Method;
import api.bdd.test.framework.client.http.dto.Request;
import api.bdd.test.framework.client.http.dto.Response;
import api.bdd.test.framework.context.RestContext;
import api.bdd.test.framework.action.body.BodyAction;
import api.bdd.test.framework.exception.ResourceNotAvailableException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;

import java.util.*;
import java.util.stream.Collectors;

import static api.bdd.test.framework.utils.Helpers.getPojoValue;


@Component
public class RestAction {

    private RestContext context;

    private HttpClient client;

    private BodyAction bodyAction;

    private static final String POJO_REST_TARGET_PACKAGE_PROPERTY = "pojo.rest.targetPackage";


    public RestAction(RestContext context, HttpClient client, @Qualifier( "jsonBodyAction") BodyAction bodyAction){
        this.context = context;
        this.client = client;
        this.bodyAction = bodyAction;
    }

    public void executeRequest(String resource, String method) {
        Method methodValue = Method.valueOf(method.toUpperCase());
        Request request = context.getRequest();
        request.setMethod(methodValue);
        request.setResource(resource);
        Response response;
        try {
            response = client.executeRequest(request);
        }catch (ResourceAccessException e){
            throw new ResourceNotAvailableException(request.getBaseUrl() + resource);
        }

        context.setResponse(response);
        context.setRequest(new Request());
    }

    public void setBaseUrl(String url) {
        context.getRequest().setBaseUrl(url);
    }

    public void setBody(Object body) {
        context.getRequest().setBody(body);
    }

    public void addHeader(String name, String value) {
        List<String> headerValues = getRequestHeaderValue(name);

        if(headerValues.isEmpty()) {
            headerValues = Collections.singletonList(value);
        } else {
            headerValues.add(value);
        }

        context.getRequest().getHeaders().put(name, headerValues);
    }

    public void addHeader(String name, List<String> values){
        List<String> headerValues = getRequestHeaderValue(name);

        if (headerValues.isEmpty()){
            headerValues.addAll(values);
        }

        context.getRequest().getHeaders().put(name, headerValues);
    }

    public List<String> getRequestHeaderValue(String name){
        Map<String, List<String>> headers = context.getRequest().getHeaders();

        return getHeaderValue(headers, name);
    }

    public void addHeaders(Map<String, String> headers){
        headers.entrySet()
                .forEach(h -> addHeader(h.getKey(), h.getValue()));
    }

    public void setHeaders(Map<String, String> headers){
        Map<String, List<String>> newHeaders = headers
                .entrySet().stream()
                .collect(Collectors.toMap(
                        h -> h.getKey(),
                        h -> Collections.singletonList(h.getValue())
                ));

        context.getRequest().setHeaders(newHeaders);
    }

    public void addQueryParam(String key, String value) {
        context.getRequest().getQueryParams().put(key, value);
    }

    public void addQueryParams(Map<String, String> params) {
        context.getRequest().getQueryParams().putAll(params);
    }

    public void addUrlParam(String key, String value) {
        context.getRequest().getUrlParams().put(key, value);
    }

    public void addUrlParams(Map<String, String> params) {
        context.getRequest().getUrlParams().putAll(params);
    }

    public void setQueryParams(Map<String, String> params) {
        context.getRequest().setQueryParams(params);
    }

    public void setUrlParams(Map<String, String> params) {
        context.getRequest().setUrlParams(params);
    }

    public String getResponseStatusCode() {
        return String.valueOf(context.getResponse().getStatusCode());
    }

    public List<String> getResponseHeaderValue(String name) {
        Map<String, List<String>> headers = context.getResponse().getHeaders();

        return getHeaderValue(headers, name);
    }

    public String getResponseBody(){
        return bodyAction.body2String(context.getResponse().getBody());
    }

    public String getResponseBodyValue(String jsonPath){
        Object body = context.getResponse().getBody();
        Object valueByBodyPath = bodyAction.getValueByBodyPath(jsonPath, body);

        return bodyAction.body2String(valueByBodyPath);
    }

    public Object setValuesInPojo(String shemName, Map<String, String> values) {
        String pojoPath = String.format("%s.%s", System.getProperty(POJO_REST_TARGET_PACKAGE_PROPERTY), shemName);
        Object pojoValue = getPojoValue(pojoPath);

        return bodyAction.setValuesByBodyPath(pojoValue, values);
    }

    private List<String> getHeaderValue(Map<String, List<String>> headers, String name) {
        Optional<Map.Entry<String, List<String>>> entryOptional = headers.entrySet().stream()
                .filter(h -> h.getKey().equals(name))
                .findFirst();

        return entryOptional.isPresent() ? entryOptional.get().getValue() : new ArrayList<>();
    }

}
