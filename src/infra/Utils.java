package infra;

public class Utils {

    public static void sleep(int second){
        int millisecond=second*1000;
        try {
            Thread.sleep(millisecond);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
