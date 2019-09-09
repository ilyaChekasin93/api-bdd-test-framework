package ru.api.bdd.test.framework.runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"ru.api.bdd.test"},
        features = "src/test/resources/features",
        plugin = {"pretty", "html:target/cucumber"})
public class TestRunner {}
