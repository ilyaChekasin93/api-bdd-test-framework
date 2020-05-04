package api.bdd.test.framework.action;

import api.bdd.test.framework.context.StorageContext;
import org.springframework.stereotype.Component;


@Component
public class StorageAction {

    private StorageContext context;


    public StorageAction(StorageContext context){
        this.context = context;
    }

    public <T> void saveVariable(String alias, T value){ context.getVariable().put(alias, value); }

    public  <T> T getVarValue(final String alias) { return (T) context.getVariable().get(alias); }

}
