package infra.ui;

import infra.Utils;
import infra.reporter.Reporter;
import org.openqa.selenium.*;

import java.util.List;

public class UiElement {

    private final By by;
    private final String desc;
    protected WebElement element;

    //אתחול הרוט מסוג UiElement הוא בעצם יכיל אלמנט שכבר אותר, וממנו יהיה אפשר להמשיך למצוא עוד אלמנטים אחריו
    private UiElement root = null;

    public static Reporter reporter = Reporter.reporter();

    public UiElement(String desc, By by) {
        this.desc = desc;
        this.by = by;
    }

    public UiElement root(UiElement root) {
        this.root = root;
        return this;
    }

    public WebElement getElement() {
        return element;
    }

    public void click() {
        reporter.hasScreenshot().message("Click on the element [" + this.desc + "]", by.toString());
        findElement();
        element.click();
        //Utils.sleepMS(500);
        reporter.takeScreenshot();
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

    public boolean isExists() {
        findElement();
        return this.element != null;
    }

    public void input(String str) {
        if (str == null) {
            return;
        }

        reporter.hasScreenshot().message("Input to element [" + this.desc + "] with value [" + str + "]", by.toString());
        findElement();
        element.clear();
        element.sendKeys(str);
        //Utils.sleep(1);
        reporter.takeScreenshot();
    }

    public void validateText(String str) {
        if (str == null) {
            return;
        }
        if (isExists() == false) {
            reporter.error("The element [" + this.desc + "] is not exists", str + "\r\n" + by.toString());
            return;
        }
        reporter.result("ValidateText: element Text [" + this.element.getText() + "] ,current Text [" + str + "]"
                , by.toString()
                , this.element.getText().equals(str));
    }

    public void validateExists() {
        if (isExists()) {
            reporter.message("The element [" + this.desc + "] is exists", by.toString());
        }
        reporter.error("The element [" + this.desc + "] is not exists", by.toString());
    }


    protected void findElement() {
        int count = 10;
        while (count-- > 0) {

            //אפשרות 1
            SearchContext _root = Browser.driver();
            //אם הרוט ריק, זה אומר שלא נמצא אלמנט עדיין, אז הוא מאתחל אותו בדרייבר
            if (root != null) {
                root.findElement(); // מציב בתוך הרוט את האלמנט שכבר נמצא, אבל הוא יכול לשלוח לאותה פונקציה שהוא נמצא בה?
                _root = root.element;
            }
            List<WebElement> elements = _root.findElements(this.by);//  צריך להיות ככה אחרי הרוט של האפשרות הראשונה

            //אפשרות 2
            //By _by= new ByChained(root.by, by );
            //List<WebElement> elements = Browser.driver().findElements(_by);צריך להיות ככה אחרי הרוט של האפשרות השניה

            if (!elements.isEmpty()) {
                this.element = elements.get(0);
                break;
            }
            Utils.sleepMS(500);
        }
    }
}
