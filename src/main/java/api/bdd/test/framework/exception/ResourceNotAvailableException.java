package api.bdd.test.framework.exception;

public class ResourceNotAvailableException extends RuntimeException {

    public ResourceNotAvailableException(String url){
        super(String.format("Resource %s not available", url));
    }

}
