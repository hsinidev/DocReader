package com.example.docreader.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "doc_annotations")
data class DocAnnotationEntity(
    @PrimaryKey val id: String,
    val fileHash: String,
    val type: String,
    val data: String,
    val timestamp: Long
)

@Entity(tableName = "recent_files")
data class RecentFileEntity(
    @PrimaryKey val id: String,
    val name: String,
    val uriString: String,
    val size: Long,
    val format: String,
    val progressPage: Int,
    val totalPages: Int,
    val timestamp: Long
)
