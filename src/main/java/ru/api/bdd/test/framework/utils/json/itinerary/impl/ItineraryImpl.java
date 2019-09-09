package ru.api.bdd.test.framework.utils.json.itinerary.impl;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import com.jayway.jsonpath.spi.json.JacksonJsonNodeJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import org.springframework.stereotype.Component;
import ru.api.bdd.test.framework.utils.json.itinerary.Itinerary;


@Component
public class ItineraryImpl implements Itinerary {

    public Object getValueByJsonPath(String jsonPath, String json){
        return getJsonDocument(json).read(jsonPath);
    }

    public Object setValueByJsonPath(String json, String jsonPath, String value){
        return JsonPath
                .using(сonfiguration())
                .parse(json)
                .set(jsonPath, value)
                .json();
    }

    private ReadContext getJsonDocument(String json){
        return JsonPath.parse(json);
    }

    private Configuration сonfiguration(){
        return Configuration
                .builder()
                .jsonProvider(new JacksonJsonNodeJsonProvider())
                .mappingProvider(new JacksonMappingProvider())
                .build();
    }
}
