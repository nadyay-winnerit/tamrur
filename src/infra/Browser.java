package infra;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class Browser {


    private static WebDriver driver;

    public static WebDriver driver() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "resources\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("-incognito");
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);
            driver.manage().window().maximize();
            driver.get(Config.server);
            System.out.println("GOTO --> " + Config.server);
        }
        return driver;
    }

    public static void close() {
        driver.quit();
    }


}