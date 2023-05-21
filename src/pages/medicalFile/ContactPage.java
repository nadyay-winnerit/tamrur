package pages.medicalFile;

import infra.reporter.Reporter;
import infra.ui.BasePage;
import infra.ui.UiElement;
import objects.pages.medicalFile.ContactPageData;
import objects.pages.medicalFile.PatientDetailsPageData;
import org.openqa.selenium.By;

public class ContactPage extends BasePage {

    private UiElement title = new UiElement("כותרת", By.xpath("//h3[text()=\"אנשי קשר\"]"));
    private UiElement firstName = new UiElement("שם פרטי", By.cssSelector("[name='fname']"));
    private UiElement lastName = new UiElement("שם משפחה", By.cssSelector("[name='lname']"));
    private UiElement phone = new UiElement("שם פרטי", By.cssSelector("[name='phone']"));
    private UiElement email = new UiElement("מייל", By.cssSelector("[name='em']"));
    private UiElement role = new UiElement("תפקיד", By.cssSelector("[name='rol']"));


    public ContactPage fillPage(ContactPageData data){
        firstName.input(data.getFirstName());
        lastName.input(data.getLastName());
        phone.input(data.getPhone());
        email.input(data.getEmail());
        role.input(data.getRole());
        return this;
    }

    public ContactPage validatePage(ContactPageData data) {
        firstName.validateText(data.getFirstName());
        lastName.validateText(data.getLastName());
        phone.validateText(data.getPhone());
        email.validateText(data.getEmail());
        role.validateText(data.getRole());
        return this;
    }

    @Override
    public boolean assertDisplay() {
        return false;
    }

    public ContactPage assertPage() {
        if (title.isExists()) {
            Reporter.reporter().message("assert ContactPage Succeeded","");
        }
        Reporter.reporter().error("assert ContactPage failed","");
        return this;
    }
}
