package pages.newTherapist;

import infra.ui.BasePage;
import infra.ui.UiElement;
import objects.pages.newTherapist.DaysAndHoursOfOperationPageData;
import org.openqa.selenium.By;

public class DaysAndHoursOfOperationPage extends BasePage {
    private final UiElement title = new UiElement("כותרת", By.xpath("//h3[text()=' ימי ושעות פעילות:']"));
    private final UiElement dayMenu = new UiElement("יום", By.cssSelector("div select"));
    private final UiElement fromHour = new UiElement("משעה", By.cssSelector(""));
    private final UiElement toAnHour = new UiElement("עד שעה", By.cssSelector(""));
    private final UiElement remarks = new UiElement("הערות", By.cssSelector("input[type='text']"));

    protected void init() {
        indexOfNav = 4;
    }

    public DaysAndHoursOfOperationPage fillPage(DaysAndHoursOfOperationPageData data) {
        dayMenu.select(data.getDayMenu());
        /*fromHour.input(data.getFromHour());
        toAnHour.input(data.getToAnHour());
        remarks.input(data.getRemarks());*/
        return this;
    }

    @Override
    public boolean assertDisplay() {
        return title.isExists();
    }
}
