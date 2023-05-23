package pages.newTherapist;

import infra.ui.BasePage;
import infra.ui.UiElement;
import objects.pages.newTherapist.ProfessionalDetailsPageData;
import org.openqa.selenium.By;

public class ProfessionalDetailsPage extends BasePage {
    UiElement SpecializationMenu = new UiElement("תפריט תחומי התמחות", By.cssSelector("select[ng-reflect-model]"));
    UiElement treatmentType = new UiElement("סוג טיפול", By.cssSelector("select[ng-reflect-model='1']"));
    UiElement treatmentPrice = new UiElement("מחיר טיפול", By.cssSelector("input[ng-reflect-name*='price']"));
    UiElement treatmentDuration = new UiElement("משך זמן טיפול", By.cssSelector("input[type='time']"));
    UiElement generalComments = new UiElement("הערות כלליות", By.cssSelector("textarea"));

    protected void init() {
        indexOfNav = 2;
    }

    public ProfessionalDetailsPage fillPage(ProfessionalDetailsPageData data) {
        treatmentPrice.input(data.getTreatmentPrice());
        treatmentDuration.input(data.getTreatmentDuration());
        generalComments.input(data.getGeneralComments());
        return this;
    }

    @Override
    public boolean assertDisplay() {
        return false;
    }
}
