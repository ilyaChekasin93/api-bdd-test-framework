package api.bdd.test.framework.transform.route.impl;

import lombok.Setter;


public abstract class AbstractExpressionRoute extends AbstractRoute {

    @Setter
    protected String expression;

    private static final String START_EXPRESSION_PATTERN = "\\[";

    private static final String END_EXPRESSION_PATTERN = "\\]";


    public String getPattern() {
        String firstArgPattern = getFirstArgPattern();
        String operationPattern = getOperationPattern();
        String secondArgPattern = getSecondArgPattern();
        return String.format("%s%s %s %s%s",
                START_EXPRESSION_PATTERN, firstArgPattern, operationPattern, secondArgPattern, END_EXPRESSION_PATTERN);
    }

    protected abstract String getFirstArgPattern();

    protected abstract String getSecondArgPattern();

    protected abstract String getOperationPattern();

}
