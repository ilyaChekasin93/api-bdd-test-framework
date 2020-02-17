package api.bdd.test.framework.instructions.steps;


import api.bdd.test.framework.action.StorageAction;
import io.qameta.allure.Step;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Component
public class StorageStep {

    private StorageAction action;


    public StorageStep(StorageAction action){
        this.action = action;
    }

    @Step("save value {value} as alias {alias}")
    public <T> void saveVariableStep(String alias, T value) {
        action.saveVariable(alias, value);
    }

    public <T> void saveVariablesStep(Map<String, T> values) {
        values.entrySet().stream().forEach(e -> saveVariableStep(e.getKey(), e.getValue()));
    }

    @Step("evaluate value {value}")
    public <T> T evaluateValueStep(String value) {
        return action.evaluateValue(value);
    }

    public <T> Map<T, T> evaluateValuesStep(Map<String, String> values) {
        return values.entrySet().stream()
                .collect(Collectors.toMap(
                        e -> evaluateValueStep(e.getKey()),
                        e -> evaluateValueStep(e.getValue())
                ));
    }

    public <T> List<T> evaluateValuesStep(List<String> values) {
        return values.stream()
                .map(e -> (T) evaluateValueStep(e))
                .collect(Collectors.toList());
    }

}
