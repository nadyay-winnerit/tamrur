package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactUsPage {

    WebDriver driver;

    //Locator for subject input
    private By subjectInput = By.cssSelector("input[name=subject]");

    //Locator for name input
    private By cNameInput = By.cssSelector("input[name=cName]");

    //Locator for email address input
    private By emailInput = By.cssSelector("input[name=from]");

    //Locator for body input
    private By bodyInput = By.cssSelector("textarea[name=body]");

    //Locator for Send button
    private By SendButton = By.xpath("/html/body/app-root/app-default/div[1]/div[2]/app-contact-us/div/div[2]/div/form/div/div[2]/div[5]/button");



    //constructor
    public ContactUsPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * The func gets subject string and enter it in subject field
     *
     * @param str - subject string to enter
     */
    public void subjectSendKeys(String str) {
        driver.findElement(subjectInput).sendKeys(str);
    }

    /**
     * The func gets string name and enter it in name field
     *
     * @param str - name string to enter
     */
    public void nameSendKeys(String str) {
        driver.findElement(cNameInput).sendKeys(str);
    }

    /**
     * The func gets string email and enter it in email field
     *
     * @param str - name string to enter
     */
    public void emailSendKeys(String str) {
        driver.findElement(emailInput).sendKeys(str);
    }

    /**
     * The func gets string body and enter it in body field
     *
     * @param str - body string to enter
     */
    public void bodySendKeys(String str) {
        driver.findElement(bodyInput).sendKeys(str);
    }


    /**
     * The func click on SendButton
     */
    public void SendButtonClick() {
        driver.findElement(SendButton).click();
    }

}
