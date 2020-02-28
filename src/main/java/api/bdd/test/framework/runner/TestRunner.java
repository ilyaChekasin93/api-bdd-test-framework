package api.bdd.test.framework.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;


@RunWith(Cucumber.class)
@ContextConfiguration
@CucumberOptions(
        glue = {"api.bdd.test.framework"},
        features = "src/test/resources/features",
        plugin = {"pretty", "html:target/cucumber"})
public class TestRunner {}
