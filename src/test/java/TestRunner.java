import cucumber.api.CucumberOptions;
import cucumber.api.testng.TestNGCucumberRunner;
import cucumber.api.testng.CucumberFeatureWrapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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
            WebDriverManager.chromedriver().setup();
            testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
        }

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
