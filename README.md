# 📰 Headlines – Kotlin Multiplatform News App

<p align="center">
  <img src="./screenshots/Logo.png" width="120" alt="Headlines Logo"/>
</p>

<p align="center">
  <b>A modern cross-platform News application built with Kotlin Multiplatform & Compose Multiplatform</b><br/>
  Android • iOS • Tablet • Desktop
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Kotlin-Multiplatform-7F52FF?style=for-the-badge&logo=kotlin"/>
  <img src="https://img.shields.io/badge/Compose-Multiplatform-4285F4?style=for-the-badge&logo=jetpackcompose"/>
  <img src="https://img.shields.io/badge/MVVM-Architecture-FF6F00?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/Cross--Platform-Android%20%7C%20iOS%20%7C%20Desktop-00C853?style=for-the-badge"/>
</p>

---

## 📱 Overview

**Headlines** is a modern **Kotlin Multiplatform News App** designed to deliver a fast, clean, and unified reading experience across multiple platforms.

Built using **Compose Multiplatform**, it shares a single codebase for business logic while delivering native-like UI across:
- Android 📱
- iOS 🍎
- Tablet 📟
- Desktop 💻

---

## ✨ Features

- 🗞️ Top Headlines feed (latest news updates)
- 🔍 Smart search for articles
- 📖 Clean and distraction-free reading experience
- 🔖 Bookmark articles for later
- 🌍 Fully cross-platform support
- 📱 Responsive UI (Mobile / Tablet / Desktop)
- ⚡ Fast performance with shared logic
- 🧭 Type-safe navigation
- 🧱 MVVM architecture

---

## 🛠️ Tech Stack

- Kotlin Multiplatform (KMP)
- Compose Multiplatform
- Ktor (Networking)
- SQLDelight (Local Database)
- Koin (Dependency Injection)
- MVVM Architecture
- Jetpack Compose
- Coroutines
- kotlinx.serialization

---

## 🧠 Architecture

- MVVM (Model–View–ViewModel)
- Repository Pattern
- Shared business logic across all platforms
- Platform-specific UI layers where required

---

## 📸 Screenshots

---

# 📱 Mobile

<p align="center">
  <img src="./screenshots/Mobile-1.png" width="160"/>
  <img src="./screenshots/Mobile-2.png" width="160"/>
  <img src="./screenshots/Mobile-3.png" width="160"/>
  <img src="./screenshots/Mobile-4.png" width="160"/>
  <img src="./screenshots/Mobile-5.png" width="160"/>
  <img src="./screenshots/Mobile-6.png" width="160"/>
</p>

---

# 📟 Tablet

<p align="center">
  <img src="./screenshots/Tablet-1.png" width="240"/>
  <img src="./screenshots/Tablet-2.png" width="240"/>
  <img src="./screenshots/Tablet-3.png" width="240"/>
</p>

---

# 💻 Desktop

<p align="center">
  <img src="./screenshots/Desktop-1.png" width="300"/>
  <img src="./screenshots/Desktop-2.png" width="300"/>
  <img src="./screenshots/Desktop-3.png" width="300"/>
  <img src="./screenshots/Desktop-4.png" width="300"/>
  <img src="./screenshots/Desktop-5.png" width="300"/>
</p>

---

## 🎥 Demo

👉 Add your YouTube demo link here

---

## ⚙️ Getting Started

### 1️⃣ Clone the repository

git clone https://github.com/your-username/headlines-kmp.git

### 2️⃣ Open in IDE

Open the project in:
Android Studio (latest recommended)
IntelliJ IDEA

### 3️⃣ Setup API Key
Get API key from:
👉 https://newsapi.org/

Then add it inside:
shared/src/commonMain/.../Constants.kt
const val API_KEY = "YOUR_API_KEY"

### 4️⃣ Run the Project
Android
Run composeApp module

Desktop
./gradlew :composeApp:run

Desktop (Hot Reload)
./gradlew desktopRun -DmainClass=MainKt

iOS
Open iosApp in Xcode and run

### 🚀 Why this project?

This project demonstrates:
Real-world Kotlin Multiplatform architecture
Scalable shared codebase design
Modern Compose UI practices
Clean and maintainable architecture
Production-ready multi-platform setup

### 👨‍💻 Author
Bilal
Senior Android Engineer
(Android | Kotlin | Jetpack Compose | MVVM | Kotlin Multiplatform (KMP) | Flutter)

### ⭐ Support
If you like this project:
⭐ Star this repository
🍴 Fork it
💬 Share feedback
