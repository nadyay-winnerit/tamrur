package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;

    //Locator for userName input
    private final By userNameInput = By.cssSelector("input[name=userName]");

    //Locator for password input
    private final By passwordInput = By.cssSelector("input[name=password]");

    //Locator for connection button
    private final By connectionButton = By.tagName("button");

    //Locator for remember check box
    private final By rememberCheckBox = By.cssSelector("input[name=remember]");

    //Locator for forgotPassword link
    private final By forgotPasswordLink = By.linkText("שכחתי סיסמא");

    //Locator for newUser link
    private final By newUserLink = By.linkText("משתמש חדש?");


    //constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * The func gets password string and enter it in password field
     *
     * @param str - Password string to enter
     */
    public void passWordSendKeys(String str) {
        driver.findElement(passwordInput).sendKeys(str);
    }

    /**
     * The func gets string userName and enter it in password field
     *
     * @param str - userName string to enter
     */
    public void userNameSendKeys(String str) {
        driver.findElement(userNameInput).sendKeys(str);
    }

    /**
     * The func click on connectionButton
     */
    public void connectionButtonClick() {
        driver.findElement(connectionButton).click();
    }

    /**
     * The func click on remember check Box
     */
    public void rememberClick() {
        driver.findElement(rememberCheckBox).click();
    }

    /**
     * The func click on forgotPassword link
     */
    public void forgotPasswordLinkClick() {
        driver.findElement(forgotPasswordLink).click();
    }

    /**
     * The func click on forgotPassword link
     */
    public void newUserLinkClick() {
        driver.findElement(newUserLink).click();
    }


}

