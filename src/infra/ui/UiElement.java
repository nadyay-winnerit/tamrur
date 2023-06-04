package infra.ui;

import infra.general.Utils;
import infra.reporter.Reporter;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class UiElement {

    private final By by;
    private final String desc;
    protected WebElement element;
    private int index = 0;

    //אתחול הרוט מסוג UiElement הוא בעצם יכיל אלמנט שכבר אותר, וממנו יהיה אפשר להמשיך למצוא עוד אלמנטים אחריו
    private UiElement root = null;

    public static Reporter reporter = Reporter.reporter();

    //constructor

    @Deprecated
    public UiElement(String desc, By by) {
        this.desc = desc;
        this.by = by;
    }

    //getters & setters

    public UiElement setIndex(int value) {
        this.index = value - 1;
        return this;
    }

    public int getIndex() {
        return index;
    }

    public UiElement root(UiElement root) {
        this.root = root;
        return this;
    }

    public WebElement getElement() {
        return element;
    }


    //methods

    public void click() {
//        reporter.hasScreenshot().message("Click on the element [" + this.desc + "]", by.toString());
        findElement();
        element.click();
        //Utils.sleepMS(500);
//        reporter.takeScreenshot();
    }

    /* הפונקציה מקבלת משתנה דגל, המורה לה לבחור באפשרות של הcheckBox או להסיר את הבחירה ממנו
     */
    public void checkBox(Boolean bool) {
        if (bool == null) {
            return;
        }

        findElement();  //מציב בשרת את האלמנט שנמצא במיפוי

        if (bool) {  //אם הדגל אמת, בצע doChooseOption שזה בוחר באפשרות של הcheckBox , כמובן אם היא לא נבחרה כבר
            doChooseOption();

        } else {   //אם הדגל שקר, בצע הסרה של הבחירה מהcheckBox
            if (!element.isSelected())
                return;

            Reporter.reporter().message("Unchecking the element [" + this.desc + "]", null);
            element.click();
            Utils.sleep(1);
        }
    }

    public void chooseRadio() {
        findElement();
        doChooseOption();
    }

    private void doChooseOption() {
        if (element.isSelected())
            return;

        Reporter.reporter().message("Check on the element [" + this.desc + "]", null);
        element.click();
        Utils.sleep(1);
    }

    public String read() {
        String str = element.getText();

        if (str == null || str.isEmpty()) {
            str = element.getAttribute("value");
        }

        if (str == null || str.isEmpty()) {
            str = element.getAttribute("innerHTML");
        }

        return str;
    }


    public void input(String str) {
        if (str == null) {
            return;
        }

//        reporter.hasScreenshot().message("Input to element [" + this.desc + "] with value [" + str + "]", by.toString());
        findElement();
        element.clear();
        element.sendKeys(str);
        //Utils.sleep(1);
//        reporter.takeScreenshot();
    }

    public boolean validateText(String str) {
        if (str == null) {
            return true;
        }

        if (!isExists()) {
            reporter.error("The element [" + this.desc + "] is not exists", str + "\r\n" + by.toString());
            return false;
        }

        boolean result = this.element.getText().equals(str);
        reporter.result("ValidateText: element Text [" + this.element.getText() + "] ,current Text [" + str + "]"
                , by.toString(), result);
        return result;
    }

    public boolean validateExists() {
        if (isExists()) {
            reporter.message("The element [" + this.desc + "] is exists", by.toString());
            return true;
        }

        reporter.error("The element [" + this.desc + "] is not exists", by.toString());
        return false;
    }

    public boolean isExists() {
        findElement();
        return this.element != null;
    }

    protected void findElement() {
        int count = 10;
        while (count-- > 0) {
            SearchContext _root = Browser.driver();

            if (root != null) {
                root.findElement();
                _root = root.element;
            }

            List<WebElement> elements = _root.findElements(this.by);

            if (!elements.isEmpty()) {
                this.element = elements.get(index);
                Browser.runJS("arguments[0].scrollIntoView()", element);

                Utils.sleepMS(500);
                break;
            }
        }
    }

    public void select(String str) {
        findElement();
        reporter.message("Select from drop-down [" + this.desc + "] the option: " + str, str + "\r\n" + by.toString());
        Select select = new Select(element);
        select.selectByVisibleText(str);
    }

    public boolean validateChooseRadio() {
        if (!isExists()) {
            reporter.error("The element [" + this.desc + "] is not exists", by.toString());
            return false;
        }

        if (element.isSelected()) {
            reporter.message("The element [" + this.desc + "] is Selected", by.toString());
            return true;
        }
        reporter.error("The element [" + this.desc + "] is not Selected", by.toString());
        return false;
    }

    /*
    private void runJS(String s) {
        ((JavascriptExecutor)Browser.driver()).executeScript("arguments[0]."+s, this.element);
    }

    public void clickJS() {
        runJS("click()");
    }

    public void setColor(String color) {
        runJS("style.color='" + color + "'");
    }

    public void dragAndDrop() {
        new Actions(Browser.driver()).moveToElement(element)
                .clickAndHold().moveByOffset(300, -30).release()
                .perform();
    }

    public void ctrlClick() {
        new Actions(Browser.driver()).keyDown(element, Keys.CONTROL).click().perform();
    }

    public void inputSeveralKey() {
        element.sendKeys(Keys.chord(Keys.CONTROL, "p"));
    }
*/

}




















