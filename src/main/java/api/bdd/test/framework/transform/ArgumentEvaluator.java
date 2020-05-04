package api.bdd.test.framework.transform;

import api.bdd.test.framework.transform.factory.TransformRouteFactory;
import api.bdd.test.framework.transform.route.TransformRoute;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;


@Component
public class ArgumentEvaluator {

    private ArgumentParser argumentParser;

    private TransformRouteFactory strategyFactory;

    public ArgumentEvaluator(ArgumentParser argumentParser, TransformRouteFactory strategyFactory) {
        this.argumentParser = argumentParser;
        this.strategyFactory = strategyFactory;
    }

    public Object evaluateStepArg(Object argument) {

        if (argument instanceof List) {
            argument = evaluateValues((List) argument);

        } else if (argument instanceof Map) {
            argument = evaluateValues((Map) argument);

        } else if (argument instanceof String) {
            argument = evaluateValue(argument.toString());
        }

        return argument;
    }

    private String evaluateValue(String value) {
        List<String> argParts = argumentParser.getArgumentParts(value);

        for (int i = argParts.size(); i > 0; i--){
            String argPart = argParts.get(i - 1);

            TransformRoute strategy = strategyFactory.getRoute(argPart);
            String partValue = strategy.getValue();

            String nextItem = argParts.get(i - 2);
            String result = nextItem.replace(argPart, partValue);
            argParts.set(i - 1, result);
        }

        return value;
    }

    private Map<String, String> evaluateValues(Map<String, String> values) {
        return values.entrySet().stream()
                .collect(Collectors.toMap(
                        e -> evaluateValue(e.getKey()),
                        e -> evaluateValue(e.getValue())
                ));
    }

    private List<String> evaluateValues(List<String> arguments) {
        return arguments.stream().map(v -> evaluateValue(v)).collect(Collectors.toList());
    }



}
