import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import cucumber.api.testng.TestNGCucumberRunner;
import cucumber.api.testng.CucumberFeatureWrapper;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;



@CucumberOptions(
        strict = true,
        dryRun = false,
        features = "src/test/resources/features",
        glue = {"configurations", "stepsdefs"},
        tags = {},
        plugin = {
                "pretty",
                "json:target/allure-results/AllureTestReport.json",
        })

public class TestRunner extends AbstractTestNGCucumberTests {

    private TestNGCucumberRunner testNGCucumberRunner;
    private String featureName;

    @BeforeClass(alwaysRun = true)
    @Parameters(value={"browser"})
    public void setupTest (@Optional("chrome")String browser) {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browser);
    }
/*
    @BeforeMethod
    public void beforeMethod(Object[] params) {
        CucumberFeatureWrapper cucumberFeature = (CucumberFeatureWrapper) params[0];
        featureName = cucumberFeature.getCucumberFeature().getGherkinFeature().getName();
    }

    @Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
    public void feature(CucumberFeatureWrapper cucumberFeature) {
        testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }

 */

    @BeforeClass(alwaysRun = true)
    public void setUpClass() throws Exception {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
    public void feature(CucumberFeatureWrapper cucumberFeature) {
        testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }
    @DataProvider(parallel = true)
    public Object[][] features() {
            return testNGCucumberRunner.provideFeatures();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
            testNGCucumberRunner.finish();
    }

}
