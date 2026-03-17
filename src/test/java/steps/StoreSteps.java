package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.WebDriver;

import pages.HomePage;
import pages.LoginPage;
import pages.ProductPage;
import pages.RegisterPage;
import pages.CartPage;
import utils.DriverManager;

import static org.junit.Assert.assertTrue;

public class StoreSteps {

    WebDriver driver = DriverManager.getDriver();
    RegisterPage registerPage = new RegisterPage(driver);

    @Given("estoy en la página de la tienda")
    public void estoy_en_la_pagina_de_la_tienda() {
        driver.get("https://qalab.bensg.com/store");
    }

    @And("me logueo con mi usuario {string} y clave {string}")
    public void me_logueo_con_mi_usuario_y_clave(String usuario, String clave) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.abrirLogin();
        loginPage.ingresarUsuario(usuario);
        loginPage.ingresarPassword(clave);
        loginPage.clickLogin();
    }
/* 
    @When("navego a la categoria {string} y subcategoria {string}")
    public void navego_a_la_categoria_y_subcategoria(String categoria, String subcategoria) {
        HomePage homePage = new HomePage(driver);
        homePage.irAClothes();
        homePage.irAMen();
    }
*/

@When("navego a la categoria {string} y subcategoria {string}")
public void navego_a_la_categoria_y_subcategoria(String categoria, String subcategoria) {
    HomePage homePage = new HomePage(driver);

    if (categoria.equalsIgnoreCase("Clothes") && subcategoria.equalsIgnoreCase("Men")) {
        homePage.irAClothes();
        homePage.irAMen();
    } else {
        homePage.intentarCategoriaInexistente(categoria);
    }
}
    @Then("valido que estoy en la subcategoria")
    public void valido_que_estoy_en_la_subcategoria() {
        assertTrue(driver.getCurrentUrl().toLowerCase().contains("men")
                || driver.getPageSource().contains("MEN"));
    }

    @Then("valido que el login fue exitoso")
    public void valido_que_el_login_fue_exitoso() {
        assertTrue(driver.getPageSource().contains("Sign out"));
    }

    @Then("valido que la autentificación no fue exitosa")
    public void valido_que_la_autentificacion_no_fue_exitosa() {
        assertTrue(driver.getCurrentUrl().contains("login"));
    }

    @When("selecciono el primer producto")
    public void selecciono_el_primer_producto() {
        ProductPage productPage = new ProductPage(driver);
        productPage.seleccionarPrimerProducto();
    }

    @And("cambio la cantidad a {string}")
    public void cambio_la_cantidad_a(String cantidad) {
        ProductPage productPage = new ProductPage(driver);
        productPage.cambiarCantidad(cantidad);
    }

    @And("agrego el producto al carrito")
    public void agrego_el_producto_al_carrito() {
        ProductPage productPage = new ProductPage(driver);
        productPage.agregarAlCarrito();
    }

    @Then("valido que el producto fue agregado al carrito")
    public void valido_que_el_producto_fue_agregado_al_carrito() {
        ProductPage productPage = new ProductPage(driver);
        assertTrue(productPage.validarProductoAgregado());
    }

    @Then("valido en el popup la confirmación del producto agregado")
    public void valido_en_el_popup_la_confirmacion_del_producto_agregado() {
        ProductPage productPage = new ProductPage(driver);
        String mensaje = productPage.obtenerMensajePopup().toLowerCase();
        assertTrue(mensaje.contains("Product successfully added to your shopping cart") || mensaje.contains("product"));
    }

    @Then("valido en el popup que el monto total sea calculado correctamente")
    public void valido_en_el_popup_que_el_monto_total_sea_calculado_correctamente() {
        ProductPage productPage = new ProductPage(driver);

        double precioUnitario = productPage.obtenerPrecioUnitarioPopup();
        int cantidad = productPage.obtenerCantidadPopup();
        double totalEsperado = precioUnitario * cantidad;
        double totalPopup = productPage.obtenerTotalPopup();

        System.out.println("Precio unitario popup: " + precioUnitario);
        System.out.println("Cantidad popup: " + cantidad);
        System.out.println("Total esperado: " + totalEsperado);
        System.out.println("Total popup: " + totalPopup);

        assertTrue(Math.abs(totalEsperado - totalPopup) < 0.01);
    }

    @When("finalizo la compra")
public void finalizo_la_compra() {
    CartPage cartPage = new CartPage(driver);
    cartPage.finalizarCompra();
}

@Then("valido el titulo de la pagina del carrito")
public void valido_el_titulo_de_la_pagina_del_carrito() {
    CartPage cartPage = new CartPage(driver);
    assertTrue(cartPage.validarTituloCarrito());
}

@Then("vuelvo a validar el calculo de precios en el carrito")
public void vuelvo_a_validar_el_calculo_de_precios_en_el_carrito() {
    CartPage cartPage = new CartPage(driver);

    double precioUnitario = cartPage.obtenerPrecioUnitarioCarrito();
    int cantidad = cartPage.obtenerCantidadCarrito();
    double subtotalEsperado = precioUnitario * cantidad;
    double subtotalCarrito = cartPage.obtenerSubtotalCarrito();

    System.out.println("Precio unitario carrito: " + precioUnitario);
    System.out.println("Cantidad carrito: " + cantidad);
    System.out.println("Subtotal esperado: " + subtotalEsperado);
    System.out.println("Subtotal carrito: " + subtotalCarrito);

    assertTrue(Math.abs(subtotalEsperado - subtotalCarrito) < 0.01);
}

@Then("valido que la categoria no existe")
public void valido_que_la_categoria_no_existe() {
    HomePage homePage = new HomePage(driver);
    assertTrue(!homePage.categoriaExiste("Autos"));
}
}