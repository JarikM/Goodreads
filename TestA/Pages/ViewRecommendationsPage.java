package POM.Pages;

import org.openqa.selenium.By;
import utils.Logger;

public class ViewRecommendationsPage extends AbstractPage {

    private static final By USER_BUTTON_LOCATOR =By.xpath("//li[@class='personalNav__listItem'][5]");
    private static final By SIGN_OUT_LINK_LOCATOR =By.xpath("//a[text()='Sign out']");

    public SignOutPage signOut() {
        Logger.STEP(this.getClass().getSimpleName() + ": clicking sign out to log out");

        getButton(USER_BUTTON_LOCATOR).waitForVisibleAndClick(3);
        getButton(SIGN_OUT_LINK_LOCATOR).waitForVisibleAndClick(3);
        return new SignOutPage();
    }
}
