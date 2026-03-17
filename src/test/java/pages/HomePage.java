package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    WebDriver driver;
    WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    By categoriaClothes = By.xpath("//a[contains(@href,'/3-clothes')]");
    By tituloClothes = By.xpath("//h1[contains(translate(text(),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'CLOTHES')]");
    By subcategoriaMen = By.xpath("//div[@id='left-column']//a[contains(@href,'/4-men') and normalize-space()='Men']");
    By tituloMen = By.xpath("//h1[contains(translate(text(),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'MEN')]");

    public void irAClothes() {
        wait.until(ExpectedConditions.elementToBeClickable(categoriaClothes)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(tituloClothes));
    }

    public void irAMen() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(subcategoriaMen));
        wait.until(ExpectedConditions.elementToBeClickable(subcategoriaMen)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(tituloMen));
    }

    public boolean categoriaExiste(String categoria) {
    try {
        By categoriaDinamica = By.xpath("//a[normalize-space()='" + categoria.toUpperCase() + "' or normalize-space()='" + categoria + "']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(categoriaDinamica));
        return driver.findElement(categoriaDinamica).isDisplayed();
    } catch (Exception e) {
        return false;
    }
}

public void intentarCategoriaInexistente(String categoria) {
    if (!categoriaExiste(categoria)) {
        System.out.println("La categoría no existe: " + categoria);
    }
}
}