package infra;

import infra.reporter.Reporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class Browser {


    private static WebDriver driver;
    private static Reporter reporter = Reporter.reporter();

    public static WebDriver driver() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "resources\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("-incognito");
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);
            driver.manage().window().maximize();
            driver.get(Config.getInstance().getValueOfProperty(Prop.URL));
            System.out.println("GOTO --> " +Config.getInstance().getValueOfProperty(Prop.URL));
        }
        return driver;
    }

    public static void close() {
        driver.quit();
    }


}
