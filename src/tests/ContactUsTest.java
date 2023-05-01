//package tests;
//
//import infra.Utils;
//import infra.test.TestBase;
//import org.junit.*;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.*;
//import pages.ContactUsPage;
//
//import java.util.concurrent.TimeUnit;
//
//public class ContactUsTest extends TestBase {
//    private static WebDriver driver;
//    ContactUsPage contactUsPage=new ContactUsPage(driver);
//
//    @BeforeClass
//    public static void beforeClass() {
//        System.setProperty("webdriver.chrome.driver", "resources\\chromedriver.exe");
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("-incognito");
//        driver = new ChromeDriver(options);
//        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
//        driver.manage().window().maximize();
//        driver.get("https://tamrur.rabai.co.il/#/home/contact");
//    }
//
//    @Test
//    public void test()  {
//        contactUsPage.subjectSendKeys("שידוך");
//        contactUsPage.nameSendKeys("יעקב");
//        contactUsPage.emailSendKeys("ds@hgj.com");
//        contactUsPage.bodySendKeys("אני מחפש שידוך הרבה שנים");
//        contactUsPage.SendButtonClick();
//        Utils.sleep(5);
//        try {
//            Assert.assertEquals("https://tamrur.rabai.co.il/#/account/login",driver.getCurrentUrl());
//            Thread.sleep(4000);
//            // Alert alert1 =driver.switchTo().alert();
//            //alert1.accept();
//        }catch (AssertionError e){
//            System.out.println(e.getMessage());
//        }
//    }
//
//    @AfterClass
//    public static void afterClass() {
//        driver.quit();
//    }
//}
