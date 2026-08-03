package com.example.docreader.domain.model

import android.net.Uri

enum class ReaderTheme {
    DAY_PAPER, SEPIA_WARM, NIGHT_DARK, SYSTEM_DEFAULT
}

data class DocAnnotation(
    val id: String,
    val fileHash: String,
    val type: String, // INK, HIGHLIGHT, COMMENT
    val data: String, // Serialized coordinates or comment text
    val timestamp: Long
)

data class RecentFile(
    val id: String,
    val name: String,
    val uri: Uri,
    val size: Long,
    val format: String, // DOCX, XLSX, PPTX
    val progressPage: Int,
    val totalPages: Int,
    val timestamp: Long
)

enum class EntitlementState {
    Free, Pro, Pending, Unknown
}
