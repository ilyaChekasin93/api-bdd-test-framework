package api.bdd.test.framework.context;

import api.bdd.test.framework.client.soap.dto.SoapRequest;
import api.bdd.test.framework.client.soap.dto.SoapResponse;
import lombok.Data;
import org.springframework.stereotype.Component;


@Data
@Component
public class SoapContext {

    private SoapRequest soapRequest;

    private SoapResponse soapResponse;


    public SoapContext() {
        soapRequest = new SoapRequest();
    }

}
