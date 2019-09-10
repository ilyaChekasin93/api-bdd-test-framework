package api.bdd.test.framework.exception;

public class GettingPojoException extends RuntimeException {

    public GettingPojoException(String pojoPath, String errorMessage) {
        super(String.format("Error getting pojo by path '%s' with error message: '%s'", pojoPath, errorMessage));
    }

}
