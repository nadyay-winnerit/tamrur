package infra;

import infra.reporter.Reporter;

public class TestFailException extends RuntimeException {
    public TestFailException(String message, Throwable cause) {
        super(message, cause);
        Reporter.reporter().error(message, null);
    }
}
