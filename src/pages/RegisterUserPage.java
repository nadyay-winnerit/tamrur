package pages;

import objects.pages.RegisterUserPageData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class RegisterUserPage {
    WebDriver driver;
    private By emailInput = By.cssSelector("input[name=email]");
    private By fNameInput = By.cssSelector("input[name=fName]");
    private By pwdInput = By.cssSelector("input[name=pwd]");
    private By lrSelect = By.cssSelector("select[name=lr]");

    private By activeInput = By.cssSelector("input[name=active]");
    private By saveButton = By.cssSelector("button[class*=btn-primary");


    //constructor
    public RegisterUserPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillPage(RegisterUserPageData data){
        driver.findElement(emailInput).sendKeys(data.getEmailInput());
        driver.findElement(fNameInput).sendKeys(data.getFNameInput());
    }

    /**
     * The func gets email string and enter it in email field
     *
     * @param str - email string to enter
     */
    public void emailSendKeys(String str) {
        driver.findElement(emailInput).sendKeys(str);
    }

    /**
     * The func gets string fName and enter it in fName field
     *
     * @param str - fName string to enter
     */
    public void fNameSendKeys(String str) {
        driver.findElement(fNameInput).sendKeys(str);
    }

    /**
     * The func gets string pwd and enter it in pwd field
     *
     * @param str - pwd string to enter
     */
    public void pwdSendKeys(String str) {
        driver.findElement(pwdInput).sendKeys(str);
    }


    /**
     * The func gets string body and enter it in body field
     *
     * @param str - body string to enter
     */
    public void lrSelectByValue(String str) {
        Select lr =new Select(driver.findElement(lrSelect));
        lr.selectByValue(str);
    }

    public void checkActive() {
        driver.findElement(activeInput).click();
    }

    public boolean IsActive() {
         return driver.findElement(activeInput).isSelected();
    }

    /**
     * The func click on SaveButton
     */
    public void SaveButtonClick() {
        driver.findElement(saveButton).click();
    }

}
