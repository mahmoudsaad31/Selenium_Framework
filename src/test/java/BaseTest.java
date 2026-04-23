import drivers.BaseDriver;
import io.qameta.allure.Step;
import listeners.TestNGListeners;
import org.testng.annotations.*;
import utils.BrowserActions;
import utils.data_Reader.JsonUtils;


@Listeners(TestNGListeners.class)
public class BaseTest {

    public String getTestData(String data) {
        JsonUtils testData = new JsonUtils("test_data");
        return testData.getJsonData(data);
    }

    //configuration

    @BeforeSuite(alwaysRun = true)
//    @BeforeClass(alwaysRun = true)
    @Step("Setup the environment")
    @Parameters("browser")
    public void setup(@Optional String browser) {
        BaseDriver.initializeDriver(browser);
    }


    @AfterSuite
//    @AfterClass(alwaysRun = true)
    @Step("shut down the environment")
    public void tearDown() {
       BrowserActions.closeBrowser();
    }
}
