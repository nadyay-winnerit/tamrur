package pages;

import infra.data.*;
import objects.pages.RegisterUserPageData;
import org.openqa.selenium.*;

public class RegisterUserPage {
    WebDriver driver;
    private By emailInput = By.cssSelector("input[name=email]");
    private By fNameInput = By.cssSelector("input[name=fName]");
    private By pwdInput = By.cssSelector("input[name=pwd]");
    private By lrSelect = By.cssSelector("select[name=lr]");

    private By activeInput = By.cssSelector("input[name=active]");
    private By saveButton = By.cssSelector("button[class*=btn-primary");


    //constructor
    public RegisterUserPage() {
        this.driver = driver;
    }

    public RegisterUserPage fillPage(RegisterUserPageData data) {

        driver.findElement(emailInput).sendKeys(data.getEmailInput());
        driver.findElement(fNameInput).sendKeys(data.getFNameInput());
        DWH.saveData(DataKeys.NewUser, data);
        return this;
    }

    public RegisterUserPage validate(/*RegisterUserPageData data*/) {
        RegisterUserPageData data = DWH.getNewUser();
        driver.findElement(emailInput).getText().equals(data.getEmailInput());
        driver.findElement(fNameInput).sendKeys(data.getFNameInput());
        return this;
    }
}
