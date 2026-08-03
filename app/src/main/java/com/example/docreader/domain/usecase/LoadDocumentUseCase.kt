package com.example.docreader.domain.usecase

import android.net.Uri
import com.example.docreader.domain.model.RecentFile
import com.example.docreader.domain.repository.DocRepository
import java.util.UUID
import javax.inject.Inject

class LoadDocumentUseCase @Inject constructor(
    private val repository: DocRepository
) {
    suspend operator fun invoke(uri: Uri): RecentFile {
        val format = when {
            uri.path?.endsWith(".docx", ignoreCase = true) == true -> "DOCX"
            uri.path?.endsWith(".xlsx", ignoreCase = true) == true -> "XLSX"
            uri.path?.endsWith(".pptx", ignoreCase = true) == true -> "PPTX"
            else -> "DOCX"
        }
        val file = RecentFile(
            id = UUID.randomUUID().toString(),
            name = uri.lastPathSegment ?: "document.docx",
            uri = uri,
            size = 1024 * 105,
            format = format,
            progressPage = 1,
            totalPages = if (format == "XLSX") 3 else 12,
            timestamp = System.currentTimeMillis()
        )
        repository.saveRecentFile(file)
        return file
    }
}
