package api.bdd.test.framework.action.body;

import java.util.Map;


public interface BodyAction {

    Object getValueByBodyPath(String path, Object body);

    String body2String(Object bodyValue);

    Object setValueByBodyPath(Object body, String path, String value);

    default Object setValuesByBodyPath(Object body, Map<String, String> values){
        Object[] result = {body};
        values.entrySet().stream().forEach(e ->
                result[0] = setValueByBodyPath(
                                result[0],
                                e.getKey(),
                                e.getValue()
                )
        );
        return result[0];
    }

}
