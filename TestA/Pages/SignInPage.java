package POM.Pages;

import org.openqa.selenium.By;
import utils.Logger;

public class SignInPage extends AbstractPage {

    private static final By EMAIL_INPUT_LOCATOR    = By.id("user_email");
    private static final By PASSWORD_INPUT_LOCATOR = By.id("user_password");
    private static final By SIGN_IN_BUTTON_LOCATOR = By.xpath("//input[@value='Sign in']");

    public SignInPage setEmail(String email){
        Logger.ACTION(this.getClass().getSimpleName() + ": setting email to sign in");

        getInput(EMAIL_INPUT_LOCATOR).sendKeys(email);
        return this;
    }

    public SignInPage setPassword(String password){
        Logger.ACTION(this.getClass().getSimpleName() + ": setting password to sign in");

        getInput(PASSWORD_INPUT_LOCATOR).sendKeys(password);
        return this;
    }

    public SignInPage clickSignIn() {
        Logger.ACTION(this.getClass().getSimpleName() + ": clicking sign in button");

        getButton(SIGN_IN_BUTTON_LOCATOR).waitForVisibleAndClick(3);
        return this;
    }

    public MainPage signIn(String email, String password) {
        Logger.STEP(this.getClass().getSimpleName() + "Login with\nUser name: " + email + "\nPassword:  " + password);

        setEmail(email);
        setPassword(password);
        clickSignIn();

        getButton(SIGN_IN_BUTTON_LOCATOR).waitForElementIsNotVisible(15);

        return new MainPage();
    }
}
