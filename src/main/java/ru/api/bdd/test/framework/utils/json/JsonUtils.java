package ru.api.bdd.test.framework.utils.json;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.api.bdd.test.framework.utils.Helpers;
import ru.api.bdd.test.framework.utils.json.itinerary.Itinerary;
import ru.api.bdd.test.framework.utils.json.mapper.Mapper;

import java.util.Map;


@Component
public class JsonUtils {

    @Autowired
    private Itinerary itinerary;

    @Autowired
    private Mapper mapper;

    public Object getValueByBodyPath(String jsonPath, Object json){
        return itinerary.getValueByJsonPath(
                jsonPath,
                mapper.jsonObiect2JsonString(json)
        );
    }

    public Object setValuesByBodyPath(Object sheamValue, Map<String, String> values){
        return itinerary.setValuesByJsonPath(
                mapper.jsonObiect2JsonString(sheamValue),
                values
        );
    }

    public Object getPojoValue(String pojoName) {
        return Helpers.getPojoValue(
                new StringBuilder(
                        System.getProperty("pojo.targetPackage"))
                        .append(".")
                        .append(pojoName)
                        .toString()
        );
    }
}