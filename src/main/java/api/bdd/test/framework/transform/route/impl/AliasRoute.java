package api.bdd.test.framework.transform.route.impl;

import api.bdd.test.framework.action.StorageAction;
import lombok.Setter;
import org.springframework.stereotype.Component;

import static api.bdd.test.framework.utils.Helpers.getGroupOneMath;


@Component
public class AliasRoute extends AbstractRoute {

    private static final String PATTERN = "\\$\\{([^\\[\\]]+)\\}";

    private StorageAction storageAction;

    @Setter
    private String expression;

    public AliasRoute(StorageAction storageAction){
        this.storageAction = storageAction;
    }

    public String getValue() {
        String varName = getGroupOneMath(expression, PATTERN);
        return storageAction.getVarValue(varName);
    }

    public String getPattern() {
        return PATTERN;
    }
}
