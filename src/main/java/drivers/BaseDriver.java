package drivers;

import io.qameta.allure.Step;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import utils.data_Reader.PropertiesUtils;
import utils.report.LogsUtils;

import java.util.HashMap;

import static org.testng.Assert.fail;

public class BaseDriver {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    // method to create the driver with the desired driver chrome or firefox or edge
    @Step("create driver ")
    public static void initializeDriver(String browser) {
        // 1️⃣ If TestNG XML provides browser → use it
        // 2️⃣ Else fallback to environment.properties
        String browserName = (browser != null)
                ? browser
                : PropertiesUtils.getPropertyValue("browserType");

        WebDriver driver =getBrowser(browserName); // initialize driver with browser
        LogsUtils.info("Driver created on: ", browserName);
        setDriver(driver); // set the initialized driver to BaseDriver.driver

    }

    // method to get current running driver
    public static WebDriver getDriver() {
        if (driverThreadLocal.get() == null) {
            LogsUtils.info("Driver is null");
            fail("Driver is null");
        }
        return driverThreadLocal.get();
    }
    // method set driver to BaseDriver.driver
    public static void setDriver(WebDriver driver) {
        driverThreadLocal.set(driver);
    }


    // Initialize WebDriver with browserName you just need to pass browser name
    public static WebDriver getBrowser(String browserName) {

        switch (browserName.toLowerCase()) {
            case "chrome":
                return new ChromeDriver(getChromeOptions());

            case "firefox":

                return  new FirefoxDriver(getFirefoxOptions());

            case "edge":
                return new EdgeDriver(getEdgeOptions());

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }
    }

    // chrome option or setting to run the browser with
    public static ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("--incognito");
        chromeOptions.addArguments("--disable-popup-blocking");
        chromeOptions.setAcceptInsecureCerts(true);
        chromeOptions.addArguments("--start-maximized"); // full screen
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--disable-save-password-bubble");
        chromeOptions.addArguments("--disable-gpu");

        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addArguments("--unsafely-treat-insecure-origin-as-secure=http://10.1.30.233:5000/");
        chromeOptions.addArguments("--disable-features=InsecureDownloadWarnings");
        chromeOptions.addArguments("--allow-running-insecure-content");
        chromeOptions.addArguments("--ignore-certificate-errors");
        chromeOptions.addArguments("--disable-web-security");

        HashMap<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", "D:\\Automation Projects\\Egypt_DL\\Downloads");
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.directory_upgrade", true);
        prefs.put("profile.default_content_setting_values.automatic_downloads", 1);
        prefs.put("profile.default_content_setting_values.Insecure_content", 1);
        prefs.put("safebrowsing.enabled", true);
        prefs.put("safebrowsing.disable_download_protection", true);
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        prefs.put("autofill.profile_enabled", false);
        prefs.put("autofill.credit_card_enabled", false);
        chromeOptions.setExperimentalOption("prefs", prefs);
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        if (!PropertiesUtils.getPropertyValue("executionType").equalsIgnoreCase("local")) {
            chromeOptions.addArguments("--headless=new");
            chromeOptions.addArguments("--window-size=1920,1080"); // headless mode get from properties file
        }
        return chromeOptions;
    }

    // firefox browser option or setting to run the browser with
    public static FirefoxOptions getFirefoxOptions() {
        FirefoxProfile profile = new FirefoxProfile();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--start-maximized");
        firefoxOptions.addArguments("--disable-extensions");
        firefoxOptions.addArguments("--disable-infobars");
        firefoxOptions.addArguments("--disable-notifications");

        profile.setPreference("dom.webnotifications.enabled", false);
        profile.setPreference("dom.disable_open_during_load", false);
        profile.setPreference("signon.rememberSignons", false);
        profile.setPreference("signon.autofillForms", false);
        profile.setPreference("extensions.formautofill.available", "off");
        profile.setPreference("extensions.formautofill.creditCards.enabled", false);
        profile.setPreference("security.mixed_content.block_active_content", false);
        profile.setPreference("security.mixed_content.block_display_content", false);
        profile.setPreference("browser.safebrowsing.downloads.enabled", false);
        profile.setPreference("browser.safebrowsing.malware.enabled", false);
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.dir", "D:\\Automation Projects\\Egypt_DL\\Downloads");
        profile.setPreference("browser.download.useDownloadDir", true);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
                "application/pdf,application/octet-stream,application/vnd.ms-excel");
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        firefoxOptions.setProfile(profile);
        firefoxOptions.setAcceptInsecureCerts(true);
        firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        if (!PropertiesUtils.getPropertyValue("executionType").equalsIgnoreCase("local")) {
            firefoxOptions.addArguments("--headless");
            firefoxOptions.addArguments("--window-size=1920,1080");// headless mode get from properties file
        }
        return firefoxOptions;
    }

    // Edge browser option or setting to run the browser with
    public static EdgeOptions getEdgeOptions() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--disable-popup-blocking");
        edgeOptions.addArguments("--start-maximized");
        edgeOptions.addArguments("--disable-extensions");
        edgeOptions.addArguments("--disable-infobars");
        edgeOptions.addArguments("--disable-notifications");
        edgeOptions.addArguments("--disable-gpu");
        edgeOptions.addArguments("--remote-allow-origins=*");
        edgeOptions.addArguments("--unsafely-treat-insecure-origin-as-secure=http://10.1.30.233:5000/");
        edgeOptions.addArguments("--unsafely-treat-insecure-origin-as-secure=http://10.1.30.233:5000/Monitoring/Reports/PrintedJobsReports");
        edgeOptions.addArguments("--disable-features=InsecureDownloadWarnings");
        edgeOptions.addArguments("--allow-running-insecure-content");
        edgeOptions.addArguments("--ignore-certificate-errors");
        edgeOptions.addArguments("--disable-web-security");
        edgeOptions.setAcceptInsecureCerts(true);

        HashMap<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", "D:\\Automation Projects\\Egypt_DL\\Downloads");
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.directory_upgrade", true);
        prefs.put("profile.default_content_setting_values.automatic_downloads", 1);
        prefs.put("profile.default_content_setting_values.Insecure_content", 1);
        prefs.put("safebrowsing.enabled", true);
        prefs.put("safebrowsing.disable_download_protection", true);
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        prefs.put("autofill.profile_enabled", false);
        prefs.put("autofill.credit_card_enabled", false);

        edgeOptions.setExperimentalOption("prefs", prefs);

        edgeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        if (!PropertiesUtils.getPropertyValue("executionType").equalsIgnoreCase("local")) {
            edgeOptions.addArguments("--headless");
            edgeOptions.addArguments("--window-size=1920,1080"); // headless mode get from properties file
        }
        return edgeOptions;
    }



}

