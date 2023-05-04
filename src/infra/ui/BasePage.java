package infra.ui;

import infra.reporter.Reporter;

import static infra.reporter.Reporter.reporter;

public abstract class BasePage {
    public BasePage() {
        reporter().openLevel(this.getClass().getSimpleName(), null);
        if (!assertDisplay())
            reporter().error("Page did not come up as expected", null);
    }

    public abstract boolean assertDisplay();

    public void finish() {
        Reporter.reporter().closeLevel();

    }
}
