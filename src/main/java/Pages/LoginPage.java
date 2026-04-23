package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utils.BrowserActions;
import utils.ElementActions;
import utils.Validations;
import utils.Waits;
import utils.data_Reader.PropertiesUtils;
import utils.report.LogsUtils;

public class LoginPage {

    // locators
    private By usernameField = By.id("UserName");
    private By passwordField = By.id("Password");
    private By loginButton = By.id("js-login-btn");
    private By warnningMsg = By.cssSelector(".alert");


    // actions on locators

    @Step("Navigate to the login page")
    public void navigateToLoginPage() {
        BrowserActions.navigateToURL(PropertiesUtils.getBaseUrl());
    }

    @Step("Enter username: {0}")
    public void enterUserName(String username) {
        ElementActions.sendData(usernameField, username);
    }

    @Step("Enter password: {0}")
    public void enterPassword(String password) {
        ElementActions.sendData(passwordField, password);
    }

    @Step("Click login button")
    public void clickLoginButton() {
        ElementActions.clickElement(loginButton);
    }

    @Step("Get error message")
    public String getErrorMSG() {
        return ElementActions.getData(warnningMsg);
    }

    @Step("login credentials")
    public void login(String username, String password) {
        enterUserName(username);
        enterPassword(password);
        clickLoginButton();
    }


    //validation

    @Step("Assert successful login")
    public void assert_Successful_Login() {
        Waits.waitForPageToLoad();
        Validations.validateEquals(BrowserActions.getPageURL(),PropertiesUtils.getPropertyValue("HomePageUrl"));
        LogsUtils.info("Logged in Successfully");
    }

}
