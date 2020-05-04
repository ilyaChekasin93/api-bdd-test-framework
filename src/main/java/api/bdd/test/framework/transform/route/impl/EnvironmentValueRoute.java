package api.bdd.test.framework.transform.route.impl;

import org.springframework.stereotype.Component;


@Component
public class EnvironmentValueRoute extends AbstractFunctionRoute {

    private static final String FUNCTION_NAME = "getEnv";

    protected String getFunctionName() {
        return FUNCTION_NAME;
    }

    public String getValue() {
        String envName = arguments.get(0);

        return System.getenv(envName);
    }
}
