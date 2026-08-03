package com.example.docreader.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DocAnnotationEntity::class, RecentFileEntity::class], version = 1, exportSchema = false)
abstract class DocDatabase : RoomDatabase() {
    abstract fun docDao(): DocDao
}
