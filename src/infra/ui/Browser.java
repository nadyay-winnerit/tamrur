package infra.ui;

import infra.general.Config;
import infra.general.Prop;
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
            reporter.message("GOTO --> " + Config.getInstance().getValueOfProperty(Prop.URL), null);
        }
        return driver;
    }

    public static boolean isOpen() {
        return driver != null;
    }

    public static void close() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }


}
