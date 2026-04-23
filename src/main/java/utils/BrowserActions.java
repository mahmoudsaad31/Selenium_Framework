package utils;


import drivers.BaseDriver;
import io.qameta.allure.Step;
import utils.report.LogsUtils;

public class BrowserActions {

    // method to navigate to url
    @Step("Navigating to URL: {url}")
    public static void navigateToURL(String url) {
        BaseDriver.getDriver().get(url);
        LogsUtils.info("Navigated to URL: ", url);    // logging for console and report
    }

    // method to get current page title
    @Step("Getting page title")
    public static String getPageTitle() {
        LogsUtils.info("Page title: ", BaseDriver.getDriver().getTitle()); // logging for console and report
        return BaseDriver.getDriver().getTitle();
    }

    // method to get current page title
    @Step("Getting page URL")
    public static String getPageURL() {
        Waits.waitForPageToLoad();
        LogsUtils.info("Page URL: ", BaseDriver.getDriver().getCurrentUrl()); // logging for console and report
        return BaseDriver.getDriver().getCurrentUrl();
    }

    // method to refresh the page
    @Step("Refreshing the page")
    public static void refreshPage() {
        BaseDriver.getDriver().navigate().refresh();
        LogsUtils.info("Refreshing the page");  // logging for console and report
    }

    //method to close the browser
    @Step("Closing the browser")
    public static void closeBrowser() {
        LogsUtils.info("Closing the browser");
        if (BaseDriver.getDriver() != null) {
            BaseDriver.getDriver().quit();
        }
    }

    public static void Back() {
        BaseDriver.getDriver().navigate().back();
        LogsUtils.info("back to previous page");  // logging for console and report
    }
}
