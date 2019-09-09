package ru.api.bdd.test.framework.instructions.steps;

import io.qameta.allure.Step;
import org.springframework.stereotype.Component;
import ru.api.bdd.test.framework.assertion.Checker;
import ru.api.bdd.test.framework.utils.Helpers;


@Component
@SuppressWarnings("unchecked")
public class UtilsStep {

    @Step("performed multiplication {firstArg} and {secondArg}")
    public int multiplicationStep(Object firstArg, Object secondArg) {
        Checker.isNumber(firstArg);
        Checker.isNumber(secondArg);
        return Helpers.multiply(
                Integer.valueOf(firstArg.toString()),
                Integer.valueOf(secondArg.toString())
        );
    }

    @Step("performed multiplication {firstArg} and {secondArg}")
    public int addNumbersStep(Object firstArg, Object secondArg) {
        Checker.isNumber(firstArg);
        Checker.isNumber(secondArg);
        return Helpers.addNumbers(
                Integer.valueOf(firstArg.toString()),
                Integer.valueOf(firstArg.toString())
        );
    }
}
