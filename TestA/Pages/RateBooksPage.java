package POM.Pages;

import org.openqa.selenium.By;
import utils.Logger;

public class RateBooksPage extends AbstractPage {
    // both pages for rating books were implemented in this class

    private static final By ART_CHECKBOX_LOCATOR = By.id("favorites_Art");
    private static final By CONTINUE_BUTTON_LOCATOR =  By.xpath("//input[@value='Continue']");
    private static final By FINISH_RATING_LINK_LOCATOR = By.xpath("//a[text()='I’m finished rating']");

    public RateBooksPage checkArtBox () {
        Logger.ACTION(this.getClass().getSimpleName() + ": checking the art check box");

        getCheckBox(ART_CHECKBOX_LOCATOR).check();
        return this;
    }

    public RateBooksPage clickContinue () {
        Logger.ACTION(this.getClass().getSimpleName() + ": clicking continue button to proceed with creating new account");

        getButton(CONTINUE_BUTTON_LOCATOR).waitForVisibleAndClick(3);
        return this;
    }

    public ViewRecommendationsPage clickFinishRating () {
        Logger.ACTION(this.getClass().getSimpleName() + ": clicking i'm finished rating link to proceed with creating new account");

        getButton(FINISH_RATING_LINK_LOCATOR).waitForVisibleAndClick(3);
        return new ViewRecommendationsPage();
    }
}
