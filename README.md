# Android Project – Version Catalog

This project uses Gradle Version Catalog to manage dependencies, making it easier to keep libraries up to date and organized. Below is an overview of the key libraries and plugins (excluding basic Android dependencies).

## Dependency Overview

### Jetpack Compose
- Compose BOM – Centralizes Compose versions for consistent updates.
- Material 3 – Modern Material Design components for Compose UIs.
- Lifecycle ViewModel Compose – ViewModel support in Compose.
- Navigation Compose – Type-safe navigation for Compose apps.
- Activity Compose – Entry point for Compose in Android activities.

### Image Loading (Coil)
- coil-compose – Image loading in Compose UI.
- coil-network-okhttp – OkHttp-based networking for Coil.
- coil-svg – SVG image loading support.

### Networking
- Retrofit – Type-safe HTTP client for APIs.
- Gson Converter – JSON serialization/deserialization for Retrofit.
- OkHttp – Core HTTP client.
- Logging Interceptor – Logs network requests and responses.

### Dependency Injection
- Hilt Android – Dependency injection framework by Google.
- Hilt Compiler – Annotation processor for Hilt.
- Hilt Navigation Compose – Hilt integration with Jetpack Navigation for Compose.

### Coroutines
- Kotlinx Coroutines Core – Asynchronous programming with structured concurrency.

### Testing
- JUnit 4 – Unit testing framework.
- AndroidX JUnit – Android-specific JUnit extensions.
- Espresso Core – UI testing for Android apps.

## Plugins
- Kotlin Compose Plugin – Enables Compose-specific Kotlin features.
- KSP (Kotlin Symbol Processing) – Annotation processing tool for Kotlin.
- Hilt Plugin – Integrates Hilt into the Gradle build process.

## Getting Started

1. Clone the repository
   ```bash
   git clone <your-repo-url>
   cd <project-folder>
