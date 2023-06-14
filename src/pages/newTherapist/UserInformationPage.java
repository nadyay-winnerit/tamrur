package pages.newTherapist;

import data.pages.newTherapist.UserInformationPageData;
import infra.ui.BasePage;
import infra.ui.UiElement;
import org.openqa.selenium.By;

public class UserInformationPage extends BasePage {

    private final UiElement firstName = new UiElement("שם פרטי", By.cssSelector("input[name='f1Name']"));
    private final UiElement lastName = new UiElement("שם משפחה", By.cssSelector("input[name='l1Name']"));
    private final UiElement gender = new UiElement("מגדר", By.cssSelector("[name='gender']"));
    private final UiElement loginPassword = new UiElement("סיסמת כניסה למערכת", By.cssSelector("input[name='p1assword']"));
    private final UiElement isActive = new UiElement("סטטוס מטפל: פעיל", By.cssSelector("input[name='isActive']"));
    private final UiElement isExternal = new UiElement("סטטוס מטפל: חיצוני", By.cssSelector("input[name='isExternal']"));


    protected void init() {
        indexOfNav = 1;
    }

    public UserInformationPage fillPage(UserInformationPageData data) {
        firstName.input(data.getFirstName());
        lastName.input(data.getLastName());
        loginPassword.input(data.getLoginPassword());
        gender.setIndex(data.getGender().getValue()).chooseRadio();
        isActive.checkBox(data.getActive());
        isExternal.checkBox(data.getExternal());
        return this;

    }


    @Override
    public boolean assertDisplay() {
        return new UiElement("כותרת", By.xpath("//h3[text()='פרטי משתמש']")).isExists();

    }
}

