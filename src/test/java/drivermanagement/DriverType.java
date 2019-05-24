package drivermanagement;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Arrays;
import java.util.HashMap;

public enum DriverType implements DriverSetup {

    FIREFOX{
        public WebDriver getWebDriverObject(MutableCapabilities capabilities) {
            return new FirefoxDriver((FirefoxOptions) capabilities);
        }

        public MutableCapabilities getDisiredCapabilties() {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
            } else if (os.contains("mac")) {
                System.setProperty("webdriver.gecko.driver", "drivers/geckodriver");
            } else if (os.contains("linux")) {
                System.setProperty("webdriver.gecko.driver", "drivers/geckodriver-v0.22.0-linux64/geckodriver");
            }

            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.setCapability("specificationLevel", "1");
            firefoxOptions.setCapability("security.sandbox.content.level", "4");
            firefoxOptions.setAcceptInsecureCerts(true);
            firefoxOptions.addPreference("browser.download.folderList", 2);
            //firefoxOptions.addPreference("browser.download.dir", "C:\\temp123d");
            firefoxOptions.addPreference("browser.download.useDownloadDir", true);
            firefoxOptions.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
            firefoxOptions.addPreference("pdfjs.disabled", true);
            return firefoxOptions;
        }
    },
    CHROME{
        public WebDriver getWebDriverObject(MutableCapabilities capabilities) {
            return new ChromeDriver((ChromeOptions) capabilities);
        }

        public MutableCapabilities getDisiredCapabilties() {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
            } else if (os.contains("mac")) {
                System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
            } else if (os.contains("linux")) {
                System.setProperty("webdriver.chrome.driver", "drivers/chromedriver_linux64/chromedriver");
            }
            HashMap<String, Object> chromePreferences = new HashMap<String, Object>();
            chromePreferences.put("profile.password_manager_enabled", "false");
            chromePreferences.put("profile.default_content_settings.popups", 0);
            chromePreferences.put("download.prompt_for_download", "false");
            //chromePreferences.put("download.default_directory", "C:\\temp123");
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setCapability("chrome.switches", Arrays.asList("--no-default-browser-check"));
            chromeOptions.setCapability("chrome.prefs", chromePreferences);
            chromeOptions.addArguments("ignore-certificate-errors");
            chromeOptions.addArguments("test-type");
            chromeOptions.addArguments("disable-popup-blocking");
            chromeOptions.addArguments("start-maximized");
            chromeOptions.setExperimentalOption("useAutomationExtension", false);
            return chromeOptions;
        }
    }
}
