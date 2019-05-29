package configurations;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class RunCukesByCompositionBase {

    @BeforeClass
    public void beforeClass() {
        // do expensive setup
    }

    @BeforeMethod
    public void beforeMethod() {
        // do expensive setup
    }
}
