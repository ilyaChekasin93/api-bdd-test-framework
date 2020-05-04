package api.bdd.test.framework.transform.route.impl;

import java.util.Arrays;
import java.util.List;

import static api.bdd.test.framework.utils.Helpers.getFullMath;


public abstract class AbstractFunctionRoute extends BaseRoute {

    protected String expression;

    protected List<String> arguments;

    private static final String START_FUNCTION_PATTERN = "\\@";

    private static final String ARGS_PATTERN = "\\(([^\\[\\]]+)\\)";


    public void setExpression(String expression) {
        this.expression = expression;
        String value = getFullMath(expression, ARGS_PATTERN);
        arguments = Arrays.asList(value.split(", "));
    }

    public String getPattern(){
        String functionName = getFunctionName();
        return START_FUNCTION_PATTERN + functionName + ARGS_PATTERN;
    }

    protected abstract String getFunctionName();

}
