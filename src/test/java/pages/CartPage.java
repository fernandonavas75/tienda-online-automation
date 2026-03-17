package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {

    WebDriver driver;
    WebDriverWait wait;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    By btnProceedToCheckout = By.xpath("//a[contains(@class,'btn-primary') and contains(.,'Proceed to checkout')]");
    By tituloCarrito = By.xpath("//h1[contains(.,'Shopping Cart')]");
    By precioUnitarioCarrito = By.xpath("(//span[@class='price'])[1]");
    By subtotalCarrito = By.xpath("//div[@id='cart-subtotal-products']//span[@class='value']");
    By cantidadCarrito = By.xpath("//input[contains(@class,'js-cart-line-product-quantity')]");

    public void finalizarCompra() {
        wait.until(ExpectedConditions.elementToBeClickable(btnProceedToCheckout)).click();
    }

    public boolean validarTituloCarrito() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(tituloCarrito)).isDisplayed();
    }

    public double obtenerPrecioUnitarioCarrito() {
        String texto = wait.until(ExpectedConditions.visibilityOfElementLocated(precioUnitarioCarrito)).getText();
        return limpiarPrecio(texto);
    }

    public int obtenerCantidadCarrito() {
        String valor = wait.until(ExpectedConditions.visibilityOfElementLocated(cantidadCarrito))
                .getAttribute("value");
        return Integer.parseInt(valor);
    }

    public double obtenerSubtotalCarrito() {
        String texto = wait.until(ExpectedConditions.visibilityOfElementLocated(subtotalCarrito)).getText();
        return limpiarPrecio(texto);
    }

    private double limpiarPrecio(String texto) {
        texto = texto.replace("PEN", "")
                     .replace("$", "")
                     .replace(",", "")
                     .trim();
        return Double.parseDouble(texto);
    }
}