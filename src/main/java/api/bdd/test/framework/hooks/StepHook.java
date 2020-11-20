package api.bdd.test.framework.hooks;

import api.bdd.test.framework.transform.ArgumentEvaluator;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.*;


@Aspect
@Component
@SuppressWarnings("unused")
public class StepHook {

    private ArgumentEvaluator evaluator;


    public StepHook(ArgumentEvaluator evaluator) {
        this.evaluator = evaluator;
    }

    @Around("bean(*StepDefinition)")
    public void aroundScenarioStep(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] currentArgs = joinPoint.getArgs();
        Object[] evaluatedArgs = Arrays.stream(currentArgs)
                .map(a -> evaluator.evaluateStepArg(a))
                .toArray(Object[]::new);
        joinPoint.proceed(evaluatedArgs);
    }

}
