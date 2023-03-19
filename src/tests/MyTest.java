package tests;

import infra.Browser;
import objects.pages.LoginPageData;
import org.junit.AfterClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import pages.LoginPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MyTest {

    @Test
    public void test_1() throws InterruptedException {

        LoginPage loginPage = new LoginPage();
        LoginPageData loginPageData = new LoginPageData();
        loginPageData.setUserNameUi("auto@mation.com");
        loginPageData.setPassWordUi("mation");
        loginPageData.setRememberCheckBoxUi(true);
        loginPage.fillPage(loginPageData);
        loginPage.connectionButtonClick();

    }


    @AfterClass
    public static void afterClass() {
        Browser.close();
    }
}
