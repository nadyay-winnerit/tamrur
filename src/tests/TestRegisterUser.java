package tests;

import infra.data.DataProcessor;
import infra.enums.MenuMain;
import infra.test.TestBase;
import infra.ui.BasePage;
import infra.ui.UiElement;
import objects.tests.TestRegisterUserData;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import pages.LoginPage;
import pages.users.RegisterUserPage;

import java.util.Collection;

public class TestRegisterUser extends TestBase {

    TestRegisterUserData testData;
    LoginPage loginPage;

    public TestRegisterUser(String testCase, TestRegisterUserData testData) {
        super(testCase, testData);
        this.testData = testData;
    }


    @Parameterized.Parameters(name = "{index}::{0}")
    public static Collection data() {
        return DataProcessor.createTestData(TestRegisterUserData.class);
    }

    @Override
    public void test() {
        new LoginPage().fillPage(testData.getLoginUser()).finish();  //לוגאין לחמערכת עם משתמש מסוג מלווה
        BasePage.chooseMenu(MenuMain.users);
        final UiElement addUserBtn = new UiElement("כפתור יצירת משתמש", By.cssSelector("button[title= 'הוספת משתמש חדש']"));
        addUserBtn.validateExists();

        //איך מטפלים במקרה שהכפתור לא נמצא? שהמערכת לא תיפול

        addUserBtn.click();
        new RegisterUserPage().fillPage(testData.getRegisterUserPageData()).finish();
    }
}
