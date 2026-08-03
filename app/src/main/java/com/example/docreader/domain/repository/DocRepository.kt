package com.example.docreader.domain.repository

import android.net.Uri
import com.example.docreader.domain.model.DocAnnotation
import com.example.docreader.domain.model.RecentFile
import com.example.docreader.domain.model.EntitlementState
import kotlinx.coroutines.flow.Flow

interface DocRepository {
    fun getRecentFiles(): Flow<List<RecentFile>>
    suspend fun saveRecentFile(file: RecentFile)
    fun getAnnotations(fileHash: String): Flow<List<DocAnnotation>>
    suspend fun saveAnnotation(annotation: DocAnnotation)
    fun getEntitlementState(): Flow<EntitlementState>
    suspend fun purchasePremium(): Boolean
}
