package infra;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class UiElement {

    private final By by;
    private final String desc;
    protected WebElement element;

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
        if (str == null) {


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
