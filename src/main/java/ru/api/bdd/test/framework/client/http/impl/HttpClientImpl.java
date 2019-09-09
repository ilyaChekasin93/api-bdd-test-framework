package ru.api.bdd.test.framework.client.http.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.api.bdd.test.framework.client.http.impl.handler.CustomErrorHandler;
import ru.api.bdd.test.framework.client.http.HttpClient;
import ru.api.bdd.test.framework.client.http.dto.Headers;
import ru.api.bdd.test.framework.client.http.dto.Request;
import ru.api.bdd.test.framework.client.http.dto.Response;

import java.util.List;
import java.util.Map;


@Component
@Import({
        RestTemplate.class
})
public class HttpClientImpl implements HttpClient {

    private RestTemplate restTemplate;

    @Autowired
    public HttpClientImpl(RestTemplate restTemplate, CustomErrorHandler customErrorHandler) {
        this.restTemplate = restTemplate;
        restTemplate.setErrorHandler(customErrorHandler);
    }

    public Response executeRequest(Request request) {
        HttpMethod httpMethod = HttpMethod.resolve(request.getMethod().name());
        HttpEntity httpEntity = getHttpEntity(
                httpMethod,
                request.getBody(),
                mapToHttpHeaders(request.getHeaders().getValues())
        );

        return ResponseEntity2Response(
                restTemplate.exchange(
                        new StringBuilder().append(request.getBaseUrl()).append(request.getResource()).toString(),
                        httpMethod,
                        httpEntity,
                        Object.class,
                        request.getQueryParams()
                )
        );
    }

    private Response ResponseEntity2Response(ResponseEntity<Object> responseEntity) {
        return new Response(
                responseEntity.getBody(),
                responseEntity.getStatusCodeValue(),
                new Headers(responseEntity.getHeaders())
        );
    }

    private HttpHeaders mapToHttpHeaders(Map<String, List<String>> headers){
        HttpHeaders httpHeaders = new HttpHeaders();
        headers
                .entrySet()
                .stream()
                .forEach(h -> httpHeaders.put(h.getKey(), h.getValue()));

        return httpHeaders;
    }

    private HttpEntity getHttpEntity(HttpMethod method, Object body, HttpHeaders httpHeaders){
        HttpEntity httpEntity;
        if(isWriteMode(method)) {
            httpEntity = new HttpEntity(body, httpHeaders);
        } else {
            httpEntity = new HttpEntity(httpHeaders);
        }
        return httpEntity;
    }

    private boolean isWriteMode(HttpMethod method){
        return !HttpMethod.GET.equals(method)
                && !HttpMethod.DELETE.equals(method)
                && !HttpMethod.OPTIONS.equals(method)
                && !HttpMethod.HEAD.equals(method);
    }
}
