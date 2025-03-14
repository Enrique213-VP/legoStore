# Lego Store

Diseñe una aplicacion en Kotlin para la store de Lego. Para esta tienda use un servicio de autenticación para nuestros clientes, utilizando Firebase Authentication. La tienda consumirá un API que desarrolle por medio de un mock server la cual esta disponible en el siguiente enlace:

[Documentación del API](https://documenter.getpostman.com/view/12709892/2sAYkAR2tM)

## Contenido

- Pantalla de autenticación que consumira el servicio de Firebase.
- La sesión del usuario debe persistir después de iniciar sesión.
- Eldashobard muestra una lista de productos obtenidos del Mock Dummy proporcionado.
- Cada producto muestra su foto.
- Seleccionar un producto lleva al usuario a una nueva vista con el detalle del producto, desde donde también podrá agregarlo.

## Distribución de la Aplicación

### Login
- Pantalla de inicio de sesión con Firebase Authentication.
- Persistencia de sesión.

### Navegación Inferior
- Secciones principales de la tienda accesibles mediante Bottom Navigation.
- Secciones:
  - **Inicio**: Lista de productos.
  - **Dashboard**: Productos.
  - **Carrito**: Lista de productos.
 
## Descargar Aplicacion

Se genero un release legos.apk la cual es el ejecutable en dispositivos android, el cual es un APK, lo unico que debe hacerse es descargar la app y darle los permisos en el telefono para instalar, disponible en el siguiente enlace:

[Descargar App](https://github.com/Enrique213-VP/legoStore/releases/tag/1.0.0)

## Imágenes de la Aplicación

### Pantalla de Login
<img src="https://raw.githubusercontent.com/Enrique213-VP/legoStore/refs/heads/master/assets/img/login.jpg" width="300" />

### Productos
<img src="https://raw.githubusercontent.com/Enrique213-VP/legoStore/refs/heads/master/assets/img/products.jpg" width="300" />

### Imagen del producto en el detalle
<img src="https://raw.githubusercontent.com/Enrique213-VP/legoStore/refs/heads/master/assets/img/detail.jpg" width="300" />

### Recuperacion de contraseña
<img src="https://raw.githubusercontent.com/Enrique213-VP/legoStore/refs/heads/master/assets/img/forget.jpg" width="300" />

---

