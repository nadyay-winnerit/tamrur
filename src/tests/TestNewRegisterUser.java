package tests;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.LoginPage;
import pages.RegisterUserPage;

import java.util.concurrent.TimeUnit;

public class TestNewRegisterUser {
    private static WebDriver driver;
    LoginPage loginPage=new LoginPage(driver);
    RegisterUserPage registerUserPage=new RegisterUserPage(driver);

    @BeforeClass
    public static void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "resources\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("-incognito");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://tamrur.rabai.co.il/#/account/login");
    }
    @Before
    public  void before() throws InterruptedException {
        Thread.sleep(4000);
        loginPage.userNameSendKeys("a@a.com");
        loginPage.passWordSendKeys("123456");
        loginPage.rememberClick();
        loginPage.connectionButtonClick();
        Thread.sleep(4000);
        driver.navigate().to("https://tamrur.rabai.co.il/#/account/register/0");
    }


    @Test
    public void test() throws InterruptedException {
        registerUserPage.emailSendKeys("s@ughkj.com");
        registerUserPage.fNameSendKeys("יעקב מנהל");
        registerUserPage.pwdSendKeys("1234565");
        registerUserPage.lrSelectByValue("0: 1");
        registerUserPage.checkActive();
        Thread.sleep(4000);
        registerUserPage.SaveButtonClick();
    }

    @AfterClass
    public static void afterClass() {
        driver.quit();
    }
}
