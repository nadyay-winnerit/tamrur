package tests;

import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.LoginPage;

import java.util.concurrent.TimeUnit;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MyTest {
    private static WebDriver driver;
    private LoginPage loginPage = new LoginPage(driver);

    @BeforeClass
    public static void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\IdeaProjects\\tamrur\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("-incognito");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://tamrur.rabai.co.il/#/account/login");
    }

    @Test
    public void test_1() throws InterruptedException {
        loginPage.userNameSendKeys("a@a.com");
        Thread.sleep(4000);
        loginPage.passWordSendKeys("123456");
        Thread.sleep(4000);
        loginPage.rememberClick();
        loginPage.connectionButtonClick();
        Thread.sleep(4000);

    }

    @Test
    public void test_2() throws InterruptedException {
        loginPage.forgotPasswordLinkClick();
        Thread.sleep(4000);

    }

    @Test
    public void test_3() throws InterruptedException {
        loginPage.newUserLinkClick();
        Thread.sleep(4000);
    }

    @After
    public void afterTest() {
        driver.navigate().to("https://tamrur.rabai.co.il/#/account/login");
    }


    @AfterClass
    public static void afterClass() {
        driver.quit();
    }
}
