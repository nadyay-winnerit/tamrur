package infra;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Config {
    private static Properties properties;
    public static String getConfig(Prop prop) {

        if (properties==null) {
            try {
                properties = new Properties();
                FileInputStream in = new FileInputStream("config.properties");
                properties.load(in);
                in.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return properties.getProperty(prop.toString());
    }
}
