# 🎮 Games Vault (Español)

**Games Vault** es una aplicación Android desarrollada con **Jetpack Compose** que permite explorar videojuegos, realizar búsquedas optimizadas y gestionar juegos favoritos utilizando servicios de Firebase.

---

## 🚀 Características

* **Arquitectura moderna**: MVVM + Clean Architecture
* **Inyección de dependencias** con Hilt
* **Paginación infinita** con Paging 3
* **Autenticación** con Firebase Authentication (Registro e inicio de sesión)
* **Gestión de favoritos** usando Firebase Firestore
* **Sección de búsqueda optimizada**
* **Pruebas unitarias e instrumentales**

---

## 🛠 Tecnologías

* **Lenguaje**: Kotlin
* **UI**: Jetpack Compose
* **Arquitectura**: MVVM + Clean Architecture
* **Inyección de dependencias**: Hilt
* **Programación asíncrona**: Coroutines + Flow
* **Paginación**: Paging 3
* **Servicios Backend**: Firebase (Auth y Firestore)
* **Testing**: JUnit, pruebas UI con Compose

---

## 🧪 Pruebas

* **Pruebas unitarias** para ViewModels y casos de uso
* **Pruebas instrumentales** para UI y navegación con Jetpack Compose

---

## 🧱 Arquitectura de la App

El proyecto sigue **Clean Architecture**, separando responsabilidades en:

* **Capa Data**: Servicios de API, Firebase y repositorios
* **Capa Domain**: Casos de uso y lógica de negocio
* **Capa Presentation**: ViewModels y UI con Jetpack Compose

Este enfoque mejora la escalabilidad, mantenibilidad y facilidad de pruebas.

---

## 📸 Capturas de Pantalla

> *A continuación se muestran algunas imágenes de la aplicación:*

---

<div style="display: grid; grid-template-columns: repeat(3, 1fr); gap: 2rem; ">
<img src="https://res.cloudinary.com/dtvbans9e/image/upload/v1768958420/projects/android/Jetpack%20compose/Games%20Vault/tdeoh6qszmehaupjwfot.png" width="200" alt="image_preview_1"/>
<img src="https://res.cloudinary.com/dtvbans9e/image/upload/v1768958420/projects/android/Jetpack%20compose/Games%20Vault/n2dzyclfdz1arimy06hx.png" width="200" alt="image_preview_2"/>
<img src="https://res.cloudinary.com/dtvbans9e/image/upload/v1768958419/projects/android/Jetpack%20compose/Games%20Vault/y6fsnpvdgagpdp89sinz.png" width="200" alt="image_preview_3"/>
</div>

---

## 🔐 Variables de Entorno

Este proyecto requiere un archivo **.ENV** para almacenar de forma segura información sensible como la API Key utilizada para consumir la API de **RAWG Video Games Database**.

### 📄 Archivo .ENV

Crea un archivo llamado **.ENV** en la raíz del proyecto y agrega lo siguiente:

```env
API_KEY=TU_RAWG_API_KEY
```

Puedes obtener tu API Key creando una cuenta en **rawg.io**.

⚠️ **Importante**: No subas el archivo `.ENV` al repositorio. Asegúrate de incluirlo en el `.gitignore`.

---

## 🚀 Instalación

1. Clona el repositorio:

   ```bash
   git clone https://github.com/byron-fran/Game-Vault
   ```
2. Abre el proyecto en **Android Studio**
3. Sincroiza los paquetes de Gradle
4. Ejecuta la app en un emulador o dispositivo físico


---

## 📌 Acerca del proyecto

✔️ Funcional y en continua mejora.

Este proyecto se desarrolló con fines **educativos y demostrativos**, aplicando las mejores prácticas modernas de desarrollo para Android.

---
⭐ Si este proyecto te parece util o interesente, no olvides darle una estrella!