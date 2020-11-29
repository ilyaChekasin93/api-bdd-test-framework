package api.bdd.test.framework.transform.route.impl;

import api.bdd.test.framework.transform.route.TransformRoute;


public abstract class AbstractRoute implements TransformRoute {

    public char getStartPatternChar() {
        String pattern = getPattern();
        char[] patternChars = pattern.toCharArray();

        return patternChars[1];
    }

    public char getEndPatternChar() {
        String pattern = getPattern();
        char[] patternChars = pattern.toCharArray();
        int lastCharIndex = patternChars.length - 1;

        return patternChars[lastCharIndex];
    }

}
