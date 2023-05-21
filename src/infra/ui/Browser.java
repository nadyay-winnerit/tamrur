package infra.ui;

import infra.general.*;
import infra.reporter.Reporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;

import java.io.File;
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
            CmdUtil.run("taskkill /IM chromedriver.exe /F");
        }
    }


    public static File getPageSourceFile(int id) {
        File file = null;
        if (Browser.isOpen()) {
            try {
                file = new File("reporter/files/", "file_" + id + "_pageSource.html");
                FileUtils.writeStringToFile(file, Browser.driver().getPageSource(), "UTF-8");
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
        return file;
    }
}
