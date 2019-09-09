package ru.api.bdd.test.framework.instructions.steps;

import io.qameta.allure.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.api.bdd.test.framework.summary.ScenarioRestAgregator;
import ru.api.bdd.test.framework.assertion.Checker;

import java.util.Collection;
import java.util.List;
import java.util.Map;


@Component
@SuppressWarnings("unchecked")
public class RestStep {

    @Autowired
    private ScenarioRestAgregator restAgregator;

    @Step("sent {method} request with resource {resource}")
    public void executeRequestStep(String resource, String method) {
        Checker.isNotEmpty(resource);

        restAgregator.executeRequest(resource, method);
    }

    @Step("set base URL {baseUrl}")
    public void setBaseUrlStep(String url) {
        Checker.isNotEmpty(url);

        restAgregator.setBaseUrl(url);
    }

    @Step("set body {body}")
    public void setBodyStep(Object body) {
        Checker.isNotEmpty(body);

        restAgregator.setRequestBody(body);
    }

    @Step("set header with name {headerName} and value {headerValue}")
    public void addHeaderStep(String headerName, String headerValue) {
        Checker.isNotEmpty(headerName);
        Checker.isNotEmpty(headerValue);

        restAgregator.addRequestHeader(headerName, headerValue);
    }

    @Step("added query param {params}")
    public void addQueryParamStep(Map<String, String> params) {
        Checker.isNotEmpty(params);

        restAgregator.addQueryParam(params);
    }

    @Step("added query param {headers}")
    public void addHeadersStep(Map<String, String> headers) {
        Checker.isNotEmpty(headers);

        restAgregator.addRequestHeaders(headers);
    }

    @Step("responseImpl code is not {statusCode}")
    public void checkResponseCodeIsNotStep(String statusCode) {
        Checker.isAboveZero(statusCode);
        Checker.isNotEquals(restAgregator.getResponseCode(), statusCode);

    }

    @Step("responseImpl code is {statusCode}")
    public void checkResponseCodeIsStep(String statusCode) {
        Checker.isNumber(statusCode);
        Checker.isAboveZero(statusCode);
        Checker.isEquals(restAgregator.getResponseCode(), statusCode);
    }

    @Step("get responseImpl header by headerName {headerName}")
    public List<String> getResponseHeaderStep(String headerName) {
        Checker.isNotEmpty(headerName);

        return restAgregator.getResponseHeader(headerName);
    }

    @Step("header {headerName} is exists")
    public void checkHeaderExistsStep(String headerName) {
        Checker.isNotEmpty(headerName);
        Checker.isNotEmpty(restAgregator.getResponseHeader(headerName));
    }

    @Step("header {headerName} is not exists")
    public void checkHeaderNotExistsStep(String headerName) {
        Checker.isNotEmpty(headerName);

        Checker.isNotEmpty(restAgregator.getResponseHeaders());
        Checker.isNotExist(restAgregator.getResponseHeader(headerName));
    }

    @Step("header {headerName} with {headerValue} is equal responseImpl header")
    public void checkHeaderEqualStep(String headerName, String headerValue) {
        Checker.isNotEmpty(headerName);
        Checker.isNotEmpty(headerValue);

        Checker.isNotEmpty(restAgregator.getResponseHeaders());
        Checker.isContains(restAgregator.getResponseHeader(headerName), headerValue);
    }

    @Step("header {headerName} with {headerValue} is not equal responseImpl header")
    public void checkHeaderNotEqualStep(String headerName, String headerValue) {
        Checker.isNotEmpty(headerName);
        Checker.isNotEmpty(headerValue);

        Checker.isNotEmpty(restAgregator.getResponseHeaders());
        Checker.isNotContains(restAgregator.getResponseHeader(headerName), headerValue);
    }

    /*@Step("responseImpl body is valid")
    public void checkBodyIsValidStep() {
        Checker.isNotEmpty(restAgregator.getResponseBody());
        Checker.isTrue(restAgregator.responseBodyIsValid());
    }*/

    @Step("responseImpl body contains {bodyValue}")
    public void checkBodyContainsStep(String bodyValue){
        Checker.isNotEmpty(bodyValue);
        Checker.isContains(restAgregator.getResponseBody(), bodyValue);
    }

    @Step("responseImpl body is empty")
    public void checkBodyEmptyStep(){
        Checker.isTrue(restAgregator.responseBodyIsEmpty());
    }

    @Step("value on path {jsonPath} exist in responseImpl body")
    public void checkBodyPathExistsStep(String jsonPath){
        Checker.isNotEmpty(jsonPath);
        Checker.isExist(restAgregator.getBodyValue(jsonPath));
    }

    @Step("get value by body path {jsonPath}")
    public Object getValueByJsonPathStep(String jsonPath){
        Checker.isNotEmpty(jsonPath);

        return restAgregator.getBodyValue(jsonPath);

    }

    @Step("value on path {jsonPath} equal value {value} in responseImpl body")
    public void checkBodyPathValueEqualStep(String jsonPath, String value){
        Object pathValue = restAgregator.getBodyValue(jsonPath);

        Checker.isNotEmpty(pathValue);
        Checker.isEquals(pathValue, value);
    }

    @Step("value on path {jsonPath} equal value {value} in responseImpl body")
    public void checkBodyPathValueEqualStep(String jsonPath, Object value){
        Object pathValue = restAgregator.getBodyValue(jsonPath);

        Checker.isNotEmpty(pathValue);
        Checker.isEquals(value, pathValue);
    }

    @Step("value on path {jsonPath} not equal value {value} in responseImpl body")
    public void checkBodyPathValueNotEqualStep(String jsonPath, String value){
        Object pathValue = restAgregator.getBodyValue(jsonPath);

        Checker.isNotEmpty(pathValue);
        Checker.isNotEquals(pathValue, value);
    }

    @Step("value on path {jsonPath} is array")
    public void checkJsonPathIsArrayStep(String jsonPath){
        Checker.isCollection(restAgregator.getBodyValue(jsonPath));
    }

    @Step("value on path {jsonPath} is array and have length {length}")
    public void checkJsonPathIsArrayStep(String jsonPath, String length){
        Object pathValue = restAgregator.getBodyValue(jsonPath);

        Checker.isCollection(pathValue);
        Checker.sizeIs(pathValue, length);
    }

    @Step("get length array path {jsonPath} is array and have length {length}")
    public int getLengthArrayByJsonPathStep(String jsonPath){
        Object pathValue = restAgregator.getBodyValue(jsonPath);

        Checker.isCollection(pathValue);

        return ((Collection) pathValue).size();
    }

    @Step("set values {values} in schema with name {sheamName}")
    public Object setValueInPojoStep(String sheamName, Map<String, String> values) {
        return restAgregator.setValuesInPojo(sheamName, values);
    }
}
