package tests;

import infra.data.DataProcessor;
import infra.enums.MenuMain;
import infra.enums.Users;
import infra.general.Utils;
import infra.test.TestBase;
import infra.ui.BasePage;
import infra.ui.components.TableCell;
import infra.ui.components.TableWe;
import objects.tests.TestTherapistData;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import pages.LoginPage;
import pages.NavbarPage;
import pages.newTherapist.ContactInformationPage;
import pages.newTherapist.DaysAndHoursOfOperationPage;
import pages.newTherapist.ProfessionalDetailsPage;
import pages.newTherapist.UserInformationPage;

import java.util.Collection;
import java.util.List;

public class TestTherapist extends TestBase {

    TestTherapistData testData;
    List<TableCell> therapistListValues;


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
        int indexTable = 1;
        /*new UserInformation().validate(testData.UserInformationPageData_OldData)
                .fillPage(testData.UserInformationPageData);*/
        new LoginPage().fillPage(Users.manager).finish();
        BasePage.chooseMenu(MenuMain.terapists);
        Utils.sleep(3);
        NavbarPage.plusBtnClick();
        new UserInformationPage().fillPage(testData.getUserInformationPageData()).finish();
        new ProfessionalDetailsPage().fillPage(testData.getProfessionalDetailsPageData()).finish();
        new ContactInformationPage().fillPage(testData.getContactInformationPageData()).finish();
        if (testData.getUserInformationPageData().getExternal()) {
            new DaysAndHoursOfOperationPage().fillPage(testData.getDaysAndHoursOfOperationPageData()).finish();
            indexTable = 2;
        }
        Utils.sleep(1);
        NavbarPage.saveClick();
        BasePage.chooseMenu(MenuMain.terapists);
        createTherapistListValues();
        new TableWe("טבלה", By.cssSelector("table")).setIndex(indexTable).validate(therapistListValues, 1);
        Utils.sleep(1);
    }

    private void createTherapistListValues() {
        therapistListValues.add(new TableCell("שם", testData.getUserInformationPageData().getFirstName().concat(" " + testData.getUserInformationPageData().getLastName()), false));
        therapistListValues.add(new TableCell("טלפון", testData.getContactInformationPageData().getPhoneNumber(), false));
        therapistListValues.add(new TableCell("אימייל", testData.getContactInformationPageData().getEmailAddress(), false));
        therapistListValues.add(new TableCell("מגדר", testData.getUserInformationPageData().getGender().name(), false));
    }
}
