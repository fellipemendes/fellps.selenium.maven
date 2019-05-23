import cucumber.api.CucumberOptions;
import cucumber.api.testng.TestNGCucumberRunner;
import cucumber.api.testng.CucumberFeatureWrapper;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;



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

    @BeforeClass(alwaysRun = true)
    public void setUpClass() throws Exception {
        System.out.println("--------BeforeClass 1-----------------------");
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
        System.out.println("--------BeforeClass 2-----------------------");
    }

    @Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
    public void feature(CucumberFeatureWrapper cucumberFeature) {
        System.out.println("--------BeforeClass 1-----------------------");
        try {
            testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
        }catch (Exception e) {
            System.out.println("--------ERRO @Test-----------" + e.getMessage());
        }

    }
    @DataProvider(parallel = true)
    public Object[][] features() {
        System.out.println("--------DataProvide 1-----------------------");
            return testNGCucumberRunner.provideFeatures();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
            testNGCucumberRunner.finish();
    }




}
