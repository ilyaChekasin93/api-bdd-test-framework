package api.bdd.test.framework.action;

import api.bdd.test.framework.client.soap.SoapClient;
import api.bdd.test.framework.client.soap.dto.SoapRequest;
import api.bdd.test.framework.client.soap.dto.SoapResponse;
import api.bdd.test.framework.context.SoapContext;
import api.bdd.test.framework.action.body.BodyAction;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.WebServiceIOException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Map;

import static api.bdd.test.framework.utils.Helpers.getPojoValue;
import static api.bdd.test.framework.utils.Helpers.reverse;


@Component
public class SoapAction {

    private SoapContext context;

    private SoapClient client;

    private BodyAction bodyAction;


    public SoapAction(SoapContext context, @Lazy SoapClient client, @Qualifier("xmlBodyAction") BodyAction bodyAction){
        this.context = context;
        this.client = client;
        this.bodyAction = bodyAction;
    }

    public void setUrl(String url) {
        context.getSoapRequest().setUrl(url);
    }

    public void setRequestBody(Object body) {
        context.getSoapRequest().setBody(body);
    }

    public void executeRequest() {
        SoapRequest request = context.getSoapRequest();
        String pojoPath = request.getPojoPath();

        client.init(pojoPath);

        SoapResponse response;
        try {
            response = client.executeRequest(request);
        }catch (WebServiceIOException e){
            throw new RuntimeException(String.format("resource %s not available", request.getUrl()));
        }

        context.setSoapResponse(response);
        context.setSoapRequest(new SoapRequest());
    }

    public void setSoapAction(String soapAction) {
        String[] packs;
        try {
            packs = new URI(soapAction).getHost().split("\\.");
        } catch (URISyntaxException e) {
            throw new RuntimeException(String.format("%s is not a URL", soapAction));
        }

        final String[] pojoPath = {""};
        Arrays.stream(reverse(packs)).forEach(p -> pojoPath[0] = pojoPath[0].equals("") ? p : String.format("%s.%s", pojoPath[0], p));

        context.getSoapRequest().setPojoPath(pojoPath[0]);
        context.getSoapRequest().setSoapAction(soapAction);
    }

    public Object setValuesInPojo(String pojoName, Map<String, String> values) {
        String pojoPath = String.format("%s.%s", context.getSoapRequest().getPojoPath(), pojoName);
        Object body = getPojoValue(pojoPath);

        return bodyAction.setValuesByBodyPath(body, values);
    }

    public Object setValueInPojo(String pojoName, String xPath, String value) {
        String pojoPath = String.format("%s.%s", context.getSoapRequest().getPojoPath(), pojoName);
        Object body = getPojoValue(pojoPath);

        return bodyAction.setValueByBodyPath(body, xPath, value);
    }

    public Object getResponseBodyValue(String xPath){
        Object responseBody = context.getSoapResponse().getBody();
        return bodyAction.getValueByBodyPath(xPath, responseBody).toString();
    }

    public String getResponseBody(){
        Object responseBody = context.getSoapResponse().getBody();

        return bodyAction.body2String(responseBody);
    }

}
