# Tema1 PrimerosPasosyExperienciadeUsuario (Eventos)

Repositorio --> https://github.com/Programacion-Eventos/Tema1PrimerosPasosyExperienciadeUsuario

# Proyecto de Cambio de Color de Fondo en Pantallas

### **OJO IMPORTANTE**, en caso de que el emulador usado para lanzar la aplicación funcione de forma inusual (como mostrar la "APP" bocaabajo/del revés) simplemente hacerle un "Cold Boot" a dicho emulador desde el Device Manager para restablecer el funcionamiento normal de la misma.

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

## Nueva Funcionalidad

### `SegundoPlano.kt`

Este archivo contiene la clase `SegundoPlano`, que extiende `AsyncTask` para realizar una operación en segundo plano que simula un retraso de red y actualiza la interfaz de usuario con el progreso.

#### Clase `SegundoPlano`

- **`actualizarProgreso`**: Una función lambda que toma un `Int` y actualiza el progreso.
- **`tareaFinalizada`**: Una función lambda que se llama cuando la tarea se completa.

#### Métodos Principales

- **`onPreExecute`**: Inicializa elementos de la UI si es necesario antes de que comience la tarea en segundo plano.
- **`doInBackground`**: Simula un retraso de red durmiendo durante 50 milisegundos en un bucle que se ejecuta 100 veces y publica el progreso.
- **`onProgressUpdate`**: Actualiza el progreso llamando a la función lambda `actualizarProgreso` con el valor actual del progreso.
- **`onPostExecute`**: Llama a la función lambda `tareaFinalizada` para indicar que la tarea ha terminado.

### `PantallaPrincipal.kt`

Se ha añadido un botón en la pantalla principal para iniciar la tarea en segundo plano utilizando la clase `SegundoPlano`. La pantalla principal ahora incluye una barra de progreso que se actualiza en tiempo real mientras la tarea está en ejecución.

#### Nueva Funcionalidad en `PantallaPrincipal.kt`

- **Botón "Iniciar Tarea en Segundo Plano"**: Inicia la tarea en segundo plano cuando se hace clic en él.
- **`LinearProgressIndicator`**: Muestra el progreso de la tarea en segundo plano.
- **Texto de Progreso**: Muestra el porcentaje de progreso de la tarea en segundo plano.

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
