package com.example.docreader.di

import android.content.Context
import androidx.room.Room
import com.example.docreader.data.database.DocDao
import com.example.docreader.data.database.DocDatabase
import com.example.docreader.data.repository.DocRepositoryImpl
import com.example.docreader.domain.repository.DocRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): DocDatabase {
        return Room.databaseBuilder(
            context,
            DocDatabase::class.java,
            "docreader_db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideDao(db: DocDatabase): DocDao = db.docDao()

    @Provides
    @Singleton
    fun provideRepository(impl: DocRepositoryImpl): DocRepository = impl
}
