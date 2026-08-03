package com.example.docreader.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DocDao {
    @Query("SELECT * FROM doc_annotations WHERE fileHash = :hash ORDER BY timestamp DESC")
    fun getAnnotationsForFile(hash: String): Flow<List<DocAnnotationEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnnotation(annotation: DocAnnotationEntity)

    @Query("DELETE FROM doc_annotations WHERE id = :id")
    suspend fun deleteAnnotation(id: String)

    @Query("SELECT * FROM recent_files ORDER BY timestamp DESC")
    fun getRecentFiles(): Flow<List<RecentFileEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecentFile(file: RecentFileEntity)
}
