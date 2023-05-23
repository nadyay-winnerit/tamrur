package infra.ui;

import infra.enums.MenuMain;
import infra.general.Utils;
import infra.reporter.Reporter;
import org.openqa.selenium.By;

import static infra.reporter.Reporter.reporter;

public abstract class BasePage {
    private final UiElement nav = new UiElement(" סרגל הטאבים (nav)", By.cssSelector("ul.nav a[class*='pointer']"));
    protected int indexOfNav = 0;

    public BasePage() {
        init();
        if (indexOfNav != 0) {
            Reporter.reporter().message("Choosing the right tab", null);
            nav.setIndex(indexOfNav).click();
        }
        reporter().openLevel(this.getClass().getSimpleName(), null);

        if (!assertDisplay())
            reporter().error("Page did not come up as expected", null);

    }

    protected void init() {

    }

    public static void chooseMenu(MenuMain menuMain) {
        new UiElement(" מתפריט ראשי" + menuMain.nameInHebrew(), By.cssSelector("a[ng-reflect-router-link='/" + menuMain.rootMenu() + "/" + menuMain.name() + "']")).click();
    }


    public abstract boolean assertDisplay();

    public void finish() {
        Utils.sleep(2);
        // Check if there is any error on the page
        Reporter.reporter().closeLevel();
    }
}
