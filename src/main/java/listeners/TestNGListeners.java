package listeners;


import org.testng.*;
import utils.Helper.FilesUtils;
import utils.data_Reader.PropertiesUtils;
import utils.report.AllureUtils;
import utils.report.LogsUtils;
import utils.report.ScreenshotsUtils;

import java.io.File;


public class TestNGListeners implements ITestListener, IExecutionListener, IInvokedMethodListener {
    File allure_results = new File("test-outputs/allure-results");
    File LOGS_PATH = new File("test-outputs/Logs");
    File SCREENSHOTS_PATH = new File("test-outputs/screenshots");
    File Downloads_PATH = new File("Downloads");
    File REPORT_PATH = new File("test-outputs/allure-report");


    @Override
    public void onExecutionStart() {
        LogsUtils.info(" Test Execution Start :");
        PropertiesUtils.loadProperties();  // load all .properties files
        FilesUtils.cleanFiles(allure_results);  // remove all files in allure-results folder
        FilesUtils.cleanFiles(SCREENSHOTS_PATH); // remove all files in screenshots folder
        FilesUtils.cleanFiles(LOGS_PATH);   // remove all files in logs folder
        FilesUtils.cleanFiles(Downloads_PATH);   // remove all files in logs folder
        FilesUtils.cleanFiles(REPORT_PATH);   // remove all files in logs folder

    }

    @Override
    public void onExecutionFinish() {
        LogsUtils.info("Test Execution Finished");
        AllureUtils.generateAllureReport();
        String reportName = AllureUtils.renameReport();
        AllureUtils.openReport(reportName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogsUtils.info("Test passed :", result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogsUtils.info("Test Skipped :", result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogsUtils.info("Test Failed :", result.getName());
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            switch (testResult.getStatus()) {
                case ITestResult.SUCCESS: // take screenshot when test passed
                    ScreenshotsUtils.takeScreenshot("Passed-" + testResult.getName()); // the name of the current running test
                    break;
                case ITestResult.SKIP:   // take screenshot when test skip
                    ScreenshotsUtils.takeScreenshot("Skipped-" + testResult.getName()); // the name of the current running test
                    break;
                case ITestResult.FAILURE: // take screenshot when test fail
                    ScreenshotsUtils.takeScreenshot("Failed-" + testResult.getName()); // the name of the current running test
                    break;
            }
            AllureUtils.attachLogsToAllure(); // attach log to allure report after every test method

        }
    }


}