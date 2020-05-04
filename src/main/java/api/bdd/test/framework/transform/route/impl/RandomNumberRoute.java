package api.bdd.test.framework.transform.route.impl;

import org.springframework.stereotype.Component;


@Component
public class RandomNumberRoute extends AbstractFunctionRoute {

    private static final String FUNCTION_NAME = "randomNumber";

    protected String getFunctionName() {
        return FUNCTION_NAME;
    }

    public String getValue() {
        String maxStr = arguments.get(0);
        String minStr = arguments.get(1);

        int max = Integer.valueOf(maxStr);
        int min = Integer.valueOf(minStr);

        int randomNumber = (int)(Math.random() * ((max - min) + 1)) + min;

        return String.valueOf(randomNumber);
    }
}
