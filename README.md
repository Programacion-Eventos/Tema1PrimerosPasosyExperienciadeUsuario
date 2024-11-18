# Tema1 PrimerosPasosyExperienciadeUsuario (Eventos)

Video --> https://youtu.be/nPQrycp0NTs

Repositorio --> https://github.com/Programacion-Eventos/Tema1PrimerosPasosyExperienciadeUsuario

# Proyecto de Cambio de Color de Fondo en Pantallas [MEJORADO]

#### Atención: Hemos dado un buelco de 180º a la App (puesto que la version base dejaba mucho que desear) --> Hemos migrado todas las clases que estaban metidas en el ui.theme a sus respectivos paquetes en base a responsabilidades (Pantallas a la vista y segundoPlano al modelo) además de añadir multiples clases orientadas a el uso de FireBase, SQlite(con almacenamiento Interno), SharedPreference y otras resultantes de la separación de logica-pantalla para que sea mas eficiente (poco cambio a nivel frontal y mucho por detrás).

### **OJO IMPORTANTE**, en caso de que el emulador usado para lanzar la aplicación funcione de forma inusual (como mostrar la "APP" bocaabajo/del revés) simplemente hacerle un "Cold Boot" a dicho emulador desde el Device Manager para restablecer el funcionamiento normal de la misma.


# Resumen de la Aplicación

Esta aplicación Android está organizada en una arquitectura modular con tres paquetes principales: **model** (Modelo), **vista** (Vista), y **util** (Utilidades). Cada uno desempeña un papel específico en la estructura de la app, desde la gestión de datos y lógica de negocio hasta la interfaz de usuario y almacenamiento. Aquí tienes un desglose completo:

## Paquete `model` (Modelo)

El paquete `model` gestiona la lógica de negocio y el almacenamiento de datos en diferentes formatos, incluyendo bases de datos locales y en la nube. Las clases en este paquete son:

1. **`Firebase`**: Configura y utiliza Firebase Realtime Database, permitiendo la sincronización y persistencia de datos en la nube. Su método `saveNombre` permite guardar un nombre en Firebase bajo una clave única, lo cual es útil para almacenar información del usuario que puede ser accedida en múltiples dispositivos.

2. **`PreferenceManager`**: Maneja las preferencias de usuario mediante `SharedPreferences`, permitiendo guardar el nombre del usuario y su color de fondo preferido. También incluye métodos para recuperar estos datos, lo cual ayuda a personalizar la experiencia del usuario de manera persistente.

3. **`SegundoPlano`**: Implementa una tarea en segundo plano con `AsyncTask`, simulando una carga o tarea larga (como una operación de red) y proporcionando actualizaciones de progreso en tiempo real a través de un `callback`. Útil para operaciones que no bloquean la interfaz de usuario.

4. **`Logica`**: Incluye lógica de negocio simple para generar un saludo basado en la hora del día. Devuelve un `AnnotatedString` con un mensaje personalizado que utiliza color para destacar la parte dinámica del saludo, mostrando un mensaje adaptado en la UI según la hora.

5. **`SQLite`**: Gestiona una base de datos SQLite local para almacenar y recuperar nombres de usuario. También incluye métodos para exportar e importar la base de datos desde el almacenamiento interno, lo cual facilita el respaldo y la restauración de datos localmente.

---

## Paquete `vista` (Vista)

El paquete `vista` define las pantallas de la interfaz de usuario de la aplicación, utilizando `Jetpack Compose` para crear una experiencia moderna y dinámica.

1. **`PantallaPrincipal`**: La pantalla principal permite al usuario introducir su nombre, almacenarlo en `SQLite` y en Firebase, y cambiar entre otras pantallas de la app. También permite iniciar una tarea en segundo plano, mostrando el progreso mediante un `LinearProgressIndicator`. La pantalla utiliza preferencias de usuario para personalizar el color de fondo y el nombre guardado.

2. **`PantallaInicio`**: Muestra un saludo personalizado adaptado a la hora del día, proporcionado por la clase `Logica`. Incluye un botón para navegar a la `PantallaPrincipal`, permitiendo una introducción amigable a la aplicación con un diseño centrado y estilizado.

