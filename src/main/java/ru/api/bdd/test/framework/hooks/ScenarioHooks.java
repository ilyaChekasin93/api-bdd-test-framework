package ru.api.bdd.test.framework.hooks;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import ru.api.bdd.test.framework.context.ScenarioContext;

@Slf4j
@Import({
        ScenarioContext.class
})
public class ScenarioHooks {

    @Autowired
    ScenarioContext context;

    @Before
    public void beforeScenario(Scenario scenario) {
        log.info("------------------------------");
        log.info("Starting - " + scenario.getName());
        log.info("------------------------------");
    }

    @After
    public void afterScenario(Scenario scenario) {
        context.update();
        log.info("------------------------------");
        log.info("Finished - " + scenario.getName());
        log.info("------------------------------");
    }
}
