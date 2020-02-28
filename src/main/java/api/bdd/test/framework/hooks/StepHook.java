package api.bdd.test.framework.hooks;

import api.bdd.test.framework.action.StorageAction;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Aspect
@Component
public class StepHook {

    private StorageAction storageAction;


    public StepHook(StorageAction storageAction){
        this.storageAction = storageAction;
    }

    @Around("execution(* api.bdd.test.framework.instructions.ScenarioStepDefinition.*(..))")
    public void aroundScenarioStep(ProceedingJoinPoint joinPoint) {
        Object[] currentArgs = joinPoint.getArgs();
        Object[] evaluatedArgs = Arrays.stream(currentArgs).map(a -> evaluateScenarioStepArg(a)).toArray(Object[]::new);

        try {
            joinPoint.proceed(evaluatedArgs);
        } catch (Throwable throwable) {
            throw new RuntimeException(String.format("Error: '%s' evaluate value", throwable.getMessage()));
        }
    }

    private Object evaluateScenarioStepArg(Object argument){

        if(argument instanceof List){
            argument = storageAction.evaluateValues((List) argument);
        }else if (argument instanceof Map){
            argument = storageAction.evaluateValues((Map) argument);
        }else {
            argument = storageAction.evaluateValue(argument);
        }

        return argument;
    }

}
