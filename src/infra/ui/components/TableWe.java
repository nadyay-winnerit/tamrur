package infra.ui.components;

import infra.general.AutomationException;
import infra.ui.UiElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class TableWe extends UiElement {

    public TableWe(String desc, By by) {
        super(desc, by);
    }

    /**
     * The function receives a list
     * and returns as a list all the row numbers that maintain the received list
     *
     * @param tableCells
     * @return -list numbers of rows
     */

    public List<Integer> search(List<TableCell> tableCells) {
        super.findElement();
        List<Integer> listIndexRows = new ArrayList<>();
        boolean flag;
        WebElement tr, td;
        //outerFor:
        for (int i = 1; i <= getCountRows(); i++) {
            tr = this.element.findElement(By.cssSelector("tbody tr:nth-child(" + i + ")"));
            flag = true;
            for (TableCell tableCell : tableCells) {
                int indexColumn = getColumnIndex(tableCell.column);
                if (indexColumn == -1) {
                    throw new AutomationException("The received key is invalid for this table " + tableCell.column, null, null);
                    //break outerFor;
                }

                td = tr.findElement(By.cssSelector("td:nth-child(" + (indexColumn + 1) + ")"));
                String strText = null;
                if (tableCell.isSelect) {

                    strText = caseSelect(td);

                } else
                    strText = td.getText().trim();

                if (!tableCell.value.equals(strText)) {
                    flag = false;
                    break;
                }
            }//close inner for
            if (flag)
                listIndexRows.add(i);
        }//close out for
        return listIndexRows;
    }

    /**
     * The func gets list of type TableCell, and number of Line
     * and checks if all condition is exist;
     *
     * @param list    - from type TableCell obj (column,value,isSelect)
     * @param numLine - number of line
     * @return
     */
    public boolean validate(List<TableCell> list, int numLine) {
        super.findElement();
        boolean bool = false;
        WebElement tr = element.findElement(By.cssSelector("tr:nth-child(" + numLine + ")"));
        List<WebElement> tdList = tr.findElements(By.cssSelector("td"));
        for (TableCell tableCell : list) {
            int indexColumn = getColumnIndex(tableCell.column);
            String valueTD;
            if (tableCell.isSelect) {
                valueTD = caseSelect(tdList.get(indexColumn));
                bool = (tableCell.value.equals(valueTD));

            } else bool = (tdList.get(indexColumn).getText().trim().equals(tableCell.value));
            if (!bool)
                break;
        }
        return bool;
    }


    private int getColumnIndex(String str) {
        List<WebElement> listTH = this.element.findElements(By.tagName("th"));

        for (int i = 0; i < listTH.size(); i++) {
            if (listTH.get(i).getText().trim().equals(str))
                return i;
        }
        return -1;
    }

    private int getCountRows() {
        List<WebElement> rowElements = this.element.findElements(By.cssSelector("tbody tr"));
        return rowElements.size();
    }

    @Override
    public TableWe setIndex(int value) {
        return (TableWe) super.setIndex(value);
    }

    private String caseSelect(WebElement webElement) {
        List<WebElement> options = webElement.findElements(By.cssSelector("option"));

        for (WebElement listItem : options) {
            if (listItem.isSelected())
                return listItem.getText().trim();

        }
        return null;
    }

}
