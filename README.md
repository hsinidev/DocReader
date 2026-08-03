# DocReader - Professional Multi-Format Office Suite & Annotator

> Open, annotate, and navigate Word, Excel, and PowerPoint with desktop-grade fidelity.

**Productivity / Office Utilities / Document Management** built with Kotlin and modern Android development standards.

---

## 📖 How It Works

This application is built following **Clean Architecture**, **MVVM / MVI pattern**, and **Offline-First** principles.

### Architecture & System Modules
- **`:app` Module**: Application initialization, Hilt module bindings, entry-point route setups.
- **`:core_ui` Module**: Professional executive tokens (Executive Blue, Steel Slate, Light Paper background), custom double-ended zoom sliders, custom paginated canvas viewers, and spring transitions.
- **`:feature_explorer` Module**: Storage Access Framework hooks, recent files shelf, category filters, metadata indexers.
- **`:feature_reader` Module**: Apache POI integrations, thread-isolated text paginators, custom Excel sheet tables, and PPT slide swipe carousels.
- **`:feature_annotations` Module**: Vector ink Canvas systems, touch gesture tracking, annotation overlay managers, and room backup controllers.
- **`:feature_pro` Module**: Google Play Billing client binders, premium entitlement verification hooks, Pro Paywall screens.

### Required Android Permissions
- `android.permission.READ_MEDIA_VISUAL_USER_SELECTED (Fallback visual pickers)`
- `android.permission.INTERNET (Required for Google Play Billing, cloud parsing, and AdMob services)`
- `android.permission.POST_NOTIFICATIONS (For WorkManager document-to-PDF conversion status notifications)`
- `android.permission.FOREGROUND_SERVICE_DATA_SYNC (Required for background document-to-PDF rendering pipelines on Android 14+)`

---

## 📱 How to Use

### 1. Multi Format Document Engine
High-fidelity, local-first parser converting proprietary office structures into readable layout models.

### 2. Vector Annotation Subsystem
An overlay layer allowing users to write, highlight, and attach comments directly on active documents.

### 3. Saf Workspace Manager
A fluid document explorer and workspace organizer integrating native Scoped Storage protocols.

### 4. Admob Monetization Layer



---

## 🚀 Key Features

- **Multi Format Document Engine**: High-fidelity, local-first parser converting proprietary office structures into readable layout models.
- **Vector Annotation Subsystem**: An overlay layer allowing users to write, highlight, and attach comments directly on active documents.
- **Saf Workspace Manager**: A fluid document explorer and workspace organizer integrating native Scoped Storage protocols.
- **Admob Monetization Layer**: 

---

## 🛠️ Tech Stack & Architecture

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose (Material Design 3)
- **Architecture**: Clean Architecture + MVVM / MVI
- **Local Storage**: Room Database & DataStore
- **Async Operations**: Kotlin Coroutines & StateFlow
- **Build System**: Gradle Kotlin DSL
- **Min SDK**: 26 | **Target SDK**: 34

---

## 💻 Getting Started

### Prerequisites
- Android Studio Ladybug (2024.2.1+) or newer
- JDK 17+
- Android SDK 34+

### Building & Running
1. Clone the repository:
   ```bash
   git clone https://github.com/hsinidev/DocReader.git
   cd DocReader
   ```
2. Open the project in Android Studio.
3. Sync Gradle dependencies and run on an Android device or emulator.

---

## 📬 Contact & Support

Created and maintained by **Hsini**.

- **Website**: [hsini.dev](https://hsini.dev)
- **Email**: [contact@hsini.dev](mailto:contact@hsini.dev)
- **GitHub**: [@hsinidev](https://github.com/hsinidev)

---

© 2026 [hsini.dev](https://hsini.dev). All rights reserved.
