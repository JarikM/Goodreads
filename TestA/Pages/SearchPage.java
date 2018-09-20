package POM.Pages;

import org.openqa.selenium.By;
import utils.Logger;

public class SearchPage extends AbstractPage {

    private static final By LOG_OUT_BUTTON_LOCATOR = By.xpath("//a[contains(text(), 'Sign out')]");

    public SearchPage markWantToRead(int index) {
        Logger.ACTION(this.getClass().getSimpleName() + ": mark top book #" + index + " as wanted to read");

        getButton(By.xpath("//tr[@itemtype = 'http://schema.org/Book'][" + index + "]//button[@class='wtrShelfButton']")).waitForVisibleAndClick(3);
        getButton(By.xpath("//button[@class='wtrExclusiveShelf' and @value='to-read']")).waitForVisibleAndClick(4);
        getButton(By.xpath("//tr[@itemtype = 'http://schema.org/Book'][" + index + "]//button[@class='wtrShelfButton']")).waitForVisibleAndClick(3);
        getButton(By.xpath("//button[@class='wtrExclusiveShelf' and @value='read']")).waitForElementIsNotVisible(4);

        return this;
    }

    public BookDetailsPopup markRead(int index) {
        Logger.ACTION(this.getClass().getSimpleName() + ": mark top book #" + index + " as already read");

        getButton(By.xpath("//tr[@itemtype = 'http://schema.org/Book'][" + index + "]//button[@class='wtrShelfButton']")).waitForVisibleAndClick(3);
        getButton(By.xpath("//button[@class='wtrExclusiveShelf' and @value='read']")).waitForVisibleAndClick(3);
//        getButton(By.xpath("//button[@class='wtrExclusiveShelf' and @value='to-read']")).waitForElementIsNotVisible(4);

        return new BookDetailsPopup();
    }

    private static final By USER_BUTTON_LOCATOR =By.xpath("//li[@class='personalNav__listItem'][5]");
    private static final By SIGN_OUT_LINK_LOCATOR =By.xpath("//a[text()='Sign out']");

    public SignOutPage signOut() {
        Logger.STEP(this.getClass().getSimpleName() + ": clicking sign out to log out");

        getButton(USER_BUTTON_LOCATOR).waitForVisibleAndClick(3);
        getButton(SIGN_OUT_LINK_LOCATOR).waitForVisibleAndClick(3);
        return new SignOutPage();
    }

}


