package ru.api.bdd.test.framework.client.http.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.annotation.PostConstruct;


@Data
@AllArgsConstructor
public class Response {

    private Object body;

    private int statusCode;

    private Headers headers;

    @PostConstruct
    public void update(){
        body = "";
        statusCode = 0;
        headers = new Headers();
    }

}
