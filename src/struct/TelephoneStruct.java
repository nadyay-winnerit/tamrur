package struct;

import infra.ui.UiElement;
import objects.struct.TelephoneStructData;
import org.openqa.selenium.By;

public class TelephoneStruct {

    private UiElement phoneNumber = new UiElement("מספר טלפון", By.cssSelector("[placeholder='הכנס מספר טלפון']"));
    private UiElement landlinePhone = new UiElement("נייח", By.xpath("//div[.=' נייח ']/input"));
    private UiElement mobilePhone = new UiElement("נייד", By.xpath("//div[.=' נייד ']/input"));
    private UiElement fax = new UiElement("פקס", By.xpath("//div[.=' פקס ']/input"));

    public void fill(TelephoneStructData data) {
        phoneNumber.input(data.getPhoneNumber());
        choosePhoneType(data.getPhoneType());
    }

    private void choosePhoneType(String phoneType) {
        if (phoneType.equals("נייח")) {
            landlinePhone.chooseRadio();
        } else if (phoneType.equals("נייד")) {
            mobilePhone.chooseRadio();
        } else if (phoneType.equals("פקס")) {
            fax.chooseRadio();
        }
    }

}
