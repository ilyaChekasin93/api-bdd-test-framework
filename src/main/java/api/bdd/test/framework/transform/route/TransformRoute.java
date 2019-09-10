package api.bdd.test.framework.transform.route;


public interface TransformRoute {

    void setExpression(String expression);

    String getValue();

    String getPattern();

    char getStartPatternChar();

    char getEndPatternChar();

}
