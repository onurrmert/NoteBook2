package com.example.notebook2.Room

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NotesModule {

    @Singleton
    @Provides
    fun createNotesDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        NotesDatabase::class.java,
        "notesDatabase"
    ).build()

    @Singleton
    @Provides
    fun createDao(db: NotesDatabase) = db.notesDao()
}