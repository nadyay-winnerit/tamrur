package infra;

import infra.reporter.Reporter;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ByChained;

import java.util.List;

public class UiElement {

    private final By by;
    private final String desc;
    protected WebElement element;

    //אתחול הרוט מסוג UiElement הוא בעצם יכיל אלמנט שכבר אותר, וממנו יהיה אפשר להמשיך למצוא עוד אלמנטים אחריו
    private UiElement root= new UiElement(null, null);

    public static Reporter reporter= Reporter.reporter();

    public UiElement(String desc, By by) {
        this.desc = desc;
        this.by = by;
    }

    public WebElement getElement() {
        return element;
    }

    public void click() {
        findElement();
        System.out.println("Click on the element [" + this.desc + "]");
        element.click();
        try {
            Thread.sleep(1000);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public String read() {
        String str = null;

        str = element.getText();
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

        findElement();
        System.out.println("Input to element [" + this.desc + "] with value [" + str + "]");
        element.clear();
        element.sendKeys(str);
        try {
            Thread.sleep(1500);
        } catch (Throwable t) {
            t.printStackTrace();
        }

    }

    protected void findElement() {
        int count = 10;
        while (count-- > 0) {

            //איפה אני מאתחלת את הרוט?????
            //אפשרות 2
            //צריך לטפל באם הרוט הוא נאל
            By _by= new ByChained(root.by, by );
            //List<WebElement> elements = Browser.driver().findElements(_by);צריך להיות ככה אחרי הרוט של האפשרות השניה

            //אפשרות 1
            //////////
            SearchContext _root= Browser.driver();

            if(root == null){  //זה נכון?
                _root= this.root;
            } else {
                root.findElement(); //מציב בתוך הרוט את האלמנט שכבר נמצא
                _root= root.element;  //זה נכון?
            }
            /////////////

            // List<WebElement> elements = _root.findElements(this.by);  צריך להיות ככה אחרי הרוט של האפשרות הראשונה
            List<WebElement> elements = Browser.driver().findElements(this.by);
            if (!elements.isEmpty()) {
                this.element = elements.get(0);
                break;
            }
            try {
                Thread.sleep(500);
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }
}
