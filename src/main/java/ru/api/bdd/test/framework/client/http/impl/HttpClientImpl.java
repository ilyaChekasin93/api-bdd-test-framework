package ru.api.bdd.test.framework.client.http.impl;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.api.bdd.test.framework.client.http.HttpClient;
import ru.api.bdd.test.framework.client.http.dto.Headers;
import ru.api.bdd.test.framework.client.http.dto.Request;
import ru.api.bdd.test.framework.client.http.dto.Response;
import ru.api.bdd.test.framework.client.http.impl.handler.CustomErrorHandler;

import java.util.List;
import java.util.Map;


@Component
public class HttpClientImpl implements HttpClient {

    private RestTemplate restTemplate;

    public HttpClientImpl(CustomErrorHandler customErrorHandler) {
        this.restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(customErrorHandler);
    }

    public Response executeRequest(Request request) {
        HttpMethod httpMethod = HttpMethod.resolve(request.getMethod().name());
        HttpEntity httpEntity = getHttpEntity(
                httpMethod,
                request.getBody(),
                mapToHttpHeaders(request.getHeaders().getValues())
        );

        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromHttpUrl(request.getBaseUrl() + request.getResource());

        request.getUrlParams().entrySet().stream()
                .forEach(p -> uriBuilder.queryParam(p.getKey(), p.getValue()));

        return ResponseEntity2Response(
                restTemplate.exchange(
                        uriBuilder.toUriString(),
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
