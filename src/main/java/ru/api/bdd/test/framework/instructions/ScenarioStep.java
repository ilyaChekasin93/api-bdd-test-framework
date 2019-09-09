package ru.api.bdd.test.framework.instructions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import pojo.UsersPOSTrequestCheam;
import ru.api.bdd.test.framework.instructions.steps.RestStep;
import ru.api.bdd.test.framework.instructions.steps.StorageStep;
import ru.api.bdd.test.framework.instructions.steps.UtilsStep;

import java.util.Map;


@ContextConfiguration
@SuppressWarnings("unused")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ScenarioStep {

    @Autowired
    RestStep restSteps;

    @Autowired
    StorageStep storageStep;

    @Autowired
    UtilsStep utilsStep;

    @Given("^baseUrl is '(.*)'$")
    public void givenSetupBaseUrl(String baseUrl) {
        restSteps.setBaseUrlStep(
                storageStep.evaluateValueStep(baseUrl)
        );
    }

    @Given("^I set body to '(.*)'$")
    public void givenSetBody(String body) {
        restSteps.setBodyStep(
                storageStep.evaluateValueStep(body)
        );
    }

    @Given("^I set '(.*)' header to '(.*)'$")
    public void givenAddHeader(String headerName, String headerValue) {
        restSteps.addHeaderStep(
                storageStep.evaluateValueStep(headerName),
                storageStep.evaluateValueStep(headerValue)
        );
    }

    @Given("^I set query parameters to:$")
    public void givenAddQueryParam(Map<String, String> params) {
        restSteps.addQueryParamStep(
                storageStep.evaluateValuesStep(params)
        );
    }

    @Given("^I set query parameters of scenario storage to:$")
    public void givenAddQueryParamOfAlias(Map<String, String> params) {
        restSteps.addQueryParamStep(
                storageStep.evaluateValuesStep(params)
        );
    }

    @Given("^I set headers to:$")
    public void givenAddHeaders(Map<String, String> params) {
        restSteps.addHeadersStep(
                storageStep.evaluateValuesStep(params)
        );
    }

    @When("^I '(.*)' resource '(.*)'$")
    public void whenExecuteRequest(String method, String resource) {
        restSteps.executeRequestStep(
                storageStep.evaluateValueStep(resource),
                storageStep.evaluateValueStep(method)
        );
    }

    @Then("^response code should be '(.*)'$")
    public void thenResponseCodeIs(String status) {
        restSteps.checkResponseCodeIsStep(
                storageStep.evaluateValueStep(status)
        );
    }

    @Then("^response code should not be '(.*)'$")
    public void thenResponseCodeIsNot(String status) {
        restSteps.checkResponseCodeIsNotStep(
                storageStep.evaluateValueStep(status)
        );
    }

    @Then("^response header '(.*)' should exist$")
    public void thenHeaderExist(String headerName) {
        restSteps.checkHeaderExistsStep(
                storageStep.evaluateValueStep(headerName)
        );
    }

    @Then("^response header '(.*)' should not exist$")
    public void thenHeaderNotExist(String headerName) {
        restSteps.checkHeaderNotExistsStep(
                storageStep.evaluateValueStep(headerName)
        );
    }

    @Then("^response header '(.*)' should be (.*)$")
    public void thenHeaderEqual(String headerName, String headerValue) {
        restSteps.checkHeaderEqualStep(
                storageStep.evaluateValueStep(headerName),
                storageStep.evaluateValueStep(headerValue)
        );
    }

    @Then("^response header '(.*)' should not be '(.*)'$")
    public void thenHeaderNotEqual(String headerName, String headerValue) {
        restSteps.checkHeaderNotEqualStep(
                storageStep.evaluateValueStep(headerName),
                storageStep.evaluateValueStep(headerValue)
        );
    }

    @Then("^response body should be valid json$")
    public void thenBodyIsValid() {
        //restSteps.checkBodyIsValidStep();
    }

    @Then("^response body should contain '(.*)'$")
    public void thenBodyContains(String bodyValue) {
        restSteps.checkBodyContainsStep(
                storageStep.evaluateValueStep(bodyValue)
        );
    }

    @Then("^response body should be empty$")
    public void thenBodyEmpty() {
        restSteps.checkBodyEmptyStep();
    }

    @Then("^response body path '(.*)' should exists$")
    public void thenBodyPathExists(String jsonPath){
        restSteps.checkBodyPathExistsStep(
                storageStep.evaluateValueStep(jsonPath)
        );
    }

    @Then("^response body path '(.*)' should be '(.*)'$")
    public void thenBodyPathValueEqual(String jsonPath, String value){
        restSteps.checkBodyPathValueEqualStep(
                storageStep.evaluateValueStep(jsonPath),
                storageStep.evaluateValueStep(value)
        );
    }

    @Then("^response body path '(.*)' should equal '(.*)'$")
    public void thenBodyPathValueEqualAlias(String jsonPath, String value){
        restSteps.checkBodyPathValueEqualStep(
                storageStep.evaluateValueStep(jsonPath),
                storageStep.evaluateValueStep(value)
        );
    }

    @Then("^response body path '(.*)' should not be '(.*)'$")
    public void thenBodyPathValueNotEqual(String jsonPath, String value){
        restSteps.checkBodyPathValueNotEqualStep(
                storageStep.evaluateValueStep(jsonPath),
                storageStep.evaluateValueStep(value)
        );
    }

    @Then("^response body path '(.*)' should have array length '(.*)'$")
    public void thenJsonPathIsArray(String jsonPath, String length){
        restSteps.checkJsonPathIsArrayStep(storageStep.evaluateValueStep(jsonPath), storageStep.evaluateValueStep(length));
    }

    @Then("^I store the value of response header '(.*)' as '(.*)' in scenario storage$")
    public void thenResponseHeader(String headerName, String headerAlias){
        storageStep.saveHeaderVariableStep(
                headerAlias,
                restSteps.getResponseHeaderStep(headerName)
        );
    }

    @Then("^I store the value of body path '(.*)' as '(.*)' in scenario storage$")
    public void thenResponseJsonPath(String jsonPath, String jsonPathAlias){
        Object jsonPathValue = restSteps.getValueByJsonPathStep(jsonPath);
        storageStep.saveJsonPathVariableStep(jsonPathAlias, jsonPathValue);
    }

    @Then("^I store the length array of body path '(.*)' as '(.*)' in scenario storage$")
    public void thenLengthArrayByJsonPath(String jsonPath, String jsonPathAlias) {
        int jsonPathLengthArray = restSteps.getLengthArrayByJsonPathStep(jsonPath);
        storageStep.saveJsonPathVariableStep(jsonPathAlias, jsonPathLengthArray);
    }

    @Then("^response body is typed as array for path '(.*)'$")
    public void thenBodyPathIsArray(String jsonPath) {
        restSteps.checkJsonPathIsArrayStep(
                storageStep.evaluateValueStep(jsonPath)
        );
    }

    @When("^I multiplication values '(.*)' and '(.*)', store result to variable '(.*)'$")
    public void whenMultiplication(String firstValue, String secondValue, String resultAlias) {
        storageStep.saveVariableStep(
                resultAlias,
                utilsStep.multiplicationStep(
                        storageStep.evaluateValueStep(firstValue),
                        storageStep.evaluateValueStep(secondValue)
                )
        );
    }

    @When("^I add number '(.*)' to '(.*)', store result to variable '(.*)'$")
    public void whenAddNumbers(String firstValue, String secondValue, String resultAlias) {
        storageStep.saveVariableStep(
                resultAlias,
                utilsStep.addNumbersStep(
                    storageStep.evaluateValueStep(firstValue),
                    storageStep.evaluateValueStep(secondValue)
                )
        );
    }

    @When("^I set values to pojo with name '(.*)':$")
    public void whenAddQueryParamOfAlias(String sheamName, Map<String, String> values) {
        restSteps.setBodyStep(
                restSteps.setValueInPojoStep(
                        storageStep.evaluateValueStep(sheamName),
                        storageStep.evaluateValuesStep(values)
                )
        );
    }

}
