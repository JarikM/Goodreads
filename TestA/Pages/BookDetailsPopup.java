package POM.Pages;

import org.openqa.selenium.By;
import utils.Logger;

public class BookDetailsPopup extends AbstractPage{

    private static final By CLOSE_BUTTON_LOCATOR = By.id("close");

    private static final By WHAT_DID_YOU_THINK_INOUT_LOCATOR = By.xpath("//textarea");
    private static final By START_DATE_YEAR_SELECT_LOCATOR = By.xpath("//select[@class='rereadDatePicker smallPicker startYear']");
    private static final By START_DATE_MONTH_SELECT_LOCATOR = By.xpath("//select[@class='rereadDatePicker largePicker startMonth']");
    private static final By START_DATE_DAY_SELECT_LOCATOR = By.xpath("//select[@class='rereadDatePicker smallPicker startDay']");
    private static final By FINISH_DATE_YEAR_SELECT_LOCATOR = By.xpath("//select[@class='rereadDatePicker smallPicker endYear']");
    private static final By FINISH_DATE_MONTH_SELECT_LOCATOR = By.xpath("//select[@class='rereadDatePicker largePicker startMonth']");
    private static final By FINISH_DATE_DAY_SELECT_LOCATOR = By.xpath("//select[@class='rereadDatePicker smallPicker startDay']");
    private static final By SAVE_BUTTON_LOCATOR = By.xpath("//input[@value='Save']");

    public BookDetailsPopup setRating(int star_index)  {
        Logger.ACTION(this.getClass().getSimpleName() + ": set rating star");

        getButton(By.xpath("//div[@class='formItem']//a[text()='" + star_index + " of 5 stars']")).waitForVisibleAndClick(3);
        return this;
    }

    public BookDetailsPopup setTextFeedback(String text) {
        Logger.ACTION(this.getClass().getSimpleName() + ": type some text to what did you think text field");

        getInput(WHAT_DID_YOU_THINK_INOUT_LOCATOR).sendKeys(text);
        return this;
    }

    public BookDetailsPopup selectYearDateStarted(String year) {
        Logger.ACTION(this.getClass().getSimpleName() + ": select year for started date");

        getSelect(START_DATE_YEAR_SELECT_LOCATOR).selectByVisibleText(year);
        return this;
    }

    public BookDetailsPopup selectMonthMonthStarted(String month) {
        Logger.ACTION(this.getClass().getSimpleName() + ": select month for started date");

        getSelect(START_DATE_MONTH_SELECT_LOCATOR).selectByVisibleText(month);
        return this;
    }
    public BookDetailsPopup selectYearDayStarted(String day) {
        Logger.ACTION(this.getClass().getSimpleName() + ": select day for started date");

        getSelect(START_DATE_DAY_SELECT_LOCATOR).selectByVisibleText(day);
        return this;
    }
    public BookDetailsPopup setDateStarted(String s_year, String s_month, String s_day) {
        Logger.ACTION(this.getClass().getSimpleName() + ": set year, month and day for started date");

        selectYearDateStarted(s_year);
        selectMonthMonthStarted(s_month);
        selectYearDayStarted(s_day);
        return this;
    }

    private BookDetailsPopup selectYearDateFinished(String year) {
        Logger.ACTION(this.getClass().getSimpleName() + ": select year for date finished");

        getSelect(FINISH_DATE_YEAR_SELECT_LOCATOR).selectByVisibleText(year);
        return this;
    }

    private BookDetailsPopup selectMonthDateFinished(String month) {
        Logger.ACTION(this.getClass().getSimpleName() + ": select month for date finished");

        getSelect(FINISH_DATE_MONTH_SELECT_LOCATOR).selectByVisibleText(month);
        return this;
    }

    private BookDetailsPopup selectDayDateFinished(String day) {
        Logger.ACTION(this.getClass().getSimpleName() + ": select day for date finished");

        getSelect(FINISH_DATE_DAY_SELECT_LOCATOR).selectByVisibleText(day);
        return this;
    }

    public BookDetailsPopup setDateFinished(String f_year, String f_month, String f_day) {
        Logger.ACTION(this.getClass().getSimpleName() + ": set year, month and day for date finished");

        selectYearDateFinished(f_year);
        selectMonthDateFinished(f_month);
        selectDayDateFinished(f_day);
        return this;
    }

    public BookDetailsPopup clickSaveButton() {
        Logger.ACTION(this.getClass().getSimpleName() + ": click save button to finish the feedback");

        getButton(SAVE_BUTTON_LOCATOR).waitForVisibleAndClick(3);
        return this;
    }

    public SearchPage makeFeedback(int star_index, String text, String s_year, String s_month, String s_day, String f_year, String f_month, String f_day) {
        Logger.STEP(this.getClass().getSimpleName() + ": set rating, left some text feedback, set start and finish date and save the feedback");

        setRating(star_index);
        setTextFeedback(text);
        setDateStarted(s_year, s_month, s_day);
        setDateFinished(f_year, f_month, f_day);
        clickSaveButton();
        getButton(CLOSE_BUTTON_LOCATOR).waitForElementIsNotVisible(3);
        return new SearchPage();
    }
}
