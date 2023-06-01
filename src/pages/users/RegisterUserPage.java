package pages.users;

import infra.data.DWH;
import infra.data.DataKeys;
import infra.ui.BasePage;
import infra.ui.Browser;
import infra.ui.UiElement;
import objects.pages.RegisterUserPageData;
import org.openqa.selenium.By;

public class RegisterUserPage extends BasePage {

    UiElement rootRegisterUser = new UiElement("מייחד דף רישום משתמש", By.cssSelector("div[class*='jumbotron']"));
    UiElement emailInput = new UiElement("אימייל", By.cssSelector("input[name=email]")).root(rootRegisterUser);
    UiElement fNameInput = new UiElement("שם פרטי", By.cssSelector("input[name=fName]")).root(rootRegisterUser);
    UiElement pwdInput = new UiElement("סיסמה", By.cssSelector("input[name=pwd]")).root(rootRegisterUser);
    UiElement lrSelect = new UiElement("מנהל/מלווה", By.cssSelector("select[name=lr]")).root(rootRegisterUser);
    UiElement activeInput = new UiElement("משתמש פעיל?", By.cssSelector("input[name=active]")).root(rootRegisterUser);
    UiElement btnGroup = new UiElement("קבוצת כפתורים", By.cssSelector("div[class*='form-group'] div[class*='btn-group']")).root(rootRegisterUser);
    UiElement saveButton = new UiElement("כפתור שמור", By.cssSelector("button[class*=btn-primary]")).root(btnGroup);
    UiElement cancelButton = new UiElement("כפתור ביטול", By.cssSelector("button[class*='danger']")).root(btnGroup);
    UiElement existUser = new UiElement("ניווט למשתמש קיים", By.cssSelector("a[role='button'][href='#/account/login']")).root(rootRegisterUser);


    public RegisterUserPage fillPage(RegisterUserPageData data) {
        emailInput.input(data.getEmailInput());
        fNameInput.input(data.getFNameInput());
        pwdInput.input(data.getPwdInput());
        lrSelect.input(data.getLrSelect());
        activeInput.checkBox(data.getActiveInput());
        DWH.saveData(DataKeys.NewUser, data);
        return this;
    }

    public RegisterUserPage validate(/*RegisterUserPageData data*/) {
        RegisterUserPageData data = DWH.getNewUser();
        emailInput.validateText(data.getEmailInput());
        fNameInput.validateText(data.getFNameInput());
        pwdInput.validateText(data.getPwdInput());
        lrSelect.validateText(data.getLrSelect());
        /*activeInput.validateText(data.getActiveInput());*/
        return this;
    }

    //The func click on SaveButton
    public void SaveButtonClick() {
        saveButton.click();
    }

    //clicking on the checkBox to choose an active user
    public void clickActive() {
        activeInput.checkBox(true);
    }

    //checking if the checkBox is chosen
    public boolean IsActive() {
        return activeInput.getElement().isSelected();
    }

    @Override
    public boolean assertDisplay() {
        return Browser.driver().getTitle().equals("מושיע")
                && new UiElement("כותרת", By.cssSelector("h4")).validateText(" רישום משתמש חדש")
                && Browser.driver().getCurrentUrl().equals("https://tamrur.rabai.co.il/#/account/register/0");
    }
}
