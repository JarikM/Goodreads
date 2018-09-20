package POM.Pages;

import Managers.WebDriverManager;
import POM.Elements.*;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import utils.Logger;

import java.util.List;

public class AbstractPage {
    protected String url;

    public AbstractPage open() {
        Logger.STEP("Navigating to: " + url);

        WebDriverManager.getDriver().get(url);
        WebDriverManager.getDriver().manage().window().maximize();
        return this;
    }

//GETTERS FOR WEB_ELEMENT WRAPPERS

    public static Button getButton(By locator) {
        return new Button(locator);
    }

    public static Button getButton(WebElement webElement) {
        return new Button(webElement);
    }

    public static Input getInput(By locator) {
        return new Input(locator);
    }

    public static Frame getFrame(By locator) {
        return new Frame(locator);
    }

    public static Select getSelect(By locator) {
        return new Select(locator);
    }

    public static SelectSearch getSelectSearch(By locator) {
        return new SelectSearch(locator);
    }

    public static Text getText(By locator) {
        return new Text(locator);
    }

    public static CheckBox getCheckBox(By locator) {
        return new CheckBox(locator);
    }

//COMMON FUNCTIONALITY

    /**
     * Waits for IFrame is visible up to 60 seconds then focuses it
     *
     * @param locator - IFrame's locator.
     */
    protected void switchToFrame(By locator) {
        Logger.DEBUG("Switching to IFrame: " + locator.toString());
        getFrame(locator).waitForElementVisible(60);
        getFrame(locator).switchTo();
    }

    /**
     * Switches to default content of the page.
     * Useful when IFrame was selected and access to default content is required
     */
    public void switchToDefaultContent() {
        Logger.DEBUG("Switching to default content");
        WebDriverManager.getDriver().switchTo().defaultContent();
    }

    /**
     * Returns current url of the page
     *
     * @return String - Current url
     */
    public String getCurrentUrl() {
        return WebDriverManager.getDriver().getCurrentUrl();
    }

    /**
     * Try to avoid using this function!
     * Wrapper for Thread.sleep
     *
     * @param millis - time in milliseconds
     */
    public static void sleep(int millis) {

        Logger.DEBUG("Sleeping for " + millis + " milliseconds.");

        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Logger.EXCEPTION("Problem was encountered during using Thread.sleep() method.");
        }
    }

    public void reloadPage() {
        Logger.STEP(this.getClass().getSimpleName() + ": Reload page");

        WebDriverManager.getDriver().navigate().refresh();
    }

    public String getSource() {
        return WebDriverManager.getDriver().getPageSource();
    }

    /**
     * Waiting for success within 7 sec
     *
     * @param req_url
     */
    public void waitForSuccessfulResponse(String req_url) {
        waitForSuccessfulResponse(req_url, 12000);
    }

    public void waitForSuccessfulResponse(String req_url, int millis) {
        long expectedTime = System.currentTimeMillis() + millis;
        Har har;
        boolean isResponseSuccess;
        int countOfReqUrls = 0;
        do {
            isResponseSuccess = true;
            try {
                sleep(50);
                har = WebDriverManager.proxyServer.getHar();
                List<HarEntry> harEntries = har.getLog().getEntries();

                for (HarEntry entry : harEntries) {

                    if (entry.getRequest().getUrl().contains(req_url)){
                        countOfReqUrls ++;
                        if (!entry.getResponse().getContent().getText().contains("\"success\":true,")) {
                            isResponseSuccess = false;
                        }

                    }
                }
            } catch (NullPointerException e) {
                isResponseSuccess = false;
            }
        }
        while (System.currentTimeMillis() < expectedTime && (countOfReqUrls == 0 || !isResponseSuccess));

        if (countOfReqUrls==0) {
            Logger.INFO("Request for : " + req_url + " was not found");
        }
        else if (!isResponseSuccess){
            Logger.INFO("Response for : " + req_url + " was NOT successful within " + millis + " milliseconds");
        }
        else if (countOfReqUrls>0 && isResponseSuccess) {
            Logger.INFO("Response for : " + req_url + " was successful within " + millis + " milliseconds");
        }

        WebDriverManager.proxyServer.endHar();
    }

    public void startHttpTrafficRecord() {
        WebDriverManager.proxyServer.newHar();
    }

    public void waitForLoaderIsAbsent(){
        try{
            sleep(300);
            getButton(By.xpath("//*[@id='loading-body']")).waitForElementIsNotVisible(5);
            getButton(By.xpath("//*[@class = 'preloader fadeIn']")).waitForElementIsNotVisible(5);
        }catch (Exception ignored){
            Logger.ERROR("Loading body element is not visible");
        }
    }

    @Deprecated
    public void waitForInnerLoaderIsAbsent(){
        try{
            getButton(By.xpath("//div[@class='preloader finished']")).waitForElementPresentInDOM(3);
        }catch (Exception ignored){}
    }

    public static void waitForNewTabIsOpened(int seconds){
        int currentTabsCount = WebDriverManager.getDriver().getWindowHandles().size();

        for (int i=0; i<seconds*1000 && WebDriverManager.getDriver().getWindowHandles().size()<=currentTabsCount; i+=200){
            sleep(200);
        }

        if (WebDriverManager.getDriver().getWindowHandles().size()<=currentTabsCount){
            throw new TimeoutException("There were no new tabs during " + seconds + " seconds");
        }
    }

    public static void closeTabByHandle(String handle) {
        WebDriverManager.getDriver().switchTo().window(handle);
        WebDriverManager.getDriver().close();
    }

    public static String getActiveTabHandle(){
        return WebDriverManager.getDriver().getWindowHandle();
    }

    public static void focusTabByHandle(String handle){
        WebDriverManager.getDriver().switchTo().window(handle);
    }


    public void scrollUp(int pixels){
        ((JavascriptExecutor) WebDriverManager.getDriver()).executeScript("window.scrollBy(0,-"+pixels+")", "");
    }
    public void scrollDown(int pixels){
        ((JavascriptExecutor) WebDriverManager.getDriver()).executeScript("window.scrollBy(0,"+pixels+")", "");
    }
}
