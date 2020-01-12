package ru.api.bdd.test.framework.client.http.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Data
@AllArgsConstructor
public class Headers {

    private Map<String, List<String>> values;


    public Headers(){
        values = new HashMap<>();
    }

    public void add(String name, String value) {
        List<String> headerValues = values.get(name);
        if(headerValues == null) {
            headerValues = Collections.singletonList(value);
        } else {
            headerValues.add(value);
        }
        values.put(name, headerValues);
    }

    public void add(Map<String, String> newHeaders){
        newHeaders
                .entrySet()
                .forEach(h -> add(h.getKey(), h.getValue()));
    }

    public void add(String name, List<String> value){
        values.put(name, value);
    }

    public List<String> getValue(String headerName){
        return values.get(headerName);
    }

}
