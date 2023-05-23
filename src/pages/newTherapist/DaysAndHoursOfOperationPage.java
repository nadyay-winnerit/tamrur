package pages.newTherapist;

import infra.ui.UiElement;
import objects.pages.newTherapist.DaysAndHoursOfOperationPageData;
import org.openqa.selenium.By;

public class DaysAndHoursOfOperationPage {
    UiElement dayMenu = new UiElement("יום", By.cssSelector("div select"));
    UiElement fromHour = new UiElement("משעה", By.cssSelector(""));
    UiElement toAnHour = new UiElement("עד שעה", By.cssSelector(""));
    UiElement remarks = new UiElement("הערות", By.cssSelector("input[type='text']"));

    public DaysAndHoursOfOperationPage fillPage(DaysAndHoursOfOperationPageData data) {
        fromHour.input(data.getFromHour());
        toAnHour.input(data.getToAnHour());
        remarks.input(data.getRemarks());
        return this;
    }

}
