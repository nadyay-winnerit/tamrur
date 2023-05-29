package pages;

import infra.ui.UiElement;
import org.openqa.selenium.By;

public class NavbarPage {

    public static UiElement medicalFiles = new UiElement("medicalFiles", By.cssSelector("a.nav-link[href='#/home/dashboard']"));

    public static UiElement plus=new UiElement("הוסף", By.cssSelector("button.btn-primary[title*='הוסף']"));

    public static UiElement next=new UiElement("הבא", By.xpath("//button[text()='הבא']"));

    public static UiElement save=new UiElement("שמירה", By.xpath("//button[text()='שמור שינויים']"));


    public static void medicalFilesClick(){medicalFiles.click();}
    public static void plusClick(){plus.click();}
    public static void nextClick(){next.click();}
    public static void saveClick(){save.click();}


}
