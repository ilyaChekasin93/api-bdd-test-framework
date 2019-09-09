package ru.api.bdd.test.framework.client.http.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;


@Data
@Component
public class Request {

    private String baseUrl;

    private String resource;

    private Method method;

    private Object body;

    private Headers headers;

    private Map<String, String> queryParams;


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
