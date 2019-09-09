package ru.api.bdd.test.framework.context;

import org.springframework.stereotype.Component;
import ru.api.bdd.test.framework.client.http.dto.Headers;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class Storage {

    private Headers headers;

    private Map<String,Object> jsonPaths;

    private Map<String,Object> other;

    private String ALIAS_PATTERN = "^[{].*[}]$";

    @PostConstruct
    public void clear() {
        this.headers = new Headers();
        this.jsonPaths = new HashMap<>();
        this.other = new HashMap<>();
    }

    public void saveVariable(String variableName, Object value){
        other.put(variableName, value);
    }

    public void saveBodyPaths(String variableName, Object value){
        jsonPaths.put(variableName, value); }

    public void saveHeader(String headerName, List<String> value){
        headers.add(headerName, value);
    }

    public boolean isFound(String alias, String value) {
        Object headerValue = headers.getValue(alias);
        boolean isHeader = headerValue != null && headerValue.equals(value);

        Object otherValue = other.get(alias);
        boolean isOther = otherValue != null && otherValue.equals(value);

        Object jsonPathValue = jsonPaths.get(alias);
        boolean isJsonPath = jsonPathValue != null && jsonPathValue.equals(value);

        return isHeader || isJsonPath || isOther;
    }

    public Object getValue(String alias) {
        alias = getAliasKey(alias);

        Object headerValue = headers.getValue(alias);
        if (headerValue != null)
            return headerValue;

        Object otherValue = other.get(alias);
        if (otherValue != null)
            return otherValue;

        Object jsonPathValue = jsonPaths.get(alias);
        if(jsonPathValue != null)
            return jsonPathValue;

        return null;
    }

    public String evaluateValue(String value) {
        return (isAlias(value) ? getValue(value).toString() : value);
    }

    public Map<String, String> getValues(Map<String, String> aliases) {
        return aliases.entrySet().stream().collect(Collectors.toMap(
                e -> e.getKey(),
                e -> getValue(e.getValue()).toString()
        ));
    }

    private String getAliasKey(String alias) {
        return alias.replaceAll("^\\{|\\}$", "");
    }

    private boolean isAlias(String value) {
        return Pattern
                .compile(ALIAS_PATTERN)
                .matcher(value)
                .matches();
    }

}
