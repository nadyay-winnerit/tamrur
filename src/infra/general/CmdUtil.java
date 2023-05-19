package infra.general;

public class CmdUtil {

    public static void run(String command) {

        try {
            Process process = Runtime.getRuntime().exec(command);
            //process.getInputStream() - can be printed to sout
            //process.getErrorStream() - can be printed to sout
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }


}
