package POM.Pages;

import org.openqa.selenium.By;
import utils.Logger;

public class SignOutPage extends AbstractPage {

    private static final By GOODREADS_HOME_BUTTON_LOCATOR = By.xpath("//a[text()='Goodreads Home']");
    private static final By SIGN_IN_AGAIN_BUTTON_LOCATOR = By.xpath("//a[text()='Sign in again']");

    public HomePage clickGoodreadsHome() {
        Logger.ACTION(this.getClass().getSimpleName() + ": clicking Googreads Home link to switch to home page");

        getButton(GOODREADS_HOME_BUTTON_LOCATOR).waitForVisibleAndClick(3);
        return new HomePage();
    }

    public SignInPage clickSignInAgain() {
        Logger.ACTION(this.getClass().getSimpleName() + ": clicking sign in again link to switch to sign in page");

        getButton(SIGN_IN_AGAIN_BUTTON_LOCATOR).waitForVisibleAndClick(3);
        return new SignInPage();
    }
}
