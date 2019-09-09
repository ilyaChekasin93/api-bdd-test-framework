package ru.api.bdd.test.framework.utils.json.itinerary;

import java.util.Map;


public interface Itinerary {

    Object getValueByJsonPath(String jsonPath, String json);

    Object setValueByJsonPath(String json, String jsonPath, String value);

    default Object setValuesByJsonPath(String json, Map<String, String> values){
        Object[] result = {json};
        values.entrySet().stream().forEach(e ->
                result[0] = setValueByJsonPath(
                                String.valueOf(result[0]),
                                e.getKey(),
                                e.getValue()
                )
        );
        return result[0];
    }

}
