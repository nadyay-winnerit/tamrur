package infra.general;

import infra.reporter.Reporter;

public class AutomationException extends RuntimeException {

    public AutomationException(String message, String moreInfo, Throwable cause) {
        super(message, cause);
        Reporter.reporter().error(message, moreInfo, this);
    }

    public static String printable(Throwable e) {
        StringBuilder strStack = new StringBuilder("\r\n[Exception]::" + e.getMessage());
        StackTraceElement[] arrStack = e.getStackTrace();
        for (StackTraceElement stackTraceElement : arrStack) {
            strStack.append("\r\n\t\t").append(stackTraceElement);
        }
        while (e.getCause() != null) {
            e = e.getCause();
            strStack.append("\r\n[Cause]::" + e.getMessage());
            arrStack = e.getStackTrace();
            for (StackTraceElement stackTraceElement : arrStack) {
                strStack.append("\r\n\t\t").append(stackTraceElement);
            }
        }
        return strStack.toString();
    }

}
