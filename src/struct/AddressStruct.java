package struct;

import data.struct.AddressStructData;
import infra.ui.UiElement;
import org.openqa.selenium.By;

public class AddressStruct {
    private final UiElement address = new UiElement("כתובת", By.cssSelector("input[name='address']"));
    private final UiElement cityMenu = new UiElement("עיר", By.cssSelector("select[name='city']"));
    private final UiElement country = new UiElement("ארץ", By.cssSelector("[name='land']"));
    private final UiElement emailAddress = new UiElement("כתובת דואר אלקטרוני", By.cssSelector("[name='email']"));
    private final UiElement agreeReceiveSMS = new UiElement("מסכים לקבל SMS", By.cssSelector("[name='agreeSMS']"));
    private final UiElement agreeReceiveEmail = new UiElement("מסכים לקבל אימייל", By.cssSelector("[name='agree']"));

    public void fill(AddressStructData data) {
        address.input(data.getAddress());
        cityMenu.select(data.getCityMenu());
        country.input(data.getCountry());
        emailAddress.input(data.getEmailAddress());
        agreeReceiveSMS.checkBox(data.getAgreeReceiveSMS());
        agreeReceiveEmail.checkBox(data.getAgreeReceiveEmail());
    }

}
