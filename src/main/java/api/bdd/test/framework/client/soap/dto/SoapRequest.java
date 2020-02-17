package api.bdd.test.framework.client.soap.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class SoapRequest {

    private String pojoPath;

    private String url;

    private Object body;

    private String soapAction;

    public SoapRequest() {
        url = "";
        body = "";
        soapAction = "";
    }

}
