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

public class hooks {
    public static WebDriver driver;

    @Before
    public void TestInitialize() {
        try {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
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
        driver.quit();
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
