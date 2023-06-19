package pages.newTherapist;

import data.pages.newTherapist.ContactInformationPageData;
import infra.ui.BasePage;
import infra.ui.UiElement;
import org.openqa.selenium.By;
import struct.AddressStruct;


public class ContactInformationPage extends BasePage {
    /*private final UiElement address = new UiElement("כתובת", By.cssSelector("input[name='address']"));
    private final UiElement cityMenu = new UiElement("עיר", By.cssSelector("select[name='city']"));
    private final UiElement country = new UiElement("ארץ", By.cssSelector("[name='land']"));
    private final UiElement emailAddress = new UiElement("כתובת דואר אלקטרוני", By.cssSelector("[name='email']"));
    private final UiElement agreeReceiveSMS = new UiElement("מסכים לקבל SMS", By.cssSelector("[name='agreeSMS']"));
    private final UiElement agreeReceiveEmail = new UiElement("מסכים לקבל אימייל", By.cssSelector("[name='agree']"));

*/
    private final AddressStruct address = new AddressStruct();
    private final UiElement phoneNumber = new UiElement("מספר טלפון", By.cssSelector("[ng-reflect-name='p0']"));
    private final UiElement typePhone = new UiElement("סוג טלפון", By.cssSelector("[ng-reflect-name='typePhone0']"));

    protected void init() {
        indexOfNav = 3;
    }

    public ContactInformationPage fillPage(ContactInformationPageData data) {
        address.fill(data.getAddress());
        phoneNumber.input(data.getPhoneNumber());
        typePhone.setIndex(data.getTypePhone().getValue()).chooseRadio();
        return this;
    }

    @Override
    public boolean assertDisplay() {
        return new UiElement("כותרת", By.xpath("//h3[text()='פרטי התקשרות']")).isExists();
    }
}
