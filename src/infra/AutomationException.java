package infra;

public class AutomationException extends RuntimeException{
    public AutomationException(String message, Throwable cause) {
        super(message,cause);
        Reporter.reporter().error(message);
    }
}
