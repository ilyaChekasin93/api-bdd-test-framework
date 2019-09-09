package ru.api.bdd.test.framework.context;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.api.bdd.test.framework.client.http.dto.Request;
import ru.api.bdd.test.framework.client.http.dto.Response;


@Data
@Component
public class ScenarioContext {

    private Request request;

    private Response response;

    private Storage storage;

    @Autowired
    public ScenarioContext(Storage storage, Request requestImpl, Response response) {
        this.request = requestImpl;
        this.storage = storage;
        this.response = response;
    }

    public void update() {
        request.update();
        response.update();
        storage.clear();
    }

}
