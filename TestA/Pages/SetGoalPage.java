package POM.Pages;

import org.openqa.selenium.By;
import utils.Logger;

public class SetGoalPage extends AbstractPage {

    private static final By SKIP_THIS_STEP_LINK_LOCATOR = By.xpath("//a[contains(text(), 'skip this step')]");

    public RateBooksPage clickSkipThisStep() {
        Logger.ACTION(this.getClass().getSimpleName() + ": clicking skip this test step to proceed with creating new account");

        getButton(SKIP_THIS_STEP_LINK_LOCATOR).waitForVisibleAndClick(3);
        return new RateBooksPage();
    }

}
