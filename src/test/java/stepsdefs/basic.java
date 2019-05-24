package stepsdefs;

import configurations.hooks;
import configurations.utils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import drivermanagement.DriverFactory;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

@Epic("AGoogle")
@Feature("Google's Search")
public class basic{

    static WebDriver driver = DriverFactory.getDriver();
    static utils oUtils = new utils();
    static WebDriverWait wait = new WebDriverWait(driver, 45);

    @Step
    @Description("Googleeeeee")
    @Given("^I access Google$")
    public void i_access_Google() {
        driver.navigate().to("https://www.google.com/");
        oUtils.waitVisibilityOfElementLocated("name", "q");
        hooks.capture("google's search page");
    }

    @Step
    @Then("^The main page will show up$")
    public void the_main_page_will_show_up() throws Throwable {
        Thread.sleep(2000);
        hooks.capture("google's search page");
    }

    @Step
    @Given("^Search Palmeiras$")
    public void search_Palmeiras() throws Throwable {
        Thread.sleep(2000);
        driver.findElement(By.name("q")).sendKeys("PALMEIRAS");
        driver.findElement(By.name("q")).click();
        hooks.capture("Palmeiras search result");
    }
}
