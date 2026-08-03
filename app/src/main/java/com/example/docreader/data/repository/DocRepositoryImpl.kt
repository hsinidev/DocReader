package com.example.docreader.data.repository

import android.net.Uri
import com.example.docreader.data.database.DocDao
import com.example.docreader.data.database.DocAnnotationEntity
import com.example.docreader.data.database.RecentFileEntity
import com.example.docreader.domain.model.DocAnnotation
import com.example.docreader.domain.model.RecentFile
import com.example.docreader.domain.model.EntitlementState
import com.example.docreader.domain.repository.DocRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DocRepositoryImpl @Inject constructor(
    private val docDao: DocDao
) : DocRepository {

    private val _entitlement = MutableStateFlow(EntitlementState.Pro) // Pro by default for local workspace execution

    override fun getRecentFiles(): Flow<List<RecentFile>> {
        return docDao.getRecentFiles().map { list ->
            list.map {
                RecentFile(it.id, it.name, Uri.parse(it.uriString), it.size, it.format, it.progressPage, it.totalPages, it.timestamp)
            }
        }
    }

    override suspend fun saveRecentFile(file: RecentFile) {
        docDao.insertRecentFile(
            RecentFileEntity(file.id, file.name, file.uri.toString(), file.size, file.format, file.progressPage, file.totalPages, file.timestamp)
        )
    }

    override fun getAnnotations(fileHash: String): Flow<List<DocAnnotation>> {
        return docDao.getAnnotationsForFile(fileHash).map { list ->
            list.map {
                DocAnnotation(it.id, it.fileHash, it.type, it.data, it.timestamp)
            }
        }
    }

    override suspend fun saveAnnotation(annotation: DocAnnotation) {
        docDao.insertAnnotation(
            DocAnnotationEntity(annotation.id, annotation.fileHash, annotation.type, annotation.data, annotation.timestamp)
        )
    }

    override fun getEntitlementState(): Flow<EntitlementState> = _entitlement

    override suspend fun purchasePremium(): Boolean {
        delay(800)
        _entitlement.value = EntitlementState.Pro
        return true
    }
}
