package api.bdd.test.framework.action;

import api.bdd.test.framework.context.StorageContext;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;


@Component
public class StorageAction {

    private StorageContext context;

    private static final String ALIAS_PATTERN = "^[{].*[}]$";

    private static final String REPLACE_ALIAS_PATTERN = "^\\{|\\}$";


    public StorageAction(StorageContext context){
        this.context = context;
    }

    public <T> void saveVariable(String alias, T value){ context.getVariable().put(getAliasKey(alias), value); }

    public <T> T evaluateValue(String value) {
        return isAlias(value) ? getVarValue(value) : (T) value;
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
