package api.bdd.test.framework.action;

import api.bdd.test.framework.context.StorageContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Component
public class StorageAction {

    private StorageContext context;

    private static final String ALIAS_PATTERN = "^[{].*[}]$";

    private static final String REPLACE_ALIAS_PATTERN = "^\\{|\\}$";


    public StorageAction(StorageContext context){
        this.context = context;
    }

    public <T> void saveVariable(String alias, T value){ context.getVariable().put(getAliasKey(alias), value); }

    public <T> T evaluateValue(T value) {
        return isAlias(value.toString()) ? getVarValue(value.toString()) : value;
    }

    public <T> List<T> evaluateValues(List<T> values) {
        return values.stream().map(v -> evaluateValue(v)).collect(Collectors.toList());
    }

    public <T> Map<T, T> evaluateValues(Map<T, T> values) {
        return values.entrySet().stream()
                .collect(Collectors.toMap(
                        e -> evaluateValue(e.getKey()),
                        e -> evaluateValue(e.getValue())
                ));
    }

    private String getAliasKey(String alias) {
        return alias.replaceAll(REPLACE_ALIAS_PATTERN, "");
    }

    private boolean isAlias(String value) {
        return Pattern
                .compile(ALIAS_PATTERN)
                .matcher(value)
                .matches();
    }

    private <T> T getVarValue(final String alias) {
        return (T) context.getVariable().get(getAliasKey(alias));
    }

}
