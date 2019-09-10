package api.bdd.test.framework.client.http.impl;

import api.bdd.test.framework.client.http.impl.handler.CustomErrorHandler;
import api.bdd.test.framework.client.http.dto.Request;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import api.bdd.test.framework.client.http.HttpClient;
import api.bdd.test.framework.client.http.dto.Response;

import java.util.List;
import java.util.Map;


@Component
public class HttpClientImpl implements HttpClient {

    private RestTemplate restTemplate;

    public HttpClientImpl(RestTemplateBuilder restTemplateBuilder, CustomErrorHandler customErrorHandler) {
        this.restTemplate = restTemplateBuilder.build();
        restTemplate.setErrorHandler(customErrorHandler);
    }

    public Response executeRequest(Request request) {
        String methodName = request.getMethod().name();
        HttpMethod requestHttpMethod = HttpMethod.resolve(methodName);
        Object requestBody = request.getBody();
        HttpHeaders requestHttpHeaders = listHeader2HttpHeaders(request.getHeaders());
        HttpEntity httpEntity = getHttpEntity(requestHttpMethod, requestBody, requestHttpHeaders);
        Map<String, String> queryParams = request.getQueryParams();

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getBaseUrl())
                .path(request.getResource());

        request.getUrlParams().forEach(uriBuilder::queryParam);

        String requestUrl = uriBuilder.toUriString();

        ResponseEntity<Object> responseEntity =
                restTemplate.exchange(requestUrl, requestHttpMethod, httpEntity, Object.class, queryParams);

        return ResponseEntity2Response(responseEntity);
    }

    private Response ResponseEntity2Response(ResponseEntity<Object> responseEntity) {
        Map<String, List<String>> headers = responseEntity.getHeaders();
        Object body = responseEntity.getBody();
        int statusCode = responseEntity.getStatusCodeValue();

        return new Response(body, statusCode, headers);
    }

    private HttpHeaders listHeader2HttpHeaders(Map<String, List<String>> headers){
        HttpHeaders httpHeaders = new HttpHeaders();
        headers.forEach(httpHeaders::put);
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
