package POM.Pages;

import org.openqa.selenium.By;
import utils.Logger;

public class MainPage extends AbstractPage {

    private static final By SEARCH_FIELD_INPUT_LOCATOR = By.xpath("//input[@class='searchBox__input searchBox__input--navbar']");
    private static final By SEARCH_SUBMIT_BUTTON_LOCATOR = By.xpath("//button[contains(@class, 'searchBox__icon')]");

    public MainPage inputSearchText(String text) {
        Logger.ACTION(this.getClass().getSimpleName() + ": type some text into search field");

        getInput(SEARCH_FIELD_INPUT_LOCATOR).sendKeys(text);
        getInput(SEARCH_FIELD_INPUT_LOCATOR).waitForTextIsPresentInElementValue(text, 3);
        sleep(300);
        return this;
    }

    public MainPage clickSubmitSearchButton() {
        Logger.ACTION(this.getClass().getSimpleName() + ": clicking submit search button");

        getButton(SEARCH_SUBMIT_BUTTON_LOCATOR).waitForVisibleAndClick(3);
        return this;
    }

    public SearchPage searchText(String text) {
        Logger.STEP(this.getClass().getSimpleName() + ": Search for some text");

        inputSearchText(text);

        clickSubmitSearchButton();
        return new SearchPage();
    }
}
