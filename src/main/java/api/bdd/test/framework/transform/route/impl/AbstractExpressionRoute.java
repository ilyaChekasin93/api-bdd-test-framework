package api.bdd.test.framework.transform.route.impl;

import lombok.Setter;

import java.util.Arrays;
import java.util.List;

import static api.bdd.test.framework.utils.Helpers.getFullMath;


public abstract class AbstractExpressionRoute extends BaseRoute {

    @Setter
    protected String expression;

    protected List<String> arguments;

    private static final String START_EXPRESSION_PATTERN = "\\[";

    private static final String END_EXPRESSION_PATTERN = "\\]";


    public String getPattern(){
        String firstArgPattern = getFirstArgPattern();
        String operationPattern = getOperationPattern();
        String secondArgPattern = getSecondArgPattern();
        return START_EXPRESSION_PATTERN + firstArgPattern + " " + operationPattern + " " + secondArgPattern + END_EXPRESSION_PATTERN;
    }

    protected abstract String getFirstArgPattern();

    protected abstract String getSecondArgPattern();

    protected abstract String getOperationPattern();

}
