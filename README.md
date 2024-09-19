# Tema1 PrimerosPasosyExperienciadeUsuario (Eventos)

Repositorio --> https://github.com/Programacion-Eventos/Tema1PrimerosPasosyExperienciadeUsuario

# Proyecto de Cambio de Color de Fondo en Pantallas

Este proyecto es una aplicación de Android desarrollada en Kotlin que permite cambiar el color de fondo de varias pantallas desde una pantalla de configuración. La aplicación utiliza Jetpack Compose para la interfaz de usuario.

## Estructura del Proyecto

El proyecto está organizado en las siguientes pantallas principales:

1. **Pantalla de Inicio (`PantallaInicio.kt`)**: La pantalla inicial de la aplicación.
2. **Pantalla Principal (`PantallaPrincipal.kt`)**: La pantalla principal donde se pueden ingresar y guardar nombres.
3. **Pantalla de Configuración (`PantallaConfiguracion.kt`)**: La pantalla donde se puede seleccionar el color de fondo que se aplicará a todas las pantallas.

## Archivos Principales

### `MainActivity.kt`

Este archivo contiene la actividad principal de la aplicación y la configuración de la navegación entre las pantallas. Define una variable de estado para el color de fondo y la pasa a cada pantalla a través de los parámetros del composable.

### `PantallaInicio.kt`

Esta pantalla muestra un saludo y un botón para navegar a la pantalla principal. Acepta el color de fondo como un parámetro y lo aplica al `Modifier` de la columna principal.

### `PantallaPrincipal.kt`

Esta pantalla permite ingresar y guardar un nombre, y navegar a la pantalla de configuración. Acepta el color de fondo como un parámetro y lo aplica al `Modifier` de la columna principal.

### `PantallaConfiguracion.kt`

Esta pantalla permite seleccionar el color de fondo que se aplicará a todas las pantallas. Acepta el color de fondo y una función de callback para cambiar el color como parámetros. Aplica el color de fondo al `Modifier` de la columna principal y utiliza la función de callback para actualizar el color de fondo cuando se selecciona un nuevo color.

## Cómo Ejecutar el Proyecto

1. Clona el repositorio en tu máquina local.
2. Abre el proyecto en Android Studio.
3. Conecta un dispositivo Android o utiliza un emulador.
4. Ejecuta la aplicación desde Android Studio.

## Contribuciones

Las contribuciones son bienvenidas. Por favor, abre un issue o un pull request para discutir cualquier cambio que desees realizar.

## Licencia

Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo `LICENSE` para más detalles.

## OJO --> Cuidado con el dispositivo que emula la app !!!!
