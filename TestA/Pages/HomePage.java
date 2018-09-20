package POM.Pages;

import org.openqa.selenium.By;
import utils.Logger;

public class HomePage extends POM.Pages.AbstractPage {

    private static final By NEW_NAME_INPUT_LOCATOR    = By.id("user_first_name");
    private static final By NEW_EMAIL_INPUT_LOCATOR = By.id("user_email");
    private static final By NEW_PASSWORD_INPUT_LOCATOR = By.id("user_password_signup");
    private static final By SIGN_UP_BUTTON_LOCATOR    = By.xpath("//input[@value='Sign up']");
    private static final By EMAIL_INPUT_LOCATOR    = By.id("userSignInFormEmail");
    private static final By PASSWORD_INPUT_LOCATOR = By.id("user_password");
    private static final By SIGN_IN_BUTTON_LOCATOR = By.xpath("//input[@value='Войти']");   //Sign in

    public HomePage() {
        url = "https://www.goodreads.com";
    }

    @Override
    public HomePage open() {
        super.open();
        return this;
    }

    public HomePage setNewName(String newName) {
        Logger.ACTION(this.getClass().getSimpleName() + ": setting new name for creating an account");

        getInput(NEW_NAME_INPUT_LOCATOR).sendKeys(newName);
        return this;
    }

    public HomePage setNewEmail(String newEmail) {
        Logger.ACTION(this.getClass().getSimpleName() + ": setting new email for creating an account");

        getInput(NEW_EMAIL_INPUT_LOCATOR).sendKeys(newEmail);
        return this;
    }

    public HomePage setNewPassword(String newPassword) {
        Logger.ACTION(this.getClass().getSimpleName() + ": setting new password for creating an account");

        getInput(NEW_PASSWORD_INPUT_LOCATOR).sendKeys(newPassword);
        return this;
    }

    public HomePage clickSingUpButton() {
        Logger.ACTION(this.getClass().getSimpleName() + ": click the sigh up button");

        getButton(SIGN_UP_BUTTON_LOCATOR).waitForVisibleAndClick(3);
        return this;
    }

    public FindFriendsPage singUp(String name, String email, String password) {
        Logger.STEP(this.getClass().getSimpleName() + ": create new account");

        setNewName(name);
        setNewEmail(email);
        setNewPassword(password);
        clickSingUpButton();
        return new FindFriendsPage();
    }

    public HomePage setEmail(String email){
        Logger.ACTION(this.getClass().getSimpleName() + ": setting email to sign in");

        getInput(EMAIL_INPUT_LOCATOR).sendKeys(email);
        return this;
    }

    public HomePage setPassword(String password){
        Logger.ACTION(this.getClass().getSimpleName() + ": setting password to sign in");

        getInput(PASSWORD_INPUT_LOCATOR).sendKeys(password);
        return this;
    }

    public HomePage clickSignIn() {
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
