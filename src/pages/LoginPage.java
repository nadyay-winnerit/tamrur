package pages;

public class LoginPage {
    WebDriver driver;

    //Locator for userName input
    private By userNameInput = By.cssSelector("input[name=userName]");

    //Locator for password input
    private By passwordInput = By.cssSelector("input[name=password]");

    //Locator for connection button
    private By connectionButton = By.tagName("button");

    //Locator for remember check box
    private By rememberCheckBox = By.cssSelector("input[name=remember]");

    //Locator for forgotPassword link
    private By forgotPasswordLink = By.linkTest("שכחתי סיסמא");

    //Locator for newUser link
    private By newUserLink = By.linkTest("משתמש חדש?");


    //constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * The func gets password string and enter it in password field
     *
     * @param str - Password string to enter
     */
    public void PassWordSendKeys(String str) {
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
    public void ConnectionButtonClick() {
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

