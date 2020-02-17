package api.bdd.test.framework.instructions.steps;

import api.bdd.test.framework.action.RestAction;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;


@Slf4j
@Component
public class RestStep {

    private RestAction action;


    public RestStep(RestAction action){
        this.action = action;
    }

    @Step("executed {method} request on resource {resource}")
    public void executeRequestStep(String resource, String method) {
        action.executeRequest(resource, method);
    }

    @Step("set URL {baseUrl}")
    public void setBaseUrlStep(String url) {
        action.setBaseUrl(url);
    }

    @Step("set request body {body}")
    public void setBodyStep(Object body) {
        action.setBody(body);
    }

    @Step("add header with name {headerName} and value {headerValue}")
    public void addHeaderStep(String name, String value) {
        action.addHeader(name, value);
    }

    @Step("add headers {headers}")
    public void addHeadersStep(Map<String, String> headers){
        action.addHeaders(headers);
    }

    @Step("add query params {params}")
    public void addQueryParamsStep(Map<String, String> params) {
        action.addQueryParams(params);
    }

    @Step("add url params {params}")
    public void addUrlParamsStep(Map<String, String> params) {
        action.addUrlParams(params);
    }

    @Step("add query params {params}")
    public void addQueryParamStep(String key, String value) {
        action.addQueryParam(key, value);
    }

    @Step("add url params {params}")
    public void addUrlParamStep(String key, String value) {
        action.addUrlParam(key, value);
    }

    @Step("set headers {headers}")
    public void setHeadersStep(Map<String, String> headers){
        action.setHeaders(headers);
    }

    @Step("set query params {params}")
    public void setQueryParamsStep(Map<String, String> params) {
        action.setQueryParams(params);
    }

    @Step("set url params {params}")
    public void setUrlParamsStep(Map<String, String> params) {
        action.setUrlParams(params);
    }

    @Step("check that response code is not equal to {statusCode}")
    public void checkResponseCodeNotEqualStep(String statusCode) {
        String responseStatusCode = action.getResponseStatusCode();
        String assertMessage = String.format("Value %s must not be equal %s", responseStatusCode, statusCode);
        Assert.isTrue(!responseStatusCode.equals(statusCode), assertMessage);
    }

    @Step("check that response code is equal to {statusCode}")
    public void checkResponseCodeEqualStep(String statusCode) {
        String responseStatusCode = action.getResponseStatusCode();
        String assertMessage = String.format("Value %s must be equal %s", responseStatusCode, statusCode);
        Assert.isTrue(responseStatusCode.equals(statusCode), assertMessage);
    }

    @Step("get response header value named {headerName}")
    public List<String> getResponseHeaderStep(String headerName) {
        return action.getRequestHeaderValue(headerName);
    }

    @Step("check that header {headerName} was found")
    public void checkHeaderExistsStep(String headerName) {
        List<String> headerValue = action.getRequestHeaderValue(headerName);
        String assertMessage = String.format("Value header %s must not be empty", headerName);
        Assert.isTrue(headerValue.size() > 0, assertMessage);
    }

    @Step("check that header {headerName} was not found")
    public void checkHeaderNotExistsStep(String headerName) {
        List<String> headerValue = action.getRequestHeaderValue(headerName);
        String assertMessage = String.format("Value header %s must be empty", headerName);
        Assert.isTrue(headerValue.size() == 0, assertMessage);
    }

    @Step("check that response header with name {headerName} has value {headerValue}")
    public void checkHeaderContainsStep(String name, String value) {
        boolean isContains = action.getResponseHeaderValue(name).contains(value);
        String assertMessage = String.format("Header %s must be contain %s", name, value);
        Assert.isTrue(isContains, assertMessage);
    }

    @Step("check that response header with name {headerName} has value {headerValue}")
    public void checkHeaderContainsStep(String name, List<String> values) {
        boolean isContains = action.getResponseHeaderValue(name).contains(values);
        String assertMessage = String.format("Header %s must be contain %s", name, values);
        Assert.isTrue(isContains, assertMessage);
    }

    @Step("checked that response header with name {headerName} does not have value {headerValue}")
    public void checkHeaderNotContainsStep(String name, String value) {
        boolean isContains = action.getResponseHeaderValue(name).contains(value);
        String assertMessage = String.format("Header %s must not be contain %s", name, value);
        Assert.isTrue(isContains, assertMessage);
    }

    @Step("checked that response header with name {headerName} does not have value {headerValue}")
    public void checkHeaderNotContainsStep(String name, List<String> value) {
        boolean isContains = action.getResponseHeaderValue(name).contains(value);
        String assertMessage = String.format("Header %s must not be contain %s", name, value);
        Assert.isTrue(isContains, assertMessage);
    }

    @Step("check that response body contains {bodyValue}")
    public void checkBodyContainsStep(String value){
        String responseBody = action.getResponseBody();
        String assertMessage = String.format("Body %s must contain %s", responseBody, value);
        Assert.isTrue(responseBody.contains(value), assertMessage);
    }

    @Step("check that response body is empty")
    public void checkBodyEmptyStep(){
        String responseBody = action.getResponseBody();
        String assertMessage = String.format("Body %s must be empty", responseBody);
        Assert.isTrue(responseBody.equals("{}"), assertMessage);
    }

    @Step("check that jsonPath {jsonPath} value is present")
    public void checkBodyPathExistsStep(String jsonPath){
        String responseBodyValue = action.getResponseBodyValue(jsonPath);
        String assertMessage = String.format("Value %s by bodyPath %s must not be null", responseBodyValue, jsonPath);
        Assert.notNull(responseBodyValue, assertMessage);
    }

    @Step("get value by jsonPath {jsonPath}")
    public String getValueByJsonPathStep(String jsonPath){
        return action.getResponseBodyValue(jsonPath);
    }

    @Step("check that in response body value by jsonPath {jsonPath} is equal to {value}")
    public void checkBodyPathValueEqualStep(String jsonPath, String value){
        String pathValue = action.getResponseBodyValue(jsonPath).replaceAll("\"", "");
        String assertMessage = String.format("%s must be equal %s", pathValue, value);
        Assert.isTrue(pathValue.equals(value), assertMessage);
    }

    @Step("check that in response body value by jsonPath {jsonPath} is not equal to {value}")
    public void checkBodyPathValueNotEqualStep(String jsonPath, String value){
        String pathValue = action.getResponseBodyValue(jsonPath);
        String assertMessage = String.format("%s must not be equal %s", pathValue, value);
        Assert.isTrue(!pathValue.equals(value), assertMessage);
    }

    @Step("add value {values} to pojo named {shemName}")
    public Object setValueInPojoStep(String shemName, Map<String, String> values) {
        return action.setValuesInPojo(shemName, values);
    }
}
