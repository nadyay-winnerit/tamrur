package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ContactsPage {

    WebDriver driver;

    //constructor
    public ContactsPage(WebDriver driver) {
        this.driver = driver;
    }

    //Locators

    //button to enter the contacts page
    private By enterContactsPageBtn= By.xpath("//*[@id=\"sidebarMenu\"]/div/ul/li[5]/a");

    //the plus button in every row, to add a new contact
    private By plusBtnNewContact= By.xpath("/html/body/app-root/app-default/div[1]/div[2]/app-case-contact-persons/div[2]/table/tbody/tr[1]/td[8]/div/button");

    //field of first name, by xpath, may not be unique otherwise
    private By fNameField= By.xpath("/html/body/app-root/app-default/div[1]/div[2]/app-case/form/div/div[2]/app-case-contact-persons/div[2]/ul/li[1]/form/div[1]/div[2]/input[@name='fname']");

    //field of last name
    private By lNameField= By.xpath("/html/body/app-root/app-default/div[1]/div[2]/app-case/form/div/div[2]/app-case-contact-persons/div[2]/ul/li[1]/form/div[2]/div[2]/input[@name='lname']");

    //field of phone number
    private By phoneField= By.xpath("/html/body/app-root/app-default/div[1]/div[2]/app-case/form/div/div[2]/app-case-contact-persons/div[2]/ul/li[1]/form/div[3]/div[2]/input[@name='phone']");

    //field of mail
    private By mailField= By.xpath("/html/body/app-root/app-default/div[1]/div[2]/app-case/form/div/div[2]/app-case-contact-persons/div[2]/ul/li[1]/form/div[4]/div[2]/input[@name='em']");

    //field of the contact's role
    private By roleField= By.xpath("/html/body/app-root/app-default/div[1]/div[2]/app-case/form/div/div[2]/app-case-contact-persons/div[2]/ul/li[1]/form/div[5]/div[2]/input[@name='rol']");

    //button to create the new contact
    private By createContactBtn= By.xpath("/html/body/app-root/app-default/div[1]/div[2]/app-case/form/div/div[2]/app-case-contact-persons/div[2]/ul/li[1]/form/div[6]/div[2]/button");

    //the bar archive that selected when the contacts' page is displayed
    private By selectedActiveBar= By.cssSelector("div.card-header a.active");

    //the contacts' bar
    private By contactsBar= By.xpath("/html/body/app-root/app-default/div[1]/div[2]/app-case/form/div/div[1]/section/ul/li[3]/a");

    //Methods

    //enter the contact page
    public void enterContactPage(){
        driver.findElement(enterContactsPageBtn).click();
    }

    //clicking the plus button in the first row to enter the creating section
    public void enterPlusBtnCreating(){
        driver.findElement(plusBtnNewContact).click();
    }

    //checking if the contacts' bar is selected and bolded
    public void checkActiveBar(){
        String activeBarText= driver.findElement(selectedActiveBar).getText();
        String contactBarText= driver.findElement(contactsBar).getText();

        /*if (activeBarText == contactBarText)
            return true;
        else return false;*/

        try {
            Assert.assertEquals(contactBarText, activeBarText);
            System.out.println("the right page is displayed, and the contacts' bar is selected and bolded");
        } catch (AssertionError e){
            System.out.println(e.getMessage());
        }
    }

    //filling the contact's details in the fields respectively
    public void fillingFieldsContact(String value){
        switch (value) {
            case "first name":
                driver.findElement(fNameField).sendKeys("מרדכי");
                break;
            case "last name":
                driver.findElement(lNameField).sendKeys("כהן");
                break;
            case "phone number":
                driver.findElement(phoneField).sendKeys("0548888888");
                break;
            case "mail":
                driver.findElement(mailField).sendKeys("as@ggg.com");
                break;
            case "contact's role":
                driver.findElement(roleField).sendKeys("חבר");
                break;
            case "create":
                driver.findElement(createContactBtn).click();
                break;
        }
    }


    //console-creating xpaths and cssSelectros
    /**
     * $x("//a[contains(@class,'active')][.='פרטי פניה']") פונקציה ליצירת איקס פאת, היא עם דולר ואיקס בהתלחה
     * [a.nav-link.pointer.active]
     * $x("//div[@class='card-header']//a[contains(@class,'active')]")
     * [a.nav-link.pointer.active]
     * פונקציה ליצירה סיאסאס היא עם שני $ בהתחלה
     * $$(" div.card-header a.active[class*='nav']")  כדי להצביע על חלק מהתוכן באטריביוט, כותבים בתוך סוגריים מרובעות כוכבית שווה ובתוך גרש אחד את חלק מהטקסט
     * [a.nav-link.pointer.active]
     * $$(" div.card-header a.active")  אם עוברים לאטריביוט שהוא לא בן ישיר, מפרידים עם רווח. אם זה בן ישיר, מפרידים ביניהם בחץ
     * [a.nav-link.pointer.active]
     * $$("div.card-header a.active") כדי להצביע על קלאס, כותבים את האלמנט ולאחריו נקודה, ואם יש עוד קלאסים, אז מפרידים ביניהם בנקודה
     * [a.nav-link.pointer.active]
     * $$(" div.card-header a.active")
     * [a.nav-link.pointer.active]
     */



}
