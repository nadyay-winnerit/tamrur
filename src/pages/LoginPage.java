package pages;

import infra.enums.Users;
import infra.ui.BasePage;
import infra.ui.UiElement;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    UiElement userNameUi = new UiElement("שם משתמש", By.cssSelector("input[name=userName]"));
    UiElement passWordUi = new UiElement("ססמא", By.cssSelector("input[name='password']"));
    UiElement rememberCheckBoxUi = new UiElement("זכור אותי", By.cssSelector("input[name=remember]"));
    UiElement connectionButtonUi = new UiElement("התחבר", By.tagName("button"));
    UiElement forgotPasswordLinkUi = new UiElement("שכחתי ססמא", By.linkText("שכחתי סיסמא"));
    UiElement newUserLinkUi = new UiElement("משתמש חדש?", By.linkText("משתמש חדש?"));

    @Override
    public boolean assertDisplay() {
        return false;
    }

    /**
     * The func gets users obj and fill the login page with it
     *
     * @param users
     */
    public LoginPage fillPage(Users users) {
        userNameUi.input(users.getEmail());
        passWordUi.input(users.getPassword());
        /*if (users.getRememberCheckBoxUi())
            rememberCheckBoxUi.click();*/
        connectionButtonClick();
        return this;
    }


    /**
     * The func click on connectionButton
     */
    public void connectionButtonClick() {
        connectionButtonUi.click();
    }

    /**
     * The func click on remember check Box
     */
    public void rememberClick() {
        rememberCheckBoxUi.click();
    }

    /**
     * The func click on forgotPassword link
     */
    public void forgotPasswordLinkClick() {
        forgotPasswordLinkUi.click();
    }

    /**
     * The func click on newUser link
     */
    public void newUserLinkClick() {
        newUserLinkUi.click();
    }


}

