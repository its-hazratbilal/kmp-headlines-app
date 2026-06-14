This is a Kotlin Multiplatform project targeting Android, iOS, Desktop (JVM).

* [/iosApp](./iosApp/iosApp) contains an iOS application. Even if you’re sharing your UI with Compose Multiplatform,
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

* [/shared](./shared/src) is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - [commonMain](./shared/src/commonMain/kotlin) is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    the [iosMain](./shared/src/iosMain/kotlin) folder would be the right place for such calls.
    Similarly, if you want to edit the Desktop (JVM) specific part, the [jvmMain](./shared/src/jvmMain/kotlin)
    folder is the appropriate location.

📰 Headlines – Kotlin Multiplatform News App
A modern Kotlin Multiplatform (KMP) news application built with Compose Multiplatform, delivering a smooth and consistent reading experience across Android, iOS, and Desktop platforms.

🎥 Demo
Click below to watch the app in action:
👉 (Add your YouTube demo link here)

🚀 Overview
Headlines is a Kotlin Multiplatform News App designed to provide a fast, clean, and unified news reading experience across multiple platforms.
Built using Compose Multiplatform, it shares most of the codebase between Android, iOS, and Desktop (Windows, macOS, Linux), ensuring high performance and consistency while maintaining native-like UI behavior.

✨ Features
🗞️ Top Headlines Feed
Stay updated with the latest trending news.
🔍 Smart Search
Quickly find news based on topics or keywords.
📖 Detailed Article View
Read full articles with a clean and distraction-free UI.
🔖 Bookmarks
Save articles for later reading (offline support ready).
🌍 Cross-Platform
Runs seamlessly on:
Android
iOS
Windows
macOS
Linux
🎨 Modern UI
Built with Jetpack Compose / Compose Multiplatform
🧭 Type-Safe Navigation
Safe and scalable navigation structure.
🧱 MVVM Architecture
Clean separation of UI, logic, and data layers.

🛠️ Tech Stack
Kotlin Multiplatform (KMP)
Compose Multiplatform
Ktor – Networking layer
kotlinx.serialization – JSON parsing
Coroutines – Async operations
Coil – Image loading
SQLDelight – Local database
DataStore – Key-value storage
Kermit – Logging
Navigation Compose
BuildKonfig – Build-time configuration

🧠 Architecture
MVVM (Model–View–ViewModel)
Shared business logic across platforms
Platform-specific UI layers
Repository pattern for data handling

⚙️ Getting Started
1. Clone the repository
git clone https://github.com/your-username/kmp-headlines-app.git

2. Open in IDE
Open the project in:
Android Studio (latest)
IntelliJ IDEA

3. API Setup
This app uses a News API, obtain an API key from [News Api ](https://newsapi.org/).
Goto shared -> commonMain -> utils -> Constants.kt
and past:
const val API_KEY = "PLACE-YOUR-NEWS-API-KEY-HERE"

4. Run the Project
Android
Run composeApp module
Desktop
./gradlew :composeApp:run
Desktop (Hot Reload)
./gradlew desktopRun -DmainClass=MainKt
iOS
Run iosApp from Xcode or IDE configuration

📦 Why this project?
This project demonstrates:
Real-world KMP architecture
Scalable shared code design
Modern Compose UI practices
Clean architecture principles
Production-ready structure

📸 Screenshots
