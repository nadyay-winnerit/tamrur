package pages.users;

import infra.ui.Browser;
import infra.ui.UiElement;
import org.openqa.selenium.By;

public class EditUserPage extends RegisterUserPage {

    @Override
    public boolean assertDisplay() {
        return new UiElement("כותרת", By.cssSelector("h4")).validateText("עריכת משתמש")
                && Browser.driver().getCurrentUrl().equals("https://tamrur.rabai.co.il/#/account/register/2021");
    }
}
