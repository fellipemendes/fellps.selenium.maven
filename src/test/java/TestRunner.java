import cucumber.api.CucumberOptions;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.PickleEventWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import drivermanagement.DriverFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static drivermanagement.DriverFactory.instantiateDriverObject;


@CucumberOptions(
        dryRun = false,
        features = "src/test/resources/features",
        glue = {"configurations", "stepsdefs", "drivermanagement"},
        tags = {},
        plugin = {
                "pretty",
                "json:target/allure-results/AllureTestReport.json",
        })

public class TestRunner {
    /*
    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass(alwaysRun = true)
    public void setUpClass() throws Exception {
        System.out.println("--------BeforeClass 1-----------------------");
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
        System.out.println("--------BeforeClass 2-----------------------");
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters(value={"browser"})
    public void setupTest (@Optional("chrome")String browser) {
        System.out.println("--------PASSOU BEFORE METHOD 1-----------------------");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        System.out.println("--------PASSOU BEFORE METHOD 2-----------------------");
        capabilities.setCapability("browserName", browser);
        System.out.println("--------PASSOU BEFORE METHOD 3-----------------------");

        //hooks.getInstance().getDriver();
        System.out.println("--------PASSOU BEFORE METHOD 4-----------------------");
    }

    @Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
    public void feature(CucumberFeatureWrapper cucumberFeature) {
        System.out.println("--------@Test 1-----------------------");
        try {
            testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
        }catch (Exception e) {
            System.out.println("--------ERRO @Test-----------" + e.getStackTrace());
        }

    }
    @DataProvider(parallel = true)
    public Object[][] features() {
        System.out.println(testNGCucumberRunner.provideFeatures());
            return testNGCucumberRunner.provideFeatures();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
            testNGCucumberRunner.finish();
    }

     */

    private TestNGCucumberRunner testNGCucumberRunner;

    @Before
    public void beforeSuite() {
        instantiateDriverObject();
    }

    @After
    public void afterSuite() {
        DriverFactory.closeAllDriverObjects();
    }


    @BeforeClass(alwaysRun = true)
    public void setUpClass() {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
    public void scenarios(PickleEventWrapper pickleEvent, CucumberFeatureWrapper cucumberFeature) throws Throwable {
        testNGCucumberRunner.runScenario(pickleEvent.getPickleEvent());


    }

    @DataProvider
    public Object[][] scenarios() {
        return testNGCucumberRunner.provideScenarios();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        testNGCucumberRunner.finish();
    }

}
