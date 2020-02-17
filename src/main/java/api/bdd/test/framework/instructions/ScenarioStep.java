package api.bdd.test.framework.instructions;

import api.bdd.test.framework.instructions.steps.RestStep;
import api.bdd.test.framework.instructions.steps.SoapStep;
import api.bdd.test.framework.instructions.steps.StorageStep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Map;


@ContextConfiguration
@SuppressWarnings("unused")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ScenarioStep {

    private RestStep restStep;

    private StorageStep storageStep;

    private SoapStep soapStep;


    public ScenarioStep(RestStep restStep, SoapStep soapStep, StorageStep storageStep){
        this.restStep = restStep;
        this.soapStep = soapStep;
        this.storageStep = storageStep;
    }

    @Given("^baseUrl is '(.*)'$")
    public void whenSetupBaseUrl(String baseUrl) {
        baseUrl = storageStep.evaluateValueStep(baseUrl);

        restStep.setBaseUrlStep(baseUrl);
    }

    @Given("^request body is '(.*)'$")
    public void whenSetBody(String body) {
        Object requestBody = storageStep.evaluateValueStep(body);

        restStep.setBodyStep(requestBody);
    }

    @Given("^query param is:$")
    public void whenSetQueryParam(Map<String, String> params) {
        params = storageStep.evaluateValuesStep(params);

        restStep.setQueryParamsStep(params);
    }

    @Given("^user add query param:$")
    public void whenAddQueryParam(Map<String, String> params) {
        params = storageStep.evaluateValuesStep(params);

        restStep.addQueryParamsStep(params);
    }

    @Given("^url param is:$")
    public void whenSetUrlParam(Map<String, String> params) {
        params = storageStep.evaluateValuesStep(params);

        restStep.addUrlParamsStep(params);
    }

    @Given("^user add url param:$")
    public void whenAddUrlParam(String key, String value) {
        key = storageStep.evaluateValueStep(key);
        value = storageStep.evaluateValueStep(value);

        restStep.addUrlParamStep(key, value);
    }

    @When("^user add query param with key '(.*)' and value '(.*)'")
    public void whenAddQueryParam(String key, String value) {
        key = storageStep.evaluateValueStep(key);
        value = storageStep.evaluateValueStep(value);

        restStep.addQueryParamStep(key, value);
    }

    @Given("^request headers is:$")
    public void whenSetHeaders(Map<String, String> params) {
        params = storageStep.evaluateValuesStep(params);

        restStep.addHeadersStep(params);
    }

    @Given("^user add request headers:$")
    public void whenAddHeaders(Map<String, String> params) {
        params = storageStep.evaluateValuesStep(params);

        restStep.addHeadersStep(params);
    }

    @Given("^user add request header with name '(.*)' and value '(.*)'$")
    public void whenAddHeader(String headerName, String headerValue) {
        headerName = storageStep.evaluateValueStep(headerName);
        headerValue = storageStep.evaluateValueStep(headerValue);

        restStep.addHeaderStep(headerName, headerValue);
    }

    @When("^user send '(.*)' request to '(.*)'$")
    public void whenExecuteRequest(String method, String resource) {
        resource = storageStep.evaluateValueStep(resource);
        method = storageStep.evaluateValueStep(method);

        restStep.executeRequestStep(resource, method);
    }

    @Then("^response code should equal to '(.*)'$")
    public void thenResponseCodeEqual(String status) {
        status = storageStep.evaluateValueStep(status);

        restStep.checkResponseCodeEqualStep(status);
    }

    @Then("^response code should not equal to '(.*)'$")
    public void thenResponseCodeIsNot(String status) {
        status = storageStep.evaluateValueStep(status);

        restStep.checkResponseCodeEqualStep(status);
    }

    @Then("^response header '(.*)' should exist")
    public void thenHeaderExist(String headerName) {
        headerName = storageStep.evaluateValueStep(headerName);

        restStep.checkHeaderExistsStep(headerName);
    }

    @Then("^response header '(.*)' should not exist")
    public void thenHeaderNotExist(String headerName) {
        headerName = storageStep.evaluateValueStep(headerName);

        restStep.checkHeaderNotExistsStep(headerName);
    }

    @Then("^response header '(.*)' should contain (.*)$")
    public void thenHeaderEqual(String headerName, String headerValue) {
        headerName = storageStep.evaluateValueStep(headerName);
        Object value = storageStep.evaluateValueStep(headerValue);

        if(value instanceof String){
            restStep.checkHeaderContainsStep(headerName, value.toString());
        }else {
            restStep.checkHeaderContainsStep(headerName, (List<String>) value);
        }
    }

    @Then("^response header '(.*)' should not contain (.*)$")
    public void thenHeaderNotEqual(String headerName, String headerValue) {
        headerName = storageStep.evaluateValueStep(headerName);
        Object value = storageStep.evaluateValueStep(headerValue);

        if(value instanceof String){
            restStep.checkHeaderNotContainsStep(headerName, value.toString());
        }else {
            restStep.checkHeaderNotContainsStep(headerName, (List<String>) value);
        }
    }

    @Then("^response body should contain '(.*)'$")
    public void thenBodyContains(String bodyValue) {
        bodyValue = storageStep.evaluateValueStep(bodyValue);

        restStep.checkBodyContainsStep(bodyValue);
    }

    @Then("^response body should be empty$")
    public void thenBodyEmpty() {
        restStep.checkBodyEmptyStep();
    }

    @Then("^response body value by jsonPath '(.*)' should exist")
    public void thenBodyPathExists(String jsonPath){
        jsonPath = storageStep.evaluateValueStep(jsonPath);

        restStep.checkBodyPathExistsStep(jsonPath);
    }

    @Then("^response body value by jsonPath '(.*)' should equal to '(.*)'$")
    public void thenBodyPathValueEqual(String jsonPath, String value){
        jsonPath = storageStep.evaluateValueStep(jsonPath);
        value = storageStep.evaluateValueStep(value);

        restStep.checkBodyPathValueEqualStep(jsonPath, value);
    }

    @Then("^response body value by jsonPath '(.*)' should not equal to '(.*)'$")
    public void thenBodyPathValueNotEqual(String jsonPath, String value){
        jsonPath = storageStep.evaluateValueStep(jsonPath);
        value = storageStep.evaluateValueStep(value);

        restStep.checkBodyPathValueNotEqualStep(jsonPath, value);
    }

    @Then("^user save response header value '(.*)' as '(.*)' variable$")
    public void thenResponseHeader(String headerName, String headerAlias){
        List<String> headerValue = restStep.getResponseHeaderStep(headerName);

        storageStep.saveVariableStep(headerAlias, headerValue);
    }

    @Then("^user save response body value by jsonPath '(.*)' as '(.*)' variable$")
    public void thenResponseJsonPath(String jsonPath, String jsonPathAlias){
        Object jsonPathValue = restStep.getValueByJsonPathStep(jsonPath);

        storageStep.saveVariableStep(jsonPathAlias, jsonPathValue);
    }

    @Given("^user add values to pojo with name '(.*)':$")
    public void whenSetValuesToBody(String shemName, Map<String, String> values) {
        shemName = storageStep.evaluateValueStep(shemName);
        values = storageStep.evaluateValuesStep(values);

        Object body = restStep.setValueInPojoStep(shemName, values);

        restStep.setBodyStep(body);
    }

    @Given("^user set web service URL '(.*)'$")
    public void whenSetupSoapUrl(String url) {
        url = storageStep.evaluateValueStep(url);

        soapStep.setUrlStep(url);
    }

    @Given("^user set SOAP request body '(.*)'$")
    public void whenSetSoapBody(String body) {
        Object bodyValue = storageStep.evaluateValueStep(body);

        soapStep.setBodyStep(bodyValue);
    }

    @Given("^user add values to SOAP pojo with name '(.*)':$")
    public void whenSetValuesToSoapBody(String shemName, Map<String, String> values) {
        shemName = storageStep.evaluateValueStep(shemName);
        values = storageStep.evaluateValuesStep(values);

        Object body = soapStep.setValueInPojoStep(shemName, values);

        soapStep.setBodyStep(body);
    }

    @Then("^SOAP response body value on xPath '(.*)' should not be empty$")
    public void thenSoapBodyPathExists(String xPath){
        xPath = storageStep.evaluateValueStep(xPath);

        soapStep.checkBodyPathExistsStep(xPath);
    }

    @Then("^user save SOAP response body value by xPath '(.*)' as '(.*)' variable$")
    public void thenSoapResponseJsonPath(String xPath, String xPathAlias){
        xPath = storageStep.evaluateValueStep(xPath);
        Object bodyValue = soapStep.getValueByXPathPathStep(xPath);

        storageStep.saveVariableStep(xPathAlias, bodyValue);
    }

    @Then("^SOAP response body value by xPath '(.*)' should be '(.*)'$")
    public void thenSoapBodyPathValueEqual(String xPath, String value){
        xPath = storageStep.evaluateValueStep(xPath);
        value = storageStep.evaluateValueStep(value);

        soapStep.checkBodyPathValueEqualStep(xPath, value);
    }

    @Then("^SOAP response body value by xPath '(.*)' should not be '(.*)'$")
    public void thenSoapBodyPathValueNotEqual(String xPath, String value){
        xPath = storageStep.evaluateValueStep(xPath);
        value = storageStep.evaluateValueStep(value);

        soapStep.checkBodyPathValueNotEqualStep(xPath, value);
    }

    @Then("^SOAP response body should contain '(.*)'$")
    public void thenSoapBodyContains(String bodyValue) {
        bodyValue = storageStep.evaluateValueStep(bodyValue);

        soapStep.checkBodyContainsStep(bodyValue);
    }

    @When("^user execute SOAP request$")
    public void executeRequestStep() {
        soapStep.executeRequestStep();
    }

}
