Feature: Product - Store

  Scenario: Validación del precio de un producto
    Given estoy en la página de la tienda
    And me logueo con mi usuario "navasfernando75@gmail.com" y clave "JbnM201211@@"
    When navego a la categoria "Clothes" y subcategoria "Men"
    And selecciono el primer producto
    And cambio la cantidad a "2"
    And agrego el producto al carrito
    Then valido en el popup la confirmación del producto agregado
    And valido en el popup que el monto total sea calculado correctamente
    When finalizo la compra
    Then valido el titulo de la pagina del carrito
    And vuelvo a validar el calculo de precios en el carrito

  Scenario: Login fallido con credenciales inválidas
    Given estoy en la página de la tienda
    And me logueo con mi usuario "correo_invalido@test.com" y clave "clave_invalida"
    Then valido que la autentificación no fue exitosa

  Scenario: Navegación fallida con categoría inexistente
    Given estoy en la página de la tienda
    And me logueo con mi usuario "navasfernando75@gmail.com" y clave "JbnM201211@@"
    When navego a la categoria "Autos" y subcategoria "Men"
    Then valido que la categoria no existe