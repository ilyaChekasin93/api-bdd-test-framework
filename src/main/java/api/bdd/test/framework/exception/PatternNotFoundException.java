package api.bdd.test.framework.exception;

public class PatternNotFoundException extends RuntimeException {

    public PatternNotFoundException(String value) {
        super(String.format("Pattern for %s not found", value));
    }

}
