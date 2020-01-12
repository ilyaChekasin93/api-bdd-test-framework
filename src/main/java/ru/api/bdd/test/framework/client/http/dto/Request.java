package ru.api.bdd.test.framework.client.http.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;


@Data
@AllArgsConstructor
public class Request {

    private String baseUrl;

    private String resource;

    private Method method;

    private Object body;

    private Headers headers;

    private Map<String, String> queryParams;

    private Map<String, String> urlParams;

    @PostConstruct
    public void update() {
        body = "";
        headers = new Headers();
        queryParams = new HashMap<>();
    }

    public void setMethod(String method){
        this.method = Method.valueOf(method.toUpperCase());

    }

}
