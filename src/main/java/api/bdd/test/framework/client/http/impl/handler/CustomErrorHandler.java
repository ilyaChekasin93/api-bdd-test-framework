package api.bdd.test.framework.client.http.impl.handler;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

@Component
public class CustomErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) { return false; }

    @Override
    public void handleError(ClientHttpResponse response) { }
}
