package ru.api.bdd.test.framework.utils.json.mapper.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.api.bdd.test.framework.utils.json.mapper.Mapper;

import java.io.IOException;

@Component
public class MapperImpl implements Mapper {

    @Autowired
    private ObjectMapper objectMapper;

    public boolean isValid(final String json) {
        boolean valid = true;
        try {
            objectMapper.enable(DeserializationFeature.FAIL_ON_TRAILING_TOKENS);
            objectMapper.readTree(json);
        } catch (IOException e) {
            valid = false;
        }
        return valid;
    }

    public String jsonObiect2JsonString(Object json) {
        String jsonString = "";
        try {
            jsonString = objectMapper.writeValueAsString(json);
        } catch(JsonProcessingException e) {
        e.printStackTrace();
        }
        return jsonString;
    }

}
