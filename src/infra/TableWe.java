package infra;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TableWe extends UiElement {

    public TableWe(String desc, By by) {
        super(desc, by);
    }

    public int searchRowIndex(WebElement rowElement) {
        System.out.println("search a line in the table");
        List<WebElement> dataElementsCur, dataElementsGet;
        dataElementsGet = rowElement.findElements(By.tagName("td"));
        super.findElement();
        List<WebElement> rowElements = this.element.findElements(By.tagName("tr"));
        boolean flag;
        for (int i = 0; i <= rowElements.size(); i++) {
            flag = true;
            dataElementsCur = rowElements.get(i).findElements(By.tagName("td"));
            for (int j = 0; j < dataElementsCur.size() && flag; j++) {
                if (!dataElementsCur.get(j).getText().equals(dataElementsGet.get(j).getText()))
                    flag = false;
            }
            if (flag) {
                return i;
            }

        }
        return -1;

    }
}
