package api.bdd.test.framework.client.soap.impl;

import api.bdd.test.framework.client.soap.SoapClient;
import api.bdd.test.framework.client.soap.dto.SoapRequest;
import api.bdd.test.framework.client.soap.dto.SoapResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;


@Data
@Lazy
@Slf4j
@Component
@EqualsAndHashCode(callSuper = true)
public class SoapClientImpl extends WebServiceGatewaySupport implements SoapClient {

    public void init(String pojoPath){
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath(pojoPath);
        this.setMarshaller(marshaller);
        this.setUnmarshaller(marshaller);
    }

    public SoapResponse executeRequest(SoapRequest request) {
        String url = request.getUrl();
        Object body = request.getBody();
        String action = request.getSoapAction();
        SoapActionCallback soapActionCallback = new SoapActionCallback(action);
        Object response = getWebServiceTemplate().marshalSendAndReceive(url, body, soapActionCallback);

        return new SoapResponse(response);
    }
}
