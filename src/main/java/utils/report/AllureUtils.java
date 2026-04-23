package utils.report;

import io.qameta.allure.Allure;
import utils.Helper.FilesUtils;
import utils.Helper.TerminalUtils;
import utils.data_Reader.PropertiesUtils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AllureUtils {

    public static final String ALLURE_RESULTS_PATH = "test-outputs/allure-results";
    static String REPORT_PATH = "test-outputs/allure-report";
    static String USER_HOME = System.getProperty("user.home");

    static String ALLURE_PATH = USER_HOME + File.separator + ".m2" + File.separator + "repository"
            + File.separator + "allure-2.37.0" + File.separator
            + "bin" + File.separator + "allure";

    private AllureUtils() {
        super();
    }


    public static void generateAllureReport() {
        //allure, generate, //path ,-o ,//path ,--single-file
        if (PropertiesUtils.getPropertyValue("os.name").toLowerCase().contains("win")) //windows 11
        {
            String WIN = ALLURE_PATH + ".bat";
            TerminalUtils.executeCommand(WIN, "generate", ALLURE_RESULTS_PATH, "-o", REPORT_PATH, "--clean", "--single-file");
            LogsUtils.info("Allure report generated successfully on Windows");
        } else {
            TerminalUtils.executeCommand(ALLURE_PATH, "generate", ALLURE_RESULTS_PATH, "-o", REPORT_PATH, "--clean", "--single-file");
            LogsUtils.info("Allure report generated successfully on " + PropertiesUtils.getPropertyValue("os.name"));
        }


    }

    public static String renameReport() {
        String timeStamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss"));
        File newName = new File("Report_" + timeStamp + ".html");
        File oldName = new File(REPORT_PATH + File.separator + "index.html");
        FilesUtils.renameFile(oldName, newName);
        return newName.getName();
    }

    public static void openReport(String fileName) {
        //allure serve //path
        String reportPath = REPORT_PATH + File.separator + fileName;
        if (PropertiesUtils.getPropertyValue("openAllureAutomatically").equalsIgnoreCase("true")) {
            if (PropertiesUtils.getPropertyValue("os.name").toLowerCase().contains("win")) //windows 11
            {
                TerminalUtils.executeCommand("cmd.exe", "/c", "start", reportPath);
            } else //linux
            {
                TerminalUtils.executeCommand("open", reportPath);
            }
        }
    }


    // method to attach logs to allure report
    public static void attachLogsToAllure() {
        try {
            File logFile = FilesUtils.getLatestFile(LogsUtils.LOGS_PATH); // get the latest file in logs
            if (!logFile.exists()) {
                LogsUtils.warn("Log file does not exist:" + LogsUtils.LOGS_PATH);
                return;
            }
            Allure.addAttachment("Logs.log", Files.readString(Path.of(logFile.getPath()))); // add logs to allure report
            LogsUtils.info("Logs attached to Allure report");
        } catch (Exception e) {
            LogsUtils.error("Failed to attach logs to allure report:" + e.getMessage());
        }
    }

    // method to attach screenshots to allure report
    public static void attachScreenshotsToAllure(String screenshotName, String screenshotPath) {
        try {
            Allure.addAttachment(screenshotName, Files.newInputStream(Path.of(screenshotPath))); // add screenshots to allure report
            LogsUtils.info("Screenshot attached to Allure report");
        } catch (Exception e) {
            LogsUtils.error("Failed to attach screenshot to allure report:" + e.getMessage());
        }
    }



}