3. **`PantallaConfiguracion`**: Permite al usuario seleccionar un color de fondo para personalizar la interfaz. Los cambios de color se guardan en `SharedPreferences` y se aplican en toda la app. También incluye navegación para volver a la `PantallaInicio`.



## Nueva Sección: Control de Ubicación (dentro de `vista`)

### Paquete `vista` (Vista)

El paquete `vista` ahora incluye nuevas pantallas y componentes para el control de ubicación:

1. **`ControlPorBarras`**: Un componente que permite controlar la ubicación actual mediante barras deslizantes para los ejes X e Y.

2. **`Movimiento`**: Un componente que calcula la distancia entre la ubicación actual y la ubicación objetivo, y cambia el color de fondo según si el usuario se está acercando o alejando del objetivo.

3. **`PantallaUbicacion`**: Una pantalla que muestra la ubicación actual y la ubicación objetivo, permite cambiar estas ubicaciones mediante barras deslizantes y un selector de ubicación, y muestra una brújula visual no funcional.

4. **`SelectorDeUbicacionUnificado`**: Un componente que muestra una cuadrícula de ubicaciones seleccionables, permitiendo al usuario seleccionar una ubicación para la ubicación actual o la ubicación objetivo.

5. **`BrújulaVisualNoFuncional`**: Un componente que muestra una brújula visual con una aguja giratoria, puramente para efectos visuales.

Estas nuevas funcionalidades permiten al usuario interactuar con la aplicación de una manera más dinámica y visual, proporcionando una experiencia de usuario enriquecida.

---

### Paquete `WidgetAdaptado` (Widgets)

1. **`WidgetNombres`**: El widget `WidgetNombres` se encuentra en el archivo `WidgetNombres.kt` dentro del paquete `widgetAdaptado`. Recupera los nombres desde Firebase y los muestra en formato de lista. El widget se expande para mostrar los nombres cuando se hace clic en él.

Este widget se utiliza en la `PantallaInicio` para proporcionar una representación visual de los nombres almacenados en Firebase.

---

## Paquete `util` (Utilidades)

El paquete `util` proporciona clases para la lectura y escritura de archivos en diferentes tipos de almacenamiento.

1. **`AlmacenamientoInterno`**: Proporciona métodos para guardar y leer archivos en el almacenamiento interno de la app, ideal para datos que solo deben ser accesibles desde la aplicación.

2. **`AlmacenamientoExterno`**: Permite guardar y leer archivos en el almacenamiento externo, facilitando la exportación de datos y el acceso a archivos desde otras aplicaciones. Es útil para funcionalidades como la exportación de bases de datos o la generación de informes.

---

## Otras Clases de la Aplicación

1. **`MainActivity`**: La actividad principal de la aplicación configura `PreferenceManager`, `SQLite`, y `Firebase`, y establece la navegación mediante `Jetpack Compose`. Implementa `NavHost` para gestionar las transiciones entre `PantallaInicio`, `PantallaPrincipal` y `PantallaConfiguracion`. Además, ejecuta la exportación e importación de la base de datos local en el almacenamiento interno al iniciarse, facilitando el respaldo de datos.

2. **`MyApplication`**: Clase de aplicación que configura Firebase para habilitar la persistencia de datos en caché, permitiendo que la app almacene datos en el dispositivo y los sincronice cuando esté en línea.

---

## Resumen General

La aplicación está diseñada para proporcionar una experiencia de usuario personalizada y persistente. Utiliza almacenamiento local (`SQLite`, `SharedPreferences`), almacenamiento en la nube (Firebase), y almacenamiento en archivos (interno y externo) para cubrir distintas necesidades de persistencia y respaldo de datos. `Jetpack Compose` facilita una interfaz moderna y adaptable, mientras que la estructura de `AsyncTask` permite manejar operaciones en segundo plano sin bloquear la UI. La arquitectura de la app es modular y escalable, ofreciendo una base sólida para agregar funcionalidades adicionales en el futuro.

Este diseño es ideal para aplicaciones que requieren almacenamiento robusto y sincrónico de datos, personalización de la interfaz, y una experiencia de usuario fluida y adaptable.
