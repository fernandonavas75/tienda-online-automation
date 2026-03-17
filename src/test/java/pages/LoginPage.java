package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    By btnLogin = By.cssSelector("div.user-info a");
    By inputUser = By.id("field-email");
    By inputPass = By.id("field-password");
    By btnSubmit = By.id("submit-login");

    public void abrirLogin() {
        WebElement botonLogin = wait.until(
                ExpectedConditions.elementToBeClickable(btnLogin)
        );
        botonLogin.click();
    }

    public void ingresarUsuario(String usuario) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputUser)).sendKeys(usuario);
    }

    public void ingresarPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputPass)).sendKeys(password);
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(btnSubmit)).click();
    }
}