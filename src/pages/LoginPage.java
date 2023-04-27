package pages;

import infra.UiElement;
import objects.pages.LoginPageData;
import org.openqa.selenium.By;

public class LoginPage {


    UiElement userNameUi = new UiElement("שם משתמש", By.cssSelector("input[name=userName]"));
    UiElement passWordUi = new UiElement("ססמא", By.cssSelector("input[name='password']"));
    UiElement rememberCheckBoxUi = new UiElement("זכור אותי", By.cssSelector("input[name=remember]"));
    UiElement connectionButtonUi = new UiElement("התחבר", By.tagName("button"));
    UiElement forgotPasswordLinkUi = new UiElement("שכחתי ססמא", By.linkText("שכחתי סיסמא"));
    UiElement newUserLinkUi = new UiElement("משתמש חדש?", By.linkText("משתמש חדש?"));

    /**
     * The func gets data obj and fill the login page with it
     *
     * @param data
     */
    public void fillPage(LoginPageData data) {
        userNameUi.input(data.getUserNameUi());
        passWordUi.input(data.getPassWordUi());
        if (data.getRememberCheckBoxUi())
            rememberCheckBoxUi.click();

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

