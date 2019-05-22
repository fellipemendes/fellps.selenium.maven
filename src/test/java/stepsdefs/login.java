package stepsdefs;

import configurations.hooks;
import configurations.utils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import io.qameta.allure.Step;
import static org.assertj.core.api.Assertions.*;

@Test
public class login {

    static WebDriver driver = hooks.getInstance().getDriver();
    static utils oUtils = new utils();
    static WebDriverWait wait = new WebDriverWait(driver, 45);

    @Step
    @Given("^I access página de login do Gmail$")
    public void i_access_página_de_login_do_Gmail() throws Throwable {
        driver.navigate().to("https://accounts.google.com/AccountChooser?service=mail&continue=https://mail.google.com/mail/");
        hooks.capture("Página de Login do Gmail");
    }

    @Step
    @Given("^realizei login no Gmail com usuário incorreto$")
    public void realizei_login_no_Gmail_com_usuário_incorreto() throws Throwable {
        oUtils.waitVisibilityOfElementLocated("id", "identifierId");
        driver.findElement(By.id("identifierId")).sendKeys("123");
        driver.findElement(By.xpath("//*[@id='identifierNext']/content/span")).click();
        Thread.sleep(5000);
        //oUtils.waitPresenceOfElementLocated("name", "password");
        driver.findElement(By.name("password")).sendKeys("123");
        driver.findElement(By.xpath("//*[@id='passwordNext']/content/span")).click();
        hooks.capture("Dados Login");
    }

    @Step
    @Then("^deverá apresentar erro de usuário incorreto$")
    public void deverá_apresentar_erro_de_usuário_incorreto() throws Throwable {
        oUtils.waitVisibilityOfElementLocated("xpath", "//*[@id='password']/div[2]/div[2]/div");
        Thread.sleep(6000);
        WebElement retornoErro = driver.findElement(By.xpath("//*[@id='password']/div[2]/div[2]/div"));
        assertThat(retornoErro.getText()).isEqualTo("Senha incorreta. Tente novamente ou clique em \"Esqueceu a senha?\" para redefini-la.");
        hooks.capture("Dados Login incorreto");
    }

}
