package utils;


import drivers.BaseDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.report.LogsUtils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;

public class ElementActions {


    //method for send keys or send data to element
    @Step("Sending data: {data} to the element: {locator}")
    public static void sendData(By locator, String data) {
        Waits.waitforElementToBeVisible(locator); // explicit wait
        findElement(locator).sendKeys(data); // send data to element
        LogsUtils.info("Data entered: ", data, " in the field: ", locator.toString()); // logging for console and report
    }

    //method to clear data in element
    public static void clearData(By locator) {
        Waits.waitforElementToBeVisible(locator); // explicit wait
        findElement(locator).clear(); // send data to element
    }

    //method for get text from element
    @Step("Getting text from the element: {locator}")
    public static String getData(By locator) {
        Waits.waitforElementToBeVisible(locator); // explicit wait
        LogsUtils.info("Getting text from the element: ", locator.toString(), " Text: ", findElement(locator).getText()); // logging for console and report
        return findElement(locator).getText();
    }

    //method to find element
    public static WebElement findElement(By locator) {
        Waits.waitforElementToBeVisible(locator); // explicit wait
        LogsUtils.info("Finding element: ", locator.toString());  // logging for console and report
        return BaseDriver.getDriver().findElement(locator);
    }

    //method to scroll to specific element
    @Step("Scrolling to the element: {0}")
    public static void scrollToElement(By locator) {
        LogsUtils.info("Scrolling to the element: ", locator.toString());  // logging for console and report
        ((JavascriptExecutor) BaseDriver.getDriver()).  // using java scriptexecuter to scroll to element
                executeScript("arguments[0].scrollIntoView(true);", findElement(locator));
    }

    // method for clicking on element
    @Step("Clicking on the element: {locator}")
    public static void clickElement(By locator) {
        Waits.waitforElementToBeClickable(locator); // explicit wait
        findElement(locator).click(); // click on element
        LogsUtils.info("Clicked on the element: ", locator.toString()); // logging for console and report
    }

    // method to find all element as a list of web elements
    public static List<WebElement> findElements(By locator) {
        Waits.waitforVisibilityeOfAllElements(locator);
        return BaseDriver.getDriver().findElements(locator);
    }

    // method to get html source text from specific element
    public static String getHtmlSourceTextFromElement(By locator) {
        Waits.waitforElementToBeVisible(locator);
        LogsUtils.info("Getting html source text from specific element: ", locator.toString());
        return findElement(locator).getAttribute("innerHTML");
    }

    public static void select_from_dropDown_List(By locator , String VisibleText ){
        Waits.waitforElementToBeVisible(locator);
        WebElement dropdownElement = findElement(locator);
        Select select = new Select(dropdownElement);
        select.selectByVisibleText(VisibleText);

    }

    public static String get_First_Selection_from_dropDown_List(By locator ){
        WebElement dropdownElement = findElement(locator);
        Select select = new Select(dropdownElement);
        return select.getFirstSelectedOption().getText();
    }

    public static String get_text_from_input_field(By locator ){
        Waits.waitforElementToBeVisible(locator); // explicit wait
        return findElement(locator).getAttribute("value");
    }

    public static void pressEnter()  {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void insertText(String text)  {

        try {
            Robot robot = new Robot();
            for (char c : text.toCharArray()) {
                int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
                robot.keyPress(keyCode);
                robot.keyRelease(keyCode);
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void checkCheckbox(By locator) {
        WebElement checkbox = findElement(locator);
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public static void unCheckCheckbox(By locator) {
        WebElement checkbox = findElement(locator);
        if (checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public static String randomString3() {
        return org.apache.commons.lang3.RandomStringUtils.randomAlphabetic(3);
    }

    public static void acceptAlert() {
        LogsUtils.info("Waiting for Alert to be present");
        WebDriverWait wait = new WebDriverWait(BaseDriver.getDriver(), Duration.ofSeconds(5));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
        LogsUtils.info("alert is accepted");
    }

    public static String getAlertText() {
        LogsUtils.info("Waiting for Alert to be present");
        WebDriverWait wait = new WebDriverWait(BaseDriver.getDriver(), Duration.ofSeconds(5));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        LogsUtils.info("Getting text from the Alert: ",alert.getText());
        return alert.getText();
    }

    public static void switchToFrame(By iframeLocator) {
        WebDriverWait wait = new WebDriverWait(BaseDriver.getDriver(), Duration.ofSeconds(10));
        WebElement frame = wait.until(ExpectedConditions.visibilityOfElementLocated(iframeLocator));
        BaseDriver.getDriver().switchTo().frame(frame);
    }



}