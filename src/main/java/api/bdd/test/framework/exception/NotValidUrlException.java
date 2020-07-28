package api.bdd.test.framework.exception;

public class NotValidUrlException extends RuntimeException {

    public NotValidUrlException(String url){
        super(String.format("%s this is not a valid url", url));
    }
}
