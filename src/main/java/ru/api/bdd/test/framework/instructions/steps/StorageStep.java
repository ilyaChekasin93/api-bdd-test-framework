package ru.api.bdd.test.framework.instructions.steps;


import io.qameta.allure.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.api.bdd.test.framework.context.ScenarioContext;
import ru.api.bdd.test.framework.context.Storage;
import ru.api.bdd.test.framework.assertion.Checker;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class StorageStep {

    private Storage storage;

    @Autowired
    public StorageStep(ScenarioContext context){
        this.storage = context.getStorage();
    }

    @Step("stored header {headerValues} in variables with alias {headerAlias}")
    public void saveHeaderVariableStep(String headerAlias, List<String> headerValues){
        Checker.isNotEmpty(headerAlias);
        Checker.isNotEmpty(headerValues);

        storage.saveHeader(headerAlias, headerValues);
    }

    @Step("stored header body value {pathValue} in variables with alias {jsonPathAlias}")
    public void saveJsonPathVariableStep(String jsonPathAlias, Object pathValue){
        Checker.isNotEmpty(jsonPathAlias);
        storage.saveBodyPaths(jsonPathAlias, pathValue);
    }

    @Step("stored value {value} in variables with alias {valueAlias}")
    public void saveVariableStep(String valueAlias, Object value){
        Checker.isNotEmpty(valueAlias);
        storage.saveVariable(valueAlias, value);
    }

    @Step("checked scenariov variable with ")
    public void checkVariableStep(String alias, String value){
        Checker.isTrue(storage.isFound(alias, value));
    }

    @Step("get vale by alias {alias}")
    public Object getVariableValueStep(String alias) {
        return storage.getValue(alias);
    }

    @Step("get vale by alias {alias}")
    public Map<String, String> getValuesByAliasesStep(Map<String, String> aliases) {
        return storage.getValues(aliases);
    }

    @Step("evaluated value {value}")
    public String evaluateValueStep(String value) {
        return storage.evaluateValue(value);
    }

    @Step("evaluated values {values}")
    public Map<String, String> evaluateValuesStep(Map<String, String> values) {
        return values
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        e -> storage.evaluateValue(e.getKey()),
                        e -> storage.evaluateValue(e.getValue())
                ));
    }

}
