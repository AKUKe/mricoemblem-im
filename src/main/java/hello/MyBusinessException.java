package hello;

public class MyBusinessException extends RuntimeException{

    public MyBusinessException(String message) {
        super(message);
    }
}
