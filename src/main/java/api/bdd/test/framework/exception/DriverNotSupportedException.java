package api.bdd.test.framework.exception;


public class DriverNotSupportedException extends RuntimeException {

    public DriverNotSupportedException(String databaseName) {
        super(String.format("Driver for database %s not supported", databaseName));
    }

}
