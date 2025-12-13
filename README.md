## **🎬 Filmoteca: Práctica Android Básico 🍿**

**Autor:** Susana Paracuellos Ralfas **Módulo:** Programación Multimedia y Dispositivos Móviles (PMDM) 

---

🎯 Objetivo del Proyecto 

Crear una aplicación de gestión de películas (**Filmoteca**) para Android, implementando las funcionalidades de una unidad didáctica básica. La interfaz de usuario fue construida íntegramente con **Jetpack Compose**

**💻 Entorno y Tecnologías Clave**

* **Lenguaje:** Kotlin   
* **UI Framework:** Jetpack Compose   
* **API Mínima:** 21 o superior   
* **Control de Versiones:** Git (con *commits* en cada ejercicio) 

---

### **🗺️ Navegación y Estructura de Pantallas**

La aplicación está compuesta por cuatro pantallas principales, gestionadas por NavController y NavHost:

| Pantalla | Descripción | Transiciones Clave |
| :---- | :---- | :---- |
| **FilmListScreen** | Pantalla principal. Muestra el listado de películas usando LazyColumn.  | ➡️ FilmDataScreen (al hacer clic en un ítem)  |
| **FilmDataScreen** | Muestra los detalles de una película seleccionada.  | ➡️ FilmEditScreen, ➡️ FilmListScreen (volver a la principal)  |
| **FilmEditScreen** | Permite modificar los datos de la película.  |  |
| **AboutScreen** | Muestra información del autor y opciones de contacto.  | ➡️ Vuelve a la anterior (popBackStack())  |

---

### **✅ Funcionalidades Destacadas**

#### **1\. Listado de Películas (FilmListScreen)**

* **Listado Dinámico:** Implementado con LazyColumn y utilizando la clase **FilmDataSource** para suministrar datos de prueba  
* **Diseño Personalizado:** Cada ítem muestra la imagen, el título de la película y su director  
* **Navegación por Clic:** Al pulsar un ítem, se navega a FilmDataScreen, pasando el índice de la película como parámetro.  
* **Borrado Múltiple:** Implementada la funcionalidad de seleccionar y borrar múltiples películas mediante pulsación larga.



#### **2\. Intents Implícitos y Contacto**

Los botones de la pantalla AboutScreen utilizan Intents para funcionalidades externas

* **Ir al sitio web:** Lanza un *Intent* ACTION\_VIEW para abrir la página web del Campus Digital FP  
* **Obtener soporte:** Lanza un *Intent* ACTION\_SENDTO para enviar un correo electrónico con un asunto predefinido



#### 

#### **3\. Interfaz de Edición (FilmEditScreen)**

La pantalla de edición incluye campos de texto (TextField) para los datos de la película y menús desplegables para el **Género** (Acción, Drama, Comedia, Terror, Sci-Fi) y **Formato** (DVD, Blu-ray, Online)

* **Manejo de estado:** Los campos utilizan variables mutables (mutableStateOf) para reflejar y almacenar los datos de la película.



#### **4\. Internacionalización**

La aplicación soporta dos idiomas, **Español** e **Inglés**, mediante el uso de directorios de recursos (res/values/strings.xml y res/values-en/strings.xml).


---

### **⚙️ Testing y Depuración**

* **Generación de Logs:** Se utiliza la clase Log de Android para registrar mensajes informativos (Log.i) sobre el guardado o descarte de cambios en FilmEditScreen  
* **Pruebas con Monkey:** Se ejecutaron pruebas de estrés con la herramienta monkey para evaluar la estabilidad de la aplicación.

| Logs en Logcat (Ejemplo) | Comando Monkey |
| :---- | :---- |
|  | adb shell monkey \-p com.campusdigitalfp.filmoteca \--pct-syskeys 0 \--pct-motion 0 \--throttle 200 \-v \-v 50 |

