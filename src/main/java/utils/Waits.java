package utils;

import drivers.BaseDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.report.LogsUtils;

import java.time.Duration;

public class Waits {

    // explicit wait for element until visibility of element
    public static void waitforElementToBeVisible(By locator) {
        LogsUtils.info("Waiting for element to be visible: ", locator.toString()); // logging for console and report
        new WebDriverWait(BaseDriver.getDriver(), Duration.ofSeconds(25)).
                until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitforElementToPresent(By locator) {
        LogsUtils.info("Waiting for element to be present: ", locator.toString()); // logging for console and report
        new WebDriverWait(BaseDriver.getDriver(), Duration.ofSeconds(25)).
                until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // explicit wait for element until invisibility of element
    public static void waitforElementToBeInVisible(By locator) {
        LogsUtils.info("Waiting for element to be invisible: ", locator.toString()); // logging for console and report
        new WebDriverWait(BaseDriver.getDriver(), Duration.ofSeconds(150)).
                until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    // explicit wait for element until element to be clickable
    public static void waitforElementToBeClickable(By locator) {
        waitforElementToBeVisible(locator);
        LogsUtils.info("Waiting for element to be clickable: ", locator.toString()); // logging for console and report
        new WebDriverWait(BaseDriver.getDriver(), Duration.ofSeconds(25)).
                until(ExpectedConditions.elementToBeClickable(locator));
    }

    // explicit wait for page title to contain specific string
    public static void waitforPageTitleToContain(String title) {
        LogsUtils.info("Waiting for page title"); // logging for console and report
        new WebDriverWait(BaseDriver.getDriver(), Duration.ofSeconds(25)).
                until(ExpectedConditions.titleContains(title));
    }

    // explicit wait for elements until visibility of all elements
    public static void waitforVisibilityeOfAllElements(By locator) {
        LogsUtils.info("Waiting for element to be visible: ", locator.toString());   // logging for console and report
        new WebDriverWait(BaseDriver.getDriver(), Duration.ofSeconds(25)).
                until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    // explicit wait for alert to br present
    public static void waitforAlertToBePresent(By locator) {
        LogsUtils.info("Waiting for element to be visible: ", locator.toString());   // logging for console and report
        new WebDriverWait(BaseDriver.getDriver(), Duration.ofSeconds(25)).
                until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public static void waitForTextToBe(By locator, String value) {
        LogsUtils.info("Waiting element: ", locator.toString(), "to change text to be", value);
        new WebDriverWait(BaseDriver.getDriver(), Duration.ofSeconds(120)).
                until(ExpectedConditions.textToBe(locator, value));
    }

    public static void waitForPageToLoad() {

        new WebDriverWait(BaseDriver.getDriver(), Duration.ofSeconds(20)).
                until(webDriver ->
                        ((JavascriptExecutor) webDriver)
                                .executeScript("return document.readyState")
                                .equals("complete"));
    }

    public static void sleepForCertainTime(int timeOutInMilliSeconds) {
        try {
            Thread.sleep(timeOutInMilliSeconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    }


