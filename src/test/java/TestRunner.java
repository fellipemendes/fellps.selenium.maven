import cucumber.api.CucumberOptions;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.PickleEventWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import cucumber.runtime.model.CucumberFeature;
import drivermanagement.DriverFactory;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import static drivermanagement.DriverFactory.instantiateDriverObject;

//
//@CucumberOptions(
//        dryRun = false,
//        features = "src/test/resources/features",
//        glue = {"configurations", "stepsdefs"},
//        tags = {},
//        plugin = {
//                "pretty",
//                "json:target/allure-results/AllureTestReport.json",
//        })

public class TestRunner {

//    private TestNGCucumberRunner testNGCucumberRunner;
//
//    @BeforeClass(alwaysRun = true)
//    public void setUpClass() throws Exception {
//        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
//    }
//
//    @Test(groups = "cucumber scenarios", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
//    public void scenario(PickleEventWrapper pickleEvent, CucumberFeatureWrapper cucumberFeature) throws Throwable{
//        testNGCucumberRunner.runScenario(pickleEvent.getPickleEvent());
//    }
//    @DataProvider
//    public Object[][] scenarios() {
//        return testNGCucumberRunner.provideScenarios();
//    }
//
//    @AfterClass(alwaysRun = true)
//    public void tearDownClass() throws Exception {
//        testNGCucumberRunner.finish();
//    }

}
