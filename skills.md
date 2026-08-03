# DocReader — Skills & Architecture Guide

## Category: Utilities / Document Reader

## Description
DocReader opens DOCX, XLSX, PPTX, DOC, XLS, RTF, and TXT files beautifully on Android without needing Microsoft Office. Read, annotate, and share documents with ease.

## Core Architecture
```
data/
  file/        — File I/O, format parsing, engine wrappers
  database/    — Room entities, DAOs (history, bookmarks, playlists)
  repository/  — Repository pattern implementations
domain/
  model/       — Clean domain models
  usecase/     — Business logic (open file, bookmark, convert, etc.)
presentation/
  ui/screens/  — Compose screens (Home, Viewer/Player, Settings)
  viewmodel/   — StateFlow ViewModels
service/       — ForegroundService for background media/conversion
```

## Key Technical Skills

### File Access (Storage Access Framework)
```kotlin
val launcher = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.OpenDocument()
) { uri ->
    uri?.let { viewModel.openFile(it) }
}
// Trigger: launcher.launch(arrayOf("application/pdf", "application/*"))
```

### Foreground Service for Background Processing
```kotlin
class ProcessingService : Service() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notification = buildProgressNotification("Processing...")
        startForeground(NOTIF_ID, notification)
        // Do heavy work in coroutine scope
        serviceScope.launch { doWork() }
        return START_NOT_STICKY
    }
}
```

### Gesture Controls for Media/Document
```kotlin
Modifier.pointerInput(Unit) {
    detectTransformGestures { _, pan, zoom, _ ->
        scale = (scale * zoom).coerceIn(1f, 5f)
        offset += pan
    }
}
```

## Play Store Checklist
- [ ] App icon 512x512 PNG
- [ ] Feature graphic 1024x500 PNG  
- [ ] 4+ screenshots (file browser, main viewer/player, settings, night mode)
- [ ] Privacy Policy (required for storage permission)
- [ ] Content Rating: Everyone
- [ ] Target SDK 34+
- [ ] Release AAB signed with upload keystore

## Unique Value Proposition
Lighter than Microsoft and WPS — focused purely on reading not editing

## Revenue Model
- Free tier with AdMob banner/interstitial ads
- Pro subscription via Google Play Billing: Free + $3.99/month Pro
- Offer 7-day free trial on Pro to maximize conversion
