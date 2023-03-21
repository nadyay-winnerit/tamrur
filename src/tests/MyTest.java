package tests;

import infra.Browser;
import objects.TableWe;
import objects.pages.LoginPageData;
import org.junit.AfterClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.LoginPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MyTest {

    @Test
    public void test_1() throws InterruptedException {
        TableWe tableWe = new TableWe("table", By.tagName("tbody"));
        LoginPage loginPage = new LoginPage();
        LoginPageData loginPageData = new LoginPageData();
        loginPageData.setUserNameUi("auto@mation.com");
        loginPageData.setPassWordUi("mation");
        loginPageData.setRememberCheckBoxUi(true);
        loginPage.fillPage(loginPageData);
        loginPage.connectionButtonClick();
        Thread.sleep(2002);
        //click on תיקים רפואיים
        Browser.driver().findElement(By.cssSelector("a.nav-link.active[ng-reflect-router-link='/home/dashboard']")).click();
        Thread.sleep(2022);
        WebElement row = Browser.driver().findElement(By.xpath("/html[1]//table[1]/tbody[1]/tr[4]"));
        //int i = tableWe.searchRowIndex(tableWe.getElement().findElements(By.tagName("tr")).get(4));
        int i = tableWe.searchRowIndex(row);
        System.out.println(i);

    }


    @AfterClass
    public static void afterClass() {
        Browser.close();
    }
}
