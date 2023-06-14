package pages.newTherapist;

import data.pages.newTherapist.ProfessionalDetailsPageData;
import infra.ui.BasePage;
import infra.ui.UiElement;
import org.openqa.selenium.By;

public class ProfessionalDetailsPage extends BasePage {
    UiElement specializationMenu = new UiElement("תפריט תחומי התמחות", By.cssSelector("select[ng-reflect-model]"));
    UiElement treatmentType = new UiElement("סוג טיפול", By.cssSelector("select[ng-reflect-name='t0']"));
    UiElement treatmentPrice = new UiElement("מחיר טיפול", By.cssSelector("input[ng-reflect-name*='price']"));
    UiElement treatmentDuration = new UiElement("משך זמן טיפול", By.cssSelector("input[type='time']"));
    UiElement generalComments = new UiElement("הערות כלליות", By.cssSelector("textarea"));

    protected void init() {
        indexOfNav = 2;
    }

    public ProfessionalDetailsPage fillPage(ProfessionalDetailsPageData data) {
        specializationMenu.select(data.getSpecializationMenu());
        treatmentType.select(data.getTreatmentType());
        treatmentPrice.input(data.getTreatmentPrice());
        treatmentDuration.input(data.getTreatmentDuration());
        generalComments.input(data.getGeneralComments());
        return this;
    }

    @Override
    public boolean assertDisplay() {
        return new UiElement("כותרת", By.xpath("//h3[text()='פרטים מקצועיים']")).isExists();
    }
}
