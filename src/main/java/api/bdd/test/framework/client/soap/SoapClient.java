package api.bdd.test.framework.client.soap;

import api.bdd.test.framework.client.soap.dto.SoapRequest;
import api.bdd.test.framework.client.soap.dto.SoapResponse;


public interface SoapClient {

    SoapResponse executeRequest(SoapRequest request);

    void init(String pojoPath);

}
