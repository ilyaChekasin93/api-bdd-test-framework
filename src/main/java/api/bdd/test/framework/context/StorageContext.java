package api.bdd.test.framework.context;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Data
@Component
public class StorageContext<T> {

    private Map<String, T> variable;


    public StorageContext() {
        this.variable = new HashMap<>();
    }

}
