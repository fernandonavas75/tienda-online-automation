package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;

public class RegisterPage {

    WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    By btnSignUp = By.id("signin2"); // botón Sign up
    By inputUser = By.id("sign-username");
    By inputPass = By.id("sign-password");
    By btnRegister = By.xpath("//button[text()='Sign up']");

    public void abrirRegistro() {
        driver.findElement(btnSignUp).click();
    }

    public void ingresarUsuario(String usuario) {
        driver.findElement(inputUser).sendKeys(usuario);
    }

    public void ingresarPassword(String password) {
        driver.findElement(inputPass).sendKeys(password);
    }

    public void clickRegistrar() {
        driver.findElement(btnRegister).click();
    }
}