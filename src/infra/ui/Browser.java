package infra.ui;

import infra.general.*;
import infra.reporter.Reporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.logging.*;
import org.openqa.selenium.remote.CapabilityType;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class Browser {


    private static WebDriver driver;
    private static Reporter reporter = Reporter.reporter();

    public static WebDriver driver() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "resources\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("-incognito");
            options.addArguments("--disable-popup-blocking", "-disable-popup-blocking", "--disable-popup-blocking=true", "--block-new-web-contents=false"
                    , "--error-console", "--disable-infobars", "--enable-logging", "--log-level=1", "--disable-gpu", "--no-sandbox"
                    , "--webview-disable-safebrowsing-support"
            );
            options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            LoggingPreferences lp = new LoggingPreferences();
            lp.enable(LogType.BROWSER, Level.INFO);
            //lp.enable(LogType.DRIVER, Level.INFO);
            options.setCapability(CapabilityType.LOGGING_PREFS, lp);
            options.setCapability(CapabilityType.HAS_NATIVE_EVENTS, true);
            options.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
            options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
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

            StringBuilder logs = new StringBuilder();
            List<LogEntry> filter = driver().manage().logs().get(LogType.BROWSER).filter(Level.SEVERE);
            for (LogEntry le : filter) {
                logs.append("\r\n").append(le.toString());
            }
            if (logs.length() > 0) {
                reporter.warning("Browser Errors", logs.toString());
            }
            driver.quit();
            driver = null;
            CmdUtil.run("taskkill /IM chromedriver.exe /F");
            Utils.sleep(1);
        }
    }

    public static Object runJS(String script, Object... arguments) {
        return ((JavascriptExecutor) driver).executeScript(script, arguments);
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
