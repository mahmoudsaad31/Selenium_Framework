package utils.report;

import drivers.BaseDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;

public class ScreenshotsUtils {

    public static final String SCREENSHOTS_PATH = "test-outputs/screenshots/";


    private ScreenshotsUtils() {
        super();
    }

    // method to take screenshots
    public static void takeScreenshot(String screenshotName) {
        //code
        try {
            File screenshot = ((TakesScreenshot) BaseDriver.getDriver()).getScreenshotAs(OutputType.FILE);
            File screenshotFile = new File(SCREENSHOTS_PATH + screenshotName + ".png"); // save screenshot
            FileUtils.copyFile(screenshot, screenshotFile); // copy from source to destination
            AllureUtils.attachScreenshotsToAllure(screenshotName, screenshotFile.getPath());  // attach screenshot to allure report
        } catch (Exception e) {
            LogsUtils.error("Failed to take screenshot: " + e.getMessage());
        }

    }
}
