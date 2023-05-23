package tests;

import infra.data.DataProcessor;
import infra.enums.MenuMain;
import infra.enums.Users;
import infra.test.TestBase;
import infra.ui.BasePage;
import infra.ui.UiElement;
import objects.tests.TestTherapistData;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import pages.LoginPage;
import pages.newTherapist.ContactInformationPage;
import pages.newTherapist.UserInformationPage;

import java.util.Collection;

public class TestTherapist extends TestBase {

    TestTherapistData testData;


    public TestTherapist(String testCase, TestTherapistData testData) {
        super(testCase, testData);
        this.testData = testData;
    }

    @Parameterized.Parameters(name = "{index}::{0}")
    public static Collection data() {
        return DataProcessor.createTestData(TestTherapistData.class);
    }

    @Override
    public void test() {
        /*new UserInformation().validate(testData.UserInformationPageData_OldData)
                .fillPage(testData.UserInformationPageData);*/
        new LoginPage().fillPage(Users.manager).finish();

        final UiElement addTherapist = new UiElement("הוספת מטפל(כפתור +)", By.cssSelector("svg[class*=plus]>path"));
        //therapists.click();
        BasePage.chooseMenu(MenuMain.terapists);

        addTherapist.click();
        new UserInformationPage().fillPage(testData.getUserInformationPageData()).finish();
        //new ProfessionalDetailsPage().fillPage(testData.getProfessionalDetailsPageData());
        //new DaysAndHoursOfOperationPage().fillPage(testData.getDaysAndHoursOfOperationPageData());
        new ContactInformationPage().fillPage(testData.getContactInformationPageData()).finish();
    }
}
