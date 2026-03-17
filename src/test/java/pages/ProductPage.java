package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {

    WebDriver driver;
    WebDriverWait wait;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    By primerProducto = By.xpath("(//h2[@class='h3 product-title']/a)[1]");
    By inputCantidad = By.id("quantity_wanted");
    By btnAgregar = By.xpath("//button[@data-button-action='add-to-cart']");
    By modalConfirmacion = By.id("blockcart-modal");

    By mensajeProductoAgregado = By.id("myModalLabel");
    By precioUnitarioPopup = By.xpath("//div[@id='blockcart-modal']//p[@class='product-price']");
    By cantidadPopup = By.xpath("//div[@id='blockcart-modal']//span[@class='product-quantity']");
    By totalPopup = By.xpath("//div[@id='blockcart-modal']//p[@class='product-total']//span[@class='value']");

    public void seleccionarPrimerProducto() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(primerProducto));
        driver.findElement(primerProducto).click();
    }

    public void cambiarCantidad(String cantidad) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputCantidad));

        var campo = driver.findElement(inputCantidad);
        campo.click();
        campo.sendKeys(Keys.CONTROL + "a");
        campo.sendKeys(Keys.DELETE);
        campo.sendKeys(cantidad);

        System.out.println("Cantidad actual input: " + campo.getAttribute("value"));
    }

    public void agregarAlCarrito() {
        wait.until(ExpectedConditions.elementToBeClickable(btnAgregar)).click();
    }

    public boolean validarProductoAgregado() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(modalConfirmacion)).isDisplayed();
    }

    public String obtenerMensajePopup() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(mensajeProductoAgregado)).getText();
    }

    public double obtenerPrecioUnitarioPopup() {
        String texto = wait.until(ExpectedConditions.visibilityOfElementLocated(precioUnitarioPopup)).getText();
        return limpiarPrecio(texto);
    }

    public int obtenerCantidadPopup() {
        String texto = wait.until(ExpectedConditions.visibilityOfElementLocated(cantidadPopup)).getText();
        texto = texto.replace("Quantity:", "").trim();
        return Integer.parseInt(texto);
    }

    public double obtenerTotalPopup() {
        String texto = wait.until(ExpectedConditions.visibilityOfElementLocated(totalPopup)).getText();
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