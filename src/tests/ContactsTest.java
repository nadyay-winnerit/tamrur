package tests;

import infra.reporter.Reporter;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.ContactsPage;

import java.util.concurrent.TimeUnit;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class ContactsTest {

    private static WebDriver driver;
    private ContactsPage contactPage = new ContactsPage(driver);
    //private LoginPage loginPage= new LoginPage(driver);

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

//    @Test
//    //login to the site
//    public void a_loginTheSite() throws InterruptedException {
//        loginPage.userNameSendKeys("a@a.com");
//        loginPage.passWordSendKeys("123456");
//        loginPage.rememberClick();
//        loginPage.connectionButtonClick();
//        Thread.sleep(2000);
//    }

    @Test
    public void b_fillingDetails() throws InterruptedException {

        //enter the contact page
        contactPage.enterContactPage();
        Thread.sleep(2000);

        //checking if the contacts' page is displayed
        try {
            Assert.assertEquals("https://tamrur.rabai.co.il/#/home/persons", driver.getCurrentUrl());
            System.out.println("the contacts' page is displayed");
        } catch (AssertionError error) {
            System.out.println("the contacts' page is not displayed");
        }

        //clicking the plus button in the first row to enter the creating section
        contactPage.enterPlusBtnCreating();
        Thread.sleep(3000);

        //checking if the contacts' bar is selected and bolded
        contactPage.checkActiveBar();

        /*try {
            if (contactPage.checkActiveBar())
                System.out.println("the right page is displayed, and the contacts' bar is selected and bolded");
            else;
        } catch (Exception e){
            System.out.println("the contacts' bar is not selected and not bolded");
        }*/

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

    @Test
    public void tryReporter() {
        int a = 10, b = 4;

        Reporter.reporter().message("hello", null);
        Reporter.reporter().error("xxxxx", null);
        Reporter r = Reporter.reporter();

        r.message("ggjkh", null);
        r.result("abc", null, a % b == 0);
    }

    @AfterClass
    public static void afterClass() {
        driver.quit();
    }
}
