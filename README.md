# 🎮 Games Vault

**Games Vault** is an Android application built with **Jetpack Compose** that allows users to explore video games, search efficiently, and manage their favorite titles using Firebase services.

---

## 🚀 Key Features

* Modern Android Architecture**: MVVM + Clean Architecture
* **Dependency Injection** with Hilt
* **Infinite Scrolling** using Paging 3
* **Authentication** with Firebase Authentication (Sign Up & Login)
* **Favorites Management** using Firebase Firestore
* **Optimized Search Section**
* **Unit & Instrumentation Testing**

---

## 🛠 Tech Stack

* **Language**: Kotlin
* **UI**: Jetpack Compose
* **Dependency Injection**: Hilt
* **Architecture**: MVVM + Clean Architecture
* **Asynchronous Programming**: Coroutines + Flow
* **Pagination**: Paging 3
* **Backend Services**: Firebase (Auth & Firestore)
* **Testing**: JUnit, Compose UI Testing

---
## 🧪 Testing

The project includes tests to ensure application quality and stability:

* ✅ **Unit tests**
* ✅ **Instrumented tests (UI tests)**
* Application of best practices to make ViewModels and data layers easy to test

---

## 🧱 Architecture

The project follows an **Clean Architecture**, separating responsibilities into:

* **Data Layer**: API services, Firebase services, repositories implementations
* **Domain Layer**: Use cases and business logic
* **features Layer**: ViewModels and Jetpack Compose UI

This structure improves maintainability, scalability, and testability.

---

## 📸 Screenshots

> Below are some images of the application:*

---

<div style="display: grid; grid-template-columns: repeat(3, 1fr); gap: 2rem; ">
<img src="https://res.cloudinary.com/dtvbans9e/image/upload/v1768958420/projects/android/Jetpack%20compose/Games%20Vault/tdeoh6qszmehaupjwfot.png" width="200" alt="image_preview_1"/>
<img src="https://res.cloudinary.com/dtvbans9e/image/upload/v1768958420/projects/android/Jetpack%20compose/Games%20Vault/n2dzyclfdz1arimy06hx.png" width="200" alt="image_preview_2"/>
<img src="https://res.cloudinary.com/dtvbans9e/image/upload/v1768958419/projects/android/Jetpack%20compose/Games%20Vault/y6fsnpvdgagpdp89sinz.png" width="200" alt="image_preview_3"/>
</div>

---

## 🔐 Environment Variables

This project requires a **.ENV** file to securely store sensitive information such as the API key used to consume the RAWG Video Games Database API.

### 📄 .ENV file

Create a file named **.ENV** in the root directory of the project and add the following:

```env
API_KEY=YOUR_RAWG_API_KEY
```

You can obtain your API key by creating an account at **rawg.io**.

⚠️ **Important**: Do not commit the `.ENV` file to version control. Make sure it is included in your `.gitignore`.

---

## 🚀 Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/byron-fran/Game-Vault
   ```
2. Open the project in **Android Studio**
3. Sync Gradle
4. Run the app on an emulator or physical device

---

## 📌 Project Status

✔️ Functional and continuously improving

This project was developed for **educational and demonstrative** purposes, applying modern Android development best practices.

---
⭐ If you find this project useful or interesting, don’t forget to give it a star!
