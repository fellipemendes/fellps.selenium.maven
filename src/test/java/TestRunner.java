import cucumber.api.CucumberOptions;
import cucumber.api.testng.TestNGCucumberRunner;
import cucumber.api.testng.CucumberFeatureWrapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;

@CucumberOptions(
        dryRun = false,
        features = "src/test/resources/features",
        glue = {"configurations", "stepsdefs"},
        tags = {},
        plugin = {
                "pretty",
                "json:target/allure-results/AllureTestReport.json",
        })

public class TestRunner {

        private TestNGCucumberRunner testNGCucumberRunner;

/*
        @BeforeClass
        @Parameters("browser")
        public void preCondicao(@Optional("chrome") String browser){
                //WebDriverManager.chromedriver().setup();
        }

*/


    @BeforeClass(alwaysRun = true)
    @Parameters(value={"browser"})
    public void setupTest (String browser) throws MalformedURLException {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
        DesiredCapabilities capabilities = new DesiredCapabilities();

        //Set BrowserName
        capabilities.setCapability("browserName", browser);
        System.out.println("-----------1");
        //Set Browser to ThreadLocalMap
        //driver.set(new RemoteWebDriver(new URL("http://192.168.99.100:4444/wd/hub"), capabilities));
    }


/*
        @BeforeClass(alwaysRun = true)
        public void setUpClass() throws Exception {
            //WebDriverManager.chromedriver().setup();
            testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
        }
*/
        @Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
        public void feature(CucumberFeatureWrapper cucumberFeature) {
                testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
        }

        @DataProvider
        public Object[][] features() {
                return testNGCucumberRunner.provideFeatures();
        }

        @AfterClass(alwaysRun = true)
        public void tearDownClass() throws Exception {
                testNGCucumberRunner.finish();
        }
}
