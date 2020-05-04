package api.bdd.test.framework.transform.route.impl;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


@Component
public class CurrentDateRoute extends AbstractFunctionRoute {

    private static final String FUNCTION_NAME = "dateNow";

    protected String getFunctionName() {
        return FUNCTION_NAME;
    }

    public String getValue() {
        String format = arguments.get(0);

        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);

        return dateFormat.format(currentDate);
    }
}
