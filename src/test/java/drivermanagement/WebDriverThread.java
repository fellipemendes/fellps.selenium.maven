package drivermanagement;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;

import static drivermanagement.DriverType.FIREFOX;

public class WebDriverThread {
    private WebDriver webDriver;
    private DriverType selectedDriverType;

    private final DriverType defaultDriverType = FIREFOX;
    private final String browser = System.getProperty("browser").toUpperCase();
    private final String operatingSystem = System.getProperty("os.name").toUpperCase();
    private final String systemArchitecture = System.getProperty("os.arch").toUpperCase();

    public WebDriver getAWebDriver(){
        if (webDriver == null) {
            selectedDriverType = determineEffectiveDriverType();
            MutableCapabilities mutableCapabilities = selectedDriverType.getDisiredCapabilties();
            instantiateWebDriver(mutableCapabilities);
        }
        return webDriver;
    }

    private void instantiateWebDriver(MutableCapabilities mutableCapabilities) {
        System.out.println("Current Operating System: " + operatingSystem);
        System.out.println("Current Architecture: " + systemArchitecture);
        System.out.println("Current Browser Selection: " + selectedDriverType);
        webDriver = selectedDriverType.getWebDriverObject(mutableCapabilities);
    }

    private DriverType determineEffectiveDriverType() {
        DriverType driverType = defaultDriverType;
        try{
            driverType = DriverType.valueOf(browser);
        } catch (IllegalArgumentException ignored){
            System.err.println("Unknown driver specified, defaulting to '" + driverType + "'....");
        } catch (NullPointerException ignored){
            System.err.println("No driver specified, defaulting to '" + driverType + "'....");
        }
        return driverType;
    }

    public void quitWebDriver(){
        if(webDriver!=null){
            webDriver.quit();
            webDriver = null;
        }
    }
}
