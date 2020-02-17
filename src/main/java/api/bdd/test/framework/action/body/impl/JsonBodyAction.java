package api.bdd.test.framework.action.body.impl;

import api.bdd.test.framework.action.body.BodyAction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import com.jayway.jsonpath.spi.json.JacksonJsonNodeJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import org.springframework.stereotype.Component;


@Component
public class JsonBodyAction implements BodyAction {

    private static ObjectMapper mapper;


    public JsonBodyAction(ObjectMapper mapper){
        this.mapper = mapper;
    }

    public Object getValueByBodyPath(String jsonPath, Object json){
        return getJsonDocument(
                body2String(json)
        ).read(jsonPath);
    }

    public Object setValueByBodyPath(Object json, String jsonPath, String value){
        return JsonPath
                .using(сonfiguration())
                .parse(body2String(json))
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

    public String body2String(Object json) {
        String jsonString = "";
        try {
            jsonString = mapper.writeValueAsString(json);
        } catch(JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }


}
