package stepsdefs;

import configurations.hooks;
import configurations.utils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Description;
import io.qameta.allure.Story;

@Epic("AGoogle")
@Feature("Google's Search")
public class basic{

    static WebDriver driver = hooks.getInstance().getDriver();
    static utils oUtils = new utils();
    static WebDriverWait wait = new WebDriverWait(driver, 45);

    @Step
    @Description("Googleeeeee")
    @Given("^I access Google$")
    public void i_access_Google() throws Throwable {
        System.out.println("-----------9");
        driver.navigate().to("https://www.google.com/");
        System.out.println("-----------9.1");
        oUtils.waitVisibilityOfElementLocated("name", "q");
        System.out.println("-----------9.2");
        hooks.capture("google's search page");
        System.out.println("-----------9.3");
    }

    @Step
    @Then("^The main page will show up$")
    public void the_main_page_will_show_up() throws Throwable {
        System.out.println("-----------9.4");
        driver.findElement(By.name("q")).sendKeys("SELENIUM");
        System.out.println("-----------9.5");
        driver.findElement(By.name("q")).click();
        Thread.sleep(2000);
    }
}
