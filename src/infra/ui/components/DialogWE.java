package infra.ui.components;

import infra.ui.UiElement;
import org.openqa.selenium.By;

public class DialogWE extends UiElement {


    public DialogWE(String desc, By by) {
        super(desc, by);
    }

    public DialogWE validateDialog(String titleText, String divText) {
        new UiElement(titleText, By.cssSelector("#swal2-title")).root(this).validateText(titleText);
        new UiElement(divText, By.cssSelector("#swal2-html-container")).root(this).validateText(divText);
        return this;
    }

    public DialogWE validateOK() {
        new UiElement("OK(V green)", By.cssSelector(".swal2-success")).root(this).validateExists();
        return this;
    }

    public void clickOk() {
        new UiElement("OK", By.cssSelector("button.swal2-confirm")).root(this).click();
    }

}
