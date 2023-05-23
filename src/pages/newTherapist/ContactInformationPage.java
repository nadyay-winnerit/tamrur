package pages.newTherapist;

import infra.ui.BasePage;
import infra.ui.UiElement;
import objects.pages.newTherapist.ContactInformationPageData;
import org.openqa.selenium.By;

public class ContactInformationPage extends BasePage {
    UiElement address = new UiElement("כתובת", By.cssSelector("input[name='address']"));
    UiElement cityMenu = new UiElement("עיר", By.cssSelector("select[name='city']"));
    UiElement country = new UiElement("ארץ", By.cssSelector("[name='land']"));
    UiElement emailAddress = new UiElement("כתובת דואר אלקטרוני", By.cssSelector("[name='email']"));
    UiElement agreeReceiveSMS = new UiElement("מסכים לקבל SMS", By.cssSelector("[name='agreeSMS']"));
    UiElement agreeReceiveEmail = new UiElement("מסכים לקבל אימייל", By.cssSelector("[name='agree']"));
    UiElement phoneNumber = new UiElement("מספר טלפון", By.cssSelector("[ng-reflect-name='p0']"));
    UiElement typePhone = new UiElement("סוג טלפון", By.cssSelector("[ng-reflect-name='typePhone0']"));

    protected void init() {
        indexOfNav = 3;
    }

    public ContactInformationPage fillPage(ContactInformationPageData data) {
        address.input(data.getAddress());
        //cityMenu.input(data.getCityMenu());
        country.input(data.getCountry());
        emailAddress.input(data.getEmailAddress());

        phoneNumber.input(data.getPhoneNumber());
        return this;

    }

    @Override
    public boolean assertDisplay() {
        return false;
    }
}
