package api.bdd.test.framework.instructions.steps;

import api.bdd.test.framework.action.SoapAction;
import io.qameta.allure.Step;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Map;


@Component
public class SoapStep {

    private SoapAction action;


    public SoapStep(SoapAction action){
        this.action = action;
    }

    @Step("set SOAP Action {soapAction}")
    public void setSoapActionStep(String soapAction) {
        action.setSoapAction(soapAction);
    }

    @Step("set SOAP URL {url}")
    public void setUrlStep(String url) {
        action.setUrl(url);
    }

    @Step("set SOAP request body {body}")
    public void setBodyStep(Object body) {
        action.setRequestBody(body);
    }

    @Step("execute SOAP request")
    public void executeRequestStep() {
        action.executeRequest();
    }

    @Step("set values {values} in pojo with name {pojoName}")
    public Object setValueInPojoStep(String pojoName, Map<String, String> values) {
        return action.setValuesInPojo(pojoName, values);
    }

    @Step("check that value by xPath {xPath} is present")
    public void checkBodyPathExistsStep(String xPath){
        Assert.notNull(action.getResponseBodyValue(xPath), "Value must not be null");
    }

    @Step("get value by xPath {xPath}")
    public Object getValueByXPathPathStep(String xPath){
        return action.getResponseBodyValue(xPath);
    }

    @Step("check that value by xPath {xPath} equal {value}")
    public void checkBodyPathValueEqualStep(String xPath, String value){
        String bodyPathValue = action.getResponseBodyValue(xPath).toString();
        Assert.isTrue(bodyPathValue.equals(value), String.format("%s must be equal %s", bodyPathValue, value));
    }

    @Step("check that value by xPath {xPath} not equal {value}")
    public void checkBodyPathValueNotEqualStep(String xPath, String value){
        String bodyPathValue = action.getResponseBodyValue(xPath).toString();
        Assert.isTrue(bodyPathValue.equals(value), String.format("%s must be equal %s", bodyPathValue, value));
    }

    @Step("check that value by xPath {xPath} contains {value}")
    public void checkBodyContainsStep(String value){
        String bodyValue = action.getResponseBody();
        Assert.isTrue(bodyValue.contains(value), String.format("Value %s must contain %s", bodyValue, value));
    }

}
