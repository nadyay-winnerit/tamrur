package infra;

public class TestFailException extends RuntimeException{
    public TestFailException(String message, Throwable cause) {
        super(message,cause);
        Reporter.reporter().error(message);
    }
}
