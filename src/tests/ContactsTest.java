package tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.ContactsPage;
import pages.LoginPage;

import java.util.concurrent.TimeUnit;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class ContactsTest {

    private static WebDriver driver;
    private ContactsPage contactPage = new ContactsPage(driver);
    private LoginPage loginPage= new LoginPage(driver);

    @BeforeClass
    public static void beforeClass() {
        // יוצר נתיב יחסי, שלוקח את הדפדפן מתיקיית הריסורסס ואז בכל מחשב הולך למיקום של הדפדפן המקומי
        System.setProperty("webdriver.chrome.driver", "resources\\chromedriver.exe");  //resources, ומשם בכל מחשב לוקח את הנתיב המקומי
        // יוצר נתיב יחסי, שלוקח את הדפדפן מתיקיית ה
        ChromeOptions options = new ChromeOptions();
        options.addArguments("-incognito");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://tamrur.rabai.co.il/#/account/login");
    }

    @Test
    public void a_loginTheSite() throws InterruptedException {
        loginPage.userNameSendKeys("a@a.com");
        Thread.sleep(4000);
        loginPage.passWordSendKeys("123456");
        Thread.sleep(4000);
        loginPage.rememberClick();
        loginPage.connectionButtonClick();
        Thread.sleep(4000);
    }

    @Test
    public void b_fillingDetails() throws InterruptedException {

        //enter the contact page
        contactPage.enterContactPage();
        Thread.sleep(3000);

        //clicking the plus button in the first row to enter the creating section
        contactPage.enterPlusBtnCreating();
        Thread.sleep(3000);

        //filling the details in the fields
        contactPage.fillingFieldsContact("first name");
        contactPage.fillingFieldsContact("last name");
        contactPage.fillingFieldsContact("phone number");
        contactPage.fillingFieldsContact("mail");
        contactPage.fillingFieldsContact("contact's role");
        Thread.sleep(3000);

        //click the create button
        contactPage.fillingFieldsContact("create");
        Thread.sleep(3000);

    }

    @AfterClass
    public static void afterClass() {
        driver.quit();
    }
}
