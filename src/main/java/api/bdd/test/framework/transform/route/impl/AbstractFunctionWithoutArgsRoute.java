package api.bdd.test.framework.transform.route.impl;

import lombok.Setter;


public abstract class AbstractFunctionWithoutArgsRoute extends AbstractRoute {

    @Setter
    protected String expression;

    private static final String START_EXPRESSION = "\\@";

    private static final String ARGS_PATTERN = "\\(\\)";

    public String getPattern() {
        String functionName = getFunctionName();
        return START_EXPRESSION + functionName + ARGS_PATTERN;
    }

    protected abstract String getFunctionName();

}
