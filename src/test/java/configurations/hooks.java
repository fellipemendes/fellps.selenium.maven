package configurations;


import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.qameta.allure.Attachment;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import java.net.MalformedURLException;
import java.net.URL;

public class hooks {
    //public static WebDriver driver;

    static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>()
    {
        @Override
        public RemoteWebDriver initialValue()
        {
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//	    	 try {
//				driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities));
//			} catch (MalformedURLException e) {
//				e.printStackTrace();
//			}
            ChromeOptions options = new ChromeOptions();
            //options.addArguments("--headless", "window-size=1366,768", "--no-sandbox");
            options.addArguments("window-size=1366,768");

            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
            return new ChromeDriver(options); // can be replaced with other browser drivers
            //return new InternetExplorerDriver();
            //return new ChromeDriver(); // can be replaced with other browser drivers
        }
    };

    public hooks()
    {
        //Hooks.driver = (ThreadLocal<WebDriver>) driver;
        //Do-nothing..Do not allow to initialize this class from outside
    }
    public static hooks instance = new hooks();

    public static hooks getInstance()
    {
        return instance;
    }

    public WebDriver getDriver() {
        //Get driver from ThreadLocalMap
        return driver.get();
    }

    @BeforeMethod
    @Parameters(value={"browser"})
    public void setupTest (String browser) throws MalformedURLException{
        DesiredCapabilities capabilities = new DesiredCapabilities();

        //Set BrowserName
        capabilities.setCapability("browserName", browser);

        //Set Browser to ThreadLocalMap
        driver.set(new RemoteWebDriver(new URL("http://192.168.99.100:4444/wd/hub"), capabilities));
    }





    @AfterMethod
    public void tearDown() throws Exception {
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
            //WebDriverManager.chromedriver().setup();
            new Dimension(1366, 768);
        } catch (Exception e) {
            System.out.println("--------ERRO Navegador-----------" + e.getMessage());
        }
    }

    @After
    public void TearDownTest(Scenario scenario) throws InterruptedException {
        if (scenario.isFailed()) {
            try {
                capture("Falha");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //driver.quit();
    }

    public static byte[] screenShot() throws InterruptedException {
        byte[] screenshot;
        screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        return screenshot;
    }

    @Attachment(value = "{namePrint}", type="image/jpg")
    public static byte[] capture(String namePrint) throws InterruptedException {
        return screenShot();
    }
}
