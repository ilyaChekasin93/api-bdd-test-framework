package api.bdd.test.framework.hooks;

import api.bdd.test.framework.client.http.dto.Request;
import api.bdd.test.framework.client.soap.dto.SoapRequest;
import api.bdd.test.framework.client.soap.dto.SoapResponse;
import api.bdd.test.framework.context.RestContext;
import api.bdd.test.framework.context.StorageContext;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import lombok.extern.slf4j.Slf4j;
import api.bdd.test.framework.client.http.dto.Response;
import api.bdd.test.framework.context.SoapContext;

import java.util.HashMap;


@Slf4j
public class ScenarioHook {

    private RestContext restContext;

    private SoapContext soapContext;

    private StorageContext storageContext;


    public ScenarioHook(RestContext restContext, SoapContext soapContext, StorageContext storageContext){
        this.restContext = restContext;
        this.soapContext = soapContext;
        this.storageContext = storageContext;
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        log.info("Starting - {}", scenario.getName());
    }

    @After
    public void afterScenario(Scenario scenario) {
        restContext.setRequest(new Request());
        restContext.setResponse(new Response());

        soapContext.setSoapRequest(new SoapRequest());
        soapContext.setSoapResponse(new SoapResponse());

        storageContext.setVariable(new HashMap<>());

        log.info("Finished - {}", scenario.getName());
    }
}