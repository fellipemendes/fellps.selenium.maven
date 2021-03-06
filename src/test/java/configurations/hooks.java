package configurations;


import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import drivermanagement.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.qameta.allure.Attachment;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;

public class hooks{
    //public static WebDriver driver;
    //static WebDriver driver = DriverFactory.getDriver();
/*
    public hooks()
    {
    }
    private static hooks instance = new hooks();

    public static hooks getInstance()
    {
        return instance;
    }

    ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>()
    {
        @Override
        public RemoteWebDriver initialValue()
        {
            System.out.println("--------Override 1-----------------------");
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();

            try {
                 driver.set(new RemoteWebDriver(new URL("http://192.168.99.100:4444/wd/hub"), capabilities));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

            ChromeOptions options = new ChromeOptions();
            options.addArguments("window-size=1366,768", "--no-sandbox");
            System.out.println("--------Override 2-----------------------");
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
            return new ChromeDriver(options); // can be replaced with other browser drivers
        }
    };

    public WebDriver getDriver() {
        return driver.get();
    }



    @AfterMethod
    public void tearDown() {
        getDriver().quit();
    }

    @AfterClass
    void terminate () {
        //Remove the ThreadLocalMap element
        driver.remove();
    }

    @Before
    public void TestInitialize() {
        try {
            //hooks.getInstance().getDriver();
            System.out.println("--------PASSOU BEFORE-----------------------");
            //WebDriverManager.chromedriver().setup();
        } catch (Exception e) {
            System.out.println("--------ERRO Navegador-----------" + e.getMessage());
        }
    }

    @After
    public void TearDownTest(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                capture("Falha");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //driver.quit();
    }
*/
    public static byte[] screenShot() {
        byte[] screenshot;
        screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
        return screenshot;
    }

    @Attachment(value = "{namePrint}", type="image/jpg")
    public static byte[] capture(String namePrint) {
        return screenShot();
    }
}
