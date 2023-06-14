package pages.medicalFile;

import data.pages.medicalFile.FileDetailsPageData;
import infra.reporter.Reporter;
import infra.ui.BasePage;
import infra.ui.UiElement;
import org.openqa.selenium.By;

public class FileDetailsPage extends BasePage {

    private UiElement accompanied = new UiElement("מלווה תיק", By.cssSelector("select[class*='ng-star-inserted'][name='lr']"));
    private UiElement title = new UiElement("כותרת", By.xpath("//h4[text()=\" תיק לקוח חדש \"]"));

    public FileDetailsPage fillPage(FileDetailsPageData data){
        accompanied.select(data.getAccompanied());
        return this;
    }

    public FileDetailsPage validatePage(FileDetailsPageData data) {
        accompanied.validateText(data.getAccompanied());
        return this;
    }
    public FileDetailsPage assertPage() {
        if (title.isExists()) {
            Reporter.reporter().message("assert FileDetailsPage Succeeded","");
        }
        Reporter.reporter().error("assert FileDetailsPage failed","");
        return this;
    }

    @Override
    public boolean assertDisplay() {
        return false;
    }
}
