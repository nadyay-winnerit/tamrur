package pages.medicalFile;

import infra.reporter.Reporter;
import infra.ui.BasePage;
import infra.ui.UiElement;
import objects.pages.LoginPageData;
import objects.pages.medicalFile.PatientDetailsPageData;
import org.openqa.selenium.By;

public class PatientDetailsPage extends BasePage {

    private UiElement firstName = new UiElement("שם פרטי", By.cssSelector("[name='fn']"));
    private UiElement lastName = new UiElement("שם משפחה", By.cssSelector("[name='ln']"));
    private UiElement numTz = new UiElement("תעודת זהות", By.cssSelector("[name='num']"));
    private UiElement lineBusiness = new UiElement("תחום עיסוק", By.cssSelector("[name='lb']"));
    private UiElement maritalStatus = new UiElement("מצב משפחתי", By.cssSelector("[name='c3t']"));
    private UiElement phoneNumber = new UiElement("מספר טלפון", By.cssSelector("[placeholder='הכנס מספר טלפון']"));
    private UiElement landlinePhone  = new UiElement("נייח", By.xpath("//div[.=' נייח ']/input"));
    private UiElement mobilePhone = new UiElement("נייד", By.xpath("//div[.=' נייד ']/input"));
    private UiElement fax = new UiElement("פקס", By.xpath("//div[.=' פקס ']/input"));
    private UiElement title = new UiElement("כותרת", By.xpath("//h3[text()=\"פרטי מטופל\"]"));



    @Override
    public boolean assertDisplay() {
        return false;
    }

    /**
     * The func gets data obj and fill the login page with it
     *
     * @param data
     */
    public PatientDetailsPage fillPage(PatientDetailsPageData data) {
        firstName.input(data.getFirstName());
        lastName.input(data.getLastName());
        numTz.input(data.getNumTz());
        lineBusiness.input(data.getLineBusiness());
        maritalStatus.select(data.getMaritalStatus());
        phoneNumber.input(data.getPhoneNumber());
        choosePhoneType(data.getPhoneType());
        return this;

    }

    public void choosePhoneType(String phoneType) {
        if (phoneType.equals("נייח")) {
            landlinePhone.chooseRadio();
        } else if (phoneType.equals("נייד")) {
            mobilePhone.chooseRadio();
        } else if (phoneType.equals("פקס")) {
            fax.chooseRadio();
        }
    }



    public PatientDetailsPage validatePage(PatientDetailsPageData data) {
        firstName.validateText(data.getFirstName());
        lastName.validateText(data.getLastName());
        numTz.validateText(data.getNumTz());
        lineBusiness.validateText(data.getLineBusiness());
        maritalStatus.validateText(data.getMaritalStatus());
        phoneNumber.validateText(data.getPhoneNumber());
        validateChoosePhoneType(data.getPhoneType());
        return this;
    }

    private void validateChoosePhoneType(String phoneType) {
        if (phoneType.equals("נייח")) {
            landlinePhone.validateChooseRadio();
        } else if (phoneType.equals("נייד")) {
            mobilePhone.validateChooseRadio();
        } else if (phoneType.equals("פקס")) {
            fax.validateChooseRadio();
        }
    }

    public PatientDetailsPage assertPage() {
        if (title.isExists()) {
            Reporter.reporter().message("Assert PatientDetailsPage Succeeded","");
        }
        Reporter.reporter().error("Assert PatientDetailsPage failed","");
        return this;
    }

}
