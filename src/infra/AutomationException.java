package infra;

import infra.reporter.Reporter;

public class AutomationException extends RuntimeException {

    public AutomationException(String message, Throwable cause) {
        super(message, cause);
        Reporter.reporter().error(message, null, this);
    }

    public static String printAble(Throwable e) {
        StringBuilder strStack = new StringBuilder();
        StackTraceElement[] arrStack = e.getStackTrace();
        for (StackTraceElement stackTraceElement : arrStack) {
            strStack.append(stackTraceElement).append("/r/n");
        }
        return e.getMessage() + "/r/n" + strStack;
    }

}
